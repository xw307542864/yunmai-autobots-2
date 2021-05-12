package com.logibeat.cloud.services;

import com.logibeat.cloud.dto.task.DriverTaskCargoDto;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;

public interface DriverTaskCargoService {

    /**
     * 保存任务（派车）货物
     * @param taskCargo
     * @param driverTask
     */
    void saveTaskCargo(DriverTaskCargoDto taskCargo, DriverTaskEntity driverTask);


    /**
     * 货物派车单货物
     * @param taskId
     * @return
     */
    JSONPrompt taskCargo(String taskId);


    /**
     * 装卸点对应货物
     * @param pointId
     * @return
     */
    JSONPrompt pointCargo(String pointId);
}
