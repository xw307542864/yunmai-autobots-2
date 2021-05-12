package com.logibeat.cloud.services;


import com.logibeat.cloud.dto.task.CreateDriverTaskDto;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;

public interface DriverTaskInfoService {


     void saveTaskInfo(CreateDriverTaskDto createDriverTaskDto, DriverTaskEntity driverTask);
}
