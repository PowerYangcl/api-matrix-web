package com.matrix.dproxy;

//import org.springframework.cglib.proxy.InvocationHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * @description: 动态代理实现
 *
 * @author Yangcl
 * @date 2020-12-22 15:34:33
 * @home https://github.com/PowerYangcl
 * @version 1.0.0.1
 */
public class ProxyInvocationHandler implements InvocationHandler{
	private Object target;	// 被代理的接口
	public void setTarget(Object target) {
		this.target = target;
	}
	// 对外暴露的方法，获取所得到的代理类
	public Object getProxy() {
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = method.invoke(target, args);
		return result;
	}
}










