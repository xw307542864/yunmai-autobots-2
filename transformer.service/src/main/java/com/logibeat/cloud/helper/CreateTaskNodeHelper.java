package com.logibeat.cloud.helper;

import com.google.gson.Gson;
import com.logibeat.cloud.common.enumtype.BizNodeAction;
import com.logibeat.cloud.common.enumtype.LoadType;
import com.logibeat.cloud.common.enumtype.TaskType;
import com.logibeat.cloud.core.dto.TrackNodeDTO;
import com.logibeat.cloud.dto.task.CreateTaskNodeDto;
import com.logibeat.cloud.dto.task.HandleTaskDto;
import com.logibeat.cloud.mq.producer.BizNodeProducer;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.persistent.entity.DriverTaskPointEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskNodeHelper {

    private Logger logger = LoggerFactory.getLogger(CreateTaskNodeHelper.class);





    @Autowired
    private BizNodeProducer bizNodeProducer;


    /**
     * 去装货
     * @param handleTaskDto
     * @param driverTask
     * @param taskPoint
     */
    public void goLoad(HandleTaskDto handleTaskDto, DriverTaskEntity  driverTask, DriverTaskPointEntity taskPoint, Integer relationOredrStatus){
        CreateTaskNodeDto createTaskNodeDto = new CreateTaskNodeDto();
        createTaskNodeDto.setTaskId(driverTask.getGuid());
        createTaskNodeDto.setPointId(taskPoint.getGuid());
        createTaskNodeDto.setToLat(taskPoint.getLat());
        createTaskNodeDto.setToLng(taskPoint.getLng());
        createTaskNodeDto.setToAddress(taskPoint.getAddress());
        createTaskNodeDto.setPlanArriveTime(handleTaskDto.getPlanArriveTime());
        createTaskNodeDto.setDriverTask(driverTask);
        createTaskNodeDto.setPointRelationOrderStatus(relationOredrStatus);
        createTaskNodeDto.setPointRelationOrderId(taskPoint.getOrderGuid());
        createNode(createTaskNodeDto,BizNodeAction.GO_LOAD.getValue());

    }




    /**
     * 去卸货
     * @param handleTaskDto
     * @param driverTask
     * @param taskPoint
     */
    public void goUnLoad(HandleTaskDto handleTaskDto,  DriverTaskEntity  driverTask, DriverTaskPointEntity taskPoint){
        CreateTaskNodeDto createTaskNodeDto = new CreateTaskNodeDto();
        createTaskNodeDto.setTaskId(driverTask.getGuid());
        createTaskNodeDto.setPointId(taskPoint.getGuid());
        createTaskNodeDto.setToLat(taskPoint.getLat());
        createTaskNodeDto.setToLng(taskPoint.getLng());
        createTaskNodeDto.setToAddress(taskPoint.getAddress());
        createTaskNodeDto.setPlanArriveTime(handleTaskDto.getPlanArriveTime());
        createTaskNodeDto.setDriverTask(driverTask);
        createTaskNodeDto.setPointRelationOrderId(taskPoint.getOrderGuid());
         createNode(createTaskNodeDto,BizNodeAction.GO_UNLOAD.getValue());
    }

    /**
     * 确认到达装货点
     * @param driverTask
     * @param taskPoint
     */
    public void arrive(DriverTaskEntity  driverTask,
                           DriverTaskPointEntity taskPoint,Integer pointRelationOrderStatus,boolean sign,Integer action){
        CreateTaskNodeDto createTaskNodeDto = new CreateTaskNodeDto();
        createTaskNodeDto.setTaskId(driverTask.getGuid());
        createTaskNodeDto.setPointId(taskPoint.getGuid());
        createTaskNodeDto.setToLat(taskPoint.getLat());
        createTaskNodeDto.setToLng(taskPoint.getLng());
        createTaskNodeDto.setToAddress(taskPoint.getAddress());
        if(TaskType.Collect.getValue().equals(driverTask.getTaskType())){
            if(LoadType.UnLoad.getValue().equals(taskPoint.getType())){
                createTaskNodeDto.setToAddress(taskPoint.getName());
            }
        }
        else if(TaskType.Deliery.getValue().equals(driverTask.getTaskType())){
            if(LoadType.Load.getValue().equals(taskPoint.getType())){
                createTaskNodeDto.setToAddress(taskPoint.getName());
            }
        }
        else if(TaskType.Stowage.getValue().equals(driverTask.getTaskType())){
            createTaskNodeDto.setToAddress(taskPoint.getName());
        }

        createTaskNodeDto.setPointRelationOrderId(taskPoint.getOrderGuid());
        createTaskNodeDto.setPointRelationOrderStatus(pointRelationOrderStatus);
        createTaskNodeDto.setSign(sign);
        createTaskNodeDto.setDriverTask(driverTask);
        createNode(createTaskNodeDto,action);
    }


    /**
     * 开始/完成 装卸货
     * @param driverTask
     * @param taskPoint
     */
    public void loadAndUnLoad(DriverTaskEntity  driverTask, DriverTaskPointEntity taskPoint,Integer action){
        CreateTaskNodeDto createTaskNodeDto = new CreateTaskNodeDto();
        createTaskNodeDto.setTaskId(driverTask.getGuid());
        createTaskNodeDto.setPointId(taskPoint.getGuid());
        createTaskNodeDto.setToLat(taskPoint.getLat());
        createTaskNodeDto.setToLng(taskPoint.getLng());
        createTaskNodeDto.setToAddress(taskPoint.getAddress());
        if(TaskType.Collect.getValue().equals(driverTask.getTaskType())){
            if(LoadType.UnLoad.getValue().equals(taskPoint.getType())){
                createTaskNodeDto.setToAddress(taskPoint.getName());
            }
        }
        else if(TaskType.Deliery.getValue().equals(driverTask.getTaskType())){
            if(LoadType.Load.getValue().equals(taskPoint.getType())){
                createTaskNodeDto.setToAddress(taskPoint.getName());
            }
        }
        else if(TaskType.Stowage.getValue().equals(driverTask.getTaskType())){
            createTaskNodeDto.setToAddress(taskPoint.getName());
        }
        createTaskNodeDto.setPointRelationOrderId(taskPoint.getOrderGuid());
        createTaskNodeDto.setDriverTask(driverTask);
        createNode(createTaskNodeDto,action);

    }


    /**
     * 派车（生成派车单）
     * @param driverTask
     */
    public void sendCar(DriverTaskEntity  driverTask){
        CreateTaskNodeDto createTaskNodeDto = new CreateTaskNodeDto();
        createTaskNodeDto.setDriverTask(driverTask);
        createNode(createTaskNodeDto,BizNodeAction.SNED_CAR.getValue());

    }




    /**
     * 生成节点
     * @param createTaskNodeDto
     * @param action
     */
    private void createNode(CreateTaskNodeDto createTaskNodeDto,Integer action){
        TrackNodeDTO trackNodeDTO = new TrackNodeDTO();
        trackNodeDTO.setAction(action);
        trackNodeDTO.setBizJson(new Gson().toJson(createTaskNodeDto));
        logger.info("生成节点"+new Gson().toJson(trackNodeDTO));
        bizNodeProducer.send(new Gson().toJson(trackNodeDTO));
        //mnsServiceHelper.pushQueue(new Gson().toJson(trackNodeDTO), queueName,mnsClient);

    }


}
