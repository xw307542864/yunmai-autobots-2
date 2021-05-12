package com.logibeat.cloud.services.impl;

import com.google.gson.Gson;
import com.logibeat.cloud.common.cache.RedisCache;
import com.logibeat.cloud.common.cache.enumType.CacheDBType;
import com.logibeat.cloud.common.enumtype.*;
import com.logibeat.cloud.common.valide.TValide;
import com.logibeat.cloud.common.vo.*;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.constant.RegionCodeUtil;
import com.logibeat.cloud.core.dto.*;
import com.logibeat.cloud.core.properties.CommonProperties;
import com.logibeat.cloud.dto.DynamicDto;
import com.logibeat.cloud.dto.SendCarDTO;
import com.logibeat.cloud.errorenum.DynamicErrorEnums;
import com.logibeat.cloud.errorenum.TaskErrorEnums;
import com.logibeat.cloud.errorenum.UserErrorEnums;
import com.logibeat.cloud.helper.EventSpaceServiceHelper;
import com.logibeat.cloud.helper.HeartBeatServiceHelper;
import com.logibeat.cloud.helper.OutServiceHelper;
import com.logibeat.cloud.msg.sender.JetfireMsgSender;
import com.logibeat.cloud.msg.template.FinishTaskPushTemplate;
import com.logibeat.cloud.msg.template.NewTaskPushTemplate;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.redis.CarListTac;
import com.logibeat.cloud.redis.CoopcarListTac;
import com.logibeat.cloud.remote.CarSender;
import com.logibeat.cloud.remote.OperationTimeSender;
import com.logibeat.cloud.services.DynamicService;
import com.logibeat.cloud.services.FileService;
import com.logibeat.cloud.services.TaskService;
import com.logibeat.cloud.util.tools.*;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.vo.RouteAreaVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author sean
 * @version 1.0
 * @ClassName: TaskServiceImpl
 * @Description: 任务服务实现类
 * @date 2016年1月4日 上午9:41:27
 */
@Service
public class TaskServiceImpl implements TaskService {

