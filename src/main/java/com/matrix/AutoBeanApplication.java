package com.matrix;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.matrix.entity.Cat;
import com.matrix.entity.Dog;
import com.matrix.entity.People;

@Configuration
public class AutoBeanApplication {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AutoBeanApplication.class);
		People master = context.getBean("people", People.class);
		master.getCat().shout();
		master.getDog().shout();
		System.out.println(master.getName()); 
	}
	
	@Bean
	public Cat initCat() {
		return new Cat();
	}
	@Bean
	public Dog initDog() {
		return new Dog();
	}
	
	@Bean(name = "peopleDefalut")
	public People initPeople() {
		return new People();
	}
	
	@Bean(name = "people")		// https://www.cnblogs.com/maohuidong/p/11764544.html
	public People initPeople(@Value("青铜时代号")String name) {
		People people = new People();
		people.setName(name);
		return people;
	}
}
