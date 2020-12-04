
package com.matrix.xml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.matrix.entity.Cat;
import com.matrix.entity.Dog;
import com.matrix.entity.People;

@Configuration
public class AutoConfiguration {
	@Bean
	public Cat initCat() {
		return new Cat();
	}
	@Bean
	public Dog initDog() {
		return new Dog();
	}
	@Bean
	public People initPeople() {
		return new People();
	}
}
