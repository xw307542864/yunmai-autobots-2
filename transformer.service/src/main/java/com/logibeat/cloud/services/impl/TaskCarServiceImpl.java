package com.logibeat.cloud.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logibeat.cloud.common.cache.enumType.CacheDBType;
import com.logibeat.cloud.common.cache.impl.RedisCacheManager;
import com.logibeat.cloud.common.enumtype.*;
import com.logibeat.cloud.common.valide.TValide;
import com.logibeat.cloud.common.vo.*;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.dto.BaseRequestDTO;
import com.logibeat.cloud.core.dto.DynamicGpsDTO;
import com.logibeat.cloud.core.dto.HeartBeatDto;
import com.logibeat.cloud.core.dto.TrackNodeDTO;
import com.logibeat.cloud.core.properties.CommonProperties;
import com.logibeat.cloud.dto.ChangeStatusResDto;
import com.logibeat.cloud.dto.DriverGpsResDto;
import com.logibeat.cloud.dto.DynamicDto;
import com.logibeat.cloud.dto.EventTaskDto;
import com.logibeat.cloud.errorenum.DynamicErrorEnums;
import com.logibeat.cloud.errorenum.TaskErrorEnums;
import com.logibeat.cloud.helper.EventSpaceServiceHelper;
import com.logibeat.cloud.helper.HeartBeatServiceHelper;
import com.logibeat.cloud.helper.OutServiceHelper;
import com.logibeat.cloud.helper.TaskServiceHelper;
import com.logibeat.cloud.helper.impl.TaskServiceHelperImpl;
import com.logibeat.cloud.msg.dto.ImSysDto;
import com.logibeat.cloud.msg.enumtype.MessageBizType;
import com.logibeat.cloud.msg.enumtype.MessageType;
import com.logibeat.cloud.msg.extra.TaskExtraDto;
import com.logibeat.cloud.msg.sender.ImMsgSender;
import com.logibeat.cloud.msg.sender.JetfireMsgSender;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.remote.CarSender;
import com.logibeat.cloud.remote.OperationTimeSender;
import com.logibeat.cloud.services.*;
import com.logibeat.cloud.util.tools.GsonUtil;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.StringConverUtil;
import com.logibeat.cloud.util.tools.TypeCastUtil;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.util.tools.pageMdl.Page;
import com.logibeat.cloud.util.tools.pageMdl.PageResultDTO;
import com.logibeat.cloud.util.tools.properties.PropertiesUtil;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import com.logibeat.jetfire.client.template.SmsTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by wilson on 2017/8/8.
 */
@Service
public class TaskCarServiceImpl implements TaskCarService {

	private static final Logger logger = LoggerFactory.getLogger(TaskCarServiceImpl.class);

	@Autowired
	private EnterpriseInfoDao enterpriseInfoDao;

	@Autowired
	private TaskOrdersCarDao taskOrdersCarDao;

	@Autowired
	private TaskOrdersAreaDao taskOrdersAreaDao;

	@Autowired
	private TaskOrdersCarInfoDao taskOrdersCarInfoDao;

	@Autowired
	private SyspersonDao syspersonDao;


	@Autowired
	private TaskOrdersGoodsDao taskOrdersGoodsDao;

	@Autowired
	private CarsDao carsDao;

	@Autowired
	private EnterpriseCooperatePerDao enterpriseCooperatePerDao;

	@Autowired
	private EnterpriseCooperateCarDao enterpriseCooperateCarDao;

	@Autowired
	private DictDao dictDao;

	@Autowired
	private DynamicService dynamicService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskOrdersDao taskOrdersDao;

	@Autowired
	private OperationTimeSender operationTimeSender;

	@Autowired
	private CarSender carSender;


	@Autowired
	private JetfireMsgSender jetfireMsgSender;

	@Autowired
	private EventSpaceServiceHelper eventSpaceServiceHelper ;

	@Autowired
	private HeartBeatServiceHelper heartBeatServiceHelper;

	@Autowired
	private GpsPackService gpsPackService;


	@Autowired
	private OutServiceHelper outServiceHelper;

	@Autowired
	private SysSettingDao sysSettingDao;




	Gson gson = new Gson();


	@Autowired
	private TaskServiceHelper taskServiceHelper;
	
	@Autowired
	private GrimlockService grimlockService;

    @Autowired
	private TaskExecutor taskExecutor;

    @Autowired
	private EnterpriseCensorDao enterpriseCensorDao;

    @Autowired
    private ImMsgSender imMsgSender;


    @Autowired
    private EntStarSoftDao entStarSoftDao;


	@Autowired
	private RedisCacheManager redisCacheManager;

	/**
	 * 进行中的任务
	 * 
	 * @param personId
	 * @param entId
	 * @param page
	 * @return
	 */
	public List<OrdersInfoVo> getRunningList(String personId, String entId, Integer status, Page page) {
		List<OrdersInfoVo> ordersInfoVoList = new ArrayList<>();
		PageHelper.startPage(page.getPageIndex(),page.getPageSize());
		List<TaskOrdersCarEntity> taskCarList = taskOrdersCarDao.getRunningTaskList(personId, entId, status);
		for (TaskOrdersCarEntity taskCar : taskCarList) {
			OrdersInfoVo ordersInfoVo = getTaskCarVo(taskCar);
			Map<String, Object> map = taskServiceHelper.getRunTime(taskCar);
			ordersInfoVo.setTimeOut(map.get(TaskServiceHelperImpl.BEYOND_TIME).toString());
			ordersInfoVo.setIsTimeOut((boolean) map.get(TaskServiceHelperImpl.IS_TIME_OUT));

			// 中通
//			if (StringUtils.isNotBlank(taskCar.getOutKeyId())) {
//				ordersInfoVo.setBarcode(taskCar.getOutKeyId());
//				ordersInfoVo.setBarcodeSymbology(BarcodeSymbology.CODE_128.getDescription());
//			}
			// 渣土车订单,普通运单需要展示二维码
			if (OrdersType.MUCK_GeneralOrder.getValue().equals(taskCar.getTaskCarType())) {
				ordersInfoVo.setQrCode(getQrCode(taskCar.getCode()));
			}
			ordersInfoVoList.add(ordersInfoVo);
		}
		return ordersInfoVoList;
	}


	/**
	 * 待发车
	 * @param personId
	 * @param page
	 * @return
	 */
	public List<OrdersInfoVo> getWaitDepartList(String personId, Page page){
		List<OrdersInfoVo> ordersInfoVoList = new ArrayList<>();
		PageHelper.startPage(page.getPageIndex(),page.getPageSize());
		List<TaskOrdersCarEntity> taskCarList = taskOrdersCarDao.getWaitDepartTaskList(personId);
		for (TaskOrdersCarEntity taskCar : taskCarList) {
			OrdersInfoVo ordersInfoVo = getTaskCarVo(taskCar);
			if(taskCar.getIsRead().equals(ConstantUtil.BYTE_FALSE)){
				ordersInfoVo.setShowRed(true);
				ordersInfoVo.setRead(false);
			}
			ordersInfoVoList.add(ordersInfoVo);
		}
		return ordersInfoVoList;
	}


	/**
	 * 任务附加项（数字 红点展示）
	 * @param personId
	 * @return
	 */
	public DriverTaskItemVo getDriverTaskItem(String personId){
		DriverTaskItemVo driverTaskItemVo = new DriverTaskItemVo();
		List<TaskOrdersCarEntity> taskCarList = taskOrdersCarDao.getWaitDepartTaskList(personId);
		driverTaskItemVo.setWaitDepartNumber(taskCarList.size());
		taskCarList = taskCarList.parallelStream().filter(p -> p.getIsRead().equals(ConstantUtil.BYTE_FALSE)).collect(Collectors.toList());
		driverTaskItemVo.setNoReadNum(taskCarList.size());
		if (taskCarList.size()>0){
			driverTaskItemVo.setShowRed(true);
		}
		return  driverTaskItemVo;
	}





