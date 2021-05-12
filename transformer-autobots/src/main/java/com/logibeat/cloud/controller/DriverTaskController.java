package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.common.cache.util.StringUtils;
import com.logibeat.cloud.common.enumtype.TaskType;
import com.logibeat.cloud.dto.task.CreateDriverTaskDto;
import com.logibeat.cloud.dto.task.HandleTaskDto;
import com.logibeat.cloud.dto.task.SearchTaskDto;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.services.DriverTaskPointService;
import com.logibeat.cloud.services.DriverTaskService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import com.logibeat.cloud.vo.task.CreateTaskByOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 派车单控制层
 */
@Controller
@RequestMapping(value = "driverTask/api")
@Scope("prototype")
public class DriverTaskController extends BaseController {


    @Autowired
    private DriverTaskService driverTaskService;


    @Autowired
    private DriverTaskPointService driverTaskPointService;


    /**
     * 创建任务（派车）单
     * @param createDriverTaskDto
     * @return
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    @NotLogin
    public JSONPrompt create(@RequestBody CreateDriverTaskDto createDriverTaskDto){
        DriverTaskEntity driverTask = driverTaskService.create(createDriverTaskDto);
        //新任务提醒
        if(StringUtils.isBlank(createDriverTaskDto.getTaskId())){
            if(TaskType.InviteCar.getValue().equals(createDriverTaskDto.getTaskType())){
                driverTaskService.taskImPush(driverTask.getGuid(),"4");
            }
            else{
                driverTaskService.taskImPush(driverTask.getGuid(),"1");
            }
        }
        return new JSONPrompt(driverTask);
    }



    /**
     * 外部接口创建任务（派车）单(商砼)
     * @param createDriverTaskDto
     * @return
     */
    @RequestMapping(value = "/createByOut")
    @ResponseBody
    @NotLogin
    public JSONPrompt createByOut(@RequestBody CreateDriverTaskDto createDriverTaskDto){
        CreateTaskByOutVo createTaskByOutVo= driverTaskService.createByOut(createDriverTaskDto);
        if(StringUtils.isNotBlank(createTaskByOutVo.getAction())){
            driverTaskService.taskImPush(createTaskByOutVo.getDriverTaskEntity().getGuid(),createTaskByOutVo.getAction());
        }
        return new JSONPrompt();
    }


    /**
     * 任务IM推送（action :1:派车、2：撤回派车、3：完成）
     * @return
     */
    @RequestMapping(value = "/taskImPush")
    @ResponseBody
    @NotLogin
    public JSONPrompt taskImPush(String taskId,String action ){
        driverTaskService.taskImPush(taskId,action);
        return  new JSONPrompt();
    }


    /**
     * 任务（派车）单列表
     * @param searchTaskDto
     * @return
     */

    @RequestMapping(value = "/list")
    @ResponseBody
    @NotLogin
    public JSONPrompt list(SearchTaskDto searchTaskDto){
        return new JSONPrompt(driverTaskService.list(searchTaskDto));
    }

    /**
     * 详情
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/detail/{taskId}")
    @ResponseBody
    @NotLogin
    public JSONPrompt detail(@PathVariable String taskId){
        return new JSONPrompt(driverTaskService.detail(taskId));
    }


    /**
     * 在途中的任务
     * @param personId
     * @return
     */
    @RequestMapping(value = "/runningTask/{personId}")
    @ResponseBody
    @NotLogin
    public JSONPrompt runningTask(@PathVariable String personId){
        return new JSONPrompt(driverTaskService.runningTask(personId));
    }



    /**
     * 执行中的任务
     * @param personId
     * @return
     */
    @RequestMapping(value = "/executingTask/{personId}")
    @ResponseBody
    @NotLogin
    public JSONPrompt executingTask(@PathVariable String personId){
        return new JSONPrompt(driverTaskService.executingTask(personId));
    }




