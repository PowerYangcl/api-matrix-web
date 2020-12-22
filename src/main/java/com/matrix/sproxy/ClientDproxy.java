package com.matrix.sproxy;

public class ClientDproxy {
	public static void main(String[] args) {
		Landlord lord = new Landlord("温加饱");
		RentProxy proxy = new RentProxy(lord);
		proxy.rent();
	}
}
