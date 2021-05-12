package com.logibeat.cloud.services.impl;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logibeat.cloud.common.cache.enumType.CacheDBType;
import com.logibeat.cloud.common.cache.impl.RedisCacheManager;
import com.logibeat.cloud.common.cache.util.StringUtils;
import com.logibeat.cloud.common.enumtype.*;
import com.logibeat.cloud.common.file.CreateOrderNumber;
import com.logibeat.cloud.common.vo.DriverTaskItemVo;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.dto.DriverGpsResDto;
import com.logibeat.cloud.dto.task.*;
import com.logibeat.cloud.errorenum.DriverTaskErrorEnums;
import com.logibeat.cloud.helper.CreateTaskNodeHelper;
import com.logibeat.cloud.msg.dto.ImSysDto;
import com.logibeat.cloud.msg.enumtype.MessageBizType;
import com.logibeat.cloud.msg.enumtype.MessageType;
import com.logibeat.cloud.msg.extra.TaskExtraDto;
import com.logibeat.cloud.msg.sender.ImMsgSender;
import com.logibeat.cloud.msg.sender.InviteCarSender;
import com.logibeat.cloud.msg.sender.JetfireMsgSender;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.remote.CarSender;
import com.logibeat.cloud.remote.OperationTimeSender;
import com.logibeat.cloud.services.*;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.vo.task.*;
import com.logibeat.jetfire.client.template.SmsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DriverTaskServiceImpl implements DriverTaskService {


    private static final Logger logger = LoggerFactory.getLogger(DriverTaskServiceImpl.class);



    @Autowired
    private DriverTaskDao driverTaskDao;

    @Autowired
    private DriverTaskInfoService driverTaskInfoService;

    @Autowired
    private DriverTaskPointService driverTaskPointService;

    @Autowired
    private DriverTaskCargoService driverTaskCargoService;


    @Autowired
    private DriverTaskPointDao taskPointDao;

    @Autowired
    private CreateTaskNodeHelper createTaskNodeHelper;

    @Autowired
    private EnterpriseInfoDao enterpriseInfoDao;


    @Autowired
    private ImMsgSender imMsgSender;

    @Autowired
    private JetfireMsgSender jetfireMsgSender;


    @Autowired
    private SyspersonDao syspersonDao;


    @Autowired
    private CarSender carSender;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;


    @Autowired
    private TaskTrackService taskTrackService;

    @Autowired
    private OperationTimeSender operationTimeSender;

    @Autowired
    private InviteCarSender inviteCarSender;

    @Autowired
    private DriverTaskInfoDao driverTaskInfoDao;

    @Autowired
    private DriverTaskCargoDao taskCargoDao;

    @Autowired
    private EntStarSoftDao entStarSoftDao;


    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private CarsDao carsDao;


    private Gson gson = new Gson();



    /**
     * 创建任务单（派车单）
     * @param createDriverTaskDto
     */
    @Override
    public DriverTaskEntity create(CreateDriverTaskDto createDriverTaskDto){
        //任务装卸点
        List<DriverTaskPointDto> taskPointList  = createDriverTaskDto.getTaskPointList();
        if(null == taskPointList || taskPointList.isEmpty()){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.POINT_IS_NULL);
        }
        //货物
        DriverTaskCargoDto taskCargo =  createDriverTaskDto.getTaskCargo();
        //车辆司机
        TaskVehicleDto taskVehicle = createDriverTaskDto.getTaskVehicle();
        if(null == taskVehicle){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.VEHICLE_IS_NULL);
        }
        if(StringUtils.isBlank(taskVehicle.getVehicleId())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.VEHICLE_IS_NULL);
        }
        if(StringUtils.isBlank(taskVehicle.getFirstPersonId()) ){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.VEHICLE_IS_NULL);
        }
        //保存任务（派车）单
        DriverTaskEntity driverTask = this.saveTask(createDriverTaskDto,taskPointList);

        //标识修改改派车单
        if(StringUtils.isNotBlank(createDriverTaskDto.getTaskId())){
            //删除任务详情
            driverTaskInfoDao.deleteDriverTaskInfoByTaskId(createDriverTaskDto.getTaskId());
            //删除装卸点
            taskPointDao.deleteByTaskId(createDriverTaskDto.getTaskId());
            //删除货物
            taskCargoDao.deleteByTaskId(createDriverTaskDto.getTaskId());
        }
        //保存任务单详情
        driverTaskInfoService.saveTaskInfo(createDriverTaskDto,driverTask);

        //保存装卸点
        driverTaskPointService.saveTaskPoint(createDriverTaskDto,driverTask);

        //保存货物
        if(null != taskCargo){
            driverTaskCargoService.saveTaskCargo(taskCargo,driverTask);
        }

        //生成派车节点
        createTaskNodeHelper.sendCar(driverTask);

        return driverTask;
    }


    /**
     * 外部创建
     * @param createDriverTaskDto
     * @return
     */
    @Override
    public CreateTaskByOutVo createByOut(CreateDriverTaskDto createDriverTaskDto){
        String action = TaskPushType.Stowage.getValue();
        if(StringUtils.isNotBlank(createDriverTaskDto.getRelationOrderId())){
            DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskByKeyId(createDriverTaskDto.getRelationOrderId());
            //修改
            if(null != driverTask){
                createDriverTaskDto.setTaskId(driverTask.getGuid());
                if(TaskType.Concrete_YUYUE.getValue().equals(createDriverTaskDto.getTaskType())){
                    action = TaskPushType.InviteCar.getValue();
                }
            }
            else{
                if(TaskType.Concrete_YUYUE.getValue().equals(createDriverTaskDto.getTaskType())){
                    action = TaskPushType.Consign.getValue();
                }
            }
        }
        //保存/修改
        DriverTaskEntity resultTask =  create(createDriverTaskDto);

        //返回参数
        CreateTaskByOutVo createTaskByOutVo = new CreateTaskByOutVo();
        createTaskByOutVo.setDriverTaskEntity(resultTask);
        createTaskByOutVo.setAction(action);
        return createTaskByOutVo;
    }





    /**
     *
     * @param createDriverTaskDto
     * @return
     */
    private DriverTaskEntity saveTask(CreateDriverTaskDto createDriverTaskDto,List<DriverTaskPointDto> taskPointList){
        //派车单单据id
        String taskId = RandomTool.getGUId();
        //派车单单号
        String taskNumber  = CreateOrderNumber.makeSendCarNum();
        if(StringUtils.isNotBlank(createDriverTaskDto.getTaskId())){
            DriverTaskEntity existTask = driverTaskDao.selectDriverTaskById(createDriverTaskDto.getTaskId());
            taskId = existTask.getGuid();
            taskNumber  = existTask.getOrderNumber();
        }
        //业务类型
        Integer bizType = createDriverTaskDto.getBizType();
        //派车单类型
        Integer taskType = createDriverTaskDto.getTaskType();
        //车辆
        TaskVehicleDto taskVehicle = createDriverTaskDto.getTaskVehicle();

        DriverTaskEntity driverTask = new DriverTaskEntity();
        driverTask.setGuid(taskId);
        driverTask.setOrderNumber(taskNumber);
        driverTask.setBizType(bizType);
        driverTask.setTaskStatus(TaskStatus.WaitRun.getValue());
        if(null != createDriverTaskDto.getTaskStatus()){
            driverTask.setStartTime(DateUtil.getSqlTime());
            driverTask.setTaskStatus(createDriverTaskDto.getTaskStatus());
            driverTask.setTaskCurrentStatus(createDriverTaskDto.getTaskCurrentStatus());
            if(TaskStatus.Cancel.getValue().equals(createDriverTaskDto.getTaskStatus())){
                driverTask.setCancel(ConstantUtil.INTEGER_CODE_ONE);
                driverTask.setCancelTime(DateUtil.getSqlTime());
            }
        }
        driverTask.setTaskType(taskType);
        driverTask.setRelationEntId(createDriverTaskDto.getRelationOrderId());
        driverTask.setRelationOrderNumber(createDriverTaskDto.getRelationOrderNumber());
        driverTask.setRelationOrderId(createDriverTaskDto.getRelationOrderId());
        driverTask.setEntId(createDriverTaskDto.getEntId());
        driverTask.setPersonId(createDriverTaskDto.getPersonId());
        driverTask.setOrgnId(createDriverTaskDto.getOrgnId());
        driverTask.setPointType(createDriverTaskDto.getPointType());
        driverTask.setPointCount(taskPointList.size());
        driverTask.setRelationEntId(createDriverTaskDto.getRelationEntId());
        driverTask.setRelationStowageId(createDriverTaskDto.getRelationStowageId());
        driverTask.setCreateTime(DateUtil.getSqlTime());
        driverTask.setEditTime(DateUtil.getSqlTime());
        driverTask.setOutTaskId(createDriverTaskDto.getOutTaskId());

        //车辆司机
        driverTask.setVehicleId(taskVehicle.getVehicleId());
        driverTask.setVehiclePlateNumber(taskVehicle.getVehicleNumber());
        driverTask.setVehicleType(taskVehicle.getVehicleType());
        driverTask.setFirstPersonId(taskVehicle.getFirstPersonId());
        driverTask.setFirstPersonMobile(taskVehicle.getFirstPersonMobile());
        driverTask.setFirstPersonName(taskVehicle.getFirstPersonName());
        driverTask.setSecondPersonId(taskVehicle.getSecondPersonId());
        driverTask.setSecondPersonMobile(taskVehicle.getSecondPersonMobile());
        driverTask.setSecondPersonName(taskVehicle.getSecondPersonName());

        //用车时间
        driverTask.setPlanDepartTime(createDriverTaskDto.getPlanDepartTime());
        driverTask.setPlanArriveTime(createDriverTaskDto.getPlanArriveTime());
        driverTask.setPlanRuningDuration(createDriverTaskDto.getPlanRuningDuration());

        //始发点
        DriverTaskPointDto originalPoint  = taskPointList.get(0);
        if(null != originalPoint){
            driverTask.setOriginalCityCode(originalPoint.getCityCode());
            driverTask.setOriginalAddress(originalPoint.getAddress());
        }
        //到达点
        DriverTaskPointDto destionPoint = taskPointList.get(taskPointList.size()-1);
        if(null != destionPoint){
            driverTask.setDestinationAddress(destionPoint.getAddress());
            driverTask.setDestinationCityCode(destionPoint.getCityCode());
        }

        //派单企业、派单员组合信息串
        EntrustInfoDto entrustInfoDto = new EntrustInfoDto();
        EnterpriseInfoEntity ent = enterpriseInfoDao.select(createDriverTaskDto.getEntId());
        if(null != ent){
            entrustInfoDto.setEntId(ent.getId());
            entrustInfoDto.setEntName(ent.getName());
            entrustInfoDto.setEntLogo(ent.getLogo());
        }
        SystemPersonEntity person  = syspersonDao.select(createDriverTaskDto.getPersonId());
        if(null != person){
            entrustInfoDto.setPersonId(person.getPersonID());
            entrustInfoDto.setPersonName(person.getMyName());
            entrustInfoDto.setPersonLogo(person.gethDpic());
        }
        driverTask.setEntrustInfo(gson.toJson(entrustInfoDto));

        //保存任务单
        if(StringUtils.isNotBlank(createDriverTaskDto.getTaskId())){
            driverTaskDao.updateDriverTask(driverTask);
        }
        else{
            driverTaskDao.insertDriverTask(driverTask);
        }
        return driverTask;
    }





    /**
     * 任务（派车）单列表
     * @param searchTaskDto
     * @return
     */
    @Override
    public List<DriverTaskListVo> list(SearchTaskDto searchTaskDto){
        List<DriverTaskListVo> resultList = new ArrayList<>();
        //分页
        PageHelper.startPage(searchTaskDto.getPageIndex(),searchTaskDto.getPageSize());
        //任务单查询
        DriverTaskEntity taskParam = new DriverTaskEntity();
        taskParam.setEntId(searchTaskDto.getEntId());
        taskParam.setPersonId(searchTaskDto.getPersonId());
        taskParam.setTaskStatus(searchTaskDto.getStatus());
        List<DriverTaskEntity> taskList = driverTaskDao.selectDriverTaskList(taskParam);
        for(DriverTaskEntity driverTask : taskList){
            DriverTaskListVo driverTaskListVo  = new DriverTaskListVo();
            driverTaskListVo.setTaskId(driverTask.getGuid());
            driverTaskListVo.setTaskStatus(driverTask.getTaskStatus());
            driverTaskListVo.setOriginalCity(driverTask.getOriginalCityCode());
            driverTaskListVo.setOriginalAddress(driverTask.getOriginalAddress());
            driverTaskListVo.setDestinationCity(driverTask.getDestinationCityCode());
            driverTaskListVo.setDestinationAddress(driverTask.getDestinationAddress());
            driverTaskListVo.setRead(driverTask.getRead());
            driverTaskListVo.setExceptionArrive(driverTask.getExceptionArrive());
            driverTaskListVo.setExceptionDepart(driverTask.getExceptionDepart());
            driverTaskListVo.setStartTime(driverTask.getStartTime());
            //货物信息
            DriverTaskInfoEntity driverTaskInfo = driverTaskInfoDao.selectDriverTaskInfoByTaskId(driverTask.getGuid());
            if(null != driverTaskInfo){
                TaskCargoVo taskCargoVo = new TaskCargoVo();
                taskCargoVo.setName(driverTaskInfo.getCargoName());
                taskCargoVo.setWeight(driverTaskInfo.getCargoWeight());
                taskCargoVo.setNum(driverTaskInfo.getCargoNum());
                taskCargoVo.setVolume(driverTaskInfo.getCargoVolume());
                driverTaskListVo.setCargoInfo(taskCargoVo);
                driverTaskListVo.setQueueNumber(driverTaskInfo.getQueueNumber());
            }
            //预约单
            if(TaskType.InviteCar.getValue().equals(driverTask.getTaskType())
                    || TaskType.Concrete_YUYUE.getValue().equals(driverTask.getTaskType())){
                driverTaskListVo.setAppointment(true);
            }
            //到达装点时效（用车时间）
            Date planTime =  driverTask.getPlanDepartTime();
            if(null != planTime){
                driverTaskListVo.setPlanDepartTime(DateUtil.dateTOString(planTime,DateUtil.yydd));
                DriverTaskPointEntity firstPoint = taskPointDao.getFirstPoint(driverTask.getGuid());
                if(null != firstPoint && null != firstPoint.getActualArriveTime()){
                    Map<String,String> beyondMap = getRunTime(planTime,firstPoint.getActualArriveTime(),null);
                    if(null != beyondMap){
                        driverTaskListVo.setDepartDurationFlag(beyondMap.get("beyondTime"));
                        driverTaskListVo.setDepartDurationType(beyondMap.get("beyondType"));
                    }

                }
            }
            //到达时效（预计到达时间）
            Date planAarriveTime = driverTask.getPlanArriveTime();
            Date actualArriveTime = driverTask.getActualArriveTime();
            if(null != planAarriveTime){
                driverTaskListVo.setPlanArriveTime(DateUtil.dateTOString(planAarriveTime,DateUtil.yydd));
                if(null != actualArriveTime){
                    Map<String,String> beyondMap = getRunTime(planAarriveTime,actualArriveTime,null);
                    if(null != beyondMap){
                        driverTaskListVo.setArriveDurationFlag(beyondMap.get("beyondTime"));
                        driverTaskListVo.setArriveDurationType(beyondMap.get("beyondType"));
                    }
                }
            }
            //到达时效（预计在途时效）
            Integer planRuningDuration = driverTask.getPlanRuningDuration();
            String duration = "";
            if(null != planRuningDuration && planRuningDuration != 0){
                Integer hour = planRuningDuration /  60;
                Integer minute = planRuningDuration % 60 ;
                if(hour != 0){
                    duration += hour + "时";
                }
                if (minute != 0) {
                    duration += minute + "分";
                }
                driverTaskListVo.setPlanArriveTime(duration);
                if(null != actualArriveTime){
                    Map<String,String> beyondMap = getRunTime(driverTask.getActualDepartTime(),actualArriveTime,planRuningDuration);
                    if(null != beyondMap){
                        driverTaskListVo.setArriveDurationFlag(beyondMap.get("beyondTime"));
                        driverTaskListVo.setArriveDurationType(beyondMap.get("beyondType"));
                    }
                }
            }
            //判断是否同城
            driverTaskListVo.setSameCity(sameCity(driverTask.getOriginalCityCode(),driverTask.getDestinationCityCode()));
            resultList.add(driverTaskListVo);
            //派单企业、人员信息
            if(StringUtils.isNotBlank(driverTask.getEntrustInfo())){
                EntrustInfoDto entrustInfoDto = gson.fromJson(driverTask.getEntrustInfo(),EntrustInfoDto.class);
                driverTaskListVo.setEntrustInfo(entrustInfoDto);
            }
        }
        return resultList;
    }


    /**
     * 时间差
     * @param planTime
     * @param actualTime
     * @return
     */
    public   Map<String,String> getRunTime(Date planTime,Date actualTime ,Integer planDuration){
        String beyondTime = "";
        String beyondType="";
        Map<String,String> resultMap = new HashMap<>();
        Long distance = DateUtil.getDistanceTime(planTime, actualTime);
        distance = Long.valueOf(distance.longValue() / 1000L);
        Integer second  = distance.intValue();
        if(null != planDuration){
            second = distance.intValue() - planDuration * 60;
        }
        // 超时
        Integer resultTime = Math.abs(second);
        Integer day = resultTime / (24 * 60 * 60);
        Integer hour = (resultTime % (24 * 60 * 60)) / (60 * 60);
        Integer minute = (resultTime % (60 * 60)) / 60;
        if (day != 0) {
            beyondTime += day + "天";
        }
        if (hour != 0) {
            beyondTime += hour + "时";
        }
        if (minute != 0) {
            beyondTime += minute + "分";
        }
        if (second <= -60) {
            beyondTime = "提前" + beyondTime;
            beyondType = ConstantUtil.String_CODE_THREE;

        } else if (second >= 60) {
            beyondTime = "超时" + beyondTime;
            beyondType = ConstantUtil.String_CODE_TWO;

        } else if (second > -60 && second < 60) {
            beyondTime = "准时到达";
            beyondType = ConstantUtil.String_CODE_ONE;
        }
        resultMap.put("beyondTime",beyondTime);
        resultMap.put("beyondType",beyondType);

        return resultMap;
    }








    /**
     * 详情
     * @param taskId
     * @return
     */
    @Override
    public TaskDetailVo detail(String taskId){
        TaskDetailVo taskDetailVo = new TaskDetailVo();
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        taskDetailVo.setTaskId(driverTask.getGuid());
        taskDetailVo.setNumber(driverTask.getOrderNumber());
        taskDetailVo.setStartTime(driverTask.getStartTime());
        taskDetailVo.setFinishTime(driverTask.getActualArriveTime());
        taskDetailVo.setStatus(driverTask.getTaskStatus());
        taskDetailVo.setEntId(driverTask.getEntId());
        taskDetailVo.setVehicleId(driverTask.getVehicleId());
        taskDetailVo.setVehiclePlateNumber(driverTask.getVehiclePlateNumber());
        taskDetailVo.setOrgnId(driverTask.getOrgnId());
        taskDetailVo.setActualMileage(driverTask.getActualRuningMileage());
        taskDetailVo.setActualWorkTime(driverTask.getActualRuningDuration());

        //任务单 待签收数量
        Integer taskType = driverTask.getTaskType();



        if( TaskType.Deliery.getValue().equals(taskType)){
            List<TaskPointVo> waitSignList  = driverTaskPointService.getWaitSignList(taskId);
            taskDetailVo.setWaitSignCount(waitSignList.size());
        }
        //更新任务状态为已读
        if(ConstantUtil.INTEGER_CODE_ZERO.equals(driverTask.getRead())){
            driverTask.setRead(ConstantUtil.INTEGER_CODE_ONE);
            driverTaskDao.updateDriverTask(driverTask);
        }
        return taskDetailVo;
    }


    /**
     * 在途任务
     * @param personId
     * @return
     */
    @Override
    public RunningTaskVo runningTask(String personId){
        RunningTaskVo runningTaskVo = null;
        DriverTaskEntity driverTask = driverTaskDao.queryRunningTask(personId,null);
        if(null != driverTask){
            runningTaskVo = new RunningTaskVo();
            runningTaskVo.setTaskId(driverTask.getGuid());
            runningTaskVo.setTaskStatus(driverTask.getTaskStatus());
            runningTaskVo.setOriginalCity(driverTask.getOriginalCityCode());
            runningTaskVo.setOriginalAddress(driverTask.getOriginalAddress());
            runningTaskVo.setDestinationCity(driverTask.getDestinationCityCode());
            runningTaskVo.setDestinationAddress(driverTask.getDestinationAddress());
            runningTaskVo.setOrgnId(driverTask.getOrgnId());
            runningTaskVo.setEntId(driverTask.getEntId());
            runningTaskVo.setVehicleId(driverTask.getVehicleId());
            runningTaskVo.setVehiclePlateNumber(driverTask.getVehiclePlateNumber());
            runningTaskVo.setActualDepartTime(driverTask.getActualDepartTime());
        }
        return runningTaskVo;
    }




    /**
     * 执行中的任务
     * @param personId
     * @return
     */
    @Override
    public RunningTaskVo executingTask(String personId){
        RunningTaskVo runningTaskVo = null;
        DriverTaskEntity driverTask = driverTaskDao.queryExecutingTask(personId,null);
        if(null != driverTask){
            runningTaskVo = new RunningTaskVo();
            runningTaskVo.setTaskId(driverTask.getGuid());
            runningTaskVo.setTaskStatus(driverTask.getTaskStatus());
            runningTaskVo.setOriginalCity(driverTask.getOriginalCityCode());
            runningTaskVo.setOriginalAddress(driverTask.getOriginalAddress());
            runningTaskVo.setDestinationCity(driverTask.getDestinationCityCode());
            runningTaskVo.setDestinationAddress(driverTask.getDestinationAddress());
            runningTaskVo.setOrgnId(driverTask.getOrgnId());
            runningTaskVo.setEntId(driverTask.getEntId());
            runningTaskVo.setVehicleId(driverTask.getVehicleId());
            runningTaskVo.setVehiclePlateNumber(driverTask.getVehiclePlateNumber());
            runningTaskVo.setActualDepartTime(driverTask.getActualDepartTime());
        }
        return runningTaskVo;
    }




    /**
     * 根据起点和终点判断是否同城
     * @param originalCity
     * @param destnationCity
     * @return
     */
    private boolean sameCity(String originalCity,String destnationCity){
        boolean isSameCity = true;
        if(StringUtils.isNotBlank(originalCity) && StringUtils.isNotBlank(destnationCity)){
            Integer oriCode = Integer.parseInt(originalCity);
            Integer destCode = Integer.parseInt(destnationCity);
            if(null != oriCode && null != destCode ){
                isSameCity = oriCode/100 == destCode/100;
            }
        }
        return isSameCity;
    }



    /**
     * 去装货
     * @param handleTaskDto
     */
    @Override
    public void goLoad(HandleTaskDto handleTaskDto){
        String taskId = handleTaskDto.getTaskId();
        boolean updateTaskStatus = false;
        Integer relationOrderStatus = BizOrdersStatus.PendingDeparture.getValue();

        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        //判断是否有其他任务已经在途  如果已经有任务在途  则 当前任务操作发车则提示
        validateRunTask(handleTaskDto.getPersonId(),taskId);
        //当前操作装卸点
        String pointId = handleTaskDto.getPointId();
        DriverTaskPointEntity taskPoint = taskPointDao.selectDriverTaskPointById(pointId);
        if(null == taskPoint){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_POINT_NOT_EXIST);
        }
        //已完成任务不可操作
        if(TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.FINISH_TASK);
        }
        //判断是否反复操作
        if(TaskCurrentStatus.GoLoad.getValue().equals(taskPoint.getStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.NOT_REPEAT);
        }


        if(null == driverTask.getStartTime()){
            driverTask.setStartTime(DateUtil.getSqlTime());
        }

        //揽收单 去装货操作后 任务单 直接变更在途
        if(TaskType.Collect.getValue().equals(driverTask.getTaskType())){
            updateTaskStatus = true;
        }
        //预约单 去装货操作后 任务单 直接变更在途
        else if(TaskType.InviteCar.getValue().equals(driverTask.getTaskType())){
            updateTaskStatus = true;
            //更新预约单（物流端）为在途中
            inviteCarSender.updateStatus(driverTask.getRelationOrderId(),TaskStatus.Runing.getValue());
        }
        //其他单据 则判断是否在第一个点完成了装货
        else{
            List<DriverTaskPointEntity> arriveList =  taskPointDao.arriveList(taskId);
            if(null != arriveList && arriveList.size()>0){
                updateTaskStatus = true;
            }
            //如果是拼装 则判断 是否需要更新上个点 对应的托运单为在途
            if(TaskType.Assemble.getValue().equals(driverTask.getTaskType()) ||
                    TaskType.Consign.getValue().equals(driverTask.getTaskType())){
                //判断当前点对应的托运单是否有已经到达的装卸点 如果有则为在途，否则为代发
                Long count =  taskPointDao.arrivePointByRelationId(taskId,taskPoint.getOrderGuid());
                if(count >0){
                    relationOrderStatus = BizOrdersStatus.Runing.getValue();
                }
            }
        }
        //更新任务单
        if(updateTaskStatus){
            driverTask.setActualDepartTime(DateUtil.getSqlTime());
            driverTask.setTaskStatus(TaskStatus.Runing.getValue());
            driverTask.setExceptionDepart(handleTaskDto.getExceptionDepart());
            driverTask.setActualDepartLat(handleTaskDto.getLat());
            driverTask.setActualDepartLng(handleTaskDto.getLng());
            //更新车辆状态
            carSender.updateCarStatus(driverTask.getEntId(),driverTask.getVehicleId(),CarStatus.Running.getValue());
        }
        driverTask.setTaskCurrentStatus(TaskCurrentStatus.GoLoad.getValue());
        driverTask.setWorkPointId(pointId);
        driverTaskDao.updateDriverTask(driverTask);

        //当前装卸点状态改为去装货
        taskPoint.setStatus(TaskCurrentStatus.GoLoad.getValue());
        taskPoint.setStatusValue(TaskCurrentStatus.GoLoad.getDescription());
        taskPoint.setActualStartTime(DateUtil.getSqlTime());
        taskPoint.setWork(ConstantUtil.INTEGER_CODE_ONE);
        taskPointDao.updateDriverTaskPoint(taskPoint);

        //已经确认到达/开始装货/开始卸货的装卸点装卸点跟新为完成装货/完成卸货
        List<DriverTaskPointEntity> arrivePointList = taskPointDao.arriveList(taskId);
        for(DriverTaskPointEntity arrivePoint : arrivePointList){
            Integer loadType = arrivePoint.getType();
            if(LoadType.Load.getValue().equals(loadType)){
                arrivePoint.setStatus(TaskCurrentStatus.FinishLoad.getValue());
                arrivePoint.setStatusValue(TaskCurrentStatus.FinishLoad.getDescription());
            }
            else if(LoadType.UnLoad.getValue().equals(loadType)){
                arrivePoint.setStatus(TaskCurrentStatus.FinishUnLoad.getValue());
                arrivePoint.setStatusValue(TaskCurrentStatus.FinishUnLoad.getDescription());
            }
            //开始装卸时间如果为空则补充
            if(null == arrivePoint.getActualWorkTime()){
                arrivePoint.setActualWorkTime(DateUtil.getSqlTime());
            }
            //完成时间一定会有
            if(null == arrivePoint.getActualFinishTime()){
                arrivePoint.setActualFinishTime(DateUtil.getSqlTime());
            }
            taskPointDao.updateDriverTaskPoint(arrivePoint);
        }


        // 任务运行时间段
        operationTimeSender.sendCar(driverTask.getVehicleId(), driverTask.getVehiclePlateNumber(),
                driverTask.getFirstPersonId(),taskId, DateUtil.getSqlTime());

        //生成去装货的节点
        createTaskNodeHelper.goLoad(handleTaskDto,driverTask,taskPoint,relationOrderStatus);
    }




    /**
     * 确认到达
     * @param handleTaskDto
     */
    @Override
    public void arrive(HandleTaskDto handleTaskDto){
        String taskId = handleTaskDto.getTaskId();
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        //判断是否有其他任务已经在途  如果已经有任务在途  则 当前任务操作发车则提示
        //validateRunTask(handleTaskDto.getPersonId(),taskId);
        //当前操作装卸点
        String pointId = handleTaskDto.getPointId();
        DriverTaskPointEntity taskPoint = taskPointDao.selectDriverTaskPointById(pointId);
        if(null == taskPoint){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_POINT_NOT_EXIST);
        }
        Integer loadType = taskPoint.getType();
        //到达装货点
        if(LoadType.Load.getValue().equals(loadType)){
            driverTask = this.arriveLoad(driverTask,taskPoint);
        }
        //到达卸货点
        else if(LoadType.UnLoad.getValue().equals(loadType)){
            driverTask = this.arriveUnLoad(driverTask,taskPoint,handleTaskDto);
        }
        //计算到达当前 装、卸货点的里程
        DriverTaskEntity finalTask = driverTask;
        taskExecutor.execute(() -> taskTrackService.countMileage(finalTask,taskPoint));

    }


    /**
     * 到达装货点
     * @param driverTask
     * @param taskPoint
     */
    private DriverTaskEntity arriveLoad(DriverTaskEntity driverTask,DriverTaskPointEntity taskPoint){
        //已完成任务不可操作
        if(TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.FINISH_TASK);
        }
        //判断是否反复操作
        if(TaskCurrentStatus.ArriveLoad.getValue().equals(taskPoint.getStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.NOT_REPEAT);
        }
        //装货点不是最后一个点 到达装货点后将下一个装卸点 置为可工作状态
        Integer currentSort = taskPoint.getSort();
        Integer nextSort = currentSort+1;
        DriverTaskPointEntity nextPoint = taskPointDao.getPointBySort(driverTask.getGuid(),nextSort);
        if(null != nextPoint){
            nextPoint.setWork(1);
            taskPointDao.updateDriverTaskPoint(nextPoint);
        }
        //判断当前装卸点的单据对应的状态
        Integer pointRelationOrderStatus = BizOrdersStatus.PendingDeparture.getValue();
        //整车拼装  到达装货点 去判断当前装货点是不是对应托运单的最后一个点，若是则改托运单状态 更改为已到达
        if(TaskType.Assemble.getValue().equals(driverTask.getTaskType())){
            String pointOrderId = taskPoint.getOrderGuid();
            Long finishCount = taskPointDao.finishPointByRelationId(driverTask.getGuid(),pointOrderId,taskPoint.getGuid());
            //表示 对应的托运单 全部装卸点都已到达 则该托运单状态更改为已达到
            if(finishCount == 0){
                pointRelationOrderStatus = BizOrdersStatus.Arrive.getValue();
            }
            else{
                Long count =  taskPointDao.arrivePointByRelationId(driverTask.getGuid(),taskPoint.getOrderGuid());
                if(count >0){
                    pointRelationOrderStatus = BizOrdersStatus.Runing.getValue();
                }
            }
        }
        //请车单 到达装货点后 派车单（预约）单  直接完成
        else if(TaskType.InviteCar.getValue().equals(driverTask.getTaskType())){
            driverTask.setTaskStatus(TaskStatus.Finish.getValue());
            driverTask.setActualArriveTime(DateUtil.getSqlTime());
            //总时长
            Long taskActualDuration = DateUtil.getDistanceTimeForSecond(driverTask.getStartTime(),driverTask.getActualArriveTime());
            driverTask.setActualRuningDuration(taskActualDuration.intValue());
            driverTaskDao.updateDriverTask(driverTask);

            //到达即完成
            taskPoint.setActualFinishTime(DateUtil.getSqlTime());

            //更新预约单（物流端）为已到达
            inviteCarSender.updateStatus(driverTask.getRelationOrderId(),TaskStatus.Finish.getValue());

            //更新车辆状态
            carSender.updateCarStatus(driverTask.getEntId(),driverTask.getVehicleId(),CarStatus.Free.getValue());

            //完成任务时间段
            operationTimeSender.finishTask(driverTask.getGuid(),driverTask.getEntId(),DateUtil.getSqlTime());

            //任务完成推送司机端
            taskImPush(driverTask.getGuid(),"3");

        }
        //其他情况 到达装货点 则跟派车单状态保持一致
        else {
            if(TaskStatus.Runing.getValue().equals(driverTask.getTaskStatus())){
                pointRelationOrderStatus = BizOrdersStatus.Runing.getValue();
            }
        }

        //更新装卸点
        taskPoint.setStatus(TaskCurrentStatus.ArriveLoad.getValue());
        taskPoint.setStatusValue(TaskCurrentStatus.ArriveLoad.getDescription());
        taskPoint.setActualArriveTime(DateUtil.getSqlTime());
        Long actualDuration = DateUtil.getDistanceTimeForSecond(taskPoint.getActualStartTime(),taskPoint.getActualArriveTime());
        taskPoint.setActualRuningDuration(actualDuration.intValue());
        taskPointDao.updateDriverTaskPoint(taskPoint);

        //生成到达装货点的节点信息
        createTaskNodeHelper.arrive(driverTask,taskPoint,pointRelationOrderStatus,false,BizNodeAction.ARRIVE_LOAD.getValue());

        return  driverTask;
    }


    /**
     * 到达卸货点
     * @param driverTask
     * @param taskPoint
     */
    private DriverTaskEntity arriveUnLoad(DriverTaskEntity driverTask,DriverTaskPointEntity taskPoint,HandleTaskDto handleTaskDto){
        boolean finish = false;
        Integer pointRelationOrderStatus = BizOrdersStatus.Runing.getValue();
        //已完成任务不可操作
        if(TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.FINISH_TASK);
        }
        if(TaskCurrentStatus.ArriveUnLoad.getValue().equals(taskPoint.getStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.NOT_REPEAT);
        }
        Integer taskType = driverTask.getTaskType();
        //多装一卸（含一装一卸） 到达卸货点 则任务单完成
        if(TaskType.Stowage.getValue().equals(taskType) || TaskType.Collect.getValue().equals(taskType)){
            finish = true;
            pointRelationOrderStatus = BizOrdersStatus.Arrive.getValue();
        }
        else{
            //派送单派车（一装多卸） 到达卸货点 运单状态为待签收
            if(TaskType.Deliery.getValue().equals(taskType)){
                pointRelationOrderStatus = BizOrdersStatus.WaitSign.getValue();
            }
            else{
                Long count = taskPointDao.finishPointByRelationId(driverTask.getGuid(),taskPoint.getOrderGuid(),taskPoint.getGuid());
                if(count == 0){
                    pointRelationOrderStatus = BizOrdersStatus.Arrive.getValue();
                }
            }
            //如果所有点 均已到达，该点则为最后一个点，到达后，任务单状态改为完成
            List<DriverTaskPointEntity> arriveList =  taskPointDao.arriveList(driverTask.getGuid());
            Integer arriveCount = arriveList.size();
            //最后一个点
            if(driverTask.getPointCount()-arriveCount == 1){
                finish = true;
            }
            //到达卸货点后将下一个装卸点 置为可工作状态
            else{
                Integer currentSort = taskPoint.getSort();
                Integer nestSort = currentSort+1;
                DriverTaskPointEntity nextPoint = taskPointDao.getPointBySort(driverTask.getGuid(),nestSort);
                if(null != nextPoint){
                    nextPoint.setWork(1);
                    taskPointDao.updateDriverTaskPoint(nextPoint);
                }
            }
        }

        //更新卸货点信息
        taskPoint.setStatus(TaskCurrentStatus.ArriveUnLoad.getValue());
        taskPoint.setStatusValue(TaskCurrentStatus.ArriveUnLoad.getDescription());
        taskPoint.setActualArriveTime(DateUtil.getSqlTime());
        Long actualDuration = DateUtil.getDistanceTimeForSecond(taskPoint.getActualStartTime(),taskPoint.getActualArriveTime());
        taskPoint.setActualRuningDuration(actualDuration.intValue());
        taskPointDao.updateDriverTaskPoint(taskPoint);

        //任务完成 则更新状态
        if(finish){
            driverTask.setTaskStatus(TaskStatus.Finish.getValue());
            driverTask.setActualArriveTime(DateUtil.getSqlTime());
            Long taskActualDuration = DateUtil.getDistanceTimeForSecond(driverTask.getStartTime(),taskPoint.getActualArriveTime());
            driverTask.setActualRuningDuration(taskActualDuration.intValue());
            driverTask.setExceptionArrive(handleTaskDto.getExceptionArrive());
            driverTask.setActualArriveLat(handleTaskDto.getLat());
            driverTask.setActualArriveLng(handleTaskDto.getLng());
            driverTaskDao.updateDriverTask(driverTask);

            //完成任务时间段
            operationTimeSender.finishTask(driverTask.getGuid(),driverTask.getEntId(),DateUtil.getSqlTime());

            //更新车辆状态
            carSender.updateCarStatus(driverTask.getEntId(),driverTask.getVehicleId(),CarStatus.Free.getValue());

            //任务完成推送司机端
            taskImPush(driverTask.getGuid(),"3");


            //星软 智慧商砼 终点经纬度
            if(TaskType.Concrete_FAHUO.getValue().equals(taskType)){
                DriverTaskInfoEntity driverTaskInfo = driverTaskInfoDao.selectDriverTaskInfoByTaskId(driverTask.getGuid());
                if(null != driverTaskInfo){
                    driverGpsToRes(handleTaskDto.getLat(),handleTaskDto.getLng(),handleTaskDto.getAddress(),
                            driverTask.getEntId(),driverTaskInfo.getOutDestinationId());
                }
            }
        }


        //是否可签收
        boolean isSign = driverTaskPointService.showSign(taskPoint,driverTask);


        //生成节点
        createTaskNodeHelper.arrive(driverTask,taskPoint,pointRelationOrderStatus,isSign,BizNodeAction.ARRIVE_UNLOAD.getValue());

        return  driverTask;
    }


    /**
     * 开始装货
     * @param handleTaskDto
     */
    @Override
    public void startLoad(HandleTaskDto handleTaskDto){
        String taskId = handleTaskDto.getTaskId();
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        //判断是否有其他任务已经在途  如果已经有任务在途  则 当前任务操作发车则提示
        //validateRunTask(handleTaskDto.getPersonId(),taskId);
        //当前操作装卸点
        String pointId = handleTaskDto.getPointId();
        DriverTaskPointEntity taskPoint = taskPointDao.selectDriverTaskPointById(pointId);
        if(null == taskPoint){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_POINT_NOT_EXIST);
        }
        //已完成任务不可操作
        if(TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.FINISH_TASK);
        }
        //判断是否反复操作
        if(TaskCurrentStatus.StartLoad.getValue().equals(taskPoint.getStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.NOT_REPEAT);
        }
        taskPoint.setStatus(TaskCurrentStatus.StartLoad.getValue());
        taskPoint.setStatusValue(TaskCurrentStatus.StartLoad.getDescription());
        taskPoint.setActualWorkTime(DateUtil.getSqlTime());
        taskPointDao.updateDriverTaskPoint(taskPoint);

        //生成节点
        createTaskNodeHelper.loadAndUnLoad(driverTask,taskPoint,BizNodeAction.START_LOAD.getValue());
    }


    /**
     * 完成装货
     * @param handleTaskDto
     */
    @Override
    public void finishLoad(HandleTaskDto handleTaskDto){
        String taskId = handleTaskDto.getTaskId();
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        //判断是否有其他任务已经在途  如果已经有任务在途  则 当前任务操作发车则提示
        //validateRunTask(handleTaskDto.getPersonId(),taskId);
        //当前操作装卸点
        String pointId = handleTaskDto.getPointId();
        DriverTaskPointEntity taskPoint = taskPointDao.selectDriverTaskPointById(pointId);
        if(null == taskPoint){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_POINT_NOT_EXIST);
        }
        //已完成任务不可操作
        if(TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.FINISH_TASK);
        }
        //判断是否反复操作
        if(TaskCurrentStatus.FinishLoad.getValue().equals(taskPoint.getStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.NOT_REPEAT);
        }
        //当前装卸点位置/顺序
        Integer currentSort = taskPoint.getSort();

        taskPoint.setStatus(TaskCurrentStatus.FinishLoad.getValue());
        taskPoint.setStatusValue(TaskCurrentStatus.FinishLoad.getDescription());
        taskPoint.setActualFinishTime(DateUtil.getSqlTime());
        taskPointDao.updateDriverTaskPoint(taskPoint);


        //获取下一个点 置为当前工作点
        Integer nestSort = currentSort+1;
        DriverTaskPointEntity nextPoint = taskPointDao.getPointBySort(taskId,nestSort);
        if(null != nextPoint){
            driverTask.setWorkPointId(nextPoint.getGuid());
            driverTaskDao.updateDriverTask(driverTask);

        }

        //生成节点
        createTaskNodeHelper.loadAndUnLoad(driverTask,taskPoint,BizNodeAction.FINISH_LOAD.getValue());

    }


    /**
     * 去卸货
     * @param handleTaskDto
     */
    @Override
    public void goUnLoad(HandleTaskDto handleTaskDto){
        String taskId = handleTaskDto.getTaskId();
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        //判断是否有其他任务已经在途  如果已经有任务在途  则 当前任务操作发车则提示
        //validateRunTask(handleTaskDto.getPersonId(),taskId);
        //当前操作装卸点
        String pointId = handleTaskDto.getPointId();
        DriverTaskPointEntity taskPoint = taskPointDao.selectDriverTaskPointById(pointId);
        if(null == taskPoint){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_POINT_NOT_EXIST);
        }
        //已完成任务不可操作
        if(TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.FINISH_TASK);
        }
        //判断是否反复操作
        if(TaskCurrentStatus.GoUnLoad.getValue().equals(taskPoint.getStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.NOT_REPEAT);
        }

        //更新状态
        taskPoint.setStatus(TaskCurrentStatus.GoUnLoad.getValue());
        taskPoint.setStatusValue(TaskCurrentStatus.GoUnLoad.getDescription());
        taskPoint.setActualStartTime(DateUtil.getSqlTime());
        taskPoint.setWork(1);
        taskPointDao.updateDriverTaskPoint(taskPoint);

        //已经确认到达/开始装货/开始卸货的装卸点装卸点跟新为完成装货/完成卸货
        List<DriverTaskPointEntity> arrivePointList = taskPointDao.arriveList(taskId);
        for(DriverTaskPointEntity arrivePoint : arrivePointList){
            Integer loadType = arrivePoint.getType();
            if(LoadType.Load.getValue().equals(loadType)){
                arrivePoint.setStatus(TaskCurrentStatus.FinishLoad.getValue());
                arrivePoint.setStatusValue(TaskCurrentStatus.FinishLoad.getDescription());
            }
            else if(LoadType.UnLoad.getValue().equals(loadType)){
                arrivePoint.setStatus(TaskCurrentStatus.FinishUnLoad.getValue());
                arrivePoint.setStatusValue(TaskCurrentStatus.FinishUnLoad.getDescription());
            }
            //开始装卸时间如果为空则补充
            if(null == arrivePoint.getActualWorkTime()){
                arrivePoint.setActualWorkTime(DateUtil.getSqlTime());
            }
            if(null == arrivePoint.getActualFinishTime()){
                arrivePoint.setActualFinishTime(DateUtil.getSqlTime());
            }
            taskPointDao.updateDriverTaskPoint(arrivePoint);

        }

        //更新任务单状态
        if(!TaskStatus.Runing.getValue().equals(driverTask.getTaskStatus())){
            driverTask.setExceptionDepart(handleTaskDto.getExceptionDepart());
            driverTask.setActualDepartLat(handleTaskDto.getLat());
            driverTask.setActualDepartLng(handleTaskDto.getLng());
            driverTask.setActualDepartTime(DateUtil.getSqlTime());
        }
        driverTask.setTaskCurrentStatus(TaskCurrentStatus.GoUnLoad.getValue());
        driverTask.setTaskStatus(TaskStatus.Runing.getValue());
        driverTask.setWorkPointId(pointId);
        driverTaskDao.updateDriverTask(driverTask);

        //更新车辆状态
        carSender.updateCarStatus(driverTask.getEntId(),driverTask.getVehicleId(),CarStatus.Running.getValue());



        // 任务运行时间段
        operationTimeSender.sendCar(driverTask.getVehicleId(), driverTask.getVehiclePlateNumber(),
                driverTask.getFirstPersonId(),taskId, DateUtil.getSqlTime());

        //生成节点
        createTaskNodeHelper.goUnLoad(handleTaskDto,driverTask,taskPoint);

    }


    /**
     * 开始卸货
     * @param handleTaskDto
     */
    @Override
    public void startUnLoad(HandleTaskDto handleTaskDto){
        String taskId = handleTaskDto.getTaskId();
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        //判断是否有其他任务已经在途  如果已经有任务在途  则 当前任务操作发车则提示
        //validateRunTask(handleTaskDto.getPersonId(),taskId);
        //当前操作装卸点
        String pointId = handleTaskDto.getPointId();
        DriverTaskPointEntity taskPoint = taskPointDao.selectDriverTaskPointById(pointId);
        if(null == taskPoint){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_POINT_NOT_EXIST);
        }
        //已完成任务不可操作