	private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);


	@Autowired
    private TaskOrdersCarDao taskOrdersCarDao;

	@Autowired
    private TaskOrdersAreaDao taskOrdersAreaDao;

	@Autowired
	protected TaskOrdersGoodsDao taskOrdersGoodsDao;

	@Autowired
	private TaskOrdersDao taskOrdersDao;

	@Autowired
	protected TaskDynamicDao taskDynamicDao;

	@Autowired
	protected EnterpriseInfoDao enterpriseInfoEntityDao;

	@Autowired
	protected CarsDao carsDao;

	@Autowired
	protected SyspersonDao userDao;

	@Autowired
	protected FileDao fileDao;

	@Autowired
	protected FileService fileService;

	@Autowired
	protected TaskDynamicDao perdynamicDao;

	@Autowired
	protected NetworkDao networkDao;

	@Autowired
	private EnterpriseCooperatePerDao enterpriseCooperatePerDao;

	@Autowired
	protected DynamicService dynamicService;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private EnterpriseCooperateCarDao enterpriseCooperateCarDao;

	@Autowired
	private EntClassLineDao entClassLineDao;

	@Autowired
	protected EntClassLineNetworkDao entClassLineNetworkDao;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private CoopcarListTac coopcarListTac;

	@Autowired
	private CarListTac carListTac;

	@Autowired
	private EnterpriseInfoDao enterpriseInfoDao;

	@Autowired
	private DictDao dictDao;

	@Autowired
	private RegionDao regionDao;

	@Autowired
	private SysSettingDao sysSettingDao;

	@Autowired
	private TaskOrdersCarInfoDao taskOrdersCarInfoDao;

	@Autowired
	private EventSpaceServiceHelper eventSpaceServiceHelper;

	@Autowired
	private CarOperationTimeDao carOperationTimeDao;

	@Autowired
	private HeartBeatServiceHelper heartBeatServiceHelper;

	@Autowired
	private JetfireMsgSender jetfireMsgSender;

	@Autowired
	private OperationTimeSender operationTimeSender;

	@Autowired
	private CarSender carSender;

	@Autowired
	private OutServiceHelper outServiceHelper;
	
	@Autowired
    private EnterpriseSettingDao enterpriseSettingDao;



	/**
	 * 获取订单的在途详情
	 */
	@Override
	public OrdersEventInfoVo getRunDetail(String orderCarId, String allOrLast) {
		OrdersEventInfoVo ordersEventInfoVo = new OrdersEventInfoVo();
		List<EventDetailVo> eventDetailVoList = new ArrayList<EventDetailVo>();
		// 订单主体
		// TaskOrdersEntity taskOrdersEntity = taskOrdersDao.find(orderId);
		TaskOrdersCarEntity taskOrdersCarEntity = taskOrdersCarDao.select(orderCarId);
		TValide.notNull(taskOrdersCarEntity, TaskErrorEnums.TaskErrors.SEND_ORDER_INFO_IS_EXCEPTION);

		String orderId = taskOrdersCarEntity.getOrdersCID();
		List<TaskDynamicEntity> resultList = taskDynamicDao.getRunDynamicList(orderId, allOrLast);
		for (TaskDynamicEntity taskDynamicEntity : resultList) {
			EventDetailVo eventDetailVo = new EventDetailVo();
			eventDetailVo.setAddress(taskDynamicEntity.getAddress());
			eventDetailVo.setEventAction(taskDynamicEntity.getAction());
			eventDetailVo.setEventGUID(taskDynamicEntity.getGuid());
			eventDetailVo.setEventTime(taskDynamicEntity.getDynamictime());
			eventDetailVo.setLat(taskDynamicEntity.getLat());
			eventDetailVo.setLng(taskDynamicEntity.getLng());
			eventDetailVo.setTextContent(taskDynamicEntity.getContent());
			eventDetailVo.setIsAtPoint(TypeCastUtil.byteToBoolean(taskDynamicEntity.getIsAtPoint()));
			if (org.apache.commons.lang.StringUtils.isNotBlank(taskDynamicEntity.getAreaGuid())) {
				TaskOrdersAreaEntity taskOrdersAreaEntity = taskOrdersAreaDao.select(taskDynamicEntity.getAreaGuid());
				if (null != taskOrdersAreaEntity) {
					eventDetailVo.setAreaName(taskOrdersAreaEntity.getName());
				}
			}

			if (StringUtils.isNotBlank(taskDynamicEntity.getPicUrls())) {
				eventDetailVo.setPics(taskDynamicEntity.getPicUrls());
			} else {
				List<FileEntity> fileList = fileService.getFileList(taskDynamicEntity.getGuid(),
						FileType.IMAGEFILE.getCode(), taskDynamicEntity.getType());
				String pics = fileService.getPicFullPath(fileList, ",");
				eventDetailVo.setPics(pics);
			}
			eventDetailVoList.add(eventDetailVo);
		}
		ordersEventInfoVo.setEventDetailList(eventDetailVoList);
		ordersEventInfoVo.setOrdersCID(orderId);
		ordersEventInfoVo
				.setStartAreaActualLeavTime(DateUtil.dateToTimestamp(taskOrdersCarEntity.getActualLeaveTime()));
		ordersEventInfoVo
				.setEndAreaActualArriveTime(DateUtil.dateToTimestamp(taskOrdersCarEntity.getActualArriveTime()));
		ordersEventInfoVo.setOrdersStatus(taskOrdersCarEntity.getOrdersStatus());
		return ordersEventInfoVo;
	}


	/**
	 * 删除行程
	 *
	 * @param orderId
	 * @return
	 */
	@Override
	public void delRoute(String orderId) {
		TValide.notNull(orderId, TaskErrorEnums.TaskErrors.PARAMS_IS_NOT_NULL);
		delTask(orderId);
		List<TaskDynamicEntity> taskDynamicEntityList = taskDynamicDao.findByOrderAsDynamic(orderId);
		for (TaskDynamicEntity taskDynamicEntity : taskDynamicEntityList) {
			 taskDynamicDao.delete(taskDynamicEntity.getGuid());
		}
//		grimlockServiceHelper.removeEvent(orderId);
	}

	/**
	 * 创建任务
	 *
	 * @param taskDto
	 * @return
	 */
	public void createTask(TaskDto taskDto) {
		Map<String, Object> map = new HashMap<>();
		log.warn("开始获取id为{}的司机创建行程", taskDto.getBaseUserId());
		//发行程发车时间
		Timestamp startAreaActualLeaveTime = DateUtil.getSqlTime();
		// 任务起始点ID
		String startAreaGUID;
		// 任务结束点ID
		String endAreaGUID;
		// 任务起始网点编码
		String startNetworkCode;
		// 任务结束网点编码
		String endNetworkCode;
		// 任务起始网点名称
		String startAreaName;
		// 任务结束网点名称
		String endAreaName;
		// 任务起始城市
		String startRegionCode;
		// 任务结束城市
		String endRegionCode;
		// 产生订单号
		String orderCid = RandomTool.getGUId();
		// 当前司机ID
		String personId = taskDto.getPersonId();
		//司机姓名
		String firstPersonName;
		//司机手机号
		String firstPersonMobile;
		//企业ID
		String entId = taskDto.getEntId();
		//车辆ID
		String carId = taskDto.getCarId();
		// 车牌号
		String plateNumber = taskDto.getPlateNumber();

		// 是否推送到星软
		boolean isPush = true;
		// 是否企业自有司机
		boolean isSelfDriver = false;
		// 图片地址
		String picUrls = taskDto.getPicUrls();

		// 判断是否有进行中的行程
		Long taskNum = taskOrdersDao.getUnFinishedOrderNum(taskDto.getPersonId());
		if (taskNum > 0) {
			throw new ServiceException(TaskErrorEnums.TaskErrors.TASK_UNFINISH_CANT_CREATE);
		}
		// 当前司机信息
		SystemPersonEntity person = userDao.select(personId);
		if(null == taskDto.getAreaList()){
			throw  new ServiceException(TaskErrorEnums.TaskErrors.PARAMS_IS_NOT_NULL);
		}
		if(StringUtils.isBlank(personId)){
			throw  new ServiceException(UserErrorEnums.UserErrors.USER_NOT_EXIST);
		}
		if(null == person){
			throw  new ServiceException(UserErrorEnums.UserErrors.USER_NOT_EXIST);
		}
		if(StringUtils.isBlank(entId)){
			throw  new ServiceException(UserErrorEnums.UserErrors.ENT_NOT_EXIST);
		}
		if(StringUtils.isBlank(carId)){
			EnterpriseCooperateCarEntity entCar  = enterpriseCooperateCarDao.getCarsByEntIdAndPersonId(entId, personId);
			firstPersonName = entCar.getFirstDriverName();
			firstPersonMobile = entCar.getFirstDriverMobile();
		}
		else{
			firstPersonName = person.getNiChen();
			firstPersonMobile = person.getLoginName();
		}
		// 添加任务网点
		log.warn("id为{}的司机，订单号是{}添加任务网点", taskDto.getBaseUserId(), orderCid);
		RouteAreaVo routeAreaVo = addTaskArea(taskDto.getAreaList(), taskDto.getEntId(), orderCid, personId, isPush);
		startAreaGUID = routeAreaVo.getStartAreaGUID();
		endAreaGUID = routeAreaVo.getEndAreaGUID();
		startNetworkCode = routeAreaVo.getStartNetworkCode();
		endNetworkCode = routeAreaVo.getEndNetworkCode();
		startAreaName = routeAreaVo.getStartAreaName();
		endAreaName = routeAreaVo.getEndAreaName();
		startRegionCode = routeAreaVo.getStartRegionCode();
		endRegionCode = routeAreaVo.getEndRegionCode();
		List<TaskOrdersAreaEntity> areaResultList = routeAreaVo.getAreaList();


		//创建任务
		log.warn("id为{}的司机，添加订单", taskDto.getBaseUserId());
		Timestamp endAreaPlanArriveTime = null;
		Timestamp startPlanLeavTime = StringConverUtil.getTimestamp(taskDto.getStartTime());
		String startTime = taskDto.getStartTime();
		if (StringUtils.isBlank(startTime)) {
			startPlanLeavTime = DateUtil.getSqlTime();
		}
		if (StringUtils.isNotBlank(taskDto.getEndTime())) {
			endAreaPlanArriveTime = StringConverUtil.getTimestamp(taskDto.getEndTime());
		}
		CreateTaskDTO createTaskDTO = new CreateTaskDTO();
		createTaskDTO.setOrderCid(orderCid);
		createTaskDTO.setStartAreaPlanLeavTime(startPlanLeavTime);
		createTaskDTO.setEndAreaPlanArriveTime(endAreaPlanArriveTime);
		createTaskDTO.setOrdersRemark(null);
		createTaskDTO.setOrderStatus(OrdersStatus.Runing.getValue());
		createTaskDTO.setOrderType(OrdersType.CreateRoute.getValue());
		createTaskDTO.setOriginalcid(orderCid);
		createTaskDTO.setParentOrdersCid(orderCid);
		createTaskDTO.setEntId(taskDto.getEntId());
		createTaskDTO.setEntrustState(ConstantUtil.FALSE);
		createTaskDTO.setIsCarOrders(CommonProperties.BYTE_ZERO);
		createTaskDTO.setStartAreaGUID(startAreaGUID);
		createTaskDTO.setStartAreaCode(startNetworkCode);
		createTaskDTO.setEndAreaCode(endNetworkCode);
		createTaskDTO.setStartAreaName(startAreaName);
		createTaskDTO.setEndAreaName(endAreaName);
		createTaskDTO.setEndAreaGUID(endAreaGUID);
		createTaskDTO.setStartRegionCode(startRegionCode);
		createTaskDTO.setEndRegionCode(endRegionCode);
		createTaskDTO.setDuration(null);
		createTaskDTO.setExpectsMileage(null);
		createTaskDTO.setCarId(carId);
		createTaskDTO.setPlateNumber(plateNumber);
		createTaskDTO.setFirstPersonId(personId);
		createTaskDTO.setFirstPersonName(firstPersonName);
		createTaskDTO.setFirstPersonMobile(firstPersonMobile);
		createTaskDTO.setStartAreaActualLeaveTime(startAreaActualLeaveTime);

		createTaskDTO.setIsFirstTask(ConstantUtil.BYTE_TRUE);
		createTaskDTO.setIsEndTask(ConstantUtil.BYTE_FALSE);

		//任务创建者信息
		InfoDto infoDto = new InfoDto();
		infoDto.setPersonId(personId);
		infoDto.setPersonName(firstPersonName);
		infoDto.setPersonMobile(firstPersonMobile);
		createTaskDTO.setCreatePersonInfo(new Gson().toJson(infoDto));

		TaskOrdersEntity taskOrdersEntity = addTaskOrder(personId, createTaskDTO, null);



		log.warn("id为{}的司机，订单添加成功", taskDto.getBaseUserId());

		// 派车
		SendCarDTO sendCarDTO = new SendCarDTO();
		sendCarDTO.setSourcePersonId(personId);
		sendCarDTO.setSourceEntId(taskDto.getEntId());
		sendCarDTO.setPlateNumber(plateNumber);
		sendCarDTO.setFirstDriverID(personId);
		sendCarDTO.setFirstDriverName(firstPersonName);
		sendCarDTO.setFirstDriverMobile(firstPersonMobile);
		sendCarDTO.setOrdersid(orderCid);
		sendCarDTO.setCarID(carId);
		sendCarDTO.setSysAutoDepart(true);
		sendCarDTO.setTaskStatus(OrdersStatus.Runing.getValue());
		sendCarDTO.setTaskType(1);
		sendCarDTO.setStartAreaActualLeaveTime(startAreaActualLeaveTime);
		if(StringUtils.isNotBlank(carId)){
			EnterpriseCooperateCarEntity entCar  = enterpriseCooperateCarDao.getCarsByEntIdAndPersonId(entId, personId);
			sendCarDTO.setOrgId(entCar.getOrgGuid());
		}
		TaskOrdersCarEntity taskCar = sendCar(sendCarDTO);

		taskExecutor.execute(() -> {
			//发车动态
			DynamicDto dynamicDto = dynamicService.preAddDynamic(personId,taskCar,taskDto,startAreaGUID);
			TaskDynamicEntity taskDynamic = dynamicService.addDynamic(dynamicDto);

			// 注册到达的电子围栏
			TaskOrdersAreaEntity targetArea = areaResultList.stream()
					.filter(p -> p.getIsEndPoint().equals(ConstantUtil.BYTE_TRUE)).findFirst().get();
			eventSpaceServiceHelper.registByTarget(targetArea, EventAction.DriverArriveEndArea.getValue(),
					PointState.ARRIVE_250.getValue(), taskCar);

			// 更新车和企业合作关系
			if (StringUtils.isNotBlank(taskDto.getEntId())) {
				enterpriseCooperateCarDao.updateIsFriend(taskDto.getEntId(), carId, (byte) 1);
				enterpriseCooperatePerDao.updateIsFriend(taskDto.getEntId(), personId, null, (byte) 1);
			}
			//更新车辆状态
			carSender.updateCarStatus(taskDto.getEntId(),carId,CarStatus.Running.getValue());
			// 推送行程数据到星软平台
			log.warn("id为{}的司机车辆信息，开始推送到星软平台", taskDto.getBaseUserId());
			if (isPush && isSelfDriver) {
				outServiceHelper.pushTravelToStar(taskCar.getFirstDriverPersonID(), taskCar.getOrdersCID(), plateNumber,
						taskDynamic, startNetworkCode, endNetworkCode, person,
						TypeCastUtil.byteToBoolean(taskDto.getIsAtPoint().byteValue()), picUrls);
			}
		});

		// 任务运行时间段
		operationTimeSender.sendCar(carId, plateNumber, personId, taskCar.getOrdersCID(), DateUtils.getSqlTime());
	}

	/**
	 * 派单给车辆
	 *
	 * @param sendCarDTO
	 * @return
	 */
	public TaskOrdersCarEntity sendCar(SendCarDTO sendCarDTO) {
		String sourceEntId = sendCarDTO.getSourceEntId();
		String sourcePersonId = sendCarDTO.getSourcePersonId();
		String entId = sendCarDTO.getSourceEntId();
		String carId = sendCarDTO.getCarID();
		String firstDriverId = sendCarDTO.getFirstDriverID();
		String firstDriverName = sendCarDTO.getFirstDriverName();
		String firstDriverMobile = sendCarDTO.getFirstDriverMobile();
		String secondDriverId = sendCarDTO.getSecondDriverID();
		String secondDriverName = sendCarDTO.getSecondDriverName();
		String secondDriverMobile = sendCarDTO.getSecondDriverMobile();
		String plateNumber = sendCarDTO.getPlateNumber();
		String orderscid = sendCarDTO.getOrdersid();
		Integer coopType = 0;
		boolean sysAutoCarrier = sendCarDTO.isSysAutoCarrier();
		boolean sysAutoCarrierDepart = sendCarDTO.isSysAutoDepart();
		Integer taskType = sendCarDTO.getTaskType();
		String outType = sendCarDTO.getOutType();

		// 判断必须输入的一些参数
		TValide.notNull(orderscid, TaskErrorEnums.TaskErrors.ORDER_ID_IS_NOT_NULL);
		TaskOrdersEntity taskOrderBean = taskOrdersDao.select(orderscid);
		if (StringUtils.isBlank(plateNumber) && StringUtils.isNotBlank(carId)) {
			CarsEntity carBean = carsDao.select(carId);
			plateNumber = carBean.getPlateNumber();
		}

		if(StringUtils.isNotBlank(outType)){
			//外部接口来源
			sendCarDTO.setIsAutoArrive(sendCarDTO.getIsAutoArrive());
			sendCarDTO.setIsAutoDepart(sendCarDTO.getIsAutoDepart());
		}else{
			//app来源
			String entrustEntId = "";
			//查首单的企业的自动发到设置
			if(taskOrderBean != null && CommonProperties.BYTE_ONE.equals(taskOrderBean.getIsFirstOrders())){
				entrustEntId = taskOrderBean.getEntID();
			}
			else if(taskOrderBean != null && CommonProperties.BYTE_ZERO.equals(taskOrderBean.getIsFirstOrders())){
				TaskOrdersEntity firstOrder = taskOrdersDao.getFirstOrders(taskOrderBean.getOriginalcid());
				if(firstOrder != null){
					entrustEntId = firstOrder.getEntID();
				}
			}
//			EnterpriseInfoEntity firstEnt = enterpriseInfoDao.select(entrustEntId);
			EnterpriseSettingEntity entSetting = enterpriseSettingDao.queryByEntId(entrustEntId);
			if(entSetting != null){
				sendCarDTO.setIsAutoArrive(entSetting.getIsAutoArrival());
				sendCarDTO.setIsAutoDepart(entSetting.getIsAutoSendCar());
			}else{
				sendCarDTO.setIsAutoArrive(CommonProperties.BYTE_ZERO);
				sendCarDTO.setIsAutoDepart(CommonProperties.BYTE_ZERO);
			}
		}

		//taskOrdersExpandDao.getTaskOrdersByoriginalcid(ordersCid)

		// 判断司机是否注册，0：未注册，发短信，
		TValide.notNull(firstDriverId, TaskErrorEnums.TaskErrors.FIRST_DIRVER_IS_NOT_NULL);
		boolean isShareCar = ConstantUtil.FALSE;
		EnterpriseCooperateCarEntity entCar = null;
		if (StringUtils.isNotBlank(carId)) {
			entCar = enterpriseCooperateCarDao.getEntCarByCarId(entId, carId);
			coopType = entCar.getCoopType();
			if (coopType.equals(CoopType.ShareCar.getValue())) {
				isShareCar = ConstantUtil.TRUE;
			}
		}

		// 主驾驶
		if (StringUtils.isBlank(firstDriverName) || StringUtils.isBlank(firstDriverMobile)) {
			EnterpriseCooperatePerEntity firstEntDriver = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, firstDriverId);
			if (null != firstEntDriver && !firstEntDriver.getInviteState().equals(InviteState.Pass.getValue())) {
				throw new ServiceException(TaskErrorEnums.TaskErrors.DRIVER_IS_NOT_SURE_JOIN);
			}
			firstDriverName = firstEntDriver.getNameRemark();
			firstDriverMobile = firstEntDriver.getPhoneNumber();
		}
		// 副驾驶
		if (StringUtils.isNotBlank(secondDriverId)) {
			if (StringUtils.isBlank(secondDriverName) || StringUtils.isBlank(secondDriverMobile)) {
				EnterpriseCooperatePerEntity secondEntDriver = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, secondDriverId);
				if (null != secondEntDriver && !secondEntDriver.getInviteState().equals(InviteState.Pass.getValue())) {
					throw new ServiceException(TaskErrorEnums.TaskErrors.DRIVER_IS_NOT_SURE_JOIN);
				}
				secondDriverName = secondEntDriver.getNameRemark();
				secondDriverMobile = secondEntDriver.getPhoneNumber();
			}
		}

		// 检查订单是否已经取消
		Boolean isCancleEntrust = TypeCastUtil.byteToBoolean(taskOrderBean.getIscancleEntrust());
		if (isCancleEntrust) {
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_CANCLE);
		}
		// 检查是否已经接单
		Integer orderStatus = taskOrderBean.getOrdersStatus();
		if (orderStatus.equals(2)) {
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_NOT_ACCEPT);
		}
		// 检验是否委派
		Long downTaskNum = taskOrdersDao.getDownEntrustOrderCount(orderscid, taskOrderBean.getCreateorder());
		if (downTaskNum != null && !downTaskNum.equals(0L)) {
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_SEND_OR_ENTRUST_ENT);
		}

		if (taskOrderBean != null && null == sendCarDTO.getTaskStatus()) {
			taskOrderBean.setEditTime(DateUtil.getSqlTime());
			// 更新当前订单信息 当前订单状态 待派单
			taskOrderBean.setOrdersStatus(OrdersStatus.WaitRun.getValue());
			taskOrderBean.setEntrustType(ConstantUtil.INTEGER_CODE_TWO);
 			taskOrdersDao.update(taskOrderBean);
		}

		// 创建派车订单
		String newOrdersCid = RandomTool.getGUId();
		// 拼装参数
		CreateTaskDTO createTaskDTO = new CreateTaskDTO();
		createTaskDTO.setOrderCid(newOrdersCid);
		createTaskDTO.setStartAreaPlanLeavTime(taskOrderBean.getStartAreaPlanLeavTime());
		createTaskDTO.setEndAreaPlanArriveTime(taskOrderBean.getEndAreaPlanArriveTime());
		createTaskDTO.setOrdersRemark(taskOrderBean.getOrdersRemark());
		createTaskDTO.setStatue(taskOrderBean.getStatute());
		createTaskDTO.setTaskStatus(sendCarDTO.getTaskStatus());
		if (sysAutoCarrier) {
			createTaskDTO.setOrderStatus(OrdersStatus.WaitRun.getValue());
		} else {
			createTaskDTO.setOrderStatus(OrdersStatus.WaitCarrier.getValue());
		}
		createTaskDTO.setOriginalcid(taskOrderBean.getOriginalcid());
		createTaskDTO.setParentOrdersCid(taskOrderBean.getOrdersCID());
		createTaskDTO.setEntId(entId);
		createTaskDTO.setEntrustState(ConstantUtil.TRUE);
		createTaskDTO.setIsCarOrders(CommonProperties.BYTE_ONE);
		createTaskDTO.setStartAreaGUID(taskOrderBean.getStartAreaGUID());
		createTaskDTO.setEndAreaGUID(taskOrderBean.getEndAreaGUID());
		createTaskDTO.setStartAreaName(taskOrderBean.getStartAreaName());
		createTaskDTO.setEndAreaName(taskOrderBean.getEndAreaName());
		createTaskDTO.setStartAreaCode(taskOrderBean.getStartAreaCode());
		createTaskDTO.setEndAreaCode(taskOrderBean.getEndAreaCode());
		createTaskDTO.setDuration(taskOrderBean.getDuration());
		createTaskDTO.setExpectsMileage(taskOrderBean.getExpectsMileage());
		createTaskDTO.setCreateTime(taskOrderBean.getAddTime());
		createTaskDTO.setTaskStatus(sendCarDTO.getTaskStatus());
		createTaskDTO.setCarId(carId);
		createTaskDTO.setPlateNumber(plateNumber);
		createTaskDTO.setStartRegionCode(taskOrderBean.getStartRegionCode());
		createTaskDTO.setEndRegionCode(taskOrderBean.getEndRegionCode());
		createTaskDTO.setFirstPersonId(firstDriverId);
		createTaskDTO.setFirstPersonMobile(firstDriverMobile);
		createTaskDTO.setFirstPersonName(firstDriverName);
		createTaskDTO.setSecondPersonId(secondDriverId);
		createTaskDTO.setSecondPersonName(secondDriverName);
		createTaskDTO.setSecondPersonMobile(secondDriverMobile);
		createTaskDTO.setOwnOrderId(taskOrderBean.getOwnOrderId());
		createTaskDTO.setOwnType(taskOrderBean.getOwnType());
		createTaskDTO.setStartAreaActualLeaveTime(sendCarDTO.getStartAreaActualLeaveTime());

		createTaskDTO.setIsFirstTask(ConstantUtil.BYTE_FALSE);
		createTaskDTO.setIsEndTask(ConstantUtil.BYTE_TRUE);

		createTaskDTO.setCarrierEntId(sourceEntId);

		//委托人/企业
		createTaskDTO.setEntrustTime(DateUtil.getSqlTime());
		createTaskDTO.setEntrustPersonId(sourcePersonId);
		createTaskDTO.setEntrustEntId(sourceEntId);

		//委托人详细信息
		InfoDto entrustInfoDto  = new InfoDto();
		entrustInfoDto.setPersonId(sourcePersonId);
		entrustInfoDto.setPersonName(firstDriverName);
		entrustInfoDto.setPersonMobile(firstDriverMobile);

		//派单企业信息
		EnterpriseInfoEntity entrustEnt = enterpriseInfoDao.select(sourceEntId);
		entrustInfoDto.setEntId(sourceEntId);
		entrustInfoDto.setEntName(entrustEnt.getName());
		entrustInfoDto.setEntLogo(entrustEnt.getLogo());
		createTaskDTO.setEntrustInfo(new Gson().toJson(entrustInfoDto));

		TaskOrdersEntity driverTaskOrder = addTaskOrder(sourcePersonId, createTaskDTO, sourceEntId);

		// 创建派车对象
		TaskOrdersCarEntity taskCar = createTaskCar(sourceEntId, sourcePersonId, driverTaskOrder, coopType,sendCarDTO);

		List<TaskOrdersEntity> list = taskOrdersDao.getTaskOrdersByOriginalcid(createTaskDTO.getOriginalcid());
		for(TaskOrdersEntity entity : list){
			entity.setCarId(carId);
			entity.setPlateNumber(plateNumber);
			entity.setFirstPersonId(createTaskDTO.getFirstPersonId());
			entity.setFirstPersonMobile(createTaskDTO.getFirstPersonMobile());
			entity.setFirstPersonName(createTaskDTO.getFirstPersonName());
			entity.setSecondPersonId(createTaskDTO.getSecondPersonId());
			entity.setSecondPersonMobile(createTaskDTO.getSecondPersonMobile());
			entity.setSecondPersonName(createTaskDTO.getSecondPersonName());
			entity.setOrdersStatus(createTaskDTO.getTaskStatus()); // createTaskDTO.getOrderStatus()
			entity.setEntrustStatus(createTaskDTO.getTaskStatus());
			taskOrdersDao.update(entity);
		}

		// 设置 企业与司机，车辆isFriend = 1
		if (StringUtils.isNotBlank(firstDriverId)) {
			enterpriseCooperatePerDao.updateIsFriend(entId, firstDriverId, secondDriverId, (byte) 1);
		}
		if (StringUtils.isNotBlank(carId)) {
			enterpriseCooperateCarDao.updateIsFriend(entId, carId, (byte) 1);
		}

		// 更新车辆状态
		if (null != entCar) {
			entCar.setIsFriend((byte) 1);
			enterpriseCooperateCarDao.update(entCar);
			// 1.50更新缓存数据
			coopcarListTac.setCarListReisCachByEntity(ConstantUtil.COOP_CAR_LIST + entCar.getEntid(), entCar);
		}
		List<TaskOrdersEntity> orderList = taskOrdersDao.getOrersByEnd(taskOrderBean.getOriginalcid());

		if(sendCarDTO.getFirstTaskEntity() != null){
			orderList.add(sendCarDTO.getFirstTaskEntity());
		}
		// 添加派车动态
		//dynamicService.addDynamic(carId, orderscid, sourcePersonId, EventAction.OrdersSendCar, sourceEntId,orderList);

		// 添加司机接单
