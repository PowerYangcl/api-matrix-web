package com.matrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * @description: 项目入口
 * 
 * @author Yangcl
 * @date 2020年5月9日 上午10:30:54 
 * @home https://github.com/PowerYangcl
 * @path api-matrix-web / com.matrix.MatrixApplication.java
 * @version 1.0.0.1
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
public class MatrixApplication {
	
    public static void main(String[] args) {
    	try {
    		SpringApplication.run(MatrixApplication.class, args);
    		System.err.println("项目启动完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}

//@ComponentScan({"com.ziroom.controller","com.ziroom.service"})  // ,"com.ziroom.dao" , 开启多数据源不要扫描指定包，否则会导致DataSourceJiraConfig内的不扫描


/**
 * http://localhost:8080/cache/ajax_btn_reset_cache?prefix=McRole&type=dict&key=matrixEr&jsonStr=sweq
 * http://localhost:8080/cache/ajax_btn_get_cache?prefix=McRole&type=dict&key=matrixEr
 * 
 * 
 * 
 * 
 * 
 * 
 */


























