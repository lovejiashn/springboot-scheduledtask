package com.jiashn.scheduledtask.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author: jiangjs
 * @description: 数据库连接池配置类
 * @date: 2021/9/6 11:42
 */
@Configuration
@Data
public class DruidConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String passWord;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.druid.max-active}")
    private Integer maxActive;
    @Value("${spring.datasource.druid.max-wait}")
    private long maxWait;
    @Value("${spring.datasource.druid.initial-size}")
    private Integer initialSize;

    @Bean
    public DataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(passWord);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setInitialSize(initialSize);
        return druidDataSource;
    }
}
