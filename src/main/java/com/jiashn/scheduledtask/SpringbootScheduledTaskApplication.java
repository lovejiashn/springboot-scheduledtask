package com.jiashn.scheduledtask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jiangjs
 */
@SpringBootApplication
@MapperScan("com.jiashn.**.*mapper")
public class SpringbootScheduledTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootScheduledTaskApplication.class, args);
    }

}