	/**
	 * 历史任务
	 * 
	 * @param personId
	 * @param entId
	 * @param page
	 * @return
	 */
	public List<OrdersInfoVo> getHistoryList(String personId, String entId, Page page) {
		List<OrdersInfoVo> ordersInfoVoList = new ArrayList<>();
		PageHelper.startPage(page.getPageIndex(),page.getPageSize());
		List<TaskOrdersCarEntity> taskCarList = taskOrdersCarDao.getHistoryTaskList(personId, entId);
		for (TaskOrdersCarEntity taskCar : taskCarList) {
			OrdersInfoVo ordersInfoVo = getTaskCarVo(taskCar);
			Map<String, Object> map = taskServiceHelper.getRunTime(taskCar);
			ordersInfoVo.setTimeOut(map.get(TaskServiceHelperImpl.BEYOND_TIME).toString());
			ordersInfoVo.setIsTimeOut((boolean) map.get(TaskServiceHelperImpl.IS_TIME_OUT));
			ordersInfoVoList.add(ordersInfoVo);
		}

		return ordersInfoVoList;
	}

	/**
	 * 全部任务单列表
	 * 
	 * @param personId
	 * @param entId
	 * @param page
	 * @return
	 */
	public PageResultDTO<OrdersInfoVo> getAllList(String personId, String entId, Page page,Integer status) {
		List<OrdersInfoVo> ordersInfoVoList = new ArrayList<>();
        PageHelper.startPage(page.getPageIndex(),page.getPageSize());
        
        List<TaskOrdersCarEntity> taskCarList =  taskOrdersCarDao.getAllTaskList(personId, entId,status);
        
		for (TaskOrdersCarEntity taskCar : taskCarList) {
			OrdersInfoVo ordersInfoVo = getTaskCarVo(taskCar);
			
			Map<String, Object> map = taskServiceHelper.getRunTime(taskCar);
			ordersInfoVo.setTimeOut(map.get(TaskServiceHelperImpl.BEYOND_TIME).toString());
			ordersInfoVo.setIsTimeOut((boolean) map.get(TaskServiceHelperImpl.IS_TIME_OUT));

			// 渣土车订单,普通运单需要展示二维码
			if (OrdersType.MUCK_GeneralOrder.getValue().equals(taskCar.getTaskCarType())) {
				ordersInfoVo.setQrCode(getQrCode(taskCar.getCode()));
			}
			
			if(taskCar.getIsCancleEntrust().equals(ConstantUtil.BYTE_FALSE)) {//未撤回
				if (OrdersStatus.WaitRun.getValue()==taskCar.getOrdersStatus() || OrdersStatus.QueueWait.getValue()==taskCar.getOrdersStatus() || OrdersStatus.Product.getValue()==taskCar.getOrdersStatus() || OrdersStatus.StopProduct.getValue()==taskCar.getOrdersStatus() || OrdersStatus.SEND.getValue()==taskCar.getOrdersStatus()) {
					//待发车
					if(taskCar.getIsRead().equals(ConstantUtil.BYTE_FALSE)){
						ordersInfoVo.setShowRed(true);
						ordersInfoVo.setRead(false);
					}
				}
			}
			
			ordersInfoVoList.add(ordersInfoVo);
		}
		
		//封装分页信息
        PageInfo pageInfo = new PageInfo(ordersInfoVoList);
        PageResultDTO<OrdersInfoVo> pageResultDTO = new PageResultDTO(ordersInfoVoList,new Page().setTotalCount(pageInfo.getTotal()));
        return  pageResultDTO;
	}

	/**
	 * 任务单详情
	 * 
	 * @param taskCarId
	 * @return
	 */
	public OrdersDriverDetailVo getInfo(String taskCarId) {
		OrdersDriverDetailVo ordersDetailVo = new OrdersDriverDetailVo();
		TaskOrdersCarEntity taskCar = taskOrdersCarDao.select(taskCarId);
		if (null == taskCar) {
			return ordersDetailVo;
		}
		Byte isCancleEntrust = taskCar.getIsCancleEntrust();
		Byte isDelete = taskCar.getIsDelete();
		//已撤回 不能看详情
		if(isCancleEntrust.equals(ConstantUtil.BYTE_TRUE) || isDelete.equals(ConstantUtil.BYTE_TRUE)){
			throw  new ServiceException(TaskErrorEnums.TaskErrors.ISCANCELENTRUST);
		}
		ordersDetailVo.setShippingManifestsId(taskCar.getShippingManifestsId());
		ordersDetailVo.setOrdersCID(taskCar.getOrdersCID());
		ordersDetailVo.setOrderCarId(taskCarId);
		ordersDetailVo.setCode(taskCar.getCode());
		ordersDetailVo.setOrdersStatus(taskCar.getOrdersStatus());
		ordersDetailVo.setDuration(taskCar.getEffectiveTime());
		ordersDetailVo.setDistance(taskCar.getPlanMileage());
		ordersDetailVo.setPlanMileage(taskCar.getPlanMileage());
		ordersDetailVo.setPlateNumber(taskCar.getPlateNumber());
		ordersDetailVo.setStartAreaPlanLeavTime(taskCar.getPlanLeaveTime());
		ordersDetailVo.setStartAreaActualLeavTime(taskCar.getActualLeaveTime());
		ordersDetailVo.setEndAreaPlanArriveTime(taskCar.getPlanArriveTime());
		ordersDetailVo.setEndAreaActualArriveTime(taskCar.getActualArriveTime());
		ordersDetailVo.setOrderType(taskCar.getTaskCarType());
		ordersDetailVo.setOrdersRemark(taskCar.getRemark());
		ordersDetailVo.setIsDelete(TypeCastUtil.byteToBoolean(taskCar.getIsDelete()));
		ordersDetailVo.setIsCancleEntrust(TypeCastUtil.byteToBoolean(taskCar.getIsCancleEntrust()));
		ordersDetailVo.setActualMileage(taskCar.getActualMileage());
		ordersDetailVo.setOrderType(taskCar.getTaskCarType());
        if(taskCar.getIsRead().equals(ConstantUtil.BYTE_FALSE)){
            ordersDetailVo.setRead(false);
        }

		// 任务单网点信息
		List<OrdersAreaInfoVo> areaInfoList = this.getOrdersArea(taskCar.getOriginalcid(), false);
		ordersDetailVo.setAreaInfoList(areaInfoList);

		// 货物
		ordersDetailVo.setGoodsInfo(getTaskGoods(taskCar.getOriginalcid()));

		// 派车企业
		if (StringUtils.isNotBlank(taskCar.getEntrustEntid())) {
			EnterpriseInfoEntity entrustEnt = enterpriseInfoDao.select(taskCar.getEntrustEntid());
			if (null != entrustEnt && entrustEnt.getIssham().equals(ConstantUtil.BYTE_FALSE)) {
				ContactInfoVo entContactInfoVo = new ContactInfoVo();
				entContactInfoVo.setID(taskCar.getEntrustEntid());
				entContactInfoVo.setName(entrustEnt.getName());
				entContactInfoVo.setLogo(entrustEnt.getLogo());
				entContactInfoVo.setAuditStatus(entrustEnt.getAuditStatus());
				entContactInfoVo.setIsAutoArrival(TypeCastUtil.byteToBoolean(taskCar.getIsAutoArrive()));
				entContactInfoVo.setIsAutoSendCar(TypeCastUtil.byteToBoolean(taskCar.getIsAutoDepart()));
				SystemPersonEntity entrustPerson = syspersonDao.select(entrustEnt.getOwerPersonID());
				if (null != entrustPerson) {
					entContactInfoVo.setPhone(entrustPerson.getMobilePhoneNumber());
				}
				ordersDetailVo.setEntrustEnt(entContactInfoVo);
			}
		}

		String entrustPersonId = taskCar.getEntrustPersonID();
		if (StringUtils.isNotBlank(entrustPersonId)) {
			SystemPersonEntity creatPerson = syspersonDao.select(entrustPersonId);
			ContactInfoVo pdContactInfoVo = new ContactInfoVo();
			EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao.getEntOwnerPerson(taskCar.getEntrustEntid(),
					entrustPersonId);
			if (null != entPer) {
				pdContactInfoVo.setName(entPer.getNameRemark());
			}
			pdContactInfoVo.setID(creatPerson.getPersonID());
			pdContactInfoVo.setLogo(creatPerson.gethDpic());
			pdContactInfoVo.setPhone(creatPerson.getMobilePhoneNumber());
			ordersDetailVo.setEntrustPerson(pdContactInfoVo);
		}

		// 中通
//		if (StringUtils.isNotBlank(taskCar.getOutKeyId())) {
//			ordersDetailVo.setBarcode(taskCar.getOutKeyId());
//			ordersDetailVo.setBarcodeSymbology(BarcodeSymbology.CODE_128.getDescription());
//		}
		// 渣土车订单,普通运单需要展示二维码
		if (OrdersType.MUCK_GeneralOrder.getValue().equals(taskCar.getTaskCarType())) {
			ordersDetailVo.setQrCode(getQrCode(taskCar.getCode()));
		}

		// 已取消 和 行程可删除
		Integer taskCarType = taskCar.getTaskCarType();
		if(ConstantUtil.BYTE_TRUE.equals(isCancleEntrust) || ConstantUtil.INTEGER_CODE_ONE.equals(taskCarType)){
			ButtonShowVo buttonShowVo = new ButtonShowVo();
			buttonShowVo.setShowDelete(true);
			ordersDetailVo.setButton(buttonShowVo);
		}

		TaskOrdersCarInfoEntity taskCarInfo = taskOrdersCarInfoDao.getByTaskId(taskCarId);
		if (null != taskCarInfo && StringUtils.isNotBlank(taskCarInfo.getCreatePersonId())) {
			SystemPersonEntity creatPerson = syspersonDao.select(taskCarInfo.getCreatePersonId());
			ContactInfoVo pdContactInfoVo = new ContactInfoVo();

			String entId = taskCar.getEntrustEntid();
			if(StringUtils.isBlank(entId)){
				EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao.getEntOwnerPerson(taskCar.getEntrustEntid(),
						taskCarInfo.getCreatePersonId());
				if (null != entPer) {
					pdContactInfoVo.setName(entPer.getNameRemark());
				}
			}

			pdContactInfoVo.setID(creatPerson.getPersonID());
			pdContactInfoVo.setLogo(creatPerson.gethDpic());
			pdContactInfoVo.setPhone(creatPerson.getMobilePhoneNumber());
			ordersDetailVo.setEntrustPerson(pdContactInfoVo);
		}

		Map<String, Object> map = taskServiceHelper.getRunTime(taskCar);
		ordersDetailVo.setTimeOut(map.get(TaskServiceHelperImpl.BEYOND_TIME).toString());
		ordersDetailVo.setIsTimeOut((boolean) map.get(TaskServiceHelperImpl.IS_TIME_OUT));

		return ordersDetailVo;
	}

