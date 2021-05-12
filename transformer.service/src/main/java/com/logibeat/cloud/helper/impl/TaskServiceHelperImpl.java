package com.logibeat.cloud.helper.impl;

import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.helper.TaskServiceHelper;
import com.logibeat.cloud.persistent.entity.TaskOrdersCarEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaskServiceHelperImpl implements TaskServiceHelper {

    public static final String IS_TIME_OUT = "isTimeOut";
    public static final String BEYOND_TIME = "beyondTime";

    public Map<String, Object> getRunTime(TaskOrdersCarEntity taskCar) {
        Map<String, Object> map = new HashMap<>();
        boolean isTimeOut = false;
        String beyondTime = "";
        Integer minuteResult = 0;
        Date startTime = taskCar.getActualLeaveTime();
        Date endTime = taskCar.getActualArriveTime();
        // 指定到达时间
        if (taskCar.getEffectiveTime() == null || taskCar.getEffectiveTime() == 0) {
            Date planTime = taskCar.getPlanArriveTime();
            if (planTime != null && endTime != null) {
                Long second = DateUtil.getDistanceTime(planTime, endTime);
                second = Long.valueOf(second.longValue() / 1000L);
                minuteResult = second.intValue();
            }
        }
        // 在途时长
        else {
            Integer effectiveTime = taskCar.getEffectiveTime();
            // 全程行驶时长
            if (startTime != null && endTime != null) {
                Long distance = DateUtil.getDistanceTime(startTime, endTime);
                distance = Long.valueOf(distance.longValue() / 1000L);
                minuteResult = distance.intValue() - effectiveTime * 60;
            }
        }
        // 超时
        Integer resultTime = Math.abs(minuteResult);
        Integer day = resultTime / (24 * 60 * 60);
        Integer hour = (resultTime % (24 * 60 * 60)) / (60 * 60);
        Integer minute = (resultTime % (60 * 60)) / 60;
        if (day != 0) {
            beyondTime += day + "天";
        }
        if (hour != 0) {
            beyondTime += hour + "时";
        }
        if (minute != 0) {
            beyondTime += minute + "分";
        }
        if (minuteResult <= -60) {
            beyondTime = "提前" + beyondTime;
        } else if (minuteResult >= 60) {
            beyondTime = "超时" + beyondTime;
            isTimeOut = true;
        } else if (minuteResult > -60 && minuteResult < 60) {
            beyondTime = "准时到达";
        }

        map.put(IS_TIME_OUT, isTimeOut);
        map.put(BEYOND_TIME, beyondTime);

        return map;
    }
}
