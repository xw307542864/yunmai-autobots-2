package com.logibeat.cloud.helper;

import com.logibeat.cloud.persistent.entity.SystemPersonEntity;
import com.logibeat.cloud.persistent.entity.TaskDynamicEntity;
import com.logibeat.cloud.persistent.entity.TaskOrdersAreaEntity;
import com.logibeat.cloud.persistent.entity.TaskOrdersCarEntity;

/**
 * Created by wilson on 2017/11/30.
 */
public interface OutServiceHelper {


     void pushTrafficToZto(TaskOrdersCarEntity taskCar, TaskDynamicEntity dynamicEntity, TaskOrdersAreaEntity areaEntity) ;




     void pushTrafficToStar(TaskOrdersCarEntity taskCar, String picURL, TaskDynamicEntity dynamicEntity, TaskOrdersAreaEntity areaEntity) ;



     void pushTravelToStar(String entDriverId, String piOrderCid, String entCarPlate,
                                  TaskDynamicEntity dynamicEntity, String startNetworkCode, String endNetworkCode,
                                  SystemPersonEntity systemPersonEntity, boolean isStartPoint, String picUrl);
}