//        if(TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus())){
//            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.FINISH_TASK);
//        }
        //判断是否反复操作
        if(TaskCurrentStatus.StartUnLoad.getValue().equals(taskPoint.getStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.NOT_REPEAT);
        }
        taskPoint.setStatus(TaskCurrentStatus.StartUnLoad.getValue());
        taskPoint.setStatusValue(TaskCurrentStatus.StartUnLoad.getDescription());
        //开始装卸时间如果为空则补充
        taskPoint.setActualWorkTime(DateUtil.getSqlTime());
        taskPointDao.updateDriverTaskPoint(taskPoint);


        //生成节点
        createTaskNodeHelper.loadAndUnLoad(driverTask,taskPoint,BizNodeAction.START_UNLOAD.getValue());

    }





    /**
     * 完成卸货
     * @param handleTaskDto
     */
    @Override
    public void finishUnLoad(HandleTaskDto handleTaskDto){
        String taskId = handleTaskDto.getTaskId();
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        //判断是否有其他任务已经在途  如果已经有任务在途  则 当前任务操作发车则提示
        // validateRunTask(handleTaskDto.getPersonId(),taskId);
        //当前操作装卸点
        String pointId = handleTaskDto.getPointId();
        DriverTaskPointEntity taskPoint = taskPointDao.selectDriverTaskPointById(pointId);
        if(null == taskPoint){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_POINT_NOT_EXIST);
        }
        //已完成任务不可操作
