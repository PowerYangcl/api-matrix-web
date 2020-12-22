package com.matrix.sproxy;

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
}









 // 