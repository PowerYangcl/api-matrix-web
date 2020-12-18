package com.matrix;

import com.matrix.entity.Cat;
import com.matrix.entity.Dog;
import com.matrix.entity.People;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//@Configuration
public class AutoBeanApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoBeanApplication.class);
		People master = context.getBean("people", People.class);
	}
	
 	@Bean(name = {"people","bean-name-2"}, autowire = Autowire.BY_TYPE, initMethod = "beanInit", destroyMethod = "beanDestroy")		
	public People initPeople(@Value("青铜时代号")String name) {
		People people = new People();
		people.setName(name);
		return people;
	}
	@Bean
	@Scope("singleton")		// 一只猫
	public Cat initCat() {	
		return new Cat();
	}
	@Bean
	@Scope("prototype")		// 好几条狗
	public Dog initDog() {
		return new Dog();
	}
}





/**
	People master = context.getBean("initPeople", People.class);
	master.getCat().shout();
	master.getDog().shout();
	System.out.println(master.getName());  	
	
 	@Bean(name = "people")		
	public People initPeople(@Value("青铜时代号")String name) {
		People people = new People();
		people.setName(name);
		return people;
	}
 */






