package com.jiashn.scheduledtask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author: jiangjs
 * @description: 执行定时任务的线程池配置类
 * @date: 2021/9/6 14:18
 */

@Configuration
public class SchedulingConfig {

    @Bean
    public TaskScheduler taskScheduler(){
        //创建定时任务线程池
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        //线程池大小
        taskScheduler.setPoolSize(4);
        //是否将取消后的任务从队列中消除，与cancel()方法结合，删除队列中的任务
        taskScheduler.setRemoveOnCancelPolicy(true);
        //线程名称前缀
        taskScheduler.setThreadNamePrefix("taskThreadPool-");
        return taskScheduler;
    }
}
