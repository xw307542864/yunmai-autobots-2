package com.logibeat.cloud.helper.impl;

import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.helper.OutServiceHelper;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.util.tools.RandomTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wilson on 2017/11/30.
 */
@Service
public class OutServiceHelperImpl implements OutServiceHelper {


    @Autowired
    private EnterpriseInfoDao enterpriseInfoDao;

    @Autowired
    private NetworkDao networkDao;

    @Autowired
    private SyspersonDao syspersonDao;

    @Autowired
    private ShareLogDao shareLogDao;

    @Autowired
    private CarImportDao carImportDao;

    /**
     * 推送路况到中通
     *
     * @param
     * @param
     * @param
     * @param dynamicEntity
     */
    public void pushTrafficToZto(TaskOrdersCarEntity taskCar, TaskDynamicEntity dynamicEntity, TaskOrdersAreaEntity areaEntity) {

        if(taskCar == null || areaEntity == null){
            return ;
        }

        String entId = "";
        if(dynamicEntity != null){
            entId = dynamicEntity.getEntId();
        }

        if(taskCar != null){
            entId = taskCar.getEntrustEntid();
        }
        if(StringUtils.isBlank(entId)){
            return;
        }
        EnterpriseInfoEntity entInfo = enterpriseInfoDao.select(entId);
        if(entInfo == null || StringUtils.isBlank(entInfo.getOwnType()) || !entInfo.getOwnType().equals("100000")){
            return ;
        }

        if(!taskCar.getTaskCarType().equals("100000")){
            return ;
        }

        NetworkEntity network = networkDao.select(areaEntity.getNetWorkGuid());
        if(network == null){
            return ;
        }

//        QueueTaskEntity queueTaskQueue = new QueueTaskEntity();
//        queueTaskQueue.setOwnTaskId(taskCar.getOutKeyId());
//        queueTaskQueue.setCarBrand(taskCar.getPlateNumber());
//        queueTaskQueue.setIsStart(areaEntity.getIsStartPoint().intValue());
//        queueTaskQueue.setIsArrive(areaEntity.getIsEndPoint().intValue());
//        queueTaskQueue.setLeaveTime(DateUtil.timestamp2Str(areaEntity.getActualLeavTime()));
//        queueTaskQueue.setArriveTime(DateUtil.timestamp2Str(areaEntity.getActualArriveTime()));
//        queueTaskQueue.setSiteCode(StringUtils.isBlank(network.getNetworkCode()) ? "0" : network.getNetworkCode());
//        queueTaskQueue.setSiteName(network.getNetworkName());
//        grimlockServiceHelper.callTask(queueTaskQueue);
    }

    /**
     * 推送路况到星软
     *
     * @param
     * @param picURL
     * @param
     * @param dynamicEntity
     */
    public void pushTrafficToStar(TaskOrdersCarEntity taskCar, String picURL,
                                  TaskDynamicEntity dynamicEntity, TaskOrdersAreaEntity areaEntity) {
        if(taskCar == null){
            return ;
        }

//        QueueDynamicEntity feedback = new QueueDynamicEntity();
//        feedback.setTaskId(StringUtils.isNotBlank(taskCar.getOutKeyId()) ? taskCar.getOutKeyId() : taskCar.getId());
//        feedback.setOwnTaskId(StringUtils.isNotBlank(taskCar.getOutKeyId()) ? taskCar.getOutKeyId() : taskCar.getId());
//        feedback.setFeedbackId(dynamicEntity.getGuid());
//        feedback.setFeedbackTime(DateUtil.dateTOString(dynamicEntity.getDynamictime(), DateUtil.YYYY_MM_DD_HH_mm_ss));
//        feedback.setFeedbackDetails(dynamicEntity.getContent());
//        feedback.setFeedbackType(dynamicEntity.getAction());
//        feedback.setCarBrand(taskCar.getPlateNumber());
//        feedback.setAreaCode(areaEntity.getSortNum().toString());
//        Double lat = null == dynamicEntity.getLat() ? 0 : dynamicEntity.getLat();
//        feedback.setLatitude((int) (lat * 3600000));
//        Double lng = null == dynamicEntity.getLng() ? 0 : dynamicEntity.getLng();
//        feedback.setLongitude((int) (lng * 3600000));
//        feedback.setAddress(dynamicEntity.getAddress());
//
//        SystemPersonEntity systemPersonEntity = syspersonDao.select(dynamicEntity.getPersonId());
//        feedback.setDriverMobile(systemPersonEntity.getLoginName());
//        feedback.setDriverName(systemPersonEntity.getNiChen());
//        feedback.setPicUrl(picURL);
//
//        grimlockServiceHelper.callTaskFeedback(feedback);

        // 添加推送日志（目前只记录成功的，后期整体调整）
        ShareLogEntity shareLogEntity = new ShareLogEntity();
        shareLogEntity.setGuid(RandomTool.getGUId());
        shareLogEntity.setObjectId(dynamicEntity.getGuid());
        shareLogEntity.setShareTime(DateUtil.getSqlTime());
        shareLogEntity.setObjectType(ConstantUtil.INTEGER_CODE_TWO); // 路况
        shareLogDao.insert(shareLogEntity);
    }




