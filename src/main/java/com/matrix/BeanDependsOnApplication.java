package com.matrix;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import com.matrix.entity.Abean;
import com.matrix.entity.Bbean;
import com.matrix.entity.Cbean;

//@Configuration
public class BeanDependsOnApplication {
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanDependsOnApplication.class);
		Abean bean = context.getBean(Abean.class);
		System.out.println(bean.getMsg());
	}
	
	@Bean
	public Abean initAbean1() {
		return new Abean("initAbean1 初始化");
	}
	
	@Bean
	@Primary
	public Abean initAbean2() {
		return new Abean("优先初始化：initAbean2 初始化");
	}
}
































