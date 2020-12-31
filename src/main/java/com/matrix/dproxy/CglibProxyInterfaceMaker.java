package com.matrix.dproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;
import com.matrix.sproxy.IRentService;
import com.matrix.sproxy.Landlord;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyInterfaceMaker{

	public static Object getProxy(Object target) {
		Enhancer enhancer = new Enhancer();
		InterfaceMaker interfaceMaker = new InterfaceMaker();
		interfaceMaker.add(target.getClass()); 			// 抽取某个类的方法生成接口方法
		Class<?> targetInterface = interfaceMaker.create();
		
		enhancer.setInterfaces(new Class[] {targetInterface});
        enhancer.setUseCache(false);
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor() {
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Object result =  proxy.invokeSuper(obj , args);
				return result;
			}
        });
        return enhancer.create();  // 要求代理类必须有默认构造函数，否则报错。
	}
}






























