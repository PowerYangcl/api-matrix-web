package com.matrix.dproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;
import com.matrix.sproxy.IRentService;
import com.matrix.sproxy.Landlord;

public class DynamicProxyClient {
	
	
	public static void main(String[] args){
		try {
			IRentService rent_ = new Landlord("沈腾");
			Object object = CglibProxyInterfaceMaker.getProxy(rent_);
			
			Method roomInfo = object.getClass().getMethod("roomInfo", new Class[] {Integer.class, String.class, String.class });
			JSONObject msg = (JSONObject) roomInfo.invoke(object, new Object[] {128, "三室一厅", "二单元"}); 
			System.out.println("msg = " + msg.toJSONString());
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}






class bbb{
	public static void main2(String[] args){
		IRentService rent_ = new Landlord("沈腾");
		JdkProxy handler = new JdkProxy(rent_);
		IRentService rent = (IRentService) handler.getProxy();
		rent.rent();
	}
	
	public static void main22(String[] args) throws NoSuchMethodException, SecurityException {
		IRentService rent_ = new Landlord("沈腾");
		CglibProxy cglib = new CglibProxy(rent_);
		IRentService rent2 = (IRentService) cglib.getProxyByType(null, new Object[]{"马丽"}, String.class);
		rent2.rent();
		System.out.println(rent2.address());
	}
}








