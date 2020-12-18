package com.matrix;

import com.matrix.entity.Cat;
import com.matrix.entity.Dog;
import com.matrix.entity.People;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
//@Lazy
//@Configuration
public class BeanLazyApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanLazyApplication.class);
		
		People people = context.getBean("people", People.class);
		people.getCat().shout();
		people.getDog().shout();
	}
	
	@Bean(name = "people")		
	public People initPeople(@Value("蓝色空间号")String name) {
		People people = new People();
		people.setName(name);
		System.out.println("老婆孩子热炕头儿，三十亩地一头牛儿");
		return people;
	}
	@Bean
	public Dog initDog() {
		System.out.println("我养了一条狗狗");
		return new Dog();
	}
	@Bean
	public Cat initCat() {	
		System.out.println("我又养了一只小猫猫");
		return new Cat();
	}
	@Bean(name = "xiao-qi")	
	public People initConcubine() {
		System.out.println("再来6个小老婆 ԅ(♡﹃♡ԅ) ~ ~ ~ ~ ~ ~ ~");
		return new People();
	}
}








