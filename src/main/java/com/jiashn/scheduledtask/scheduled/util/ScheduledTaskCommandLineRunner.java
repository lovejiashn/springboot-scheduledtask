package com.jiashn.scheduledtask.scheduled.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiashn.scheduledtask.scheduled.entity.SysSchedulerTask;
import com.jiashn.scheduledtask.scheduled.mapper.ScheduledTaskManageMapper;
import com.jiashn.scheduledtask.task.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: jiangjs
 * @description: 启动程序时，自动加载定时任务
 * @date: 2021/9/16 16:19
 */
@Component
public class ScheduledTaskCommandLineRunner implements CommandLineRunner {
    @Autowired
    private ScheduledTaskManageMapper taskManageMapper;
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Override
    public void run(String... args) throws Exception {
        //获取定时任务列表
        QueryWrapper<SysSchedulerTask> queryWrapper = new QueryWrapper();
        queryWrapper.eq("task_status",1);
        List<SysSchedulerTask> schedulerTasks = taskManageMapper.selectList(queryWrapper);
        schedulerTasks.forEach(task ->{
            SchedulingRunnable runnable = new SchedulingRunnable(task.getBeanName(), task.getMethodName(), task.getParams());
            cronTaskRegistrar.addCronTask(runnable,task.getCronExp());
        });

    }
}
