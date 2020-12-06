package com.matrix;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.matrix.config.BeanConfig;
import com.matrix.entity.Cat;
import com.matrix.entity.Dog;
import com.matrix.entity.People;
import com.matrix.entity.Wife;

@Configuration
public class AutoBeanApplication {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AutoBeanApplication.class);
		Wife beauty = context.getBean("beauty" , Wife.class);
		beauty.warning();
	}
	
	@Configuration
	static class WifeConfig {
		@Bean(name="beauty")
		Wife dataSource() {
			return new Wife("你快蹩说话了！！");
		}
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






