package com.matrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * @description: 项目入口
 *
 * @author Yangcl
 * @date 2020年5月9日 上午10:30:54 
 * @version 1.0.0.1
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@ServletComponentScan({"com.matrix.servlet"})   // 启动器启动时，扫描指定目录带有的@WebServlet、@WebFilter和@WebListener注解的注册类
public class MatrixApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(MatrixApplication.class, args);
    }

}

//@ComponentScan({"com.ziroom.controller","com.ziroom.service"})  // ,"com.ziroom.dao" , 开启多数据源不要扫描指定包，否则会导致DataSourceJiraConfig内的不扫描





























