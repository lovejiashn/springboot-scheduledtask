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
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(4);
        taskScheduler.setRemoveOnCancelPolicy(true);
        taskScheduler.setThreadNamePrefix("taskScheduledThreadPool-");
        return taskScheduler;
    }
}
