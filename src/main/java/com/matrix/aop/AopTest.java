package com.matrix.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		IUserService bean = context.getBean("userService", IUserService.class);
//		bean.add();
		bean.delete("msg");
	}
}
