package com.logibeat.cloud.helper.impl;

import com.logibeat.cloud.common.enumtype.TaskStatus;
import com.logibeat.cloud.common.vo.CarGpsOperationVo;
import com.logibeat.cloud.common.vo.CarGpsSourceVo;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.errorenum.DriverTaskErrorEnums;
import com.logibeat.cloud.helper.CarGpsServiceHelper;
import com.logibeat.cloud.persistent.dao.DriverTaskDao;
import com.logibeat.cloud.persistent.dao.TaskOperationTimeDao;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.persistent.entity.TaskOperationTimeEntity;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarGpsServiceHelperImpl implements CarGpsServiceHelper {



    @Autowired
    private TaskOperationTimeDao taskOperationTimeDao;


    @Autowired
    private DriverTaskDao driverTaskDao;


    /**
     * 任务跟踪
     * @param taskId
     * @return
     */
    @Override
    public List<CarGpsSourceVo> getTaskHistoryOrbit(String taskId){
        List<CarGpsSourceVo> resultList = new ArrayList<>();


        //任务单
        DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
        if(null == driverTask){
            throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
        }

        Integer taskStatus = driverTask.getTaskStatus();


        List<TaskOperationTimeEntity> taskOperationTimeList = taskOperationTimeDao.getTaskOperationTimeList(taskId);
        List<Integer> sourceTypeList =  taskOperationTimeList.parallelStream().map(TaskOperationTimeEntity::getDeviceType).distinct().collect(Collectors.toList());
        for(Integer source : sourceTypeList){
            CarGpsSourceVo carGpsSourceVo = new CarGpsSourceVo();
            carGpsSourceVo.setSource(source.toString());
            List<CarGpsOperationVo> operationVoList = new ArrayList<>();
            //根据来源过滤
            List<TaskOperationTimeEntity> sourceTimeList = taskOperationTimeList.parallelStream().filter(p->p.getDeviceType().equals(source)).collect(Collectors.toList());
            for(int i = 0;i<sourceTimeList.size();i++){
                TaskOperationTimeEntity sourceTime = sourceTimeList.get(i);
                CarGpsOperationVo carGpsOperationVo = new CarGpsOperationVo();
                String fromOperation=DateUtil.dateTOString(sourceTime.getStartTime(),DateUtil.YYYY_MM_DD_HH_mm_ss);;
                String endOperation =DateUtil.dateTOString(sourceTime.getEndTime(),DateUtil.YYYY_MM_DD_HH_mm_ss);;
                if(TaskStatus.Runing.getValue().equals(taskStatus)){
                    if(sourceTimeList.size() == 1){
                        endOperation = DateUtil.dateTOString(new Date(),DateUtil.YYYY_MM_DD_HH_mm_ss);
                    }
                    else{
                        if(i == sourceTimeList.size()-1){
                            endOperation = DateUtil.dateTOString(new Date(),DateUtil.YYYY_MM_DD_HH_mm_ss);
                        }
                    }
                }
                carGpsOperationVo.setFrom(fromOperation);
                carGpsOperationVo.setTo(endOperation);
                carGpsOperationVo.setKey(sourceTime.getKeyWord());
                carGpsOperationVo.setEquipment(sourceTime.getEquipment());
                operationVoList.add(carGpsOperationVo);
            }
            carGpsSourceVo.setOperationVoList(operationVoList);
            resultList.add(carGpsSourceVo);
        }
        return  resultList;
    }
}
