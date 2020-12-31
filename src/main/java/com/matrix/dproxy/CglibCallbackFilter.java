package com.matrix.dproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

public class CglibCallbackFilter implements CallbackFilter{
	@Override
	public int accept(Method method) {
		switch(method.getName()){
		    case "rent" :
		    	System.err.println("cglib回调过滤" + method.getName() + " 返回索引：0");
		    	return 0;
		    case "address" :
		    	System.err.println("cglib回调过滤" + method.getName() + " 返回索引：1");
		    	return 1;
		    default:  
		    	return 2;
		}
	}
}








