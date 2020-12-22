package com.matrix.dproxy;

import com.matrix.sproxy.IRentService;
import com.matrix.sproxy.Landlord;

public class DynamicProxyClient {

	public static void main(String[] args) {
		IRentService rent = new Landlord("沈腾");
		
		ProxyInvocationHandler handler = new ProxyInvocationHandler();
		handler.setTarget(rent);
		IRentService proxy = (IRentService) handler.getProxy();
		
		proxy.rent();
	}

}
