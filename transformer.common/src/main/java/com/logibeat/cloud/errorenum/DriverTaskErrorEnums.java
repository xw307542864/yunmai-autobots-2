package com.logibeat.cloud.errorenum;

import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

public class DriverTaskErrorEnums {
    public enum TaskErrors implements ExceptionEnums {
        POINT_IS_NULL("800123","装卸点信息不能为空!"),
        VEHICLE_IS_NULL("800124","车辆信息不能为空!"),
        TASK_NOT_EXIST("800125","派车单不存在!"),
        TASK_POINT_NOT_EXIST("800126","装卸点不存在!"),
        NOT_REPEAT("800127","请不要反复操作!"),
        HAS_RUNNING_TASK("800128","有正在执行的任务，不能执行其它任务!"),
        NO_DELETE_TASK("800129","当前状态下不可删除!"),
        FINISH_TASK("800130","任务已完成!");






        private String value;
        private String description;

        TaskErrors(String value, String description){
            this.value = value;
            this.description = description;
        }

        @Override
        public String module() {
            return "CoopEnt";
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String getDescription() {
            return this.description;
        }

    }



}