//		if(sendCarDTO.getTaskEntrustEntity() != null){
//			orderList.add(sendCarDTO.getTaskEntrustEntity());
//		}
//		if (sysAutoCarrier) {
//			dynamicService.addDynamic(carId, taskCar.getOrdersCID(), firstDriverId, EventAction.DriverReceiv, null,orderList);
//		}


        String mobile = firstDriverMobile;
        taskExecutor.execute(() -> {
            // 注册电子围栏
            if (!sendCarDTO.isSysAutoDepart()) {
                TaskOrdersAreaEntity targerArea = taskOrdersAreaDao.select(driverTaskOrder.getStartAreaGUID());
                eventSpaceServiceHelper.registByTarget(targerArea, EventAction.DriverDeparting.getValue(), 0, taskCar);
            }

            // 注册心跳事件
//            registHeartBeat(driverTaskOrder, carId, firstDriverId, mobile);


            //发行程不给司机做新任务提醒
            if(!OrdersType.CreateRoute.getValue().equals(taskType)){
                EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(entId);
                String entName = enterpriseInfoEntity == null ? null : enterpriseInfoEntity.getName();
                boolean isSameCity = RegionCodeUtil.isSameCity(taskOrderBean.getStartRegionCode(), taskOrderBean.getEndRegionCode());
                int areaNum = taskOrdersAreaDao.select(driverTaskOrder.getEndAreaGUID()).getSortNum().intValue();
                NewTaskPushTemplate newTaskPushTemplate = new NewTaskPushTemplate(entName, firstDriverId, taskCar.getId(),
                        taskOrderBean.getStartAreaName(), taskOrderBean.getEndAreaName(), isSameCity, areaNum);
                jetfireMsgSender.sendPush(newTaskPushTemplate);
            }
        });
		return taskCar;
	}

	/**
	 * 注册心跳事件
	 *
	 * @param driverTaskOrder
	 * @param carId
	 * @param firstDriverId
	 * @param firstDriverMobile
	 */
	public void registHeartBeat(TaskOrdersEntity driverTaskOrder, String carId, String firstDriverId,
								String firstDriverMobile) {
		// 注册心跳事件
		if (StringUtils.isNotBlank(carId)) {
			List<CarOperationTimeEntity> carOperationTimeEntityList = carOperationTimeDao
					.getAllCarOperationTime(carId);
			if (!carOperationTimeEntityList.isEmpty() && carOperationTimeEntityList.size() > 0) {
				registHeartBeatForCarId(driverTaskOrder, carId, firstDriverId, firstDriverMobile,
						carOperationTimeEntityList, 1);
				registHeartBeatForCarId(driverTaskOrder, carId, firstDriverId, firstDriverMobile,
						carOperationTimeEntityList, 2);
			}
		} else {
			registHeartBeatNotCarId(driverTaskOrder, carId, firstDriverId, firstDriverMobile, 1);
			registHeartBeatNotCarId(driverTaskOrder, carId, firstDriverId, firstDriverMobile, 2);
		}
	}

	/*
	 * 注册心跳事件，有CarId的情况
	 */
	public void registHeartBeatForCarId(TaskOrdersEntity driverTaskOrder, String carId, String firstDriverId,
										String firstDriverMobile, List<CarOperationTimeEntity> carOperationTimeEntityList, int type) {
		if (driverTaskOrder.getIsAuto() != null && driverTaskOrder.getIsAuto() == 1) {
			String exceptionId = RandomTool.getGUId();
			Integer duration = driverTaskOrder.getDuration();
			Timestamp startTime = driverTaskOrder.getStartAreaPlanLeavTime();
			Timestamp endTime = driverTaskOrder.getEndAreaPlanArriveTime();
			for (CarOperationTimeEntity carOperationTimeEntity : carOperationTimeEntityList) {
				HeartBeatDto heartBeatDto = new HeartBeatDto();
				heartBeatDto.setCarId(carId);
				if (startTime != null && (duration != null || duration != 0)) {
					if (type == 1) {
						heartBeatDto.setStartTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(startTime, -30)));
						heartBeatDto.setEndTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(startTime, 30)));
					} else if (type == 2) {
						heartBeatDto.setStartTime(
								DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(startTime, duration - 30)));
						heartBeatDto.setEndTime(
								DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(startTime, duration + 30)));
					}

				} else if (endTime != null) {
					if (type == 1) {
						heartBeatDto
								.setStartTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(new Date(), -30)));
						heartBeatDto.setEndTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(new Date(), 30)));
					} else if (type == 2) {
						heartBeatDto.setStartTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(endTime, -30)));
						heartBeatDto.setEndTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(endTime, 30)));
					}
				}
				heartBeatDto.setTaskId(driverTaskOrder.getOrdersCID());
				heartBeatDto.setType(type);
				heartBeatDto.setKeyId(carOperationTimeEntity.getKeyWord());
				heartBeatDto.setSource(carOperationTimeEntity.getDeviceType());
				heartBeatDto.setExceptionId(exceptionId);
//				heartBeatServiceHelper.regHeartBeatEvent(heartBeatDto);
			}
			heartBeatServiceHelper.regHeartBeatException(driverTaskOrder.getOrdersCID(), firstDriverId, firstDriverMobile,
					exceptionId, type);
		}
	}

	/**
	 * 注册心跳事件，没有传carId的情况
	 */
	public void registHeartBeatNotCarId(TaskOrdersEntity driverTaskOrder, String carId, String firstDriverId,
										String firstDriverMobile, int type) {
		if (driverTaskOrder.getIsAuto() != null && driverTaskOrder.getIsAuto() == 1) {
			String exceptionId = RandomTool.getGUId();
			Integer duration = driverTaskOrder.getDuration();
			Timestamp startTime = driverTaskOrder.getStartAreaPlanLeavTime();
			Timestamp endTime = driverTaskOrder.getEndAreaPlanArriveTime();
			HeartBeatDto heartBeatDto = new HeartBeatDto();
			if (startTime != null && (duration != null || duration != 0)) {
				if (type == 1) {
					heartBeatDto.setStartTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(startTime, -30)));
					heartBeatDto.setEndTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(startTime, 30)));
				} else if (type == 2) {
					heartBeatDto.setStartTime(
							DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(startTime, duration - 30)));
					heartBeatDto
							.setEndTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(startTime, duration + 30)));
				}

			} else if (endTime != null) {
				if (type == 1) {
					heartBeatDto.setStartTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(new Date(), -30)));
					heartBeatDto.setEndTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(new Date(), 30)));
				} else if (type == 2) {
					heartBeatDto.setStartTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(endTime, -30)));
					heartBeatDto.setEndTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddMinutes(endTime, 30)));
				}
			}
			heartBeatDto.setTaskId(driverTaskOrder.getOrdersCID());
			heartBeatDto.setType(type);
			heartBeatDto.setKeyId(firstDriverId);
			heartBeatDto.setSource(0);
			heartBeatDto.setExceptionId(exceptionId);