//        if(TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus())){
//            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.FINISH_TASK);
//        }
        //判断是否反复操作
        if(TaskCurrentStatus.FinishUnLoad.getValue().equals(taskPoint.getStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.NOT_REPEAT);
        }
        taskPoint.setStatus(TaskCurrentStatus.FinishUnLoad.getValue());
        taskPoint.setStatusValue(TaskCurrentStatus.FinishUnLoad.getDescription());
        taskPoint.setActualFinishTime(DateUtil.getSqlTime());
        taskPointDao.updateDriverTaskPoint(taskPoint);


        //获取下一个点 置为当前工作点
        Integer currentSort = taskPoint.getSort();
        Integer nestSort = currentSort+1;
        DriverTaskPointEntity nextPoint = taskPointDao.getPointBySort(taskId,nestSort);
        if(null != nextPoint){
            driverTask.setWorkPointId(nextPoint.getGuid());
            driverTaskDao.updateDriverTask(driverTask);
        }

        //生成节点
        createTaskNodeHelper.loadAndUnLoad(driverTask,taskPoint,BizNodeAction.FINISH_UNLOAD.getValue());
    }


    /**
     * 判断除当前任务外 是否还有别的在途任务
     * @param taskId
     * @param personId
     */
    private void validateRunTask(String personId,String taskId){
        //判断是否有其他任务已经在执行  如果已经有执行任务  则 当前任务操作发车则提示
        DriverTaskEntity executingTask = driverTaskDao.queryExecutingTask(personId,taskId);
        if(null != executingTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.HAS_RUNNING_TASK);
        }
    }




    /**
     * 删除
     * @param taskId
     */
    @Override
    public void delete(String taskId){
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        //已取消状态下才能删除
        if(!TaskStatus.Cancel.getValue().equals(driverTask.getTaskStatus())){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.NO_DELETE_TASK);
        }
        driverTask.setDelete(ConstantUtil.INTEGER_CODE_ONE);
        driverTask.setDeleteTime(DateUtil.getSqlTime());
        driverTaskDao.updateDriverTask(driverTask);
    }


    /**
     * 根据关联单据id删除
     * @param relationOrderId
     */
    @Override
    public void deleteByRelationId(String relationOrderId,String vehicleId){
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskByKeyIdAndVehicleId(relationOrderId,vehicleId);
        if(null != driverTask){
            driverTask.setDelete(ConstantUtil.INTEGER_CODE_ONE);
            driverTask.setDeleteTime(DateUtil.getSqlTime());
            driverTaskDao.updateDriverTask(driverTask);
            //if(TaskType.Concrete_YUYUE.getValue().equals(driverTask.getTaskType())){
                taskImPush(driverTask.getGuid(),TaskPushType.Concrete_YUYUE.getValue());
            //}
        }
    }



    /**
     * 任务附加项（数字 红点展示）
     * @param personId
     * @return
     */
    @Override
    public DriverTaskItemVo getDriverTaskItem(String personId, String entId){
        DriverTaskItemVo driverTaskItemVo = new DriverTaskItemVo();
        DriverTaskEntity driverTaskParam = new DriverTaskEntity();
        driverTaskParam.setPersonId(personId);
        driverTaskParam.setEntId(entId);
        driverTaskParam.setTaskStatus(TaskStatus.WaitRun.getValue());
        List<DriverTaskEntity> taskList = driverTaskDao.selectDriverTaskList(driverTaskParam);
        driverTaskItemVo.setWaitDepartNumber(taskList.size());
        taskList = taskList.parallelStream().filter(p -> p.getRead().equals(ConstantUtil.INTEGER_CODE_ZERO)).collect(Collectors.toList());
        driverTaskItemVo.setNoReadNum(taskList.size());
        if (taskList.size()>0){
            driverTaskItemVo.setShowRed(true);
        }
        return  driverTaskItemVo;
    }


    /**
     * 任务推送
     * @param taskId
     * action
     */
    @Override
    public void taskImPush(String taskId,String action){
        Gson formatGson = new GsonBuilder().disableHtmlEscaping().create();
        SmsTemplate smsTemplate = new SmsTemplate();
        ImSysDto imSysDto = new ImSysDto();
        TaskExtraDto taskExtraDto = new TaskExtraDto();
        MessageBizType messageBizType = null;
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        String firstPersonId = driverTask.getFirstPersonId();
        String secondPersonId = driverTask.getSecondPersonId();
        String linkUrl = null;
        if(action.equals("1")){
            messageBizType = MessageBizType.NEW_TASK_PUSH;
            linkUrl=ConstantUtil.taskInfoPage+"?taskId="+taskId;
        }
        else if(action.equals("2")){
            messageBizType = MessageBizType.RETURN_TASK_PUSH;
        }
        else if(action.equals("3")){
            messageBizType = MessageBizType.FINISH_TASK_PUSH;
            linkUrl=ConstantUtil.taskInfoPage+"?taskId="+taskId;
        }
        else if(action.equals("4")){
            messageBizType = MessageBizType.NEW_PLAN_CAR_PUSH;
            linkUrl=ConstantUtil.planCarInfoPage+"?taskId="+taskId;

        }
        else if(action.equals("5")){
            messageBizType = MessageBizType.PLAN_CAR_CHANGE_PUSH;
            linkUrl=ConstantUtil.planCarInfoPage+"?taskId="+taskId;
        }
        else if(action.equals("6")){
            messageBizType = MessageBizType.PLAN_CAR_DELETE_PUSH;
            linkUrl=ConstantUtil.planCarInfoPage+"?taskId="+taskId;
        }
        imSysDto.setTitle(messageBizType.getTitle());
        imSysDto.setContent(messageBizType.getContent());
        imSysDto.setEntId(driverTask.getEntId());
        //派单企业、人员信息
        if(StringUtils.isNotBlank(driverTask.getEntrustInfo())){
            EntrustInfoDto entrustInfoDto = gson.fromJson(driverTask.getEntrustInfo(),EntrustInfoDto.class);
            imSysDto.setEntName(entrustInfoDto.getEntName());
            taskExtraDto.setEntrustEntName(entrustInfoDto.getEntName());
        }
        EnterpriseInfoEntity ent = enterpriseInfoDao.select(driverTask.getEntId());
        imSysDto.setEntLogo(ent.getLogo());
        imSysDto.setType(MessageType.TASK.getValue().toString());



        imSysDto.setUrl(linkUrl);
        List<String> toUserList = new ArrayList<>();
        if(StringUtils.isNotBlank(firstPersonId)){
            SystemPersonEntity firstPerson = syspersonDao.select(firstPersonId);
            if(null != firstPerson){
                toUserList.add(firstPerson.getLogitalkId());
                smsTemplate.setBaseUserId(firstPersonId);
                smsTemplate.setMobile(firstPerson.getLoginName());
            }

        }
        if(StringUtils.isNotBlank(secondPersonId)){
            SystemPersonEntity secondPerson = syspersonDao.select(secondPersonId);
            if(null != secondPerson){
                toUserList.add(secondPerson.getLogitalkId());
            }

        }
        imSysDto.setToUser(toUserList);
        imSysDto.setSendTime(DateUtil.timestamp2Str(DateUtil.getSqlTime()));


        //自定义
        taskExtraDto.setBizType(messageBizType.getType());
        taskExtraDto.setLoadPlace(driverTask.getOriginalAddress());
        //taskExtraDto.setEntrustEntName(taskCarInfo.getEntrustEntName());
        taskExtraDto.setTaskCarNumber(driverTask.getOrderNumber());
        String route = driverTask.getOriginalAddress()+"至"+driverTask.getDestinationAddress();
        taskExtraDto.setTaskTransportRoute(route);
        taskExtraDto.setTaskStatus(TaskStatus.find(driverTask.getTaskStatus()).getDescription());
        taskExtraDto.setLinkUrl(linkUrl);
        if(null != driverTask.getPlanDepartTime()){
            taskExtraDto.setVehicleTime(DateUtil.dateTOString(driverTask.getPlanDepartTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        }
        imSysDto.setExtra(formatGson.toJson(taskExtraDto));


        //短信模板
        smsTemplate.setType(messageBizType.getSmsType());
        smsTemplate.setIsVoice(1);

        if(toUserList.size()>0){
            //im推送
            imMsgSender.sendSysPush(imSysDto);

            //发短信
            jetfireMsgSender.sendSms(smsTemplate);
        }
    }



    /**
     * 司机上报位置到redis
     * @param lat
     * @param lng
     * @param address
     * @param outDestinationId
     */
    private void driverGpsToRes(Double lat,Double lng,String address,String entId,String outDestinationId){
        EntStarSoftEntity entSatrSoft = entStarSoftDao.getByStar(entId);
        if(null != entSatrSoft){
            if(entSatrSoft.getDriverGps().equals(ConstantUtil.INTEGER_CODE_ONE)){
                String key = ConstantUtil.Driver_Location_Res+entSatrSoft.getStarsoftId();
                DriverGpsResDto driverGpsResDto = new DriverGpsResDto();
                driverGpsResDto.setId(RandomTool.getGUId());
                driverGpsResDto.setOutDestinationId(outDestinationId);
                driverGpsResDto.setLat(lat);
                driverGpsResDto.setLng(lng);
                driverGpsResDto.setAddress(address);
                List<DriverGpsResDto> driverGpsList = redisCacheManager.getRedisCache(key, CacheDBType.QueueDB);
                if (null != driverGpsList && driverGpsList.size() > 0) {
                    driverGpsList = driverGpsList.parallelStream().filter(p -> !outDestinationId.equals(p.getOutDestinationId()))
                            .collect(Collectors.toList());
                    driverGpsList.add(driverGpsResDto);
                    redisCacheManager.setRedisCache(key, driverGpsList, CacheDBType.QueueDB);
                }
                else{
                    driverGpsList = new ArrayList<>();
                    driverGpsList.add(driverGpsResDto);
                    redisCacheManager.setRedisCache(key, driverGpsList, CacheDBType.QueueDB);
                }
                logger.info(">>>>>>>>>>>司机位置添加到redis>>>>>>>>>>>>>>>>>>driverGpsResDto:"+driverGpsResDto);
            }
        }

    }




}
