package com.matrix.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matrix.entity.Cat;
import com.matrix.entity.Dog;
import com.matrix.entity.People;

@Profile("bird-lover")
@Configuration
public class BirdLoverConfiguration {		// 爱鸟儿人士

 	@Bean(name = "people")		
	public People initPeople(@Value("爱鸟儿人士")String name) {
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