    /**
     * 当前工作的装卸点
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/workPoint/{taskId}")
    @ResponseBody
    @NotLogin
    public JSONPrompt workPoint(@PathVariable String taskId){
        return new JSONPrompt(driverTaskPointService.workPoint(taskId));
    }




    /**
     * 线路详情
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/pointDetail/{taskId}")
    @ResponseBody
    @NotLogin
    public JSONPrompt pointDetail(@PathVariable String taskId){
        return new JSONPrompt(driverTaskPointService.getPointList(taskId));
    }


    /**
     * 去装货
     * @param handleTaskDto
     * @return
     */
    @RequestMapping(value = "/goLoad")
    @ResponseBody
    @NotLogin
    public JSONPrompt goLoad(@RequestBody HandleTaskDto handleTaskDto){
        driverTaskService.goLoad(handleTaskDto);
        return new JSONPrompt();
    }


    /**
     * 确认到达（装卸货点）
     * @param handleTaskDto
     * @return
     */
    @RequestMapping(value = "/arrive")
    @ResponseBody
    @NotLogin
    public JSONPrompt arrive(@RequestBody HandleTaskDto handleTaskDto){
        driverTaskService.arrive(handleTaskDto);
        return new JSONPrompt();
    }


    /**
     * 开始装货
     * @param handleTaskDto
     * @return
     */
    @RequestMapping(value = "/startLoad")
    @ResponseBody
    @NotLogin
    public JSONPrompt startLoad(@RequestBody HandleTaskDto handleTaskDto){
        driverTaskService.startLoad(handleTaskDto);
        return new JSONPrompt();
    }


    /**
     * 完成装货
     * @param handleTaskDto
     * @return
     */
    @RequestMapping(value = "/finishLoad")
    @ResponseBody
    @NotLogin
    public JSONPrompt finishLoad(@RequestBody HandleTaskDto handleTaskDto){
        driverTaskService.finishLoad(handleTaskDto);
        return new JSONPrompt();
    }


    /**
     去卸货
     * @param handleTaskDto
     * @return
     */
    @RequestMapping(value = "/goUnload")
    @ResponseBody
    @NotLogin
    public JSONPrompt goUnload(@RequestBody HandleTaskDto handleTaskDto){
        driverTaskService.goUnLoad(handleTaskDto);
        return new JSONPrompt();
    }

    /**
     * 开始卸货
     * @param handleTaskDto
     * @return
     */
    @RequestMapping(value = "/startUnload")
    @ResponseBody
    @NotLogin
    public JSONPrompt startUnload(@RequestBody HandleTaskDto handleTaskDto){
        driverTaskService.startUnLoad(handleTaskDto);
        return new JSONPrompt();
    }


    /**
     * 完成卸货
     * @param handleTaskDto
     * @return
     */
    @RequestMapping(value = "/finishUnload")
    @ResponseBody
    @NotLogin
    public JSONPrompt finishUnload(@RequestBody HandleTaskDto handleTaskDto){
        driverTaskService.finishUnLoad(handleTaskDto);
        return new JSONPrompt();
    }


    /**
     * 待签收卸货点列表
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/waitSignList")
    @ResponseBody
    @NotLogin
    public JSONPrompt waitSignList(String taskId){
        return new JSONPrompt(driverTaskPointService.getWaitSignList(taskId));
    }



    /**
     * 签收（业务单据系统签收成功后通知司机端）
     * @param consignOrderId
     * @return
     */
    @RequestMapping(value = "/sign")
    @ResponseBody
    @NotLogin
    public JSONPrompt sign(String consignOrderId){
        driverTaskPointService.sign(consignOrderId);
        return  new JSONPrompt();

    }


    /**
     * 删除
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @NotLogin
    public JSONPrompt delete(String taskId,String relationOrderId,String vehicleId){
        if(StringUtils.isNotBlank(taskId)){
            driverTaskService.delete(taskId);
        }
        if(StringUtils.isNotBlank(relationOrderId)){
            driverTaskService.deleteByRelationId(relationOrderId,vehicleId);
        }
        return  new JSONPrompt();
    }



    /**
     * 任务附加项（数字 红点）
     *
     *
     *
     * @return
     */
    @RequestMapping(value = "/getTaskItem/{personId}")
    @ResponseBody
    @NotLogin
    public JSONPrompt getDriverTaskItem(@PathVariable String  personId,String entId){
        return new JSONPrompt<>(driverTaskService.getDriverTaskItem(personId,entId));
    }


}
