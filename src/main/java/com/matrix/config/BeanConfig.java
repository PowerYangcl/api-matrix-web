package com.matrix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.matrix.entity.Wife;

@Configuration
public class BeanConfig {
	@Bean(name="beauty")
	public Wife initWife() {
		return new Wife();
	}
}



























