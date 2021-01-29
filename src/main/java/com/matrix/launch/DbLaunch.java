package com.matrix.launch;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * @description: 单数据源配置
 * 
 * @author Yangcl
 * @date 2021-1-21 20:01:53
 * @home https://github.com/PowerYangcl
 * @path matrix-core/com.matrix.launch.DbLaunch.java
 * @version 1.0.0.1
 */
@Configuration
public class DbLaunch {
	
    @Bean  // (initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.matrix")
    public DruidDataSource dataSourceTwo(){
    	
    	System.err.println("开始初始化数据库链接。。。。"); 
    	
        return DruidDataSourceBuilder.create().build();
    }
    
}
