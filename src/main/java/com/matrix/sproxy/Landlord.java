package com.matrix.sproxy;

public class Landlord implements IRentService{
	private String name;
	public Landlord(String name) {
		this.name = name;
	}
	public void rent() {
		System.out.println(this.name + " 要出租房屋！");
	}
}















// 