	/**
	 * 任务单基本信息
	 * 
	 * @param taskCar
	 * @return
	 */
	public OrdersInfoVo getTaskCarVo(TaskOrdersCarEntity taskCar) {
		OrdersInfoVo ordersInfoVo = new OrdersInfoVo();
		ordersInfoVo.setOrderCarId(taskCar.getId());
		ordersInfoVo.setOrdersCID(taskCar.getId()); // 兼容老版本
		ordersInfoVo.setTaskCode(taskCar.getCode());
		ordersInfoVo.setOrdersStatus(taskCar.getOrdersStatus());
		ordersInfoVo.setIsCancleEntrust(TypeCastUtil.byteToBoolean(taskCar.getIsCancleEntrust()));
		ordersInfoVo.setDuration(taskCar.getEffectiveTime());
		ordersInfoVo.setStartAreaPlanLeavTime(taskCar.getPlanLeaveTime());
		ordersInfoVo.setEndAreaPlanArriveTime(taskCar.getPlanArriveTime());
		ordersInfoVo.setStartAreaActualLeavTime(taskCar.getActualLeaveTime());
		ordersInfoVo.setEndAreaActualArriveTime(taskCar.getActualArriveTime());
		ordersInfoVo.setOrderType(taskCar.getTaskCarType());
		ordersInfoVo.setDistance(taskCar.getPlanMileage());
		ordersInfoVo.setPlanMileage(taskCar.getPlanMileage());
		ordersInfoVo.setActualMileage(taskCar.getActualMileage());
		ordersInfoVo.setIsAutoSendCar(TypeCastUtil.byteToBoolean(taskCar.getIsAutoDepart()));
		ordersInfoVo.setIsAutoArrival(TypeCastUtil.byteToBoolean(taskCar.getIsAutoArrive()));
		ordersInfoVo.setExceptionSendTask(taskCar.getExceptionSendTask());
		ordersInfoVo.setExceptionArriveTask(taskCar.getExceptionArriveTask());
		ordersInfoVo.setOrderType(taskCar.getTaskCarType());
		ordersInfoVo.setQueueNumber(taskCar.getQueueNumber());
		//承运企业
		if (StringUtils.isNotBlank(taskCar.getEntrustEntid())) {
			EnterpriseInfoEntity entrustEnt = enterpriseInfoDao.select(taskCar.getEntrustEntid());
			if (null != entrustEnt && entrustEnt.getIssham().equals(ConstantUtil.BYTE_FALSE)) {
				ordersInfoVo.setEntrustNameType(NameType.EnterpriseName.getValue());
				ordersInfoVo.setEntrustName(entrustEnt.getName());
				// }
				ordersInfoVo.setEntrustTime(taskCar.getAddTime());
				ordersInfoVo.setEntrustName(entrustEnt.getName());
				ordersInfoVo.setEntrustEntName(entrustEnt.getName());
				ordersInfoVo.setEntrustEntID(entrustEnt.getId());
				ordersInfoVo.setEntLogo(entrustEnt.getLogo());
			}
			if (null != entrustEnt && entrustEnt.getIssham().equals(ConstantUtil.BYTE_TRUE)) {
				ordersInfoVo.setEntrustNameType(OrdersType.CreateRouteNoEnt.getValue());
				ordersInfoVo.setEntrustName(taskCar.getFirstPersonName());
			}
		}

		// 任务单网点信息
		List<OrdersAreaInfoVo> areaInfoList = this.getOrdersArea(taskCar.getOriginalcid(), false);
		ordersInfoVo.setAreaInfoList(areaInfoList);

		// 是否同城
		int startAreaRegionCode = areaInfoList.parallelStream()
				.filter(p -> ConstantUtil.INTEGER_CODE_ZERO.equals(p.getSortNum()) && null != p.getRegionCode())
				.mapToInt(OrdersAreaInfoVo::getRegionCode).max().orElse(0);
		int endAreaSortNum = areaInfoList.parallelStream().mapToInt(OrdersAreaInfoVo::getSortNum).max().orElse(0);
		int endAreaRegionCode = areaInfoList.parallelStream()
				.filter(p -> p.getSortNum() == endAreaSortNum  && null != p.getRegionCode())
				.mapToInt(OrdersAreaInfoVo::getRegionCode).max().orElse(0);
		boolean isSameCity = startAreaRegionCode/100 == endAreaRegionCode/100;
		ordersInfoVo.setIsSameCity(isSameCity);

		// 货物信息
		GoodsInfoVo goodsInfoVo = getTaskGoods(taskCar.getOriginalcid());
		ordersInfoVo.setGoodsInfo(goodsInfoVo);

		return ordersInfoVo;
	}

	// 渣土车应用二维码url获取
	private String getQrCode(String taskOrderNo) {
		String url = sysSettingDao.getValue("QRCODE_URL_MUCK");
		if (StringUtils.isNotBlank(url)) {
			return url + "?taskOrderNo=" + taskOrderNo;
		}
		return null;
	}


	/**
	 * 任务单-货物
	 * 
	 * @param orilatId
	 * @return
	 */
	public GoodsInfoVo getTaskGoods(String orilatId) {
		TaskOrdersGoodsEntity taskOrdersGoodsEntity = taskOrdersGoodsDao.getEntityByOrdersCid(orilatId);
		if (null != taskOrdersGoodsEntity) {
			GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
			goodsInfoVo.setGoodsTypeDictGUID(taskOrdersGoodsEntity.getGoodsTypeDictGUID());
			goodsInfoVo.setName(taskOrdersGoodsEntity.getName());
			goodsInfoVo.setVolume(
					null == taskOrdersGoodsEntity.getVolume() ? null : taskOrdersGoodsEntity.getVolume());
			goodsInfoVo.setWeight(
					null == taskOrdersGoodsEntity.getWeight() ? null : taskOrdersGoodsEntity.getWeight());

			if(null != taskOrdersGoodsEntity.getNum()){
				goodsInfoVo.setNumber(taskOrdersGoodsEntity.getNum());
			}

			return goodsInfoVo;
		}
		return null;
	}

	
	
