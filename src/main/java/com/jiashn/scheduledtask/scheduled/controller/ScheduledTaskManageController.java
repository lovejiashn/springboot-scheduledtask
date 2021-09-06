package com.jiashn.scheduledtask.scheduled.controller;

import com.jiashn.scheduledtask.scheduled.entity.SysSchedulerTask;
import com.jiashn.scheduledtask.scheduled.service.ScheduledTaskManageService;
import com.jiashn.scheduledtask.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/9/6 14:14
 */
@RestController
@RequestMapping("/scheduled")
public class ScheduledTaskManageController {

    @Autowired
    private ScheduledTaskManageService taskManageService;

    @PostMapping("/create")
    public ResultJson<?> createScheduledTask(@Validated @RequestBody SysSchedulerTask schedulerTask){
       return taskManageService.createScheduledTask(schedulerTask);
    }

    @PutMapping("/edit")
    public ResultJson<?> editScheduledTask(@Validated @RequestBody SysSchedulerTask schedulerTask){
       return taskManageService.editScheduledTask(schedulerTask);
    }

    @DeleteMapping("/delete/{id}")
    public ResultJson<?> deleteScheduledTask(@PathVariable("id") int id){
       return taskManageService.deleteScheduledTask(id);
    }

    @GetMapping("/find/{id}")
    public ResultJson<SysSchedulerTask> findScheduledTask(@PathVariable("id") int id){
       return taskManageService.findScheduledTask(id);
    }

}
