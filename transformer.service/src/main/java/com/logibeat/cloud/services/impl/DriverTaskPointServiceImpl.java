package com.logibeat.cloud.services.impl;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.google.gson.Gson;
import com.logibeat.cloud.common.enumtype.*;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.dto.task.CreateDriverTaskDto;
import com.logibeat.cloud.dto.task.DriverTaskPointDto;
import com.logibeat.cloud.errorenum.DriverTaskErrorEnums;
import com.logibeat.cloud.msg.sender.JetfireMsgSender;
import com.logibeat.cloud.persistent.dao.DriverTaskDao;
import com.logibeat.cloud.persistent.dao.DriverTaskPointDao;
import com.logibeat.cloud.persistent.dao.SyspersonDao;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.persistent.entity.DriverTaskPointEntity;
import com.logibeat.cloud.persistent.entity.SystemPersonEntity;
import com.logibeat.cloud.services.DriverTaskPointService;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.vo.task.TaskPointButtonVo;
import com.logibeat.cloud.vo.task.TaskPointVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverTaskPointServiceImpl implements DriverTaskPointService {


    @Autowired
    private DriverTaskPointDao taskPointDao;

    @Autowired
    private DriverTaskDao driverTaskDao;

    @Autowired
    private SyspersonDao syspersonDao;

    @Autowired
    private JetfireMsgSender jetfireMsgSender;



    @NacosValue(value = "${entWebsite.mp.url}", autoRefreshed = true)
    private String url;

    /**
     * 保存装卸点
     * @param createDriverTaskDto
     * @param driverTask
     */
    @Override
    public void saveTaskPoint(CreateDriverTaskDto createDriverTaskDto, DriverTaskEntity driverTask){
        //任务装卸点
        List<DriverTaskPointDto> taskPointList  = createDriverTaskDto.getTaskPointList();
        //线路类型
        Integer pointType = driverTask.getPointType();
        Integer sort = 0;
        for(DriverTaskPointDto driverTaskPointDto : taskPointList){
            sort++;
            DriverTaskPointEntity taskPoint = new DriverTaskPointEntity();
            taskPoint.setGuid(RandomTool.getGUId());
            taskPoint.setTaskId(driverTask.getGuid());
            taskPoint.setSort(sort);
            taskPoint.setType(driverTaskPointDto.getType());
            taskPoint.setNetWorkId(driverTaskPointDto.getNetWorkId());
            taskPoint.setName(driverTaskPointDto.getName());
            taskPoint.setAddress(driverTaskPointDto.getAddress());
            taskPoint.setCityCode(driverTaskPointDto.getCityCode());
            taskPoint.setCityName(driverTaskPointDto.getCityName());
            taskPoint.setLat(driverTaskPointDto.getLat());
            taskPoint.setLng(driverTaskPointDto.getLng());
            taskPoint.setOrderType(driverTaskPointDto.getOrderType());
            taskPoint.setOrderGuid(driverTaskPointDto.getOrderId());
            taskPoint.setContact(driverTaskPointDto.getContact());
            taskPoint.setContactMobile(driverTaskPointDto.getContactMobile());

            //标记起点、终点
            if(sort == 1){
                taskPoint.setStartPoint(ConstantUtil.INTEGER_CODE_ONE);
            }
            else if(sort == taskPointList.size()){
                taskPoint.setEndPoint(ConstantUtil.INTEGER_CODE_ONE);
            }
            //根据装卸 初始化点的状态
            if(LoadType.Load.getValue().equals(driverTaskPointDto.getType())){
                taskPoint.setStatus(TaskCurrentStatus.WaitLoadDepart.getValue());
                taskPoint.setStatusValue(TaskCurrentStatus.WaitLoadDepart.getDescription());
            }
            else{
                taskPoint.setStatus(TaskCurrentStatus.WaitUnLoadDepart.getValue());
                taskPoint.setStatusValue(TaskCurrentStatus.WaitUnLoadDepart.getDescription());
            }

            //指定线路 第一个点标记为可工作
            if(PointType.Fixed.getValue().equals(pointType)){
                if(sort == 1){
                    taskPoint.setWork(ConstantUtil.INTEGER_CODE_ONE);
                }
                else{
                    taskPoint.setWork(ConstantUtil.INTEGER_CODE_ZERO);
                }
            }
            // 推荐线路 所有装货点标记为可工作
            else if(PointType.Change.getValue().equals(pointType)){
                if(LoadType.Load.getValue().equals(driverTaskPointDto.getType())){
                    taskPoint.setWork(ConstantUtil.INTEGER_CODE_ONE);
                }
            }

            //目前针对的是星软的预约单 和发货单
            if(null != driverTaskPointDto.getCurrentStatus()){
                taskPoint.setStatus(driverTaskPointDto.getCurrentStatus());
                taskPoint.setStatusValue(TaskCurrentStatus.find(driverTaskPointDto.getCurrentStatus()).getDescription());
                //更新
                if(TaskType.Concrete_YUYUE.getValue().equals(driverTask.getTaskType())){
                    driverTask.setWorkPointId(taskPoint.getGuid());
                    driverTaskDao.updateDriverTask(driverTask);
                }
                else{
                    if(sort == taskPointList.size()){
                        taskPoint.setWork(ConstantUtil.INTEGER_CODE_ONE);
                        driverTask.setWorkPointId(taskPoint.getGuid());
                        driverTaskDao.updateDriverTask(driverTask);
                    }
                }
            }
            //更新
//            if(TaskType.Concrete_YUYUE.getValue().equals(driverTask.getTaskType())){
//                driverTask.setWorkPointId(taskPoint.getGuid());
//                driverTaskDao.updateDriverTask(driverTask);
//            }
//            else{
//                if(sort == taskPointList.size()){
//                    taskPoint.setWork(ConstantUtil.INTEGER_CODE_ONE);
//                    driverTask.setWorkPointId(taskPoint.getGuid());
//                    driverTaskDao.updateDriverTask(driverTask);
//                }
//            }
            taskPoint.setCreateTime(DateUtil.getSqlTime());
            taskPointDao.insertDriverTaskPoint(taskPoint);
        }


    }


    /**
     * 当前工作的装卸点
     * @param taskId
     * @return
     */
    @Override
    public TaskPointVo workPoint(String taskId){
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        TaskPointVo taskPointVo = null;
        DriverTaskPointEntity workPoint = null;
        TaskCurrentStatus taskCurrentStatus = null;
        if(null == driverTask.getStartTime()){
            DriverTaskPointEntity taskPointParam = new DriverTaskPointEntity();
            taskPointParam.setTaskId(taskId);
            taskPointParam.setSign(null);
            List<DriverTaskPointEntity> pointList = taskPointDao.selectDriverTaskPointList(taskPointParam);
            workPoint = pointList.get(0);
            taskCurrentStatus = TaskCurrentStatus.WaitLoadDepart;
        }
        else{
            String pointId = driverTask.getWorkPointId();
            workPoint = taskPointDao.selectDriverTaskPointById(pointId);
            taskCurrentStatus = TaskCurrentStatus.find(workPoint.getStatus());
        }
        taskPointVo = assemblePointVo(workPoint);
        if(null != workPoint.getActualArriveTime()){
            taskPointVo.setArrive(true);
            taskPointVo.setActualWorkTime(workPoint.getActualRuningDuration());
            taskPointVo.setActualMileage(workPoint.getActualRuningMileage());
        }
        //商砼预约单做特殊处理
        TaskPointButtonVo taskPointButtonVo = new TaskPointButtonVo();
        if(!TaskType.Concrete_YUYUE.getValue().equals(driverTask.getTaskType())){
            taskPointButtonVo = assembleButtonVo(workPoint,driverTask,true);
        }
        if(TaskStatus.Runing.getValue().equals(driverTask.getTaskStatus())){
            taskPointButtonVo.setReportEventButton(true);
        }
        //根据装卸类型判断 联系发货人/收货人
        LoadType loadType = LoadType.find(workPoint.getType());
        switch (loadType){
            case Load:
                taskPointButtonVo.setContactSendButton(true);
                break;
            case UnLoad:
                taskPointButtonVo.setContactRecieveButton(true);
                break;
        }
        taskPointVo.setButton(taskPointButtonVo);
        if(TaskType.InviteCar.getValue().equals(driverTask.getTaskType())){
            if(TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus())){
                taskCurrentStatus = TaskCurrentStatus.Finish;
                taskPointVo.setFinish(true);
                taskPointVo.setFinishTime(workPoint.getActualFinishTime());
            }
        }
        else{
            if(TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus()) &&
                    TaskCurrentStatus.FinishUnLoad.getValue().equals(taskCurrentStatus.getValue())){
                taskCurrentStatus = TaskCurrentStatus.Finish;
                taskPointVo.setFinish(true);
                taskPointVo.setFinishTime(workPoint.getActualFinishTime());
            }
        }
        if(null != taskCurrentStatus){
            taskPointVo.setTip(taskCurrentStatus.getRemark());
        }
        return  taskPointVo;
    }


    /**
     * 获取任务装卸点
     * @param taskId
     * @return
     */
    @Override
   public List<TaskPointVo> getPointList(String taskId){
        List<TaskPointVo> resuliList = new ArrayList<>();
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
           throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        //派单员
        SystemPersonEntity person = syspersonDao.select(driverTask.getPersonId());
        DriverTaskPointEntity taskPointParam = new DriverTaskPointEntity();
        taskPointParam.setTaskId(taskId);
        taskPointParam.setSign(null);
        List<DriverTaskPointEntity> pointList = taskPointDao.selectDriverTaskPointList(taskPointParam);
        for(DriverTaskPointEntity driverTaskPoint : pointList){
            TaskPointVo taskPointVo = assemblePointVo(driverTaskPoint);
            if(null != person){
                taskPointVo.setEntrustPersonMobile(person.getLoginName());
            }
            if(null != driverTaskPoint.getActualArriveTime()){
                taskPointVo.setArrive(true);
                taskPointVo.setActualWorkTime(driverTaskPoint.getActualRuningDuration());
                taskPointVo.setActualMileage(driverTaskPoint.getActualRuningMileage());
            }
            //按钮信息
            TaskPointButtonVo taskPointButtonVo = new TaskPointButtonVo();
            //商砼预约单做特殊处理
            if(!TaskType.Concrete_YUYUE.getValue().equals(driverTask.getTaskType())){
                //未完成
                if(null == driverTaskPoint.getActualFinishTime()){
                    taskPointButtonVo = assembleButtonVo(driverTaskPoint,driverTask,false);
                }
                //已完成
                else{
                    if(LoadType.UnLoad.getValue().equals(driverTaskPoint.getType())){
                        taskPointButtonVo.setSignButton(showSign(driverTaskPoint,driverTask));
                    }
                    taskPointButtonVo.setGoHereButton(false);
                    taskPointVo.setFinish(true);
                }
                //根据装卸类型判断 联系发货人/收货人
                LoadType loadType = LoadType.find(driverTaskPoint.getType());
                switch (loadType){
                    case Load:
                        taskPointButtonVo.setContactSendButton(true);
                        break;
                    case UnLoad:
                        taskPointButtonVo.setContactRecieveButton(true);
                        break;
                }
            }
            if(TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus())){
                taskPointButtonVo.setGoHereButton(false);
            }

            //已签收 则展示签收详情按钮
            if(ConstantUtil.INTEGER_CODE_ONE.equals(driverTaskPoint.getSign()) && LoadType.UnLoad.getValue().equals(driverTaskPoint.getType())){
                taskPointButtonVo.setSignDetailButton(true);
                taskPointVo.setSignConsignId(driverTaskPoint.getOrderGuid());
            }
            taskPointVo.setButton(taskPointButtonVo);
            //需要签收的托运单id
            if(taskPointButtonVo.isSignButton()){
                taskPointVo.setSignConsignId(driverTaskPoint.getOrderGuid());
            }
            resuliList.add(taskPointVo);
        }
        return resuliList;

   }


    /**
     * 封装结果
     * @param taskPoint
     * @return
     */
   private TaskPointVo assemblePointVo(DriverTaskPointEntity taskPoint){
       TaskPointVo taskPointVo = new TaskPointVo();
       taskPointVo.setPointId(taskPoint.getGuid());
       taskPointVo.setName(taskPoint.getName());
       taskPointVo.setType(taskPoint.getType());
       taskPointVo.setCity(taskPoint.getCityCode());
       taskPointVo.setAddress(taskPoint.getAddress());
       taskPointVo.setLat(taskPoint.getLat());
       taskPointVo.setLng(taskPoint.getLng());
       taskPointVo.setContact(taskPoint.getContact());
       taskPointVo.setContactMobile(taskPoint.getContactMobile());
       taskPointVo.setDepartTime(taskPoint.getActualDepartTime());
       taskPointVo.setArriveTime(taskPoint.getActualArriveTime());
       taskPointVo.setFinishTime(taskPoint.getActualFinishTime());
       if(ConstantUtil.INTEGER_CODE_ONE.equals(taskPoint.getSign()) && LoadType.UnLoad.getValue().equals(taskPoint.getType())){
           taskPointVo.setSign(true);
       }
       return  taskPointVo;
   }


    /**
     * 封装按钮
     * @param taskPoint
     * @return
     */
   private TaskPointButtonVo assembleButtonVo(DriverTaskPointEntity taskPoint,DriverTaskEntity driverTask,boolean workPoint){
       TaskPointButtonVo taskPointButtonVo = new TaskPointButtonVo();
       //是否可工作
       Integer work = taskPoint.getWork();
       //状态
       Integer status = taskPoint.getStatus();
       //可工作装卸点的按钮判断
       if(ConstantUtil.INTEGER_CODE_ONE.equals(work)){
           TaskCurrentStatus taskCurrentStatus = TaskCurrentStatus.find(status);
           switch (taskCurrentStatus){
               //去装货
               case WaitLoadDepart:
                   taskPointButtonVo.setGoLoadButton(true);
                   break;
               //到达装货点
               case GoLoad:
                   taskPointButtonVo.setArriveButton(true);
                   break;
               //开始装货/完成装货
               case ArriveLoad:
                   if(!TaskStatus.Finish.getValue().equals(driverTask.getTaskStatus())){
                       taskPointButtonVo.setStartLoadButton(true);
                       if(!workPoint){
                           taskPointButtonVo.setFinishLoadButton(true);
                       }
                   }
                   break;
               //完成装货
               case StartLoad:
                   taskPointButtonVo.setFinishLoadButton(true);
                   taskPointButtonVo.setGoHereButton(false);
                   break;
               //去卸货
               case WaitUnLoadDepart:
                   taskPointButtonVo.setGoUnloadButton(true);
                   break;
               //到达卸货点
               case GoUnLoad:
                   taskPointButtonVo.setArriveButton(true);
                   break;
               //开始卸货/完成卸货
               case ArriveUnLoad:
                   taskPointButtonVo.setSignButton(showSign(taskPoint,driverTask));
                   taskPointButtonVo.setStartUnloadButton(true);
                   if(!workPoint){
                       taskPointButtonVo.setFinishUnloadButton(true);
                   }
                   break;
               //开始卸货
               case StartUnLoad:
                   taskPointButtonVo.setSignButton(showSign(taskPoint,driverTask));
                   taskPointButtonVo.setFinishUnloadButton(true);
                   break;
               case FinishUnLoad:
                   taskPointButtonVo.setSignButton(showSign(taskPoint,driverTask));
                   taskPointButtonVo.setGoHereButton(false);
                   break;
           }
       }
       return taskPointButtonVo;
   }


    /**
     * 签收
     * @param consignOrderId
     */
    @Override
    public void sign(String consignOrderId){
        taskPointDao.sign(consignOrderId);
    }



    /**
     * 判断是否展示签收按钮
     * @param taskPoint
     * @param driverTask
     * @return
     */
    @Override
   public boolean showSign(DriverTaskPointEntity taskPoint,DriverTaskEntity driverTask){
       boolean signButton = false;
       Integer taskType = driverTask.getTaskType();
       //未签收情况下  才去判断是否展示签收按钮
       if(taskPoint.getSign().equals(ConstantUtil.INTEGER_CODE_ZERO) ){
           //派送单派车显示签收按钮
           if(TaskType.Deliery.getValue().equals(taskType) || TaskType.Concrete_FAHUO.getValue().equals(taskType)){
               signButton = true;
           }
           //整车托运单派车 如果是最后一个点则有签收
//           else if(TaskType.Consign.getValue().equals(taskType)){
//               if(ConstantUtil.INTEGER_CODE_ONE.equals(taskPoint.getEndPoint())){
//                   signButton = true;
//               }
//           }
           //整车拼装后派车  托运单派车  判断是否其他的所有卸货点都完成卸货了  若完成并且是该托运单的最后一个卸货点 则展示
           else if(TaskType.Assemble.getValue().equals(taskType) || TaskType.Consign.getValue().equals(taskType)){
               Long count = taskPointDao.finishUnloadCount(driverTask.getGuid(),taskPoint.getOrderGuid(),taskPoint.getGuid());
               if(count == 0){
                   signButton = true;
               }
           }
       }
       return  signButton;
   }




    /**
     * 待签收
     * @param taskId
     * @return
     */
    @Override
    public List<TaskPointVo> getWaitSignList(String taskId){
        Gson gson = new Gson();
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        DriverTaskPointEntity pointParam = new DriverTaskPointEntity();
        pointParam.setTaskId(taskId);
        pointParam.setDelete(0);
        pointParam.setSign(0);
        pointParam.setType(LoadType.UnLoad.getValue());
        List<DriverTaskPointEntity> pointList = taskPointDao.selectDriverTaskPointList(pointParam);
        List<String> orderIdList = pointList.parallelStream().map(DriverTaskPointEntity :: getOrderGuid).distinct().collect(Collectors.toList());
        List<DriverTaskPointEntity> resultList = new ArrayList<>();
        for(String orderId : orderIdList){
            List<DriverTaskPointEntity> orderPointList = pointList.parallelStream().filter(p -> orderId.equals(p.getOrderGuid())).collect(Collectors.toList());
            Integer count = orderPointList.size();
            Long arriveCount = pointList.parallelStream().filter(p -> orderId.equals(p.getOrderGuid())   &&  null != p.getActualArriveTime() ).count();
            if(count.equals(arriveCount.intValue())){
                resultList.addAll(orderPointList);
            }
        }
        List<TaskPointVo> waitSignPointVoList = new ArrayList<>();
        for(DriverTaskPointEntity driverTaskPoint : resultList){
            TaskPointVo taskPointVo = assemblePointVo(driverTaskPoint);
            taskPointVo.setSignConsignId(driverTaskPoint.getOrderGuid());
            //二维码url
            StringBuffer codeUrlBuffer =  new StringBuffer(url);
            codeUrlBuffer.append("webPages/entRouter.html?");



            codeUrlBuffer.append("entId="+driverTask.getEntId());
            //派单企业、人员信息
//            if(StringUtils.isNotBlank(driverTask.getEntrustInfo())){
//                EntrustInfoDto entrustInfoDto = gson.fromJson(driverTask.getEntrustInfo(),EntrustInfoDto.class);
//                codeUrlBuffer.append("&entName="+entrustInfoDto.getEntName());
//            }
            codeUrlBuffer.append("&orderId="+driverTaskPoint.getOrderGuid());
            codeUrlBuffer.append("&type=2");

            String signCodeUrl = codeUrlBuffer.toString();

            String urlPid = jetfireMsgSender.getShortUrl(signCodeUrl);

            String codeUrl = url+"webPages/entRouter.html?pId="+urlPid;

            //短链接
            taskPointVo.setSignCodeUrl(codeUrl);
            waitSignPointVoList.add(taskPointVo);
        }
        return waitSignPointVoList;
    }
}