	public List<GoodsInfoVo> findTaskGoods(String shippingManifestsId){
		if(StringUtils.isNotBlank(shippingManifestsId)) {
			
		}
		return null;
	}

	/**
	 * 返回司机当前任务或者车辆
	 */
	@Override
	public List<ChoiceCarVo> choiceCar(BaseRequestDTO baseRequestDTO){
		List<ChoiceCarVo> list = new ArrayList<ChoiceCarVo>();
		String baseUserId = baseRequestDTO.getBaseUserId();
		// 所有在途任务
		List<TaskOrdersCarEntity> taskCarList = taskOrdersCarDao.getRunningTaskList(baseRequestDTO.getBaseUserId(), null, null);
		if(taskCarList.size()>0){
			taskCarList = taskCarList.parallelStream().filter(p -> p.getOrdersStatus().equals(OrdersStatus.Runing.getValue())).collect(Collectors.toList());
		}
		taskCarList = taskCarList.parallelStream().filter(p -> p.getOrdersStatus().equals(OrdersStatus.Runing.getValue())).collect(Collectors.toList());
		for (TaskOrdersCarEntity taskCar : taskCarList) {
			TaskOrdersCarInfoEntity taskInfo = taskOrdersCarInfoDao.getByTaskId(taskCar.getId());
			ChoiceCarVo vo = new ChoiceCarVo();
			vo.setCarOrTaskType(ConstantUtil.INTEGER_CODE_ONE);
			vo.setOrdersCID(taskCar.getOrdersCID());
			vo.setCarID(taskCar.getCarID());
			vo.setStartPoint(taskInfo.getOriginatNetworkName());
			vo.setEndPoint(taskInfo.getDestinationNetworkName());
			vo.setPlateNumber(taskCar.getPlateNumber());
			list.add(vo);
		}
		//获取该企业自有车
		String coopType = CoopType.Car.getValue()+"|"+CoopType.SelfCar.getValue();
		List<DriverEntVo> driverEntList =this.getDriverCarList(baseUserId, coopType);
		if(driverEntList.size() == 0){
			return list;
		}
		for (DriverEntVo driverEntVo : driverEntList) {
			if(StringUtils.isNotBlank(driverEntVo.getCarId()) && StringUtils.isNotBlank(driverEntVo.getEntName())){
				ChoiceCarVo vo = new ChoiceCarVo();
				vo.setCarOrTaskType(ConstantUtil.INTEGER_CODE_ZERO);
				vo.setCarID(driverEntVo.getCarId());
				vo.setPlateNumber(driverEntVo.getPlateNumber());
				vo.setEntName(driverEntVo.getEntName());
				list.add(vo);
			}
		}

		driverEntList =  driverEntList.parallelStream().filter(p ->StringUtils.isBlank(p.getEntName())).collect(Collectors.toList());
		if(driverEntList.size()>0){
			for(DriverEntVo selfDriverCar : driverEntList){
				if(null != selfDriverCar){
					ChoiceCarVo vo = new ChoiceCarVo();
					vo.setCarOrTaskType(ConstantUtil.INTEGER_CODE_ZERO);
					vo.setCarID(selfDriverCar.getCarId());
					vo.setPlateNumber(selfDriverCar.getPlateNumber());
					vo.setEntName("自己的车");
					list.add(vo);
				}
			}
		}
		return list;
	}


	private List<OrdersAreaInfoVo> getOrdersArea(String orderCid, boolean isCheck) {
		List<OrdersAreaInfoVo> resultList = new ArrayList<>();

		List<TaskOrdersAreaEntity> areaList = taskOrdersAreaDao.getOrdersAreaByCid(orderCid);

		for (TaskOrdersAreaEntity taskOrdersAreaEntity : areaList) {
			OrdersAreaInfoVo ordersAreaInfoVo = new OrdersAreaInfoVo();
			// 网点联系人
			ordersAreaInfoVo.setContactName(taskOrdersAreaEntity.getContact());
			ordersAreaInfoVo.setContactPhone(taskOrdersAreaEntity.getContactphone());
			ordersAreaInfoVo.setContactAddress(taskOrdersAreaEntity.getAddress());
			ordersAreaInfoVo.setPlanLeavTime(taskOrdersAreaEntity.getPlanLeavTime());
			ordersAreaInfoVo.setActualLeavTime(taskOrdersAreaEntity.getActualLeavTime());
			ordersAreaInfoVo.setPlanArriveTime(taskOrdersAreaEntity.getPlanArriveTime());
			ordersAreaInfoVo.setActualArriveTime(taskOrdersAreaEntity.getActualArriveTime());
			ordersAreaInfoVo.setGUID(taskOrdersAreaEntity.getGuid());
			ordersAreaInfoVo.setAreaName(taskOrdersAreaEntity.getName());
			ordersAreaInfoVo.setLat(taskOrdersAreaEntity.getLat());
			ordersAreaInfoVo.setLng(taskOrdersAreaEntity.getLng());
			ordersAreaInfoVo.setSortNum(taskOrdersAreaEntity.getSortNum());
			ordersAreaInfoVo.setPointLineGUID(taskOrdersAreaEntity.getPointLineGUID());
			ordersAreaInfoVo.setRegionCode(taskOrdersAreaEntity.getRegionCode());
			ordersAreaInfoVo.setIsEndPoint(TypeCastUtil.byteToBoolean(taskOrdersAreaEntity.getIsEndPoint()));
			ordersAreaInfoVo.setIsStartPoint(TypeCastUtil.byteToBoolean(taskOrdersAreaEntity.getIsStartPoint()));
			ordersAreaInfoVo.setRegionCode(taskOrdersAreaEntity.getRegionCode());
			if (isCheck) {
				if (taskOrdersAreaEntity.getIsStartPoint().equals(ConstantUtil.BYTE_TRUE)
						|| taskOrdersAreaEntity.getIsEndPoint().equals(ConstantUtil.BYTE_TRUE)) {
					resultList.add(ordersAreaInfoVo);
				}
			} else {
				resultList.add(ordersAreaInfoVo);
			}
		}
		return resultList;
	}

	private List<DriverEntVo> getDriverCarList(String personId, String coopType) {
		List<DriverEntVo> resultList = new ArrayList<>();
		List<EnterpriseCooperateCarEntity> entCarList = enterpriseCooperateCarDao.getDriverEntCarList(personId, coopType);
		for (EnterpriseCooperateCarEntity entCar : entCarList) {
			//过滤停用和注销的企业
			EnterpriseCensorEntity censorEntity = enterpriseCensorDao.getCensorEntityByEntId(entCar.getEntid());
			if(censorEntity != null && (censorEntity.getAccountState() == 3||censorEntity.getAccountState() == 2)){
				continue;
			}

			DriverEntVo driverEntVo = new DriverEntVo();
			//企业信息
			EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(entCar.getEntid());
			driverEntVo.setEntId(enterpriseInfoEntity.getId());
			driverEntVo.setEntName(enterpriseInfoEntity.getName());
			driverEntVo.setEntLogo(enterpriseInfoEntity.getLogo());
			driverEntVo.setAuditStatus(enterpriseInfoEntity.getAuditStatus());

			//车辆信息
			CarsEntity carsEntity = carsDao.select(entCar.getCarid());
			driverEntVo.setCarId(carsEntity.getCarID());
			driverEntVo.setCarLength(dictDao.getDictNameByGuid(carsEntity.getCarLengthDictGUID()));
			driverEntVo.setCarCoachType(dictDao.getDictNameByGuid(carsEntity.getCoachTypeDictGUID()));
			driverEntVo.setPlateNumber(carsEntity.getPlateNumber());
			driverEntVo.setRatedLoad(carsEntity.getRatedLoad());
			driverEntVo.setRatedVolume(carsEntity.getRatedVolume());
			if (StringUtils.isNotBlank(entCar.getFirstDriverPersonID())) {
				if (entCar.getFirstDriverPersonID().equals(personId)) {
					driverEntVo.setIsFirstDriver(ConstantUtil.TRUE);
				} else {
					driverEntVo.setIsFirstDriver(ConstantUtil.FALSE);
				}
			} else {
				driverEntVo.setIsFirstDriver(ConstantUtil.FALSE);
			}


			EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao.getCoopByEntAndPersonByDelete(entCar.getEntid(), personId);
			if (null != entPer) {
				driverEntVo.setNameRemark(entPer.getNameRemark());
			}
			resultList.add(driverEntVo);
		}
		return resultList;
	}

