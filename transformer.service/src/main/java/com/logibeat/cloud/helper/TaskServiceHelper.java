package com.logibeat.cloud.helper;

import com.logibeat.cloud.persistent.entity.TaskOrdersCarEntity;

import java.util.Map;

public interface TaskServiceHelper {

    Map<String, Object> getRunTime(TaskOrdersCarEntity taskCar);
}
