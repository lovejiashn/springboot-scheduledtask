package com.jiashn.scheduledtask.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/9/9 16:17
 */
@Component
public class ScheduledTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    private void testTask(){
        logger.info("---------------------------测试定时任务----------------------");
    }
}