	/**
	 * 任务发车/到达
	 * @param event
	 * @param baseRequestDTO
	 */
	@Transactional(propagation= Propagation.REQUIRES_NEW)
	public void sendTaskEvent(String event, BaseRequestDTO baseRequestDTO) {
		TValide.notNull(event, DynamicErrorEnums.MESSAGE_DTO_FAIL);
		EventTaskDto eventTaskDto = (EventTaskDto) GsonUtil.fromJson(event, EventTaskDto.class);
		String personId = baseRequestDTO.getBaseUserId();
		if(StringUtils.isNotBlank(personId) && !personId.equals("null")){
			personId = baseRequestDTO.getBaseUserId();
		}else{
			personId = eventTaskDto.getBaseUserId();
		}
		
		//兼容之前自动单的传参
		if(StringUtils.isBlank(eventTaskDto.getTaskId())) {
			eventTaskDto.setTaskId(eventTaskDto.getOrderCarId()!=null?eventTaskDto.getOrderCarId():eventTaskDto.getOrdersCid());
		}
        

		// 当前操作动作
		Integer eventAction = eventTaskDto.getEventAction();

        // 司机确定发车
        if (eventAction == EventAction.DriverDeparting.getValue()) {
			eventTaskDto.setExceptionSendTask(0);
            driverDepart(personId, eventTaskDto, baseRequestDTO);
        }
        // 司机到达网点（包含自动到达）
        else if (eventAction == EventAction.DriverArriveEndArea.getValue()
                || eventAction == EventAction.DriverArrive.getValue()) {
			eventTaskDto.setExceptionArriveTask(0);
            driverArrive(personId, eventTaskDto, baseRequestDTO);
        }
        // 司机完成任务
        else if (eventAction == EventAction.DriverFinish.getValue()) {
			eventTaskDto.setExceptionArriveTask(0);
            driverFinish(personId, eventTaskDto, baseRequestDTO);
        }
        else if(eventAction == EventAction.ExceptionSendCar.getValue()){
			eventTaskDto.setExceptionSendTask(1);
			driverDepart(personId, eventTaskDto, baseRequestDTO);
		}
		else if(eventAction ==EventAction.ExceptionArrive.getValue()){
			eventTaskDto.setExceptionArriveTask(1);
			driverArrive(personId, eventTaskDto, baseRequestDTO);
		}
		else if(eventAction ==EventAction.ExceptionFinish.getValue()){
			eventTaskDto.setExceptionArriveTask(1);
			driverFinish(personId, eventTaskDto, baseRequestDTO);
		}

    }