//			heartBeatServiceHelper.regHeartBeatEvent(heartBeatDto);
			heartBeatServiceHelper.regHeartBeatException(driverTaskOrder.getOrdersCID(), firstDriverId, firstDriverMobile,
					exceptionId, type);

		}
	}

	/**
	 * 生成派车单(兼容现有任务和以后的业务单据)
	 */
	public TaskOrdersCarEntity createTaskCar(String sourceEntId, String sourcePersonId, TaskOrdersEntity taskOrder,
											 Integer coopType,SendCarDTO sendCarDTO) {
		TaskOrdersCarEntity taskCar = new TaskOrdersCarEntity();
		taskCar.setId(taskOrder.getOrdersCID());
		taskCar.setAddTime(DateUtil.getSqlTime());
		taskCar.setCarID(taskOrder.getCarId());
		taskCar.setCode(taskOrder.getCode());
		taskCar.setEntrustPersonID(sourcePersonId);
		taskCar.setEntrustTime(DateUtil.getSqlTime());
		taskCar.setEntrustEntid(taskOrder.getEntID());
		taskCar.setEditTime(DateUtil.getSqlTime());
		taskCar.setOrdersCID(taskOrder.getOrdersCID());
		taskCar.setOriginalcid(taskOrder.getOriginalcid());
		taskCar.setIsReceiv(CommonProperties.BYTE_ZERO);
		taskCar.setState(CommonProperties.BYTE_ZERO);
		taskCar.setIsCancleEntrust(CommonProperties.BYTE_ZERO);
		taskCar.setIsDelete(CommonProperties.BYTE_ZERO);
		taskCar.setPlanLeaveTime(taskOrder.getStartAreaPlanLeavTime());
		taskCar.setPlanArriveTime(taskOrder.getEndAreaPlanArriveTime());
		taskCar.setPlanMileage(
				Double.valueOf(taskOrder.getExpectsMileage() == null ? 0 : taskOrder.getExpectsMileage()));
		taskCar.setPlateNumber(taskOrder.getPlateNumber());
		taskCar.setFirstDriverPersonID(taskOrder.getFirstPersonId());
		taskCar.setFirstPersonName(taskOrder.getFirstPersonName());
		taskCar.setFirstPersonMobile(taskOrder.getFirstPersonMobile());
		taskCar.setSecondDriverPersonID(taskOrder.getSecondPersonId());
		taskCar.setSecondPersonName(taskOrder.getSecondPersonName());
		taskCar.setSecondPersonMobile(taskOrder.getSecondPersonMobile());
		taskCar.setOrdersStatus(taskOrder.getOrdersStatus());
		taskCar.setEffectiveTime(taskOrder.getDuration());
		// taskCar.setEntId(taskOrder.getEntId());
		taskCar.setCoopType(coopType);
		taskCar.setIsShareCar(ConstantUtil.BYTE_FALSE);
		taskCar.setOutKeyId(taskOrder.getOwnOrderId());
		taskCar.setAddTime(taskOrder.getAddTime());
		taskCar.setRemark(taskOrder.getOrdersRemark());
		taskCar.setActualLeaveTime(taskOrder.getStartAreaActualLeavTime());
		taskCar.setTaskCarType(sendCarDTO.getTaskType());

		// 已自动接单
		if (taskOrder.getOrdersStatus().equals(OrdersStatus.WaitRun.getValue())) {
			taskCar.setReceivPersonID(taskOrder.getFirstPersonId());
			taskCar.setIsReceiv(ConstantUtil.BYTE_TRUE);
			taskCar.setReceivTime(taskOrder.getAddTime());
		}

		if (null != sendCarDTO.getIsAutoArrive()) {
			taskCar.setIsAutoArrive(sendCarDTO.getIsAutoArrive());
		}else{
			taskCar.setIsAutoArrive(ConstantUtil.BYTE_FALSE);
		}

		if(null != sendCarDTO.getIsAutoDepart()){
			taskCar.setIsAutoDepart(sendCarDTO.getIsAutoDepart());
		}else{
			taskCar.setIsAutoDepart(ConstantUtil.BYTE_FALSE);
		}

		// 如果是给共享车辆派单，则记录下共享标识和当前派车的企业
		if (coopType.equals(CoopType.ShareCar.getValue())) {
			taskCar.setIsShareCar(ConstantUtil.BYTE_TRUE);
			taskCar.setShareEntId(sourceEntId);
			taskCar.setCoopEntId(sourceEntId);
		}
		if(sendCarDTO.getOrgId()!=null){
			taskCar.setOrgId(sendCarDTO.getOrgId());
		}
		taskOrdersCarDao.insert(taskCar);

		// 派车单详情
		TaskOrdersCarInfoEntity taskCarInfo = new TaskOrdersCarInfoEntity();
		taskCarInfo.setId(RandomTool.getGUId());
		taskCarInfo.setOrdercarGuid(taskOrder.getOrdersCID());
		TaskOrdersAreaEntity startArea = taskOrdersAreaDao.select(taskOrder.getStartAreaGUID());
		if (null != startArea) {
			taskCarInfo.setOriginatCityCode(StringConverUtil.getString(startArea.getRegionCode()));
			taskCarInfo.setOriginatAddress(startArea.getAddress());
			taskCarInfo.setOriginatLat(startArea.getLat());
			taskCarInfo.setOriginatLng(startArea.getLng());
			taskCarInfo.setOriginatContact(startArea.getContact());
			taskCarInfo.setOriginatPhone(startArea.getContactphone());

			//if (StringUtils.isNotBlank(startArea.getNetWorkGuid())) {
			//	NetworkEntity startNetwork = networkDao.find(startArea.getNetWorkGuid());
			taskCarInfo.setOriginatNetworkId(startArea.getNetWorkGuid());
			taskCarInfo.setOriginatNetworkName(startArea.getName());
			//}

		}
		TaskOrdersAreaEntity endArea = taskOrdersAreaDao.select(taskOrder.getEndAreaGUID());
		if (null != endArea) {
			taskCarInfo.setDestinationCityCode(StringConverUtil.getString(endArea.getRegionCode()));
			taskCarInfo.setDestinationAddress(endArea.getAddress());
			taskCarInfo.setDestinationLat(endArea.getLat());
			taskCarInfo.setDestinationLng(endArea.getLng());
			taskCarInfo.setDestinationContact(endArea.getContact());
			taskCarInfo.setDestinationPhone(endArea.getContactphone());
			//if (StringUtils.isNotBlank(endArea.getNetWorkGuid())) {
			//	NetworkEntity endNetwork = networkDao.find(endArea.getNetWorkGuid());
			taskCarInfo.setDestinationNetworkId(endArea.getNetWorkGuid());
			taskCarInfo.setDestinationNetworkName(endArea.getName());
			//}
		}
		taskCarInfo.setEntrustEntId(taskCar.getEntrustEntid());
		if (StringUtils.isNotBlank(taskCar.getEntrustEntid())) {
			EnterpriseInfoEntity entrustEnt = enterpriseInfoDao.select(taskCar.getEntrustEntid());
			taskCarInfo.setEntrustEntName(entrustEnt.getName());
		}
		taskCarInfo.setCreateTime(DateUtil.getSqlTime());
		taskCarInfo.setCreatePersonId(sourcePersonId);
		taskCarInfo.setUpdateTime(DateUtil.getSqlTime());
		taskCarInfo.setUpdatePersonId(sourcePersonId);
		taskOrdersCarInfoDao.insert(taskCarInfo);
		return taskCar;
	}






	/**
	 * 发送行程时 添加任务网点
	 *
	 * @param areaList
	 * @param entId
	 * @param orderCid
	 * @param personId
	 * @param isPush
	 * @return
	 */
	public RouteAreaVo addTaskArea(List<TaskOrdersAreaDto> areaList, String entId, String orderCid, String personId,
								   boolean isPush) {
		RouteAreaVo routeAreaVo = new RouteAreaVo();
		int areaNum = areaList.size();
		String startNetworkId = null;
		String endNetworkId = null;
		String classGuid = RandomTool.getGUId();
		// 添加区域,即网点
		List<TaskOrdersAreaEntity> areaResultList = new ArrayList<>();
		for (TaskOrdersAreaDto areaBean : areaList) {
			TaskOrdersAreaEntity entity = new TaskOrdersAreaEntity();
			entity.setAddTime(DateUtil.getSqlTime());
			entity.setEntID(entId);
			entity.setOrdersCID(orderCid);
			entity.setSortNum(areaBean.getSortNum());
			entity.setRegionCode((int) areaBean.getRegionCode());
			entity.setGuid(RandomTool.getGUId());
			entity.setAddress(areaBean.getAddress());
			entity.setContact(areaBean.getContactName());
			entity.setContactphone(areaBean.getContactPhone());
			entity.setLat(areaBean.getLat());
			entity.setLng(areaBean.getLng());
			entity.setMapType(areaBean.getMapType());
			entity.setName(areaBean.getName());
			entity.setIsStartPoint(CommonProperties.BYTE_ZERO);
			if (areaBean.getSortNum() == 0) {
				entity.setIsStartPoint(CommonProperties.BYTE_ONE);
				entity.setActualLeavTime(DateUtil.getSqlTime());
				startNetworkId = areaBean.getGuid();
				routeAreaVo.setStartAreaGUID(entity.getGuid());
				routeAreaVo.setStartAreaName(areaBean.getName());
				routeAreaVo.setStartRegionCode(StringConverUtil.getString(areaBean.getRegionCode()));

			} else {
				endNetworkId = areaBean.getGuid();
				routeAreaVo.setEndAreaGUID(entity.getGuid());
				routeAreaVo.setEndAreaName(areaBean.getName());
				routeAreaVo.setEndRegionCode(StringConverUtil.getString(areaBean.getRegionCode()));
			}
			entity.setIsEndPoint(CommonProperties.BYTE_ZERO);
			if (areaBean.getSortNum() == areaNum - 1)
				entity.setIsEndPoint(CommonProperties.BYTE_ONE);

			if (StringUtils.isBlank(areaBean.getGuid())) {
				// 增加点到网点列表
				// 网点信息
				NetworkEntity networkEntity = new NetworkEntity();
				networkEntity.setGuid(RandomTool.getGUId());
				networkEntity.setAddTime(DateUtil.getSqlTime());
				if (StringUtils.isNotBlank(entId)) {
					networkEntity.setEntid(entId);
				}
				networkEntity.setNetworkName(areaBean.getName());
				networkEntity.setNetworkNamePinyin(PinyinUtil.getPinYin(areaBean.getName()));
				networkEntity.setNetworkNamePinyinHead(PinyinUtil.getPinYinHeadChar(areaBean.getName()));
				networkEntity.setNetworkAddress(areaBean.getAddress());
				networkEntity.setLat(areaBean.getLat());
				networkEntity.setLng(areaBean.getLng());
				networkEntity.setNetworkContactPhone(areaBean.getContactPhone());
				networkEntity.setNetworkContactUser(areaBean.getContactName());
				networkEntity.setIsDelete(ConstantUtil.BYTE_FALSE);
				networkEntity.setEditTime(DateUtil.getSqlTime());
				networkEntity.setRegionCode(String.valueOf(areaBean.getRegionCode()));
				networkEntity.setType(ConstantUtil.INTEGER_CODE_ZERO);

				// 线路与网点关系
                EntClassLineNetworkEntity classlineNetworkEntity = new EntClassLineNetworkEntity();
				classlineNetworkEntity.setGuid(RandomTool.getGUId());
				if (StringUtils.isNotBlank(entId)) {
					classlineNetworkEntity.setEntId(entId);
				}
				classlineNetworkEntity.setAddPersonId(personId);
				classlineNetworkEntity.setAddTime(DateUtil.getSqlTime());
				classlineNetworkEntity.setLineId(classGuid);
				classlineNetworkEntity.setNetworkId(networkEntity.getGuid());
				classlineNetworkEntity.setSequence(areaBean.getSortNum());
				classlineNetworkEntity.setIsDelete(ConstantUtil.BYTE_FALSE);
				classlineNetworkEntity.setEditTime(DateUtil.getSqlTime());

				if (areaBean.getSortNum() == 0) {
					startNetworkId = networkEntity.getGuid();

				}
				if (areaBean.getSortNum() == areaNum - 1) {
					endNetworkId = networkEntity.getGuid();
				}

				networkDao.insert(networkEntity);
				entClassLineNetworkDao.insert(classlineNetworkEntity);
			} else {
				NetworkEntity existNetwork = networkDao.select(areaBean.getGuid());
				entity.setAreaCode(existNetwork.getNetworkCode());
				entity.setNetWorkGuid(areaBean.getGuid());//保存 netWorkGuid
				if (isPush) {
					if (StringUtils.isNotBlank(existNetwork.getNetworkCode())) {
						isPush = true;
					} else {
						isPush = false;
					}
					if (areaBean.getSortNum() == 0) {
						routeAreaVo.setStartNetworkCode(existNetwork.getNetworkCode());
					}
					if (areaBean.getSortNum() == areaNum - 1) {
						routeAreaVo.setEndNetworkCode(existNetwork.getNetworkCode());
					}
				}
			}
			areaResultList.add(entity);
			taskOrdersAreaDao.insert(entity);

		}
		// 增加线路，暂时可以重复，下个版本去重
		EntClassLineEntity entClassLineEntity = new EntClassLineEntity();
		entClassLineEntity.setGuid(classGuid);
		entClassLineEntity.setAddTime(DateUtil.getSqlTime());
		entClassLineEntity.setAddPersonId(personId);

		// 路线信息
		if (StringUtils.isNotBlank(entId)) {
			entClassLineEntity.setEntId(entId);
		}
		entClassLineEntity.setStartNetworkId(startNetworkId);
		entClassLineEntity.setEndNetworkId(endNetworkId);
		entClassLineEntity.setNetworkCount(areaNum);
		entClassLineEntity.setIsDelete(ConstantUtil.BYTE_FALSE);
		entClassLineEntity.setLineType(Integer.valueOf(ConstantUtil.String_CODE_ONE));
		entClassLineDao.insert(entClassLineEntity);

		routeAreaVo.setAreaList(areaResultList);
		return routeAreaVo;
	}

	/**
	 * 删除任务
	 *
	 * @param orderId
	 */
	private void delTask(String orderId) {
		List<TaskOrdersEntity> list = taskOrdersDao.getOrderFormList(orderId);
		for (TaskOrdersEntity entity : list) {
			taskOrdersDao.delete(entity.getOrdersCID());
			if (entity.getIsEndOrders() != null && ConstantUtil.BYTE_ONE.equals(entity.getIsEndOrders())) {
				TaskOrdersCarEntity entityCar = taskOrdersCarDao.getOrdersCarByOrderId(orderId);
				if(null != entityCar){
					taskOrdersCarDao.delete(entityCar.getId());
				}
			}
		}
	}

	/**
	 * @param userId
	 * @param
	 * @return void
	 * @Title: addTaskOrder
	 * @Description: 添加新任务单
	 */
	public TaskOrdersEntity addTaskOrder(String userId, CreateTaskDTO createTaskDTO, String entrustEntId) {
		String entId = createTaskDTO.getEntId();
		String orderCid = createTaskDTO.getOrderCid();
		Timestamp startAreaPlanLeavTime = createTaskDTO.getStartAreaPlanLeavTime();
		Timestamp endAreaPlanArriveTime = createTaskDTO.getEndAreaPlanArriveTime();
		String ordersRemark = createTaskDTO.getOrdersRemark();
		Integer statue = createTaskDTO.getStatue();
		Integer orderStatus = null != createTaskDTO.getTaskStatus() ? createTaskDTO.getTaskStatus()
				: createTaskDTO.getOrderStatus();
		String originalcid = createTaskDTO.getOriginalcid();
		String parentOrdersCid = createTaskDTO.getParentOrdersCid();
		Boolean entrustState = createTaskDTO.getEntrustState();
		Byte isCarOrders = createTaskDTO.getIsCarOrders();
		String startAreaGUID = createTaskDTO.getStartAreaGUID();
		String endAreaGUID = createTaskDTO.getEndAreaGUID();
		Integer duration = createTaskDTO.getDuration();
		Integer expectsMileage = createTaskDTO.getExpectsMileage();
		String startAreaCode = createTaskDTO.getStartAreaCode();
		String endAreaCode = createTaskDTO.getEndAreaCode();
		String startAreaName = createTaskDTO.getStartAreaName();
		String endAreaName = createTaskDTO.getEndAreaName();
		String lineId = createTaskDTO.getLineId();
		String startRegionCode = createTaskDTO.getStartRegionCode();
		String endRegionCode = createTaskDTO.getEndRegionCode();
		String carId = createTaskDTO.getCarId();
		String plateNumber = createTaskDTO.getPlateNumber();
		String firstPersonId = createTaskDTO.getFirstPersonId();
		String firstPersonName = createTaskDTO.getFirstPersonName();
		String firstPersonMobile = createTaskDTO.getFirstPersonMobile();
		String secondPersonId = createTaskDTO.getSecondPersonId();
		String secondPersonName = createTaskDTO.getSecondPersonName();
		String secondPersonMobile = createTaskDTO.getSecondPersonMobile();
		Timestamp createTime = null == createTaskDTO.getCreateTime() ? DateUtil.getSqlTime()
				: createTaskDTO.getCreateTime();
		Integer isAuto = null;
		if (StringUtils.isNotBlank(entId)) {
//			EnterpriseInfoEntity enterpriseEntity = enterpriseInfoDao.select(entId);
			EnterpriseSettingEntity entSetting = enterpriseSettingDao.queryByEntId(entId);
			isAuto = entSetting.getIsAutoSendCar().intValue();
		}
		TaskOrdersEntity taskOrdersEntity = new TaskOrdersEntity();
		taskOrdersEntity.setAddTime(createTime);
		taskOrdersEntity.setEditTime(createTime);
		taskOrdersEntity.setStartAreaGUID(startAreaGUID);
		taskOrdersEntity.setEndAreaGUID(endAreaGUID);
		taskOrdersEntity.setStartAreaCode(startAreaCode);
		taskOrdersEntity.setEndAreaCode(endAreaCode);
		taskOrdersEntity.setStartAreaName(startAreaName);
		taskOrdersEntity.setEndAreaName(endAreaName);
		taskOrdersEntity.setCarId(carId);
		taskOrdersEntity.setPlateNumber(plateNumber);
		taskOrdersEntity.setStartRegionCode(startRegionCode);
		taskOrdersEntity.setEndRegionCode(endRegionCode);
		taskOrdersEntity.setFirstPersonId(firstPersonId);
		taskOrdersEntity.setFirstPersonName(firstPersonName);
		taskOrdersEntity.setFirstPersonMobile(firstPersonMobile);
		taskOrdersEntity.setSecondPersonId(secondPersonId);
		taskOrdersEntity.setSecondPersonName(secondPersonName);
		taskOrdersEntity.setSecondPersonMobile(secondPersonMobile);
		taskOrdersEntity.setEntID(entId);
		taskOrdersEntity.setOrdersStatus(orderStatus);
		taskOrdersEntity.setIsAuto(isAuto);
		taskOrdersEntity.setOwnOrderId(createTaskDTO.getOwnOrderId());
		taskOrdersEntity.setOrdersType(createTaskDTO.getOrderType());
		taskOrdersEntity.setStartAreaActualLeavTime(startAreaPlanLeavTime);
		taskOrdersEntity.setIsCancle(CommonProperties.BYTE_ZERO);
		taskOrdersEntity.setIsEntrustOrders(CommonProperties.BYTE_ZERO);
		taskOrdersEntity.setOrdersCID(orderCid);
		taskOrdersEntity.setOwnType(createTaskDTO.getOwnType());
		taskOrdersEntity.setStartAreaActualLeavTime(createTaskDTO.getStartAreaActualLeaveTime());
		// 外部接口同步任务，如果预计发车时间不为空，只存预计发车时间，不存到达时间
		if (startAreaPlanLeavTime != null && (statue != null || duration != null)) {
			taskOrdersEntity.setStartAreaPlanLeavTime(startAreaPlanLeavTime);
		} else {
			taskOrdersEntity.setEndAreaPlanArriveTime(endAreaPlanArriveTime);
		}
		taskOrdersEntity.setOrdersRemark(ordersRemark);
		if (duration != null) {
			duration = duration == null ? statue * 60 : duration;
		}
		taskOrdersEntity.setDuration(duration);
		taskOrdersEntity.setExpectsMileage(expectsMileage);
		taskOrdersEntity.setStatute(statue);
		taskOrdersEntity.setIsDelete(CommonProperties.BYTE_ZERO);
		taskOrdersEntity.setCreatePersonId(userId);
		taskOrdersEntity.setCreatePersonInfo(createTaskDTO.getCreatePersonInfo());
		taskOrdersEntity.setCode(RandomTool.getTimeName() + RandomTool.getRandom(10000L));
		taskOrdersEntity.setParentOrdersCID(parentOrdersCid);
		taskOrdersEntity.setOriginalcid(originalcid);
		taskOrdersEntity.setIscancleEntrust(CommonProperties.BYTE_ZERO);
		taskOrdersEntity.setCreateorder(DateUtil.getTimeValue());
		taskOrdersEntity.setIsCarOrders(isCarOrders);
		taskOrdersEntity.setIsRead(CommonProperties.BYTE_ZERO);
		taskOrdersEntity.setLineId(lineId);
		taskOrdersEntity.setIsNeedSign(ConstantUtil.BYTE_FALSE);
		if (createTaskDTO.isCarrier()) {
			taskOrdersEntity.setIsCarrierOrders(ConstantUtil.BYTE_TRUE);
		}
		//委托信息
		taskOrdersEntity.setEntrustTime(createTaskDTO.getEntrustTime());
		taskOrdersEntity.setEntrustPersonId(createTaskDTO.getEntrustPersonId());
		taskOrdersEntity.setEntrustEntID(createTaskDTO.getEntrustEntId());
 		taskOrdersEntity.setIsFirstOrders(createTaskDTO.getIsFirstTask());
		taskOrdersEntity.setIsEndOrders(createTaskDTO.getIsEndTask());
		taskOrdersEntity.setEntrustInfo(createTaskDTO.getEntrustInfo());

		//承运信息
		taskOrdersEntity.setCarrierEntID(createTaskDTO.getCarrierEntId());
		taskOrdersEntity.setCarrierInfo(createTaskDTO.getCarrierInfo());

		taskOrdersDao.insert(taskOrdersEntity);
		return taskOrdersEntity;
	}


	/**
	 * 为DynamicServiceImpl类中的updateOrders方法提供， 为了解决线程中的方法没有事物报的异常
	 *
	 * @param list
	 * @param actualMileage
	 * @param actualDrivetime
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void flushTaskOrdersStatus(List<TaskOrdersEntity> list, Double actualMileage, Long actualDrivetime) {
		if (list != null && list.size() > 0) {
			for (TaskOrdersEntity task : list) {
				task.setActualDrivetime(actualDrivetime);
				task.setActualMileage(actualMileage);
				taskOrdersDao.update(task);
			}
		}
	}

	@Transactional
	public String finishOrder2(String orderId, String baseUserId) {
		Double actualMileage = null;
		Long actualDrivetime = null;
		// 获取订单
		TaskOrdersEntity taskOrdersEntity = taskOrdersDao.select(orderId);
		TValide.notNull(taskOrdersEntity, TaskErrorEnums.TaskErrors.ORDER_IS_NOT_EXIST);
		String originalcid = taskOrdersEntity.getOriginalcid();

		// 更新订单状态
		List<TaskOrdersEntity> finishList = taskOrdersDao.getTaskOrdersByOriginalcid(originalcid);
		for (TaskOrdersEntity task : finishList) {
			task.setEntrustStatus(OrdersStatus.Finish.getValue());
			task.setOrdersStatus(OrdersStatus.Finish.getValue());
			task.setEndAreaActualArriveTime(DateUtil.getSqlTime());
			task.setIsManual(ConstantUtil.BYTE_TRUE);
			task.setActualDrivetime(actualDrivetime);
			task.setActualMileage(actualMileage);
			taskOrdersDao.update(task);
		}
		return originalcid;
	}


	/**
	 * @param @param
	 *            ordersCid 首单id
	 * @return void 返回类型
	 * @throws @Title:
	 *             removeRedisCache
	 * @Description: 根据订单id执行缓存数据删除使用(订单、订单车、人、关联动态删除)
	 */
	private void removeRedisCache(String ordersCid) {
		// 订单缓存删除
		long a = redisCache.del("taskOrders.guid." + ordersCid, CacheDBType.DataDB);
		// 货物缓存删除
		List<TaskOrdersGoodsEntity> goodslist = taskOrdersGoodsDao.getOrderGoods(ordersCid);
		if (null != goodslist && goodslist.size() > 0) {
			goodslist.stream().filter(good -> null != good)
					.forEach(good -> redisCache.del("taskOrdersgoods.guid." + good.getGuid(), CacheDBType.DataDB));
		}
		// 订单网点缓存删除
		List<TaskOrdersAreaEntity> arealist = taskOrdersAreaDao.getOrdersAreaByCid(ordersCid);
		if (null != arealist && arealist.size() > 0) {
			arealist.stream().filter(area -> null != area)
					.forEach(area -> redisCache.del("taskOrdersarea.guid." + area.getGuid(), CacheDBType.DataDB));
		}
		// 订单车辆缓存
		List<TaskOrdersCarEntity> carlist = taskOrdersCarDao.getTaskOrdersCarByCid(ordersCid);
		if (null != carlist && carlist.size() > 0) {
			carlist.stream().filter(car -> null != car)
					.forEach(car -> redisCache.del("taskOrderscar.guid." + car.getId(), CacheDBType.DataDB));
		}
		System.out.println("--------------执行订单缓存删除----------------");
	}

	public void driverCarrier(TaskOrdersCarEntity taskCar, String personId) {
		if (null != taskCar.getReceivTime()) {
			throw new ServiceException(DynamicErrorEnums.TASK_CAN_NOT_REPEAT);
		}
		taskCar.setIsReceiv(ConstantUtil.BYTE_TRUE);
		taskCar.setReceivPersonID(personId);
		taskCar.setReceivTime(DateUtil.getSqlTime());
		taskOrdersCarDao.update(taskCar);

		// 车辆状态改为待发
		if (StringUtils.isNotBlank(taskCar.getCarID())) {
			EnterpriseCooperateCarEntity entCar = enterpriseCooperateCarDao.getEntCarByCarId(taskCar.getEntrustEntid(),
					taskCar.getCarID());
			if (null != entCar) {
				entCar.setCarStatus(CarStatus.Ready.getValue().byteValue());
				enterpriseCooperateCarDao.update(entCar);
				// 1.50更新缓存数据
				coopcarListTac.setCarListReisCachByEntity(ConstantUtil.COOP_CAR_LIST + entCar.getEntid(),
						entCar);
				if (null != entCar && StringUtils.isNotBlank(entCar.getCarid())) {
					CarsEntity carsEntity = carsDao.select(entCar.getCarid());
					carListTac.setCarListReisCachByEntity(ConstantUtil.CAR_LIST + entCar.getEntid(),
							carsEntity);
				}
			}
		}

		// 更改订单状态
		updateOrderStatus(taskCar, OrdersStatus.WaitRun.getValue(), taskCar.getCarID(), null);

	}

	/**
	 * 司机发车
	 *
	 * @param areaGuid
	 * @param taskCar
	 * @param taskCar
	 */
	public TaskOrdersAreaEntity driverDepart(String areaGuid, TaskOrdersCarEntity taskCar,Timestamp repeatTime) {
		Byte isAuto = taskCar.getIsAutoArrive();
		Timestamp startAreaActualLeavTime = repeatTime != null?repeatTime : DateUtil.getSqlTime();

		TaskOrdersAreaEntity ordersArea = taskOrdersAreaDao.select(areaGuid);
		if (null != ordersArea.getActualLeavTime() && (isAuto == null || (isAuto != null && isAuto == 0))) {
			throw new ServiceException(DynamicErrorEnums.START_CAN_NOT_REPEAT);
		}
		ordersArea.setActualLeavTime(startAreaActualLeavTime);
		taskOrdersAreaDao.update(ordersArea);

		// 如果发车网点是起始网点，则更新订单表中 起始点实际发车时间
		if (ordersArea.getIsStartPoint().equals(ConstantUtil.BYTE_TRUE)) {
			updateOrderStatus(taskCar, OrdersStatus.Runing.getValue(), taskCar.getCarID(), repeatTime);
			// 发车后发送微信
			//this.sendStartWechatMode(taskOrder, taskCar.getCarId(), startAreaActualLeavTime);
		}

		// 发车之后，车辆状态修改为在途
		if (StringUtils.isNotBlank(taskCar.getCarID())) {
            enterpriseCooperateCarDao.updateCarStatus(taskCar.getEntrustEntid(), taskCar.getCarID(),
					CarStatus.Running.getValue());
			// 更新缓存任务状态
			EnterpriseCooperateCarEntity cooperateCarEntity = enterpriseCooperateCarDao
					.getCooperCarByEntIdAndCarId(taskCar.getEntrustEntid(), taskCar.getCarID());
			cooperateCarEntity.setCarStatus(CarStatus.Running.getValue().byteValue());
			if (null != cooperateCarEntity && StringUtils.isNotBlank(taskCar.getEntrustEntid())) {
				coopcarListTac.setCarListReisCachByEntity(ConstantUtil.COOP_CAR_LIST + taskCar.getEntrustEntid(),
						cooperateCarEntity);
				if (StringUtils.isNotBlank(cooperateCarEntity.getCarid())) {
					CarsEntity carsEntity = carsDao.select(cooperateCarEntity.getCarid());
					carListTac.setCarListReisCachByEntity(ConstantUtil.CAR_LIST + taskCar.getEntrustEntid(),
							carsEntity);
				}
			}
		}
		// 任务运行时间段
        operationTimeSender.sendCar(taskCar.getCarID(), taskCar.getPlateNumber(), taskCar.getFirstDriverPersonID(),
				taskCar.getOrdersCID(), startAreaActualLeavTime);
		return ordersArea;
	}

	// 司机到达网点（包含自动到达）
	public TaskOrdersAreaEntity driverArrive(String areaGuid, TaskOrdersCarEntity taskCar, Timestamp repeatTime) {
		String entId = taskCar.getEntrustEntid();
		String carId = taskCar.getCarID();
		Timestamp arriveTime = repeatTime != null?repeatTime : DateUtil.getSqlTime();
		Integer carStatus = null;
		// 重复到达
		Byte isAuto = taskCar.getIsAutoArrive();
		TaskOrdersAreaEntity ordersArea = taskOrdersAreaDao.select(areaGuid);
		if (null != ordersArea.getActualArriveTime() && (isAuto == null || isAuto == 0)) {
			throw new ServiceException(DynamicErrorEnums.ARRIVE_CAN_NOT_REPEAT);
		}
		ordersArea.setActualArriveTime(arriveTime);
		taskOrdersAreaDao.update(ordersArea);
		if (ordersArea.getIsEndPoint().equals(ConstantUtil.BYTE_TRUE)) {
			updateOrderStatus(taskCar, OrdersStatus.Arrive.getValue(), carId, repeatTime);
			// 自动到达时发送微信
			// sendEndWechatMode(taskOrder,arriveTime,ordersArea);
			taskExecutor.execute(() -> {
				// 任务运行时间段
				//operationTimeSender.finishTask(taskCar.getOrdersCID(), arriveTime);
			});

			//更新车辆状态
			if (StringUtils.isNotBlank(entId) && StringUtils.isNotBlank(carId)) {
				// 设置车辆状态 若存在代发单 设置为待发 否则设置为空闲
				Long waitTaskNum = taskOrdersCarDao.getTaskNum(entId, carId, OrdersStatus.WaitRun.getValue());
				Long runTaskNum = taskOrdersCarDao.getTaskNum(entId, carId, OrdersStatus.Runing.getValue());
				if (waitTaskNum > 1) {
                    enterpriseCooperateCarDao.updateCarStatus(entId, carId, CarStatus.Ready.getValue());
					carStatus = CarStatus.Ready.getValue();
				}
				if (runTaskNum > 1) {
                    enterpriseCooperateCarDao.updateCarStatus(entId, carId, CarStatus.Running.getValue());
					carStatus = CarStatus.Running.getValue();
				} else {
                    enterpriseCooperateCarDao.updateCarStatus(entId, carId, CarStatus.Free.getValue());
					carStatus = CarStatus.Free.getValue();
				}
			}
			// 更新缓存任务状态
			if (StringUtils.isNotBlank(carId)) {
				EnterpriseCooperateCarEntity cooperateCarEntity = enterpriseCooperateCarDao.getCooperCarByEntIdAndCarId(entId, carId);
				cooperateCarEntity.setCarStatus(carStatus.byteValue());
				if (null != cooperateCarEntity && StringUtils.isNotBlank(entId)) {
					coopcarListTac.setCarListReisCachByEntity(ConstantUtil.COOP_CAR_LIST + entId, cooperateCarEntity);
					if (StringUtils.isNotBlank(carId)) {
						CarsEntity carsEntity = carsDao.select(carId);
						carListTac.setCarListReisCachByEntity(ConstantUtil.CAR_LIST + entId, carsEntity);
					}
				}
			}
		}
		return ordersArea;
	}

	// 司机完成任务
	public TaskOrdersAreaEntity driverFinish(String areaGuid, String personId,TaskOrdersCarEntity taskCar) {
		String entId = taskCar.getEntrustEntid();
		String carId = taskCar.getCarID();
		TaskOrdersAreaEntity ordersArea = taskOrdersAreaDao.select(areaGuid);
		Timestamp finishTime = DateUtil.getSqlTime();
		Integer carStatus = null;
		if (taskCar.getOrdersStatus().equals(OrdersStatus.Finish.getValue())) {
			throw new ServiceException(DynamicErrorEnums.FINISH_CAN_NOT_REPEAT);
		}
		// 在已到达的状态下完成任务，则只需要修改订单状态和完成时间
		if (taskCar.getOrdersStatus().equals(OrdersStatus.Arrive.getValue())) {
			List<TaskOrdersEntity> list = taskOrdersDao.getTaskOrdersByOriginalcid(taskCar.getOriginalcid());
			for (TaskOrdersEntity ordersEntity : list) {
				ordersEntity.setFinishTime(finishTime);
				ordersEntity.setOrdersStatus(OrdersStatus.Finish.getValue());
				ordersEntity.setEntrustStatus(OrdersStatus.Finish.getValue());
				taskOrdersDao.update(ordersEntity);
			}
			taskCar.setOrdersStatus(OrdersStatus.Finish.getValue());
			taskCar.setFinishTime(finishTime);
			taskOrdersCarDao.update(taskCar);
		}
		// 订单在途状态下直接完成任务
		else {
			ordersArea.setActualArriveTime(DateUtil.getSqlTime());
			taskOrdersAreaDao.update(ordersArea);
			// 更新订单状态
			updateOrderStatus(taskCar, OrdersStatus.Finish.getValue(), carId, null);
			// 司机在未自动到达的情况下完成任务
			// sendEndWechatMode(taskOrder,finishTime,ordersArea);
			if (StringUtils.isNotBlank(entId) && StringUtils.isNotBlank(carId)) {
				// 设置车辆状态 若存在代发单 设置为待发 否则设置为空闲
				Long waitTaskNum = taskOrdersCarDao.getTaskNum(entId, carId, OrdersStatus.WaitRun.getValue());
				Long runTaskNum = taskOrdersCarDao.getTaskNum(entId, carId, OrdersStatus.Runing.getValue());
				if (waitTaskNum > 1) {
					enterpriseCooperateCarDao.updateCarStatus(entId, carId, CarStatus.Ready.getValue());
					carStatus = CarStatus.Ready.getValue();
				}
				if (runTaskNum > 1) {
                    enterpriseCooperateCarDao.updateCarStatus(entId, carId, CarStatus.Running.getValue());
					carStatus = CarStatus.Running.getValue();
				} else {
                    enterpriseCooperateCarDao.updateCarStatus(entId, carId, CarStatus.Free.getValue());
					carStatus = CarStatus.Free.getValue();
				}
			}

            // 任务运行时间段
            //operationTimeSender.finishTask(taskCar.getOrdersCID(), null);

		}

		if (StringUtils.isNotBlank(entId) && StringUtils.isNotBlank(carId)) {
			// 更新缓存任务状态
			EnterpriseCooperateCarEntity cooperateCarEntity = enterpriseCooperateCarDao
					.getCooperCarByEntIdAndCarId(entId, carId);
			if (null != cooperateCarEntity) {
			    cooperateCarEntity.setCarStatus(carStatus.byteValue());
				coopcarListTac.setCarListReisCachByEntity(ConstantUtil.COOP_CAR_LIST + entId, cooperateCarEntity);
				if (StringUtils.isNotBlank(carId)) {
					CarsEntity carsEntity = carsDao.select(carId);
					carListTac.setCarListReisCachByEntity(ConstantUtil.CAR_LIST + entId, carsEntity);
				}
			}
		}

		// 推送
		List<FinishTaskPushTemplate> pushTemplates = new ArrayList<>();
		List<TaskOrdersEntity> list = taskOrdersDao.getTaskOrdersByOriginalcid(taskCar.getOriginalcid());
		for (TaskOrdersEntity taskOrdersEntity : list) {
			// 推送模板
			if(ConstantUtil.BYTE_FALSE.equals(taskOrdersEntity.getIsFirstOrders())) {
				String entrustPersonId = taskOrdersEntity.getEntrustPersonId();
				String carrierName = null;
				if(ConstantUtil.BYTE_TRUE.equals(taskOrdersEntity.getIsEndOrders())) {
					carrierName = taskOrdersEntity.getFirstPersonName() + " " + taskOrdersEntity.getPlateNumber();
				}else{
					String carrierEntId = taskOrdersEntity.getCarrierEntID();
					EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(carrierEntId);
					carrierName = enterpriseInfoEntity == null ? null : enterpriseInfoEntity.getName();
				}

				FinishTaskPushTemplate finishTaskPushTemplate = new FinishTaskPushTemplate(entrustPersonId, carrierName);
				pushTemplates.add(finishTaskPushTemplate);
			}
		}

		jetfireMsgSender.sendPush(pushTemplates);

		return ordersArea;
	}

	// 更改订单状态（计算里程）
	public void updateOrderStatus(TaskOrdersCarEntity taskCar, Integer ordersStatus,String carId, Timestamp repeatTime) {
		Timestamp endActualArriveTime = DateUtil.getSqlTime();
		taskCar.setOrdersStatus(ordersStatus);
		List<TaskOrdersEntity> list = taskOrdersDao.getTaskOrdersByOriginalcid(taskCar.getOriginalcid());
		//跟新之前业务系统产生的单据（除业务单据之外的业务）
		for (TaskOrdersEntity taskOrder : list) {
			taskOrder.setOrdersStatus(ordersStatus);
			taskOrder.setEntrustStatus(ordersStatus);
			// 接单
			if (ordersStatus == 4) {
				// 任务表
				taskOrder.setCarId(taskCar.getCarID());
				taskOrder.setPlateNumber(taskCar.getPlateNumber());
				taskOrder.setFirstPersonId(taskCar.getFirstDriverPersonID());
				taskOrder.setFirstPersonName(taskCar.getFirstPersonName());
				taskOrder.setFirstPersonMobile(taskCar.getFirstPersonMobile());
				taskOrder.setSecondPersonId(taskCar.getSecondDriverPersonID());
				taskOrder.setSecondPersonName(taskCar.getSecondPersonName());
				taskOrder.setSecondPersonMobile(taskCar.getSecondPersonMobile());
			}
			if (ordersStatus == 5) {
				// 重复发车
				if (repeatTime != null) {
					taskOrder.setStartAreaActualLeavTime(repeatTime);
				} else {
					taskOrder.setStartAreaActualLeavTime(DateUtil.getSqlTime());
				}


			} else if (ordersStatus == 6) {
				// 重复到达时间
				if (repeatTime != null) {
					taskOrder.setEndAreaActualArriveTime(repeatTime);
				} else {
					taskOrder.setEndAreaActualArriveTime(endActualArriveTime);
				}
			} else if (ordersStatus == 7) {
				if (taskOrder.getEndAreaActualArriveTime() == null) {
					// 重复到达时间
					if (repeatTime != null) {
						taskOrder.setStartAreaActualLeavTime(repeatTime);
					} else {
						taskOrder.setEndAreaActualArriveTime(endActualArriveTime);
					}
				}
				taskOrder.setFinishTime(DateUtil.getSqlTime());
			}
			taskOrdersDao.update(taskOrder);
		}

		//任务单更新
		taskCar.setOrdersStatus(ordersStatus);
		if (ordersStatus == 5) {
			if (repeatTime != null) {
				taskCar.setActualLeaveTime(repeatTime);
			}
			else{
				taskCar.setActualLeaveTime(DateUtil.getSqlTime());
			}
		}
		else if (ordersStatus == 6) {
			if (repeatTime != null) {
				taskCar.setActualArriveTime(repeatTime);
			}
			else{
				taskCar.setActualArriveTime(DateUtil.getSqlTime());
			}
		}
		else if (ordersStatus == 7) {
			taskCar.setActualArriveTime(DateUtil.getSqlTime());
			taskCar.setFinishTime(DateUtil.getSqlTime());
		}
		taskOrdersCarDao.update(taskCar);

	}


	@Override
	public OrderReminderInfoVo getOrderReminder(String orderCarId, BaseRequestDTO baseRequestDTO) {
		TValide.notNull(orderCarId, TaskErrorEnums.TaskErrors.ORDER_ID_IS_NOT_NULL);

		// 订单主体
		// TaskOrdersEntity taskOrdersEntity = taskOrdersDao.find(orderCid);
		// TValide.notNull(taskOrdersEntity,
		// TaskErrorEnums.TaskErrors.SEND_ORDER_INFO_IS_EXCEPTION);

		// 获取派车信息
		TaskOrdersCarEntity taskOrdersCarEntity = taskOrdersCarDao.select(orderCarId);
		TValide.notNull(taskOrdersCarEntity, TaskErrorEnums.TaskErrors.SEND_ORDER_INFO_IS_EXCEPTION);

		OrderReminderInfoVo orderReminderInfoVo = new OrderReminderInfoVo();
		orderReminderInfoVo.setOrderCarId(orderCarId);
		orderReminderInfoVo.setStartAreaPlanLeavTime(DateUtil.dateToTimestamp(taskOrdersCarEntity.getPlanLeaveTime()));
		orderReminderInfoVo.setEndAreaPlanArriveTime(DateUtil.dateToTimestamp(taskOrdersCarEntity.getPlanArriveTime()));
		Double expectsMileage = taskOrdersCarEntity.getPlanMileage();
		if (null != expectsMileage) {
			orderReminderInfoVo.setDistance(Double.valueOf(expectsMileage / 1000));
		}

		String originalcid = taskOrdersCarEntity.getOriginalcid();
		TValide.notNull(originalcid, TaskErrorEnums.TaskErrors.ORDER_IS_NOT_EXIST);

		String startRegionCode = null;
		String endRegionCode = null;
		// 起始点信息
		TaskOrdersAreaEntity startAreaEntity = taskOrdersAreaDao.getStartPoint(originalcid);
		if (null != startAreaEntity) {
			orderReminderInfoVo.setLng(startAreaEntity.getLng());
			orderReminderInfoVo.setLat(startAreaEntity.getLat());
			orderReminderInfoVo.setStartPoint(startAreaEntity.getName());
			startRegionCode = null == startAreaEntity.getRegionCode() ? null
					: startAreaEntity.getRegionCode().toString();
		}
		// 结束点信息
		TaskOrdersAreaEntity endAreaEntity = taskOrdersAreaDao.getEndPoint(originalcid);
		if (null != endAreaEntity) {
			orderReminderInfoVo.setEndPoint(endAreaEntity.getName());
			endRegionCode = null == endAreaEntity.getRegionCode() ? null : endAreaEntity.getRegionCode().toString();
		}

		if (StringUtils.isNotBlank(startRegionCode) && StringUtils.isNotBlank(endRegionCode)) {
			if (startRegionCode.length() == 6 && endRegionCode.length() == 6) {
				String startCityRegionCode = startRegionCode.substring(0, 4) + "00";
				String endCityRegionCode = endRegionCode.substring(0, 4) + "00";
				// 同城
				if (startCityRegionCode.equals(endCityRegionCode)) {
					orderReminderInfoVo.setRegionCode(startRegionCode);
				}
				// 跨城
				else {
					String startPoint = orderReminderInfoVo.getStartPoint();
					String entPoint = orderReminderInfoVo.getEndPoint();

					RegionEntity startCity = regionDao.getRegionByCode(startCityRegionCode);
					if (null != startCity) {
						orderReminderInfoVo.setStartPoint(startCity.getRegionName() + " " + startPoint);
					}

					RegionEntity endCity = regionDao.getRegionByCode(endCityRegionCode);
					if (null != endCity) {
						orderReminderInfoVo.setEndPoint(endCity.getRegionName() + " " + entPoint);
					}
				}
			}
		}

		// 货物信息
		TaskOrdersGoodsEntity taskOrdersGoodsEntity = taskOrdersGoodsDao.getEntityByOrdersCid(originalcid);
		if (null != taskOrdersGoodsEntity) {
			GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
			String goodsTypeDictGuid = taskOrdersGoodsEntity.getGoodsTypeDictGUID();
			if (StringUtils.isNotBlank(goodsTypeDictGuid)) {
				goodsInfoVo.setGoodsTypeDictGUID(goodsTypeDictGuid);
				DictEntity dictEntity = dictDao.select(goodsTypeDictGuid);
				if (null != dictEntity) {
					goodsInfoVo.setTypeName(dictEntity.getName());
				}
			}
			goodsInfoVo.setName(taskOrdersGoodsEntity.getName());
			goodsInfoVo.setVolume(
					null == taskOrdersGoodsEntity.getVolume() ? null : taskOrdersGoodsEntity.getVolume());
			goodsInfoVo.setWeight(
					null == taskOrdersGoodsEntity.getWeight() ? null : taskOrdersGoodsEntity.getWeight());
			orderReminderInfoVo.setGoodsInfo(goodsInfoVo);
		}

		String entId = taskOrdersCarEntity.getEntrustEntid();
		if (StringUtils.isBlank(entId)) {
			return orderReminderInfoVo;
		}

		// 派单企业信息
		EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoEntityDao.select(entId);
		if (null != enterpriseInfoEntity) {
			orderReminderInfoVo.setEntrustEntId(entId);
			orderReminderInfoVo.setEntrustEntName(enterpriseInfoEntity.getName());
			orderReminderInfoVo.setEntrustEntLogo(enterpriseInfoEntity.getLogo());
		}

		// 派单人员信息
		String personId = taskOrdersCarEntity.getEntrustPersonID();
		if (StringUtils.isBlank(personId)) {
			return orderReminderInfoVo;
		}

		SystemPersonEntity sysPerson = userDao.select(personId);
		EnterpriseCooperatePerEntity enterpriseCooperatePer = enterpriseCooperatePerDao.getEntOwnerPerson(entId,
				personId);
		if (null != enterpriseCooperatePer) {
			orderReminderInfoVo.setEntrustPersonId(personId);
			orderReminderInfoVo.setEntrustPersonName(enterpriseCooperatePer.getNameRemark());
			orderReminderInfoVo.setEntrustPersonPhone(enterpriseCooperatePer.getPhoneNumber());
		}

		if (null != sysPerson) {
			orderReminderInfoVo.setEntrustPersonLogitalkId(sysPerson.getLogitalkId());
		}
		return orderReminderInfoVo;
	}

	/**
	 * 修改行程目的点
	 *
	 * @param taskOrdersAreaDto
	 */
	public void changeEndArea(TaskOrdersAreaDto taskOrdersAreaDto) {
		String orderCid = taskOrdersAreaDto.getOrderCid();
		String name = taskOrdersAreaDto.getName();
		String address = taskOrdersAreaDto.getAddress();
		Double lat = taskOrdersAreaDto.getLat();
		Double lng = taskOrdersAreaDto.getLng();
		Long regionCode = taskOrdersAreaDto.getRegionCode();
		String netWorkId = taskOrdersAreaDto.getGuid();
		String netWorkCode = "";

		String orderCarId = StringUtils.isBlank(taskOrdersAreaDto.getOrderCarId()) ? orderCid : taskOrdersAreaDto.getOrderCarId();
		// 派车信息
		TaskOrdersCarEntity taskOrdersCarEntity = taskOrdersCarDao.select(orderCarId);

		String originalcid = taskOrdersCarEntity.getOriginalcid();
		// 任务结束网点信息
		TaskOrdersAreaEntity taskOrdersAreaEntity = taskOrdersAreaDao
				.getEndPoint(originalcid);
		if (StringUtils.isNotBlank(netWorkId)) {
			NetworkEntity entity = networkDao.select(netWorkId);
			name = entity.getNetworkName();
			address = entity.getNetworkAddress();
			lat = entity.getLat();
			lng = entity.getLng();
			regionCode = Long.valueOf(entity.getRegionCode());
			netWorkCode = entity.getNetworkCode();
		}
		taskOrdersAreaEntity.setName(name);
		taskOrdersAreaEntity.setAddress(address);
		taskOrdersAreaEntity.setLat(lat);
		taskOrdersAreaEntity.setLng(lng);
		taskOrdersAreaEntity.setRegionCode(regionCode.intValue());
		taskOrdersAreaDao.update(taskOrdersAreaEntity);

		// 更改任务表
		List<TaskOrdersEntity> orderList = taskOrdersDao
				.getTaskOrdersByOriginalcid(originalcid);
		for (TaskOrdersEntity ordersBean : orderList) {
			ordersBean.setEndAreaName(name);
			ordersBean.setEndAreaCode(netWorkCode);
			taskOrdersDao.update(ordersBean);
		}
	}



	@Override
	public OrderReminderInfoVo getSendCarReminder(String orderCid) {

		OrderReminderInfoVo infoVo = new OrderReminderInfoVo();
		// 订单主体
		TaskOrdersEntity taskOrdersEntity = taskOrdersDao.select(orderCid);
		OrdersAreaInfoVo areaInfoVo = null;
		List<OrdersAreaInfoVo> areaInfoVos = new ArrayList<>();
		if (null != taskOrdersEntity) {
			String entId = taskOrdersEntity.getEntID();
//			EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(entId);
			EnterpriseSettingEntity entSetting = enterpriseSettingDao.queryByEntId(entId);
			Byte isAutoSendCar = entSetting.getIsAutoSendCar();
			if (isAutoSendCar == null || ConstantUtil.BYTE_FALSE.equals(isAutoSendCar)) {
				// 起始点信息
				TaskOrdersAreaEntity startAreaEntity = taskOrdersAreaDao.getStartPoint(orderCid);
				areaInfoVo = new OrdersAreaInfoVo();
				areaInfoVo.setStartAreaName(startAreaEntity.getName());
				areaInfoVo.setLng(startAreaEntity.getLng());
				areaInfoVo.setLat(startAreaEntity.getLat());
				areaInfoVos.add(areaInfoVo);

				List<TaskOrdersAreaEntity> areaList = taskOrdersAreaDao
						.getUnLeaveNode(taskOrdersEntity.getOriginalcid());
				if (areaList.size() > 0) {
					areaInfoVo = new OrdersAreaInfoVo();
					TaskOrdersAreaEntity area = areaList.get(0);
					areaInfoVo.setLat(area.getLat());
					areaInfoVo.setLng(area.getLng());
					areaInfoVo.setNextStation(area.getName());
					areaInfoVos.add(areaInfoVo);
				}
			}
		}
		return infoVo;
	}


	@Override
	public GpsPackTimeVo isTask(String baseUserId) {
		GpsPackTimeVo vo = new GpsPackTimeVo();
		List<TaskOrdersCarEntity> unFinishList = taskOrdersCarDao.getUnFinishOrdersCar(baseUserId);
		if (!unFinishList.isEmpty() && unFinishList.size() > 0) {
			vo.setIsTask(true);
		}
		return vo;
	}


	@Override
	public OrderReminderInfoVo getGoingToSendCarReminder(String orderCid, BaseRequestDTO baseRequestDTO) {
		TValide.notNull(orderCid, TaskErrorEnums.TaskErrors.ORDER_ID_IS_NOT_NULL);
		// 订单主体
		TaskOrdersEntity taskOrdersEntity = taskOrdersDao.select(orderCid);
		TValide.notNull(taskOrdersEntity, TaskErrorEnums.TaskErrors.SEND_ORDER_INFO_IS_EXCEPTION);
		if (taskOrdersEntity.getOrdersStatus() > 4) {
			return null;
		}

		OrderReminderInfoVo orderReminderInfoVo = new OrderReminderInfoVo();
		orderReminderInfoVo.setStartAreaPlanLeavTime(taskOrdersEntity.getStartAreaPlanLeavTime());
		orderReminderInfoVo.setEndAreaPlanArriveTime(taskOrdersEntity.getEndAreaPlanArriveTime());
		Integer expectsMileage = taskOrdersEntity.getExpectsMileage();
		if (null != expectsMileage) {
			orderReminderInfoVo.setDistance(Double.valueOf(expectsMileage / 1000));
		}

		String originalcid = taskOrdersEntity.getOriginalcid();
		TValide.notNull(originalcid, TaskErrorEnums.TaskErrors.ORDER_IS_NOT_EXIST);

		// 获取订单车辆信息
		TaskOrdersCarEntity taskOrdersCarEntity = taskOrdersCarDao.getOrdersCarByOrderId(orderCid);
		if (null != taskOrdersCarEntity) {
			orderReminderInfoVo.setOrderCarId(taskOrdersCarEntity.getId());
		}

		String startRegionCode = null;
		String endRegionCode = null;
		// 起始点信息
		TaskOrdersAreaEntity startAreaEntity = taskOrdersAreaDao.getStartPoint(originalcid);
		if (null != startAreaEntity) {
			orderReminderInfoVo.setLng(startAreaEntity.getLng());
			orderReminderInfoVo.setLat(startAreaEntity.getLat());
			orderReminderInfoVo.setStartPoint(startAreaEntity.getName());
			startRegionCode = null == startAreaEntity.getRegionCode() ? null
					: startAreaEntity.getRegionCode().toString();
		}
		// 结束点信息
		TaskOrdersAreaEntity endAreaEntity = taskOrdersAreaDao.getEndPoint(originalcid);
		if (null != endAreaEntity) {
			orderReminderInfoVo.setEndPoint(endAreaEntity.getName());
			endRegionCode = null == endAreaEntity.getRegionCode() ? null : endAreaEntity.getRegionCode().toString();
		}

		if (StringUtils.isNotBlank(startRegionCode) && StringUtils.isNotBlank(endRegionCode)) {
			if (startRegionCode.length() == 6 && endRegionCode.length() == 6) {
				String startCityRegionCode = startRegionCode.substring(0, 4) + "00";
				String endCityRegionCode = endRegionCode.substring(0, 4) + "00";
				// 同城
				if (startCityRegionCode.equals(endCityRegionCode)) {
					orderReminderInfoVo.setRegionCode(startRegionCode);
				}
				// 跨城
				else {
					String startPoint = orderReminderInfoVo.getStartPoint();
					String entPoint = orderReminderInfoVo.getEndPoint();

					RegionEntity startCity = regionDao.getRegionByCode(startCityRegionCode);
					if (null != startCity) {
						orderReminderInfoVo.setStartPoint(startCity.getRegionName() + " " + startPoint);
					}

					RegionEntity endCity = regionDao.getRegionByCode(endCityRegionCode);
					if (null != endCity) {
						orderReminderInfoVo.setEndPoint(endCity.getRegionName() + " " + entPoint);
					}
				}
			}
		}

		String entId = taskOrdersEntity.getEntID();
		if (StringUtils.isBlank(entId)) {
			return orderReminderInfoVo;
		}

		// 派单企业信息
		EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoEntityDao.select(entId);
		if (null != enterpriseInfoEntity) {
			orderReminderInfoVo.setEntrustEntId(entId);
			orderReminderInfoVo.setEntrustEntName(enterpriseInfoEntity.getName());
			orderReminderInfoVo.setEntrustEntLogo(enterpriseInfoEntity.getLogo());
		}
		// 派单人员信息
		TaskDynamicEntity pdDynamicEntity = taskDynamicDao.getTaskDynamic(taskOrdersEntity.getParentOrdersCID(),
				EventAction.OrdersSendCar.getValue());
		if (pdDynamicEntity == null) {
			pdDynamicEntity = taskDynamicDao.getTaskDynamic(taskOrdersEntity.getOrdersCID(), 301);
		}
		if (null == pdDynamicEntity || StringUtils.isBlank(pdDynamicEntity.getPersonId())) {
			return orderReminderInfoVo;
		}

		String personId = pdDynamicEntity.getPersonId();
		SystemPersonEntity sysPerson = userDao.select(personId);
		EnterpriseCooperatePerEntity enterpriseCooperatePer = enterpriseCooperatePerDao.getEntOwnerPerson(entId,
				personId);
		if (null != enterpriseCooperatePer) {
			orderReminderInfoVo.setEntrustPersonId(personId);
			orderReminderInfoVo.setEntrustPersonName(enterpriseCooperatePer.getNameRemark());
			orderReminderInfoVo.setEntrustPersonPhone(enterpriseCooperatePer.getPhoneNumber());
		}

		if (null != sysPerson) {
			orderReminderInfoVo.setEntrustPersonLogitalkId(sysPerson.getLogitalkId());
		}
		String minuteStr = sysSettingDao.getValue(ConstantUtil.GOING_TO_TASK_SEND_CAR_MINUTES);
		String minutes[] = minuteStr.split(",");
		List<String> minuteList = Arrays.asList(minutes);
		List<Integer> minuteNumList = new ArrayList<>();
		for (String minute : minuteList) {
			minuteNumList.add(Integer.valueOf(minute));
		}
		orderReminderInfoVo.setMinutes(minuteNumList);

		return orderReminderInfoVo;
	}

	/**
	 * 删除任务
	 *
	 * @param origilatId
	 */
	@Transactional(propagation= Propagation.REQUIRES_NEW)
	public void deleteByOrigilatId(String origilatId,String ownType) {
		List<TaskOrdersEntity> taskList = taskOrdersDao.getTaskOrdersByOriginalcid(origilatId);
		for (TaskOrdersEntity task : taskList) {
			deleteOrders(task.getOrdersCID(),ownType);
			//循环任务，并删除
			List<TaskDynamicEntity> taskDynamicList = taskDynamicDao.findByOrderAsDynamic(task.getOrdersCID());
			//循环任务动态，并删除
			for(TaskDynamicEntity taskDynamicEntity : taskDynamicList){
				taskDynamicDao.delete(taskDynamicEntity.getGuid());
			}
		}

		// 删除taskCar
		TaskOrdersCarEntity taskCar = taskOrdersCarDao.getOrdersCarByOriginalcid(origilatId);
		if (null != taskCar) {
			taskOrdersCarDao.delete(taskCar.getId());
		}

		// 删除area
		taskOrdersAreaDao.deleteOrdersAreaFromOrdersId(origilatId);

//		// 删除电子围栏
//		grimlockServiceHelper.removeEvent(taskCar.getOrdersCID());
		
		try{
			TaskOrdersEntity driverTaskOrder = taskOrdersDao.getEndOrders(origilatId);
			//删除车辆gps注册事件
			heartBeatServiceHelper.delCarGpsEvent(3, driverTaskOrder.getOrdersCID());
			//删除心跳注册事件
			heartBeatServiceHelper.delHeartBeatEvent(1, taskCar.getOrdersCID());
			heartBeatServiceHelper.delHeartBeatEvent(2, taskCar.getOrdersCID());
		}catch(Exception e){
			log.error("删除车辆gps注册事件或心跳注册事件失败，originalcid:"+origilatId);
			e.printStackTrace();
		}
	}

	/**
	 * 方法: deleteOrders 描述: 删除订单
	 */
	private void deleteOrders(String ordersId, String ownType) {
		TValide.notNull(ordersId, TaskErrorEnums.TaskErrors.ORDER_ID_IS_NOT_NULL);
		// 查询订单
		TaskOrdersEntity entity = taskOrdersDao.select(ordersId);
		TValide.notNull(entity, TaskErrorEnums.TaskErrors.ORDER_IS_NOT_EXIST);

		Byte cancleState = entity.getIsCancle();
		Byte isFirst = entity.getIsFirstOrders();
		Integer ordersStatus = entity.getOrdersStatus();
		//如果是外部接口调用该方法，跳过以下判断
		if(StringUtils.isBlank(ownType)){
			Boolean deleteFirstState = isFirst.equals(CommonProperties.BYTE_ONE)
					&& ordersStatus.equals(CommonProperties.INTEGER_ONE);
			Boolean deleteCancleState = cancleState.equals(CommonProperties.BYTE_ONE);

			TValide.isTrue((deleteCancleState || deleteFirstState), TaskErrorEnums.TaskErrors.ORDER_IS_NOT_FIT_DELETE_RULE);
		}
		taskOrdersDao.delete(entity.getOrdersCID());
	}






	/**
	 * 修改任务单
	 * @param updateTaskDto
	 */
	public void updateTask(UpdateTaskDto updateTaskDto){
		String taskId = updateTaskDto.getTaskId();
		String taskAreaId = updateTaskDto.getTaskAreaId();
		Timestamp updateTime = StringUtils.isNotBlank(updateTaskDto.getUpdateTime())
				? DateUtil.dateStrToTimestamp(updateTaskDto.getUpdateTime())
				: DateUtil.getSqlTime();
		Integer action =updateTaskDto.getAction();
		TaskOrdersCarEntity taskCar = taskOrdersCarDao.select(taskId);
		if(null == taskCar){
			throw new ServiceException(TaskErrorEnums.TaskErrors.SEND_ORDER_INFO_IS_EXCEPTION);
		}
		//如果任务单已被取消
		if(taskCar.getIsCancleEntrust().equals(CommonProperties.BYTE_ONE)){
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_CANCLE);
		}
		//如果任务单已被删除
		if(taskCar.getIsDelete().equals(CommonProperties.BYTE_ONE)){
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_DELETED);
		}
		//当前网点
		TaskOrdersAreaEntity taskArea = taskOrdersAreaDao.select(taskAreaId);
		//原任务首单尾单
		List<TaskOrdersEntity> taskList = taskOrdersDao.getTaskOrdersByOriginalcid(taskCar.getOriginalcid());
        //更改动态
		List<TaskDynamicEntity> taskDynamicList = taskDynamicDao.getDynamicListByAreaGUid(taskId, action,taskAreaId);
		if(taskDynamicList.size()>0){
			TaskDynamicEntity taskDynamic = taskDynamicList.get(0);
			taskDynamic.setDynamictime(updateTime);
			taskDynamicDao.update(taskDynamic);
		}
		//修改发车时间
		if(action.equals(EventAction.DriverDeparting.getValue())){
			//原首单尾单
			for(TaskOrdersEntity taskOrder : taskList){
				taskOrder.setStartAreaActualLeavTime(updateTime);
				taskOrdersDao.update(taskOrder);
			}
			//网点
			taskArea.setActualLeavTime(updateTime);
			//任务单
			taskCar.setActualLeaveTime(updateTime);
		}

		//修改途经点到达时间
		else if(action.equals(EventAction.DriverArrive.getValue())){
			taskArea.setActualArriveTime(updateTime);
		}
		//修改到达时间
		else if(action.equals(EventAction.DriverArriveEndArea.getValue())){
			for(TaskOrdersEntity taskOrder : taskList){
				taskOrder.setEndAreaActualArriveTime(updateTime);
				taskOrdersDao.update(taskOrder);
			}
			taskArea.setActualArriveTime(updateTime);
			taskCar.setActualArriveTime(updateTime);
		}
		//更新
		taskOrdersAreaDao.update(taskArea);
		taskOrdersCarDao.update(taskCar);
	}

	@Override
	public void deleteTaskOrderCar(String orderCarId) {
		TaskOrdersCarEntity taskOrdersCarEntity = taskOrdersCarDao.select(orderCarId);
		if(null != taskOrdersCarEntity){
			if(taskOrdersCarEntity.getTaskCarType().equals(TaskordersCarType.PLAN_CAR_ORDER.getValue())){
				taskOrdersCarEntity.setIsDelete(ConstantUtil.BYTE_TRUE);
				taskOrdersCarDao.update(taskOrdersCarEntity);
			}
			else{
				Byte isCancleEntrust = taskOrdersCarEntity.getIsCancleEntrust();
				if (ConstantUtil.BYTE_TRUE.equals(isCancleEntrust)) {
					taskOrdersCarEntity.setIsDelete(ConstantUtil.BYTE_TRUE);
					taskOrdersCarDao.update(taskOrdersCarEntity);
				}
			}
		}
	}

}
