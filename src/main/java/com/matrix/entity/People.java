package com.matrix.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class People {
	private String name;
	@Autowired
	private Cat cat;
	@Autowired
	private Dog dog;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Cat getCat() {
		return cat;
	}
	public void setCat(Cat cat) {
		this.cat = cat;
	}
	public Dog getDog() {
		return dog;
	}
	public void setDog(Dog dog) {
		this.dog = dog;
	}
    public void beanInit(){
        System.out.println("Bean 初始化方法被调用");
    }
    public void beanDestroy(){
        System.out.println("Bean 销毁方法被调用");
    }

}
