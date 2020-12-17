package com.matrix;

import com.matrix.entity.Cat;
import com.matrix.entity.Dog;
import com.matrix.entity.People;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class BeanProfileApplication {
	public static void main(String[] args) {
		 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("cat-lover");
		context.register(BeanProfileApplication.class);
		context.refresh();
		
		People people = context.getBean("people", People.class);
		System.out.println(people.getName());
		people.getCat().shout();
		people.getDog().shout();
	}
	
	@Profile("dog-lover")
	@Bean(name = "people")		
	public People initDogLoverPeople(@Value("爱狗人士")String name) {
		People people = new People();
		people.setName(name);
		return people;
	}
	
	@Profile("cat-lover")
	@Bean(name = "people")		
	public People initCatLoverPeople(@Value("爱猫人士")String name) {
		People people = new People();
		people.setName(name);
		return people;
	}
	
	@Profile("bird-lover")
	@Bean(name = "people")		
	public People initBirdLoverPeople(@Value("爱鸟儿人士")String name) {
		People people = new People();
		people.setName(name);
		return people;
	}
	@Bean
	public Dog initDog() {
		return new Dog();
	}
	@Bean
	public Cat initCat() {	
		return new Cat();
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






