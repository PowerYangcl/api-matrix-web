package com.matrix.sproxy;

import com.alibaba.fastjson.JSONObject;

public class RentProxy implements IRentService{
	private Landlord lord;
	
	public RentProxy(Landlord lord) {
		this.lord = lord;
	}
	public void rent() {
		this.seeHouse();
		lord.rent();
		this.fare();
	}
	
	private void seeHouse() {
		System.out.println("中介带你看房");
	}
	
	private void fare() {
		System.out.println("收中介费");
	}
	
	@Override
	public String address() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JSONObject roomInfo(Integer area, String standard, String unit) {
		// TODO Auto-generated method stub
		return null;
	}
}









 // 