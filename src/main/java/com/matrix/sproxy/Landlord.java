package com.matrix.sproxy;

import com.alibaba.fastjson.JSONObject;

public class Landlord implements IRentService{
	private String name;
	public Landlord(String name) {
		this.name = name;
	}
	
	public void rent() {
		System.out.println("获取属性值 - this.name 方式：" + this.name + " 要出租房屋！");
		System.out.println("获取属性值 - this.getName() 方式：" + this.getName() + " 要出租房屋！");
	}
	
	public String address() {
		return "中国北京开心麻花剧组";
	}
	
	public JSONObject roomInfo(Integer area, String standard, String unit) {
		JSONObject result = new JSONObject();
		result.put("area", area + " 平方米");
		result.put("standard", standard);		// 三室一厅 or 其他
		result.put("unit", unit);  // 一单元 or 二单元
		return result;
	}
	
	public Landlord() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}















