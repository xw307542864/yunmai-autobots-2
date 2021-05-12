package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.dto.task.CreateDriverTaskDto;
import com.logibeat.cloud.dto.task.DriverTaskCargoDto;
import com.logibeat.cloud.dto.task.DriverTaskPointDto;
import com.logibeat.cloud.persistent.dao.DriverTaskInfoDao;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.persistent.entity.DriverTaskInfoEntity;
import com.logibeat.cloud.services.DriverTaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverTaskInfoServiceImpl implements DriverTaskInfoService {


    @Autowired
    private DriverTaskInfoDao taskInfoDao;


    /**
     * 保存任务单详情
     * @param createDriverTaskDto
     * @param driverTask
     */
    @Override
    public void saveTaskInfo(CreateDriverTaskDto createDriverTaskDto, DriverTaskEntity driverTask){
        //任务装卸点
        List<DriverTaskPointDto> taskPointList  = createDriverTaskDto.getTaskPointList();
        //始发点
        DriverTaskPointDto originalPoint  = taskPointList.get(0);
        //到达点
        DriverTaskPointDto destionPoint = taskPointList.get(taskPointList.size()-1);
        DriverTaskInfoEntity driverTaskInfo = new DriverTaskInfoEntity();
        driverTaskInfo.setGuid(driverTask.getGuid());
        driverTaskInfo.setTaskId(driverTask.getGuid());
        driverTaskInfo.setOriginalNetworkId(originalPoint.getNetWorkId());
        driverTaskInfo.setOriginalNetworkName(originalPoint.getName());
        driverTaskInfo.setOriginalCityCode(originalPoint.getCityCode());
        driverTaskInfo.setOriginalCity(originalPoint.getCityName());
        driverTaskInfo.setOriginalAddress(originalPoint.getAddress());
        driverTaskInfo.setOriginalLat(originalPoint.getLat());
        driverTaskInfo.setOriginalLng(originalPoint.getLng());
        driverTaskInfo.setOriginatContact(originalPoint.getContact());
        driverTaskInfo.setOriginatPhone(originalPoint.getContactMobile());
        driverTaskInfo.setDestinationNetworkId(destionPoint.getNetWorkId());
        driverTaskInfo.setDestinationNetworkName(destionPoint.getName());
        driverTaskInfo.setDestinationCityCode(destionPoint.getCityCode());
        driverTaskInfo.setDestinationCity(destionPoint.getCityName());
        driverTaskInfo.setDestinationAddress(destionPoint.getAddress());
        driverTaskInfo.setDestinationLat(destionPoint.getLat());
        driverTaskInfo.setDestinationLng(destionPoint.getLng());
        driverTaskInfo.setDestinationContact(destionPoint.getContact());
        driverTaskInfo.setDestinationPhone(destionPoint.getContactMobile());
        driverTaskInfo.setQueueNumber(createDriverTaskDto.getQueueNumber());
        driverTaskInfo.setOutDestinationId(createDriverTaskDto.getOutDestinationId());
        //货物
        DriverTaskCargoDto taskCargoDto = createDriverTaskDto.getTaskCargo();
        if(taskCargoDto!=null) {
        	driverTaskInfo.setCargoName(taskCargoDto.getName());
            driverTaskInfo.setCargoNum(taskCargoDto.getNum());
            driverTaskInfo.setCargoVolume(taskCargoDto.getVolume());
            driverTaskInfo.setCargoWeight(taskCargoDto.getWeight());
        }
        taskInfoDao.insertDriverTaskInfo(driverTaskInfo);
    }






}