    // 验证 当前任务
    private TaskOrdersCarEntity verifyTaskOrdersCarEntity(String taskId, String taskAreaGUID){

        if(StringUtils.isBlank(taskAreaGUID)){
            throw  new ServiceException(TaskErrorEnums.TaskErrors.NETWORK_IS_NOT_NULL);
        }

        //任务详情
        TaskOrdersCarEntity taskCar = taskOrdersCarDao.select(taskId);
        if(null == taskCar){
            throw new ServiceException(TaskErrorEnums.TaskErrors.SEND_ORDER_INFO_IS_EXCEPTION);
        }
        //如果订单已被取消
        if(taskCar.getIsCancleEntrust().equals(CommonProperties.BYTE_ONE)){
            throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_CANCLE);
        }
        //如果订单已被删除
        if(taskCar.getIsDelete().equals(CommonProperties.BYTE_ONE)){
            throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_DELETED);
        }

        return taskCar;
    }

    private void callBack(String personId, EventTaskDto eventTaskDto, BaseRequestDTO baseRequestDTO,
                          TaskOrdersCarEntity taskCar, TaskOrdersAreaEntity taskArea){

        Integer isAutoDepart = taskCar.getIsAutoDepart().intValue();
        Integer isAutoArrive = taskCar.getIsAutoArrive().intValue();
        String taskId = taskCar.getId();
        Integer taskStatus = taskCar.getOrdersStatus();
        String originalcid = taskCar.getOriginalcid();

        String taskAreaGUID = eventTaskDto.getOrdersAreaGuid();
        Integer eventAction = eventTaskDto.getEventAction();
        DynamicGpsDTO gpsDto = eventTaskDto.getGps();
        String picUrls = eventTaskDto.getPicUrls();

        //原任务首单尾单
        List<TaskOrdersEntity> taskList = taskOrdersDao.getTaskOrdersByOriginalcid(originalcid);
        //添加动态
        DynamicDto dynamicDto = dynamicService.preAddDynamic(personId,taskCar,eventTaskDto,taskList);
        TaskDynamicEntity dynamicEntity = dynamicService.addDynamic(dynamicDto);

        //手动单发车、到达后，结束电子围栏
        eventSpaceServiceHelper.finishEventLog(isAutoDepart,isAutoArrive,taskId, eventAction, taskAreaGUID);
        // 注册自动发车/自动到达
        eventSpaceServiceHelper.registByCurrent(taskArea,eventAction,taskCar);

        //删除心跳事件
        //heartBeatServiceHelper.delHeartBeatEvent(eventAction,taskCar);
        //注册监控有在途任务的车辆Gps事件
        registCarGps(eventAction, taskCar, 3);


        // 司机反馈时上传一次GPS
        gpsPackService.sendTaskEventGps(gpsDto, baseRequestDTO.getEquipment(), personId, DateUtil.getCurrentTime());
        // 异步处理推送在途反馈到星软平台
        outServiceHelper.pushTrafficToStar(taskCar, picUrls, dynamicEntity, taskArea);
        // 异步处理推送在途反馈到中通平台
        outServiceHelper.pushTrafficToZto(taskCar, dynamicEntity, taskArea);

        //任务单详情
        TaskOrdersCarInfoEntity taskInfo   = taskOrdersCarInfoDao.getByTaskId(taskId);
        //异步生成节点（针对业务单据）
        callBackStatus(eventAction,taskStatus, gson.toJson(taskCar),gson.toJson(taskInfo),taskAreaGUID);

    }

    @Override
    public void driverDepart(String personId, EventTaskDto eventTask, BaseRequestDTO  baseRequestDTO) {

        String taskId = eventTask.getTaskId();
        String taskAreaGUID = eventTask.getOrdersAreaGuid();
        // 验证并获取当前任务
        TaskOrdersCarEntity taskCar = this.verifyTaskOrdersCarEntity(taskId, taskAreaGUID);

        // 原始订单id
        String originalcid = taskCar.getOriginalcid();

        Byte isAutoDepart = taskCar.getIsAutoDepart();
        //重复时间（发车/到达）
        Timestamp repeatTime = StringConverUtil.getTimestamp(eventTask.getRepeatTime());
        Timestamp startAreaActualLeavTime = null == repeatTime ?  DateUtil.getSqlTime() : repeatTime;
        DynamicGpsDTO gpsDTO = eventTask.getGps();
        String entId = taskCar.getEntrustEntid();
        String carId = taskCar.getCarID();
        String plateNumber = taskCar.getPlateNumber();
        String driverId = taskCar.getFirstDriverPersonID();
        Integer taskCarType =taskCar.getTaskCarType();
        Integer exceptionSendTask=eventTask.getExceptionSendTask();
        Integer exceptionArriveTask=eventTask.getExceptionArriveTask();
        Timestamp actualArriveTime = taskCar.getActualArriveTime();
        if (null != actualArriveTime) {
            // 自动发车单，重复发车
            if(ConstantUtil.BYTE_TRUE.equals(isAutoDepart)){
                //updateTask(taskCar,taskList,OrdersStatus.Runing.getValue(),startAreaActualLeavTime,null);
                taskOrdersCarDao.updateTask(taskId, originalcid, OrdersStatus.Runing.getValue(),
                        startAreaActualLeavTime, null, null, null, null,exceptionSendTask,exceptionArriveTask);
                logger.info(">>>>>>>>>>>>>>重复发车>>>>>>>>>>>>>>重复发车");
                return;
            }
            // 非自动发车单，禁止重复发车
            else{
                throw new ServiceException(DynamicErrorEnums.START_CAN_NOT_REPEAT);
            }
        }

        TaskOrdersAreaEntity taskArea = taskOrdersAreaDao.select(taskAreaGUID);
        Byte isStartArea = taskArea.getIsStartPoint();

        //非中通非途经点才做如此限制
        if(null != taskCarType && !taskCarType.equals(100000) && !taskCarType.equals(666666)
                && isStartArea.equals(ConstantUtil.BYTE_TRUE)){
            //当前司机是否有在途任务
            long driverRunTaskNum = taskOrdersCarDao.getDriverTaskNum(personId,OrdersStatus.Runing.getValue());
            if(driverRunTaskNum>0){
                throw  new ServiceException(DynamicErrorEnums.DRIVER_HAVE_RUNNING_TASK);
            }
            //当前车辆是否有在途任务
            if(StringUtils.isNotBlank(carId)){
                long carRunTaskNum = taskOrdersCarDao.getTaskNum(null, carId, OrdersStatus.Runing.getValue());
                if(carRunTaskNum>0){
                    throw  new ServiceException(DynamicErrorEnums.CAR_HAVE_RUNNING_TASK);
                }
            }
        }
        // 如果发车网点是起始网点，则更新订单表中 起始点实际发车时间
        if (isStartArea.equals(ConstantUtil.BYTE_TRUE)) {
            //updateTask(taskCar,taskList, OrdersStatus.Runing.getValue(),null,gpsDTO);
            taskOrdersCarDao.updateTask(taskId, originalcid, OrdersStatus.Runing.getValue(),
                    startAreaActualLeavTime, null, null, gpsDTO.getLat(), gpsDTO.getLng(),exceptionSendTask,exceptionArriveTask);
        }

        //更新任务网点
        taskArea.setActualLeavTime(startAreaActualLeavTime);
        taskOrdersAreaDao.update(taskArea);

        // 发车之后，车辆状态修改为在途
        if (StringUtils.isNotBlank(carId)) {
            carSender.updateCarStatus(entId,carId,CarStatus.Running.getValue());
            enterpriseCooperateCarDao.updateCoopNum(entId, carId);//更新企业和车辆的合作次数
        }
        // 任务运行时间段
        operationTimeSender.sendCar(carId, plateNumber, driverId,taskId, startAreaActualLeavTime);

        // 异步回调各种service
        taskExecutor.execute(() -> callBack(personId, eventTask, baseRequestDTO, taskCar, taskArea));

        //状态变更放进redis
		changeStatusToRes(OrdersStatus.Runing.getValue(), entId,taskCar.getOutKeyId());

		//重新规划计划到达时间（主要针对待发车的单据）
    }

    @Override
    public void driverArrive(String personId, EventTaskDto eventTask, BaseRequestDTO  baseRequestDTO) {

        String taskId = eventTask.getTaskId();
        String taskAreaGUID = eventTask.getOrdersAreaGuid();
        // 验证并获取当前任务
        TaskOrdersCarEntity taskCar = this.verifyTaskOrdersCarEntity(taskId, taskAreaGUID);

		TaskOrdersCarInfoEntity taskInfo = taskOrdersCarInfoDao.getByTaskId(taskId);


		//GPS
		DynamicGpsDTO gps = eventTask.getGps();

        // 原始订单id
		String originalcid = taskCar.getOriginalcid();

		String entId = taskCar.getEntrustEntid();
        String carId = taskCar.getCarID();
        Integer exceptionSendTask=eventTask.getExceptionSendTask();
		Integer exceptionArriveTask=eventTask.getExceptionArriveTask();
        //重复时间（发车/到达）
        Timestamp repeatTime = StringConverUtil.getTimestamp(eventTask.getRepeatTime());
        //实际发车时间
        Timestamp arriveTime = null == repeatTime ?  DateUtil.getSqlTime() : repeatTime;
        // 重复到达
        Byte isAutoArrive = taskCar.getIsAutoArrive();
        TaskOrdersAreaEntity taskArea = taskOrdersAreaDao.select(taskAreaGUID);
        if (null != taskArea.getActualArriveTime()) {
            // 自动到达单，重复到达
            if(ConstantUtil.BYTE_TRUE.equals(isAutoArrive) && ConstantUtil.BYTE_TRUE.equals(taskArea.getIsEndPoint())){
                //updateTask(taskCar,taskList, OrdersStatus.Arrive.getValue(), arriveTime,null);
                taskOrdersCarDao.updateTask(taskId, originalcid, OrdersStatus.Arrive.getValue(),
                        null, arriveTime, null, null, null,exceptionSendTask,exceptionArriveTask);
                logger.info(">>>>>>>>>>>>>>>>>>>>>>重复到达>>>>>>>>>>>>>>>>>>>>>>重复到达");
                return;
            }
            // 非自动到达单，禁止重复到达
            else{
                throw new ServiceException(DynamicErrorEnums.ARRIVE_CAN_NOT_REPEAT);
            }
        }
        taskArea.setActualArriveTime(arriveTime);
        taskOrdersAreaDao.update(taskArea);
        if (taskArea.getIsEndPoint().equals(ConstantUtil.BYTE_TRUE)) {
            //更新任务单的状态和时间
            //updateTask(taskCar,taskList, OrdersStatus.Arrive.getValue(), repeatTime,eventTask.getGps());
            DynamicGpsDTO gpsDto = eventTask.getGps();
            taskOrdersCarDao.updateTask(taskId, originalcid, OrdersStatus.Arrive.getValue(),
                    null, arriveTime, null, gpsDto.getLat(), gpsDto.getLng(),exceptionSendTask,exceptionArriveTask);

            // 任务运行时间段
            //operationTimeSender.finishTask(taskId, arriveTime);

            //更新车辆状态
            taskExecutor.execute(() -> carSender.updateCarStatus(entId,carId, null));

			//im退推送
			taskImPush(taskId,"3");

			//状态变更放进redis
			changeStatusToRes(OrdersStatus.Arrive.getValue(), entId,taskCar.getOutKeyId());

			//司机上报位置放到redis
			driverGpsToRes(gps.getLat(),gps.getLng(),gps.getAddress(),entId,taskInfo.getOutDestinationId());

        }

        // 异步回调各种service
        taskExecutor.execute(() -> callBack(personId, eventTask, baseRequestDTO, taskCar, taskArea));
    }

    @Override
    public void driverFinish(String personId, EventTaskDto eventTask, BaseRequestDTO  baseRequestDTO) {

        String taskId = eventTask.getTaskId();
        String taskAreaGUID = eventTask.getOrdersAreaGuid();
        // 验证并获取当前任务
        TaskOrdersCarEntity taskCar = this.verifyTaskOrdersCarEntity(taskId, taskAreaGUID);


        TaskOrdersCarInfoEntity taskInfo = taskOrdersCarInfoDao.getByTaskId(taskId);

        // 原始订单id
        String originalcid = taskCar.getOriginalcid();

        //GPS
		DynamicGpsDTO gps = eventTask.getGps();

        String entId = taskCar.getEntrustEntid();
        String carId = taskCar.getCarID();
        Integer taskStatus = taskCar.getOrdersStatus();
        String areaGuid = eventTask.getOrdersAreaGuid();
        Integer exceptionSendTask=eventTask.getExceptionSendTask();
		Integer exceptionArriveTask=eventTask.getExceptionArriveTask();
        TaskOrdersAreaEntity taskArea = taskOrdersAreaDao.select(areaGuid);
        Timestamp finishTime = DateUtil.getSqlTime();
        Integer carStatus;
        if (taskCar.getOrdersStatus().equals(OrdersStatus.Finish.getValue())) {
            throw new ServiceException(DynamicErrorEnums.FINISH_CAN_NOT_REPEAT);
        }
        //更新任务状态/时间
        //updateTask(taskCar,taskList, OrdersStatus.Finish.getValue(), null,eventTask.getGps());
        DynamicGpsDTO gpsDto = eventTask.getGps();
        taskOrdersCarDao.updateTask(taskId, originalcid, OrdersStatus.Finish.getValue(),
                null, finishTime, finishTime, gpsDto.getLat(), gpsDto.getLng(),exceptionSendTask,exceptionArriveTask);
        // 订单在途状态下直接完成任务
        if (taskStatus.equals(OrdersStatus.Runing.getValue())) {
            // 任务运行时间段
            //operationTimeSender.finishTask(taskId, finishTime);

            // 设置车辆状态 若存在待发单 设置为待发 否则设置为空闲
            taskExecutor.execute(() -> carSender.updateCarStatus(entId,carId,null));

            //更新网点信息
            taskArea.setActualArriveTime(DateUtil.getSqlTime());
            taskOrdersAreaDao.update(taskArea);

            //im退推送
			taskImPush(taskId,"3");

			//单据状态变更放进redis
			changeStatusToRes(OrdersStatus.Arrive.getValue(), entId,taskCar.getOutKeyId());

			//司机位置放进redis
			driverGpsToRes(gps.getLat(),gps.getLng(),gps.getAddress(),entId,taskInfo.getOutDestinationId());
			logger.info(">>>>>>>>>>>司机位置添加到redis>>>>>>>>>>>>>>>>>>entId:"+entId);


		}

        // 异步回调各种service
        taskExecutor.execute(() -> callBack(personId, eventTask, baseRequestDTO, taskCar, taskArea));
    }


	/**
	 * 状态变更 放到redis
	 * @param toStatus
	 * @param entId
	 * @param outKeyId
	 */
    private void changeStatusToRes(Integer toStatus,String entId,String outKeyId){
    	EntStarSoftEntity entSatrSoft = entStarSoftDao.getByStar(entId);
    	if(null != entSatrSoft){
    		if(entSatrSoft.getChangeStatus().equals(ConstantUtil.INTEGER_CODE_ONE)){
				String key = ConstantUtil.Change_Status_Res+entSatrSoft.getStarsoftId();
				ChangeStatusResDto changeStatusResDto = new ChangeStatusResDto();
				changeStatusResDto.setId(RandomTool.getGUId());
				changeStatusResDto.setEntKeyId(entSatrSoft.getStarsoftId());
				changeStatusResDto.setCurrentStatus(toStatus);
				changeStatusResDto.setOrdersId(outKeyId);
				changeStatusResDto.setTime(DateUtil.getSqlTime().toString());
				List<ChangeStatusResDto> changeStatusList = redisCacheManager.getRedisCache(key, CacheDBType.QueueDB);
				if (null != changeStatusList && changeStatusList.size() > 0) {
					changeStatusList.add(changeStatusResDto);
					redisCacheManager.setRedisCache(key, changeStatusList, CacheDBType.QueueDB);
				}
				else{
					changeStatusList = new ArrayList<>();
					changeStatusList.add(changeStatusResDto);
					redisCacheManager.setRedisCache(key, changeStatusList, CacheDBType.QueueDB);
				}
				logger.info(">>>>>>>>>>>状态变更添加到redis>>>>>>>>>>>>>>>>>>changeStatusResDto:"+changeStatusResDto);
			}
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





	/**
	 * 司机发车/到达 状态回溯（业务单据）
	 * @param eventAction
	 * @param taskStatus
	 * @param bizJson
	 * @param bizinfoJson
	 */
	private void callBackStatus(Integer eventAction,Integer taskStatus,String bizJson,String bizinfoJson,String taskAreaId){
		//任务单据节点生成
		Integer action = null;
		Integer status = null;
		//司机发车
	    if(eventAction.equals(EventAction.DriverDeparting.getValue())||eventAction.equals(EventAction.ExceptionSendCar.getValue())){
	      action = 2015;
	      status = OrdersStatus.Runing.getValue();
	    }
	    //司机到达途经点
	    else if(eventAction.equals(EventAction.DriverArrive.getValue())){
	      action = 2016;
	      status = OrdersStatus.Runing.getValue();
	    }
	    //司机到达终点
	    else if(eventAction.equals(EventAction.DriverArriveEndArea.getValue())||eventAction.equals(EventAction.ExceptionArrive.getValue())){
	      action = 2016;
	      status = OrdersStatus.Arrive.getValue();
	    }
	    //司机完成任务
	    else if((eventAction.equals(EventAction.DriverFinish.getValue())||eventAction.equals(EventAction.ExceptionFinish.getValue()))
	        && taskStatus.equals(OrdersStatus.Runing.getValue())){
	      action = 2016;
	      status = OrdersStatus.Arrive.getValue();
	    }
		if(null != action){
			TrackNodeDTO trackNodeDTO = new TrackNodeDTO();
			trackNodeDTO.setAction(action);
			trackNodeDTO.setContentRemark(taskAreaId);
			trackNodeDTO.setBizJson(bizJson);
			trackNodeDTO.setBizInfoJson(bizinfoJson);
			trackNodeDTO.setNodeStatus(status);
			String queueName = PropertiesUtil.getStringByKey("aliyun.mns.queuename.biznode", PropertiesUtil.PropertiesConfig.QTZ.getPropertyName());
			//mnsServiceHelper.pushQueue(new Gson().toJson(trackNodeDTO), queueName,mnsClient);
		}
	}


	/**
	 * 注册有在途任务的车辆gps事件
	 * @param eventAction
	 * @param taskCar
	 * @param type
	 */
	public void registCarGps(Integer eventAction, TaskOrdersCarEntity taskCar ,int type) {
		if(taskCar.getTaskCarType() != null && (taskCar.getTaskCarType() == 100000 || taskCar.getTaskCarType() == 666666)){
			EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.getEntByZtoEntId("100000");
			EnterpriseCooperateCarEntity entity = null;
			String carId = taskCar.getCarID();
			if(StringUtils.isNotBlank(carId)){
				entity = enterpriseCooperateCarDao.getSelfCarByEntId(taskCar.getCarID(), enterpriseInfoEntity.getId());
			}
			//如果不是中通的企业车辆，则注册车辆Gps事件
			if(StringUtils.isBlank(carId) || entity == null){
				if(taskCar.getIsAutoDepart() != null && taskCar.getIsAutoDepart() == 1){
					if(eventAction == 12){
						HeartBeatDto heartBeatDto = new HeartBeatDto();
						heartBeatDto.setCarId(carId);
						heartBeatDto.setStartTime(DateUtil.getSqlTime());
						heartBeatDto.setEndTime(DateUtil.dateToTimestamp(DateUtil.getTimeAddDate(new Date(), 3)));
						heartBeatDto.setTaskId(taskCar.getOrdersCID());
						heartBeatDto.setType(type);
						heartBeatDto.setPlateNumber(taskCar.getPlateNumber());
						heartBeatDto.setKeyId(taskCar.getFirstDriverPersonID());
						heartBeatDto.setSource(0);
						logger.info(">>>>>>>>>>>开始注册车辆Gps事件>>>>>>>>>>>>>>>>>>heartBeatDto:"+heartBeatDto);
						heartBeatServiceHelper.regCarGpsEvent(heartBeatDto);
						logger.info(">>>>>>>>>>>结束注册车辆Gps事件>>>>>>>>>>>>>>>>>>heartBeatDto:"+heartBeatDto);
					}
					else if(eventAction == 16){
						logger.info(">>>>>>>>>>>删除车辆Gps事件>>>>>>>>>>>>>>>>>>");
						heartBeatServiceHelper.delCarGpsEvent(3, taskCar.getOrdersCID());
					}
				}
			}
		}
	}




    
    public TaskPositionVo getTaskPosiotn(String taskId){
		TaskPositionVo taskPositionVo = new TaskPositionVo();
		TaskOrdersCarInfoEntity taskInfo = taskOrdersCarInfoDao.getByTaskId(taskId);
		TaskOrdersCarEntity task = taskOrdersCarDao.select(taskId);
		if(null != taskInfo){
			String carId = task.getCarID();
			taskPositionVo.setOrigatLat(taskInfo.getOriginatLat());
			taskPositionVo.setOrigatLng(taskInfo.getOriginatLng());
			taskPositionVo.setOrigatAddress(taskInfo.getOriginatAddress());
			taskPositionVo.setDestLat(taskInfo.getDestinationLat());
			taskPositionVo.setDestLng(taskInfo.getDestinationLng());
			taskPositionVo.setDestAddress(taskInfo.getDestinationAddress());
//			CarGpsInfoVo carGpsInfoVo = null;
//			if(StringUtils.isNotBlank(carId)){
//				CarsEntity carsEntity = carsDao.select(carId);
////				carGpsInfoVo = grimlockService.getLastGps(carsEntity);
//			}
//            else{
////				carGpsInfoVo = grimlockService.getLastGps(task.getFirstDriverPersonID(),0);
//			}
//
//			if(null != carGpsInfoVo){
//				taskPositionVo.setCurrentLat(carGpsInfoVo.getLat());
//				taskPositionVo.setCurrentLng(carGpsInfoVo.getLng());
//				taskPositionVo.setCurrentAddress(carGpsInfoVo.getLocationInfo());
//			}
		}
		return  taskPositionVo;

	}


	/**
	 * 任务推送
	 * @param taskId
	 * action
	 */
	public void taskImPush(String taskId,String action){
        Gson formatGson = new GsonBuilder().disableHtmlEscaping().create();
		SmsTemplate smsTemplate = new SmsTemplate();
		ImSysDto imSysDto = new ImSysDto();
		TaskExtraDto taskExtraDto = new TaskExtraDto();
		MessageBizType messageBizType = null;

		TaskOrdersCarEntity taskCar = taskOrdersCarDao.select(taskId);

		TaskOrdersCarInfoEntity taskCarInfo = taskOrdersCarInfoDao.getByTaskId(taskId);
		String firstPersonId = taskCar.getFirstDriverPersonID();
		String secondPersonId = taskCar.getSecondDriverPersonID();
		String linkUrl = null;
		if(action.equals("1")){
			messageBizType = MessageBizType.NEW_TASK_PUSH;
			linkUrl=ConstantUtil.taskInfoPage+"?orderCarId="+taskId;
		}
		else if(action.equals("2")){
			messageBizType = MessageBizType.RETURN_TASK_PUSH;
		}
		else if(action.equals("3")){
			messageBizType = MessageBizType.FINISH_TASK_PUSH;
			linkUrl=ConstantUtil.taskInfoPage+"?orderCarId="+taskId;
		}
		else if(action.equals("4")){
			messageBizType = MessageBizType.NEW_PLAN_CAR_PUSH;
			linkUrl=ConstantUtil.planCarInfoPage+"?orderCarId="+taskId;

		}
		else if(action.equals("5")){
			messageBizType = MessageBizType.PLAN_CAR_CHANGE_PUSH;
			linkUrl=ConstantUtil.planCarInfoPage+"?orderCarId="+taskId;
		}
		else if(action.equals("6")){
			messageBizType = MessageBizType.PLAN_CAR_DELETE_PUSH;
			linkUrl=ConstantUtil.planCarInfoPage+"?orderCarId="+taskId;
		}
		imSysDto.setTitle(messageBizType.getTitle());
		imSysDto.setContent(messageBizType.getContent());
		imSysDto.setEntId(taskCar.getEntrustEntid());
		imSysDto.setEntName(taskCarInfo.getEntrustEntName());
		EnterpriseInfoEntity ent = enterpriseInfoDao.select(taskCar.getEntrustEntid());
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
		taskExtraDto.setLoadPlace(taskCarInfo.getOriginatAddress());
		taskExtraDto.setEntrustEntName(taskCarInfo.getEntrustEntName());
		taskExtraDto.setTaskCarNumber(taskCar.getCode());
		String route = taskCarInfo.getOriginatCity()+"至"+taskCarInfo.getDestinationCity();
		taskExtraDto.setTaskTransportRoute(route);
		taskExtraDto.setTaskStatus(OrdersStatus.find(taskCar.getOrdersStatus()).getDescription());
		taskExtraDto.setLinkUrl(linkUrl);
		if(null != taskCar.getPlanLeaveTime()){
			taskExtraDto.setVehicleTime(DateUtil.dateTOString(taskCar.getPlanLeaveTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
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
	 * 任务状态变更为已读
	 * @param taskCarId
	 */
	public  void read(String taskCarId){
		//任务详情
		TaskOrdersCarEntity taskCar = taskOrdersCarDao.select(taskCarId);
		if(null == taskCar){
			throw new ServiceException(TaskErrorEnums.TaskErrors.SEND_ORDER_INFO_IS_EXCEPTION);
		}
		//如果订单已被取消
		if(taskCar.getIsCancleEntrust().equals(CommonProperties.BYTE_ONE)){
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_CANCLE);
		}
		//如果订单已被删除
		if(taskCar.getIsDelete().equals(CommonProperties.BYTE_ONE)){
			throw new ServiceException(TaskErrorEnums.TaskErrors.ORDER_IS_DELETED);
		}
		taskCar.setIsRead(ConstantUtil.BYTE_TRUE);
		taskOrdersCarDao.update(taskCar);
	}





	/**
	 * 获取单据变更状态
	 * @param resKey
	 * @return
	 */
	public JSONPrompt getChangeStatus(String resKey){
		JSONPrompt jsonPrompt = new JSONPrompt();
		List<ChangeStatusResDto> changeStatusList = redisCacheManager.getRedisCache(resKey, CacheDBType.QueueDB);
		if(null == changeStatusList){
			changeStatusList = new ArrayList<>();
		}
		List<ChangeStatusResDto> resultList = null;
		if(changeStatusList.size()>=100){
			resultList = changeStatusList.subList(0,99);
			jsonPrompt.setGo(true);
		}
		else{
			resultList = changeStatusList;
			jsonPrompt.setGo(false);
		}
		jsonPrompt.setData(resultList);
		List<String> idList = resultList.parallelStream().map(ChangeStatusResDto::getId).collect(Collectors.toList());
		List<ChangeStatusResDto> saveList = changeStatusList.parallelStream().filter(p -> !idList.contains(p.getId())).collect(Collectors.toList());
		redisCacheManager.setRedisCache(resKey,saveList,CacheDBType.QueueDB);
		return  jsonPrompt;
	}


	/**
	 * 获取司机上报位置
	 * @param resKey
	 * @return
	 */
	public JSONPrompt getDriverLocation(String resKey){
		JSONPrompt jsonPrompt = new JSONPrompt();
		List<DriverGpsResDto> driverGpsResDtoList = redisCacheManager.getRedisCache(resKey, CacheDBType.QueueDB);
		if(null == driverGpsResDtoList){
			driverGpsResDtoList = new ArrayList<>();
		}
		List<DriverGpsResDto> resultList = null;
		if(driverGpsResDtoList.size()>=100){
			resultList = driverGpsResDtoList.subList(0,99);
			jsonPrompt.setGo(true);
		}
		else{
			resultList = driverGpsResDtoList;
			jsonPrompt.setGo(false);
		}
		jsonPrompt.setData(resultList);
		List<String> idList = resultList.parallelStream().map(DriverGpsResDto::getId).collect(Collectors.toList());
		List<DriverGpsResDto> saveList = driverGpsResDtoList.parallelStream().filter(p -> !idList.contains(p.getId())).collect(Collectors.toList());
 		redisCacheManager.setRedisCache(resKey,saveList,CacheDBType.QueueDB);
		return  jsonPrompt;
	}


	/**
	 * 自动完成24h已完成的预约单
	 */
	public void autofinishPlanCar(){
		taskOrdersCarDao.autoFinishPlanCar();
	}
}
