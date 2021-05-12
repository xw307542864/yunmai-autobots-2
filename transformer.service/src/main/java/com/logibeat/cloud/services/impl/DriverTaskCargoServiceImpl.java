package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.common.enumtype.TaskType;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.dto.task.DriverTaskCargoDto;
import com.logibeat.cloud.errorenum.DriverTaskErrorEnums;
import com.logibeat.cloud.msg.sender.BizorderSender;
import com.logibeat.cloud.persistent.dao.DriverTaskCargoDao;
import com.logibeat.cloud.persistent.dao.DriverTaskDao;
import com.logibeat.cloud.persistent.dao.DriverTaskPointDao;
import com.logibeat.cloud.persistent.entity.DriverTaskCargoEntity;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.persistent.entity.DriverTaskPointEntity;
import com.logibeat.cloud.services.DriverTaskCargoService;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverTaskCargoServiceImpl implements DriverTaskCargoService {


    @Autowired
    private DriverTaskCargoDao taskCargoDao;


    @Autowired
    private DriverTaskDao driverTaskDao;


    @Autowired
    private BizorderSender bizorderSender;

    @Autowired
    private DriverTaskPointDao taskPointDao;


    /**
     * 保存任务（派车）货物
     * @param taskCargo
     * @param driverTask
     */
    @Override
    public void saveTaskCargo(DriverTaskCargoDto taskCargo, DriverTaskEntity driverTask){
        DriverTaskCargoEntity driverTaskCargo  = new DriverTaskCargoEntity();
        driverTaskCargo.setGuid(RandomTool.getGUId());
        driverTaskCargo.setTaskId(driverTask.getGuid());
        driverTaskCargo.setName(taskCargo.getName());
        driverTaskCargo.setNum(taskCargo.getNum());
        driverTaskCargo.setSpec(taskCargo.getSpec());
        driverTaskCargo.setMaterial(taskCargo.getMaterial());
        driverTaskCargo.setWeight(taskCargo.getWeight());
        driverTaskCargo.setVolume(taskCargo.getVolume());
        driverTaskCargo.setPics(taskCargo.getPics());
        driverTaskCargo.setRemarks(taskCargo.getRemark());
        driverTaskCargo.setTakeRemars(taskCargo.getTakeRemark());
        driverTaskCargo.setCreateTime(DateUtil.getSqlTime());
        taskCargoDao.insertDriverTaskCargo(driverTaskCargo);
    }


    /**
     *派车单货物
     * @param taskId
     * @return
     */
    @Override
    public JSONPrompt taskCargo(String taskId){
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        Integer taskType = driverTask.getTaskType();
        String relationId = driverTask.getRelationOrderId();
        if(TaskType.Concrete_FAHUO.getValue().equals(taskType)){
            relationId = driverTask.getGuid();
        }
        return bizorderSender.getCargo(relationId,taskType);
    }


    /**
     *
     * 装卸点货物
     * @param pointId
     * @return
     */
    @Override
    public JSONPrompt pointCargo(String pointId){
        DriverTaskPointEntity taskPoint = taskPointDao.selectDriverTaskPointById(pointId);
        if(null == taskPoint){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_POINT_NOT_EXIST);
        }
        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskPoint.getTaskId());
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }
        Integer taskType = driverTask.getTaskType();
        String relationOrderId = null;
        if(TaskType.Stowage.getValue().equals(taskType)){
            relationOrderId = driverTask.getRelationOrderId();
        }
        else if(TaskType.Deliery.getValue().equals(taskType)){
            if(ConstantUtil.INTEGER_CODE_ONE.equals(taskPoint.getStartPoint())){
                relationOrderId = driverTask.getRelationOrderId();
            }
            else{
                relationOrderId = taskPoint.getOrderGuid();
                taskType = TaskType.Consign.getValue();
            }
        }
        else if(TaskType.Collect.getValue().equals(taskType)){
            if(ConstantUtil.INTEGER_CODE_ONE.equals(taskPoint.getEndPoint())){
                relationOrderId = driverTask.getRelationOrderId();
            }
            else{
                relationOrderId = taskPoint.getOrderGuid();
            }
        }
        else if(TaskType.Consign.getValue().equals(taskType)){
            relationOrderId = taskPoint.getOrderGuid();
        }
        else if(TaskType.Assemble.getValue().equals(taskType)){
            relationOrderId = taskPoint.getOrderGuid();
            taskType = TaskType.Consign.getValue();
        }
        else if(TaskType.Concrete_FAHUO.getValue().equals(taskType)){
            relationOrderId = taskPoint.getTaskId();
        }
        return bizorderSender.getCargo(relationOrderId,taskType);
    }
}
