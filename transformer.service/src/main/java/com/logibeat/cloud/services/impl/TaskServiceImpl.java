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
 * @Description: ?????????????????????
 * @date 2016???1???4??? ??????9:41:27
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
	 * ???????????????????????????
	 */
	@Override
	public OrdersEventInfoVo getRunDetail(String orderCarId, String allOrLast) {
		OrdersEventInfoVo ordersEventInfoVo = new OrdersEventInfoVo();
		List<EventDetailVo> eventDetailVoList = new ArrayList<EventDetailVo>();
		// ????????????
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
	 * ????????????
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
	 * ????????????
	 *
	 * @param taskDto
	 * @return
	 */
	public void createTask(TaskDto taskDto) {
		Map<String, Object> map = new HashMap<>();
		log.warn("????????????id???{}?????????????????????", taskDto.getBaseUserId());
		//?????????????????????
		Timestamp startAreaActualLeaveTime = DateUtil.getSqlTime();
		// ???????????????ID
		String startAreaGUID;
		// ???????????????ID
		String endAreaGUID;
		// ????????????????????????
		String startNetworkCode;
		// ????????????????????????
		String endNetworkCode;
		// ????????????????????????
		String startAreaName;
		// ????????????????????????
		String endAreaName;
		// ??????????????????
		String startRegionCode;
		// ??????????????????
		String endRegionCode;
		// ???????????????
		String orderCid = RandomTool.getGUId();
		// ????????????ID
		String personId = taskDto.getPersonId();
		//????????????
		String firstPersonName;
		//???????????????
		String firstPersonMobile;
		//??????ID
		String entId = taskDto.getEntId();
		//??????ID
		String carId = taskDto.getCarId();
		// ?????????
		String plateNumber = taskDto.getPlateNumber();

		// ?????????????????????
		boolean isPush = true;
		// ????????????????????????
		boolean isSelfDriver = false;
		// ????????????
		String picUrls = taskDto.getPicUrls();

		// ?????????????????????????????????
		Long taskNum = taskOrdersDao.getUnFinishedOrderNum(taskDto.getPersonId());
		if (taskNum > 0) {
			throw new ServiceException(TaskErrorEnums.TaskErrors.TASK_UNFINISH_CANT_CREATE);
		}
		// ??????????????????
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
		// ??????????????????
		log.warn("id???{}????????????????????????{}??????????????????", taskDto.getBaseUserId(), orderCid);
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


		//????????????
		log.warn("id???{}????????????????????????", taskDto.getBaseUserId());
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

		//?????????????????????
		InfoDto infoDto = new InfoDto();
		infoDto.setPersonId(personId);
		infoDto.setPersonName(firstPersonName);
		infoDto.setPersonMobile(firstPersonMobile);
		createTaskDTO.setCreatePersonInfo(new Gson().toJson(infoDto));

		TaskOrdersEntity taskOrdersEntity = addTaskOrder(personId, createTaskDTO, null);



		log.warn("id???{}??????????????????????????????", taskDto.getBaseUserId());

		// ??????
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
			//????????????
			DynamicDto dynamicDto = dynamicService.preAddDynamic(personId,taskCar,taskDto,startAreaGUID);
			TaskDynamicEntity taskDynamic = dynamicService.addDynamic(dynamicDto);

			// ???????????????????????????
			TaskOrdersAreaEntity targetArea = areaResultList.stream()
					.filter(p -> p.getIsEndPoint().equals(ConstantUtil.BYTE_TRUE)).findFirst().get();
			eventSpaceServiceHelper.registByTarget(targetArea, EventAction.DriverArriveEndArea.getValue(),
					PointState.ARRIVE_250.getValue(), taskCar);

			// ??????????????????????????????
			if (StringUtils.isNotBlank(taskDto.getEntId())) {
				enterpriseCooperateCarDao.updateIsFriend(taskDto.getEntId(), carId, (byte) 1);
				enterpriseCooperatePerDao.updateIsFriend(taskDto.getEntId(), personId, null, (byte) 1);
			}
			//??????????????????
			carSender.updateCarStatus(taskDto.getEntId(),carId,CarStatus.Running.getValue());
			// ?????????????????????????????????
			log.warn("id???{}???????????????????????????????????????????????????", taskDto.getBaseUserId());
			if (isPush && isSelfDriver) {
				outServiceHelper.pushTravelToStar(taskCar.getFirstDriverPersonID(), taskCar.getOrdersCID(), plateNumber,
						taskDynamic, startNetworkCode, endNetworkCode, person,
						TypeCastUtil.byteToBoolean(taskDto.getIsAtPoint().byteValue()), picUrls);
			}
		});

		// ?????????????????????
		operationTimeSender.sendCar(carId, plateNumber, personId, taskCar.getOrdersCID(), DateUtils.getSqlTime());
	}

	/**
	 * ???????????????
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

		// ?????????????????????????????????
		TValide.notNull(orderscid, TaskErrorEnums.TaskErrors.ORDER_ID_IS_NOT_NULL);
		TaskOrdersEntity taskOrderBean = taskOrdersDao.select(orderscid);
		if (StringUtils.isBlank(plateNumber) && StringUtils.isNotBlank(carId)) {
			CarsEntity carBean = carsDao.select(carId);
			plateNumber = carBean.getPlateNumber();
		}

		if(StringUtils.isNotBlank(outType)){
			//??????????????????
			sendCarDTO.setIsAutoArrive(sendCarDTO.getIsAutoArrive());
			sendCarDTO.setIsAutoDepart(sendCarDTO.getIsAutoDepart());
		}else{
			//app??????
			String entrustEntId = "";
			//???????????????????????????????????????
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

		// ???????????????????????????0???????????????????????????
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

		// ?????????
		if (StringUtils.isBlank(firstDriverName) || StringUtils.isBlank(firstDriverMobile)) {
			EnterpriseCooperatePerEntity firstEntDriver = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, firstDriverId);
			if (null != firstEntDriver && !firstEntDriver.getInviteState().equals(InviteState.Pass.getValue())) {
				throw new ServiceException(TaskErrorEnums.TaskErrors.DRIVER_IS_NOT_SURE_JOIN);
			}
			firstDriverName = firstEntDriver.getNameRemark();
			firstDriverMobile = firstEntDriver.getPhoneNumber();
		}
		// ?????????
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

		// ??????????????????????????????
		Boolean isCancleEntrust = TypeCastUtil.byteToBoolean(taskOrderBean.getIscancleEntrust());
		if (isCancleEntrust) {
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_CANCLE);
		}
		// ????????????????????????
		Integer orderStatus = taskOrderBean.getOrdersStatus();
		if (orderStatus.equals(2)) {
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_NOT_ACCEPT);
		}
		// ??????????????????
		Long downTaskNum = taskOrdersDao.getDownEntrustOrderCount(orderscid, taskOrderBean.getCreateorder());
		if (downTaskNum != null && !downTaskNum.equals(0L)) {
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_SEND_OR_ENTRUST_ENT);
		}

		if (taskOrderBean != null && null == sendCarDTO.getTaskStatus()) {
			taskOrderBean.setEditTime(DateUtil.getSqlTime());
			// ???????????????????????? ?????????????????? ?????????
			taskOrderBean.setOrdersStatus(OrdersStatus.WaitRun.getValue());
			taskOrderBean.setEntrustType(ConstantUtil.INTEGER_CODE_TWO);
 			taskOrdersDao.update(taskOrderBean);
		}

		// ??????????????????
		String newOrdersCid = RandomTool.getGUId();
		// ????????????
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

		//?????????/??????
		createTaskDTO.setEntrustTime(DateUtil.getSqlTime());
		createTaskDTO.setEntrustPersonId(sourcePersonId);
		createTaskDTO.setEntrustEntId(sourceEntId);

		//?????????????????????
		InfoDto entrustInfoDto  = new InfoDto();
		entrustInfoDto.setPersonId(sourcePersonId);
		entrustInfoDto.setPersonName(firstDriverName);
		entrustInfoDto.setPersonMobile(firstDriverMobile);

		//??????????????????
		EnterpriseInfoEntity entrustEnt = enterpriseInfoDao.select(sourceEntId);
		entrustInfoDto.setEntId(sourceEntId);
		entrustInfoDto.setEntName(entrustEnt.getName());
		entrustInfoDto.setEntLogo(entrustEnt.getLogo());
		createTaskDTO.setEntrustInfo(new Gson().toJson(entrustInfoDto));

		TaskOrdersEntity driverTaskOrder = addTaskOrder(sourcePersonId, createTaskDTO, sourceEntId);

		// ??????????????????
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

		// ?????? ????????????????????????isFriend = 1
		if (StringUtils.isNotBlank(firstDriverId)) {
			enterpriseCooperatePerDao.updateIsFriend(entId, firstDriverId, secondDriverId, (byte) 1);
		}
		if (StringUtils.isNotBlank(carId)) {
			enterpriseCooperateCarDao.updateIsFriend(entId, carId, (byte) 1);
		}

		// ??????????????????
		if (null != entCar) {
			entCar.setIsFriend((byte) 1);
			enterpriseCooperateCarDao.update(entCar);
			// 1.50??????????????????
			coopcarListTac.setCarListReisCachByEntity(ConstantUtil.COOP_CAR_LIST + entCar.getEntid(), entCar);
		}
		List<TaskOrdersEntity> orderList = taskOrdersDao.getOrersByEnd(taskOrderBean.getOriginalcid());

		if(sendCarDTO.getFirstTaskEntity() != null){
			orderList.add(sendCarDTO.getFirstTaskEntity());
		}
		// ??????????????????
		//dynamicService.addDynamic(carId, orderscid, sourcePersonId, EventAction.OrdersSendCar, sourceEntId,orderList);

		// ??????????????????
//		if(sendCarDTO.getTaskEntrustEntity() != null){
//			orderList.add(sendCarDTO.getTaskEntrustEntity());
//		}
//		if (sysAutoCarrier) {
//			dynamicService.addDynamic(carId, taskCar.getOrdersCID(), firstDriverId, EventAction.DriverReceiv, null,orderList);
//		}


        String mobile = firstDriverMobile;
        taskExecutor.execute(() -> {
            // ??????????????????
            if (!sendCarDTO.isSysAutoDepart()) {
                TaskOrdersAreaEntity targerArea = taskOrdersAreaDao.select(driverTaskOrder.getStartAreaGUID());
                eventSpaceServiceHelper.registByTarget(targerArea, EventAction.DriverDeparting.getValue(), 0, taskCar);
            }

            // ??????????????????
//            registHeartBeat(driverTaskOrder, carId, firstDriverId, mobile);


            //???????????????????????????????????????
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
	 * ??????????????????
	 *
	 * @param driverTaskOrder
	 * @param carId
	 * @param firstDriverId
	 * @param firstDriverMobile
	 */
	public void registHeartBeat(TaskOrdersEntity driverTaskOrder, String carId, String firstDriverId,
								String firstDriverMobile) {
		// ??????????????????
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
	 * ????????????????????????CarId?????????
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
	 * ??????????????????????????????carId?????????
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
	 * ???????????????(??????????????????????????????????????????)
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

		// ???????????????
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

		// ?????????????????????????????????????????????????????????????????????????????????
		if (coopType.equals(CoopType.ShareCar.getValue())) {
			taskCar.setIsShareCar(ConstantUtil.BYTE_TRUE);
			taskCar.setShareEntId(sourceEntId);
			taskCar.setCoopEntId(sourceEntId);
		}
		if(sendCarDTO.getOrgId()!=null){
			taskCar.setOrgId(sendCarDTO.getOrgId());
		}
		taskOrdersCarDao.insert(taskCar);

		// ???????????????
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
	 * ??????????????? ??????????????????
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
		// ????????????,?????????
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
				// ????????????????????????
				// ????????????
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

				// ?????????????????????
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
				entity.setNetWorkGuid(areaBean.getGuid());//?????? netWorkGuid
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
		// ??????????????????????????????????????????????????????
		EntClassLineEntity entClassLineEntity = new EntClassLineEntity();
		entClassLineEntity.setGuid(classGuid);
		entClassLineEntity.setAddTime(DateUtil.getSqlTime());
		entClassLineEntity.setAddPersonId(personId);

		// ????????????
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
	 * ????????????
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
	 * @Description: ??????????????????
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
		// ????????????????????????????????????????????????????????????????????????????????????????????????????????????
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
		//????????????
		taskOrdersEntity.setEntrustTime(createTaskDTO.getEntrustTime());
		taskOrdersEntity.setEntrustPersonId(createTaskDTO.getEntrustPersonId());
		taskOrdersEntity.setEntrustEntID(createTaskDTO.getEntrustEntId());
 		taskOrdersEntity.setIsFirstOrders(createTaskDTO.getIsFirstTask());
		taskOrdersEntity.setIsEndOrders(createTaskDTO.getIsEndTask());
		taskOrdersEntity.setEntrustInfo(createTaskDTO.getEntrustInfo());

		//????????????
		taskOrdersEntity.setCarrierEntID(createTaskDTO.getCarrierEntId());
		taskOrdersEntity.setCarrierInfo(createTaskDTO.getCarrierInfo());

		taskOrdersDao.insert(taskOrdersEntity);
		return taskOrdersEntity;
	}


	/**
	 * ???DynamicServiceImpl?????????updateOrders??????????????? ??????????????????????????????????????????????????????
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
		// ????????????
		TaskOrdersEntity taskOrdersEntity = taskOrdersDao.select(orderId);
		TValide.notNull(taskOrdersEntity, TaskErrorEnums.TaskErrors.ORDER_IS_NOT_EXIST);
		String originalcid = taskOrdersEntity.getOriginalcid();

		// ??????????????????
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
	 *            ordersCid ??????id
	 * @return void ????????????
	 * @throws @Title:
	 *             removeRedisCache
	 * @Description: ????????????id??????????????????????????????(?????????????????????????????????????????????)
	 */
	private void removeRedisCache(String ordersCid) {
		// ??????????????????
		long a = redisCache.del("taskOrders.guid." + ordersCid, CacheDBType.DataDB);
		// ??????????????????
		List<TaskOrdersGoodsEntity> goodslist = taskOrdersGoodsDao.getOrderGoods(ordersCid);
		if (null != goodslist && goodslist.size() > 0) {
			goodslist.stream().filter(good -> null != good)
					.forEach(good -> redisCache.del("taskOrdersgoods.guid." + good.getGuid(), CacheDBType.DataDB));
		}
		// ????????????????????????
		List<TaskOrdersAreaEntity> arealist = taskOrdersAreaDao.getOrdersAreaByCid(ordersCid);
		if (null != arealist && arealist.size() > 0) {
			arealist.stream().filter(area -> null != area)
					.forEach(area -> redisCache.del("taskOrdersarea.guid." + area.getGuid(), CacheDBType.DataDB));
		}
		// ??????????????????
		List<TaskOrdersCarEntity> carlist = taskOrdersCarDao.getTaskOrdersCarByCid(ordersCid);
		if (null != carlist && carlist.size() > 0) {
			carlist.stream().filter(car -> null != car)
					.forEach(car -> redisCache.del("taskOrderscar.guid." + car.getId(), CacheDBType.DataDB));
		}
		System.out.println("--------------????????????????????????----------------");
	}

	public void driverCarrier(TaskOrdersCarEntity taskCar, String personId) {
		if (null != taskCar.getReceivTime()) {
			throw new ServiceException(DynamicErrorEnums.TASK_CAN_NOT_REPEAT);
		}
		taskCar.setIsReceiv(ConstantUtil.BYTE_TRUE);
		taskCar.setReceivPersonID(personId);
		taskCar.setReceivTime(DateUtil.getSqlTime());
		taskOrdersCarDao.update(taskCar);

		// ????????????????????????
		if (StringUtils.isNotBlank(taskCar.getCarID())) {
			EnterpriseCooperateCarEntity entCar = enterpriseCooperateCarDao.getEntCarByCarId(taskCar.getEntrustEntid(),
					taskCar.getCarID());
			if (null != entCar) {
				entCar.setCarStatus(CarStatus.Ready.getValue().byteValue());
				enterpriseCooperateCarDao.update(entCar);
				// 1.50??????????????????
				coopcarListTac.setCarListReisCachByEntity(ConstantUtil.COOP_CAR_LIST + entCar.getEntid(),
						entCar);
				if (null != entCar && StringUtils.isNotBlank(entCar.getCarid())) {
					CarsEntity carsEntity = carsDao.select(entCar.getCarid());
					carListTac.setCarListReisCachByEntity(ConstantUtil.CAR_LIST + entCar.getEntid(),
							carsEntity);
				}
			}
		}

		// ??????????????????
		updateOrderStatus(taskCar, OrdersStatus.WaitRun.getValue(), taskCar.getCarID(), null);

	}

	/**
	 * ????????????
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

		// ????????????????????????????????????????????????????????? ???????????????????????????
		if (ordersArea.getIsStartPoint().equals(ConstantUtil.BYTE_TRUE)) {
			updateOrderStatus(taskCar, OrdersStatus.Runing.getValue(), taskCar.getCarID(), repeatTime);
			// ?????????????????????
			//this.sendStartWechatMode(taskOrder, taskCar.getCarId(), startAreaActualLeavTime);
		}

		// ??????????????????????????????????????????
		if (StringUtils.isNotBlank(taskCar.getCarID())) {
            enterpriseCooperateCarDao.updateCarStatus(taskCar.getEntrustEntid(), taskCar.getCarID(),
					CarStatus.Running.getValue());
			// ????????????????????????
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
		// ?????????????????????
        operationTimeSender.sendCar(taskCar.getCarID(), taskCar.getPlateNumber(), taskCar.getFirstDriverPersonID(),
				taskCar.getOrdersCID(), startAreaActualLeavTime);
		return ordersArea;
	}

	// ??????????????????????????????????????????
	public TaskOrdersAreaEntity driverArrive(String areaGuid, TaskOrdersCarEntity taskCar, Timestamp repeatTime) {
		String entId = taskCar.getEntrustEntid();
		String carId = taskCar.getCarID();
		Timestamp arriveTime = repeatTime != null?repeatTime : DateUtil.getSqlTime();
		Integer carStatus = null;
		// ????????????
		Byte isAuto = taskCar.getIsAutoArrive();
		TaskOrdersAreaEntity ordersArea = taskOrdersAreaDao.select(areaGuid);
		if (null != ordersArea.getActualArriveTime() && (isAuto == null || isAuto == 0)) {
			throw new ServiceException(DynamicErrorEnums.ARRIVE_CAN_NOT_REPEAT);
		}
		ordersArea.setActualArriveTime(arriveTime);
		taskOrdersAreaDao.update(ordersArea);
		if (ordersArea.getIsEndPoint().equals(ConstantUtil.BYTE_TRUE)) {
			updateOrderStatus(taskCar, OrdersStatus.Arrive.getValue(), carId, repeatTime);
			// ???????????????????????????
			// sendEndWechatMode(taskOrder,arriveTime,ordersArea);
			taskExecutor.execute(() -> {
				// ?????????????????????
				//operationTimeSender.finishTask(taskCar.getOrdersCID(), arriveTime);
			});

			//??????????????????
			if (StringUtils.isNotBlank(entId) && StringUtils.isNotBlank(carId)) {
				// ?????????????????? ?????????????????? ??????????????? ?????????????????????
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
			// ????????????????????????
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

	// ??????????????????
	public TaskOrdersAreaEntity driverFinish(String areaGuid, String personId,TaskOrdersCarEntity taskCar) {
		String entId = taskCar.getEntrustEntid();
		String carId = taskCar.getCarID();
		TaskOrdersAreaEntity ordersArea = taskOrdersAreaDao.select(areaGuid);
		Timestamp finishTime = DateUtil.getSqlTime();
		Integer carStatus = null;
		if (taskCar.getOrdersStatus().equals(OrdersStatus.Finish.getValue())) {
			throw new ServiceException(DynamicErrorEnums.FINISH_CAN_NOT_REPEAT);
		}
		// ????????????????????????????????????????????????????????????????????????????????????
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
		// ???????????????????????????????????????
		else {
			ordersArea.setActualArriveTime(DateUtil.getSqlTime());
			taskOrdersAreaDao.update(ordersArea);
			// ??????????????????
			updateOrderStatus(taskCar, OrdersStatus.Finish.getValue(), carId, null);
			// ????????????????????????????????????????????????
			// sendEndWechatMode(taskOrder,finishTime,ordersArea);
			if (StringUtils.isNotBlank(entId) && StringUtils.isNotBlank(carId)) {
				// ?????????????????? ?????????????????? ??????????????? ?????????????????????
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

            // ?????????????????????
            //operationTimeSender.finishTask(taskCar.getOrdersCID(), null);

		}

		if (StringUtils.isNotBlank(entId) && StringUtils.isNotBlank(carId)) {
			// ????????????????????????
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

		// ??????
		List<FinishTaskPushTemplate> pushTemplates = new ArrayList<>();
		List<TaskOrdersEntity> list = taskOrdersDao.getTaskOrdersByOriginalcid(taskCar.getOriginalcid());
		for (TaskOrdersEntity taskOrdersEntity : list) {
			// ????????????
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

	// ????????????????????????????????????
	public void updateOrderStatus(TaskOrdersCarEntity taskCar, Integer ordersStatus,String carId, Timestamp repeatTime) {
		Timestamp endActualArriveTime = DateUtil.getSqlTime();
		taskCar.setOrdersStatus(ordersStatus);
		List<TaskOrdersEntity> list = taskOrdersDao.getTaskOrdersByOriginalcid(taskCar.getOriginalcid());
		//???????????????????????????????????????????????????????????????????????????
		for (TaskOrdersEntity taskOrder : list) {
			taskOrder.setOrdersStatus(ordersStatus);
			taskOrder.setEntrustStatus(ordersStatus);
			// ??????
			if (ordersStatus == 4) {
				// ?????????
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
				// ????????????
				if (repeatTime != null) {
					taskOrder.setStartAreaActualLeavTime(repeatTime);
				} else {
					taskOrder.setStartAreaActualLeavTime(DateUtil.getSqlTime());
				}


			} else if (ordersStatus == 6) {
				// ??????????????????
				if (repeatTime != null) {
					taskOrder.setEndAreaActualArriveTime(repeatTime);
				} else {
					taskOrder.setEndAreaActualArriveTime(endActualArriveTime);
				}
			} else if (ordersStatus == 7) {
				if (taskOrder.getEndAreaActualArriveTime() == null) {
					// ??????????????????
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

		//???????????????
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

		// ????????????
		// TaskOrdersEntity taskOrdersEntity = taskOrdersDao.find(orderCid);
		// TValide.notNull(taskOrdersEntity,
		// TaskErrorEnums.TaskErrors.SEND_ORDER_INFO_IS_EXCEPTION);

		// ??????????????????
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
		// ???????????????
		TaskOrdersAreaEntity startAreaEntity = taskOrdersAreaDao.getStartPoint(originalcid);
		if (null != startAreaEntity) {
			orderReminderInfoVo.setLng(startAreaEntity.getLng());
			orderReminderInfoVo.setLat(startAreaEntity.getLat());
			orderReminderInfoVo.setStartPoint(startAreaEntity.getName());
			startRegionCode = null == startAreaEntity.getRegionCode() ? null
					: startAreaEntity.getRegionCode().toString();
		}
		// ???????????????
		TaskOrdersAreaEntity endAreaEntity = taskOrdersAreaDao.getEndPoint(originalcid);
		if (null != endAreaEntity) {
			orderReminderInfoVo.setEndPoint(endAreaEntity.getName());
			endRegionCode = null == endAreaEntity.getRegionCode() ? null : endAreaEntity.getRegionCode().toString();
		}

		if (StringUtils.isNotBlank(startRegionCode) && StringUtils.isNotBlank(endRegionCode)) {
			if (startRegionCode.length() == 6 && endRegionCode.length() == 6) {
				String startCityRegionCode = startRegionCode.substring(0, 4) + "00";
				String endCityRegionCode = endRegionCode.substring(0, 4) + "00";
				// ??????
				if (startCityRegionCode.equals(endCityRegionCode)) {
					orderReminderInfoVo.setRegionCode(startRegionCode);
				}
				// ??????
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

		// ????????????
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

		// ??????????????????
		EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoEntityDao.select(entId);
		if (null != enterpriseInfoEntity) {
			orderReminderInfoVo.setEntrustEntId(entId);
			orderReminderInfoVo.setEntrustEntName(enterpriseInfoEntity.getName());
			orderReminderInfoVo.setEntrustEntLogo(enterpriseInfoEntity.getLogo());
		}

		// ??????????????????
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
	 * ?????????????????????
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
		// ????????????
		TaskOrdersCarEntity taskOrdersCarEntity = taskOrdersCarDao.select(orderCarId);

		String originalcid = taskOrdersCarEntity.getOriginalcid();
		// ????????????????????????
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

		// ???????????????
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
		// ????????????
		TaskOrdersEntity taskOrdersEntity = taskOrdersDao.select(orderCid);
		OrdersAreaInfoVo areaInfoVo = null;
		List<OrdersAreaInfoVo> areaInfoVos = new ArrayList<>();
		if (null != taskOrdersEntity) {
			String entId = taskOrdersEntity.getEntID();
//			EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(entId);
			EnterpriseSettingEntity entSetting = enterpriseSettingDao.queryByEntId(entId);
			Byte isAutoSendCar = entSetting.getIsAutoSendCar();
			if (isAutoSendCar == null || ConstantUtil.BYTE_FALSE.equals(isAutoSendCar)) {
				// ???????????????
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
		// ????????????
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

		// ????????????????????????
		TaskOrdersCarEntity taskOrdersCarEntity = taskOrdersCarDao.getOrdersCarByOrderId(orderCid);
		if (null != taskOrdersCarEntity) {
			orderReminderInfoVo.setOrderCarId(taskOrdersCarEntity.getId());
		}

		String startRegionCode = null;
		String endRegionCode = null;
		// ???????????????
		TaskOrdersAreaEntity startAreaEntity = taskOrdersAreaDao.getStartPoint(originalcid);
		if (null != startAreaEntity) {
			orderReminderInfoVo.setLng(startAreaEntity.getLng());
			orderReminderInfoVo.setLat(startAreaEntity.getLat());
			orderReminderInfoVo.setStartPoint(startAreaEntity.getName());
			startRegionCode = null == startAreaEntity.getRegionCode() ? null
					: startAreaEntity.getRegionCode().toString();
		}
		// ???????????????
		TaskOrdersAreaEntity endAreaEntity = taskOrdersAreaDao.getEndPoint(originalcid);
		if (null != endAreaEntity) {
			orderReminderInfoVo.setEndPoint(endAreaEntity.getName());
			endRegionCode = null == endAreaEntity.getRegionCode() ? null : endAreaEntity.getRegionCode().toString();
		}

		if (StringUtils.isNotBlank(startRegionCode) && StringUtils.isNotBlank(endRegionCode)) {
			if (startRegionCode.length() == 6 && endRegionCode.length() == 6) {
				String startCityRegionCode = startRegionCode.substring(0, 4) + "00";
				String endCityRegionCode = endRegionCode.substring(0, 4) + "00";
				// ??????
				if (startCityRegionCode.equals(endCityRegionCode)) {
					orderReminderInfoVo.setRegionCode(startRegionCode);
				}
				// ??????
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

		// ??????????????????
		EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoEntityDao.select(entId);
		if (null != enterpriseInfoEntity) {
			orderReminderInfoVo.setEntrustEntId(entId);
			orderReminderInfoVo.setEntrustEntName(enterpriseInfoEntity.getName());
			orderReminderInfoVo.setEntrustEntLogo(enterpriseInfoEntity.getLogo());
		}
		// ??????????????????
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
	 * ????????????
	 *
	 * @param origilatId
	 */
	@Transactional(propagation= Propagation.REQUIRES_NEW)
	public void deleteByOrigilatId(String origilatId,String ownType) {
		List<TaskOrdersEntity> taskList = taskOrdersDao.getTaskOrdersByOriginalcid(origilatId);
		for (TaskOrdersEntity task : taskList) {
			deleteOrders(task.getOrdersCID(),ownType);
			//????????????????????????
			List<TaskDynamicEntity> taskDynamicList = taskDynamicDao.findByOrderAsDynamic(task.getOrdersCID());
			//??????????????????????????????
			for(TaskDynamicEntity taskDynamicEntity : taskDynamicList){
				taskDynamicDao.delete(taskDynamicEntity.getGuid());
			}
		}

		// ??????taskCar
		TaskOrdersCarEntity taskCar = taskOrdersCarDao.getOrdersCarByOriginalcid(origilatId);
		if (null != taskCar) {
			taskOrdersCarDao.delete(taskCar.getId());
		}

		// ??????area
		taskOrdersAreaDao.deleteOrdersAreaFromOrdersId(origilatId);

//		// ??????????????????
//		grimlockServiceHelper.removeEvent(taskCar.getOrdersCID());
		
		try{
			TaskOrdersEntity driverTaskOrder = taskOrdersDao.getEndOrders(origilatId);
			//????????????gps????????????
			heartBeatServiceHelper.delCarGpsEvent(3, driverTaskOrder.getOrdersCID());
			//????????????????????????
			heartBeatServiceHelper.delHeartBeatEvent(1, taskCar.getOrdersCID());
			heartBeatServiceHelper.delHeartBeatEvent(2, taskCar.getOrdersCID());
		}catch(Exception e){
			log.error("????????????gps??????????????????????????????????????????originalcid:"+origilatId);
			e.printStackTrace();
		}
	}

	/**
	 * ??????: deleteOrders ??????: ????????????
	 */
	private void deleteOrders(String ordersId, String ownType) {
		TValide.notNull(ordersId, TaskErrorEnums.TaskErrors.ORDER_ID_IS_NOT_NULL);
		// ????????????
		TaskOrdersEntity entity = taskOrdersDao.select(ordersId);
		TValide.notNull(entity, TaskErrorEnums.TaskErrors.ORDER_IS_NOT_EXIST);

		Byte cancleState = entity.getIsCancle();
		Byte isFirst = entity.getIsFirstOrders();
		Integer ordersStatus = entity.getOrdersStatus();
		//?????????????????????????????????????????????????????????
		if(StringUtils.isBlank(ownType)){
			Boolean deleteFirstState = isFirst.equals(CommonProperties.BYTE_ONE)
					&& ordersStatus.equals(CommonProperties.INTEGER_ONE);
			Boolean deleteCancleState = cancleState.equals(CommonProperties.BYTE_ONE);

			TValide.isTrue((deleteCancleState || deleteFirstState), TaskErrorEnums.TaskErrors.ORDER_IS_NOT_FIT_DELETE_RULE);
		}
		taskOrdersDao.delete(entity.getOrdersCID());
	}






	/**
	 * ???????????????
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
		//???????????????????????????
		if(taskCar.getIsCancleEntrust().equals(CommonProperties.BYTE_ONE)){
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_CANCLE);
		}
		//???????????????????????????
		if(taskCar.getIsDelete().equals(CommonProperties.BYTE_ONE)){
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_DELETED);
		}
		//????????????
		TaskOrdersAreaEntity taskArea = taskOrdersAreaDao.select(taskAreaId);
		//?????????????????????
		List<TaskOrdersEntity> taskList = taskOrdersDao.getTaskOrdersByOriginalcid(taskCar.getOriginalcid());
        //????????????
		List<TaskDynamicEntity> taskDynamicList = taskDynamicDao.getDynamicListByAreaGUid(taskId, action,taskAreaId);
		if(taskDynamicList.size()>0){
			TaskDynamicEntity taskDynamic = taskDynamicList.get(0);
			taskDynamic.setDynamictime(updateTime);
			taskDynamicDao.update(taskDynamic);
		}
		//??????????????????
		if(action.equals(EventAction.DriverDeparting.getValue())){
			//???????????????
			for(TaskOrdersEntity taskOrder : taskList){
				taskOrder.setStartAreaActualLeavTime(updateTime);
				taskOrdersDao.update(taskOrder);
			}
			//??????
			taskArea.setActualLeavTime(updateTime);
			//?????????
			taskCar.setActualLeaveTime(updateTime);
		}

		//???????????????????????????
		else if(action.equals(EventAction.DriverArrive.getValue())){
			taskArea.setActualArriveTime(updateTime);
		}
		//??????????????????
		else if(action.equals(EventAction.DriverArriveEndArea.getValue())){
			for(TaskOrdersEntity taskOrder : taskList){
				taskOrder.setEndAreaActualArriveTime(updateTime);
				taskOrdersDao.update(taskOrder);
			}
			taskArea.setActualArriveTime(updateTime);
			taskCar.setActualArriveTime(updateTime);
		}
		//??????
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
