package com.logibeat.cloud.remote;

import com.google.gson.Gson;
import com.logibeat.cloud.common.cache.util.JsonMapper;
import com.logibeat.cloud.common.enumtype.CarStatus;
import com.logibeat.cloud.common.enumtype.TaskStatus;
import com.logibeat.cloud.vo.CarShortInfoVo;
import com.logibeat.cloud.core.constant.SettingConstantUtil;
import com.logibeat.cloud.core.dto.CarDTO;
import com.logibeat.cloud.core.tools.StarsoftHttpUtil;
import com.logibeat.cloud.persistent.dao.DriverTaskDao;
import com.logibeat.cloud.persistent.dao.SysSettingDao;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.util.tools.HttpClientUtil;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wilson on 2017/11/30.
 */
@Component
public class CarSender {

    private static final Logger logger = LoggerFactory.getLogger(OperationTimeSender.class);

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private SysSettingDao sysSettingDao;

    @Autowired
    private DriverTaskDao driverTaskDao;


    /**
     * 更新车辆状态
     * @param entId
     * @param carId
     * @param carStatus
     */
    public void updateCarStatus(String entId,String carId,Integer carStatus){
        if(carStatus == null){
            // 设置车辆状态 若存在代发单 设置为待发 否则设置为空闲
            DriverTaskEntity taskParam = new DriverTaskEntity();
            taskParam.setEntId(entId);
            taskParam.setVehicleId(carId);
            taskParam.setTaskStatus(TaskStatus.WaitRun.getValue());
            List<DriverTaskEntity> waitList  = driverTaskDao.selectDriverTaskList(taskParam);
            if (null != waitList && waitList.size()>0) {
                carStatus = CarStatus.Ready.getValue();
            }
            else {
                carStatus = CarStatus.Free.getValue();
            }
        }
        Map<String, String> params = new HashMap<>();
        params.put("carId", carId);
        params.put("entId", entId);
        params.put("carStatus", carStatus.toString());
        String url = sysSettingDao.getValue(SettingConstantUtil.UPDATE_CAR_STATUS);
        logger.info(url);
        logger.info(new Gson().toJson(params));
        taskExecutor.execute(() -> HttpClientUtil.post(url, params));
    }


    /**
     * 添加企业车辆
     * @param carDTO
     */
    public CarShortInfoVo addOrUpdateCar(CarDTO carDTO, String tag){
        CarShortInfoVo carShortInfoVo = null;
        String postUrl = sysSettingDao.getValue(tag);
        String body = JsonMapper.toNonNullJson(carDTO);
        String result = StarsoftHttpUtil.post(postUrl, body);
        JSONPrompt jsonPrompt = JsonMapper.buildNonDefaultMapper().fromJson(result, JSONPrompt.class);
        if(null != jsonPrompt){
            if(jsonPrompt.isSuc()){
                logger.info("车辆添加成功" + " 车牌号：" + carDTO.getPlateNumber() + "-----" + result);
                carShortInfoVo =  JsonMapper.buildNonDefaultMapper().fromJson(JsonMapper.toNonNullJson(jsonPrompt.getData()),CarShortInfoVo.class);
            }
            else{
                logger.error("车辆添加失败], 异常.. 车牌号={}", carDTO.getPlateNumber(), jsonPrompt.getMessage());
            }
        }
        return  carShortInfoVo;
    }


    public void deleteDriverCar(String personId,String handlePersonName,String myVehicleId,Integer historyType,String handleMessage){
        Map<String, String> params = new HashMap<>();
        params.put("myVehicleId", myVehicleId);
        params.put("historyType", historyType.toString());
        params.put("personId", personId);
        params.put("handlePersonName", handlePersonName);
        params.put("handleMessage", handleMessage);
        String url = sysSettingDao.getValue(SettingConstantUtil.DELETE_DRIVER_CAR);
        logger.info(url);
        logger.info(new Gson().toJson(params));
        taskExecutor.execute(() -> HttpClientUtil.post(url, params));
    }





}