    /**
     * 推送行程到星软
     *
     * @param entDriverId
     * @param piOrderCid
     * @param entCarPlate
     * @param dynamicEntity
     * @param startNetworkCode
     * @param endNetworkCode
     * @param systemPersonEntity
     * @param
     * @param picUrl
     */
    public void pushTravelToStar(String entDriverId, String piOrderCid, String entCarPlate,
                                 TaskDynamicEntity dynamicEntity, String startNetworkCode, String endNetworkCode,
                                 SystemPersonEntity systemPersonEntity, boolean isStartPoint, String picUrl) {

        CarImportEntity carImportEntity = carImportDao.select(entDriverId);
        if (null != carImportEntity) {
//            QueueTaskEntity travel = new QueueTaskEntity();
//            travel.setTaskId(piOrderCid);
//            travel.setCarBrand(entCarPlate);
//            travel.setStartAreaCode(startNetworkCode);
//            travel.setEndAreaCode(endNetworkCode);
//            travel.setDriverName(systemPersonEntity.getNiChen());
//            travel.setDriverMobile(systemPersonEntity.getLoginName());
//            travel.setCompanyId(carImportEntity.getSrcEntId());
//            travel.setStartDate(DateUtil.dateTOString(DateUtil.getSqlTime(), DateUtil.YYYY_MM_DD_HH_mm_ss));
//            travel.setArriveTime("");
//            travel.setIsStart(isStartPoint ? 1 : 0);
//            travel.setIsArrive(0);
//            travel.setIsGps(0);
//            travel.setAddress(dynamicEntity.getAddress());
//
//            if (null != dynamicEntity.getLat()) {
//                Double lat = dynamicEntity.getLat() * 3600000;
//                travel.setLatitude(lat.intValue());
//            }
//            if (null != dynamicEntity.getLng()) {
//                Double lng = dynamicEntity.getLng() * 3600000;
//                travel.setLongitude(lng.intValue());
//            }
//
//            // 反馈信息
//            travel.setFeedbackType(dynamicEntity.getAction());
//            travel.setFeedbackId(dynamicEntity.getGuid());
//            travel.setFeedbackDetails(dynamicEntity.getContent());
//            if (StringUtils.isNotBlank(picUrl)) {
//                int idx = picUrl.lastIndexOf(",");
//                picUrl = picUrl.substring(0, idx);
//            }
//            travel.setPicUrl(picUrl);
//            grimlockServiceHelper.callTask(travel);

            // 添加推送日志
             ShareLogEntity shareLogEntity = new ShareLogEntity();
             shareLogEntity.setGuid(RandomTool.getGUId());
             shareLogEntity.setObjectId(piOrderCid);
             shareLogEntity.setShareTime(DateUtil.getSqlTime());
             shareLogEntity.setObjectType(ConstantUtil.INTEGER_CODE_ONE); //任务
             shareLogDao.insert(shareLogEntity);
        }
    }

}
