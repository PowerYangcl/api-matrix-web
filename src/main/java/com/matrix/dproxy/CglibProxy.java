package com.matrix.dproxy;

import java.lang.reflect.Method;

import org.springframework.aop.framework.ProxyFactory;

import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

public class CglibProxy {
	private Enhancer enhancer = null;
	private Object target;								// 被代理的接口
	public CglibProxy(Object target) {
		this.target = target;
		enhancer = new Enhancer(); 
		enhancer.setSuperclass(this.target.getClass());
	}
	
	public Object getProxyByType(Boolean useCache, Object[] arguments, Class<?>... parameterTypes) {
        CallbackHelper helper = new CallbackHelper(this.target.getClass() , new Class[0]) {		// CallbackHelper had implements CallbackFilter
        	protected Object getCallback(Method method) {
                if (method.getReturnType() == void.class || method.getReturnType() == String.class) {
                	// TODO 其他策略判断 
                }
                switch(method.getName()) {
	                case "rent" :
	                	return new MethodInterceptor() {
	                        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
	                        	System.out.println("enhancer callback filter：" + method.getName() + "() 返回CallbackFilter索引：" + accept(method));
	                            System.out.println("before invocation");
	                            Object result = proxy.invokeSuper(obj, args);
	                            System.out.println("after invocation");
	                            return result;
	                        }
	                    };
	    		    case "address" :
	    		    	return new FixedValue() {		// 表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
	                        public Object loadObject() throws Exception {
	                             return target.getClass() + "." + method.getName() + "() 被锁定方法返回值";
	                        }
	                    };
	    		    default:  
	    		    	return NoOp.INSTANCE; 
                }
            }
        };
        enhancer.setCallbacks(helper.getCallbacks());	// setCallbacks和setCallbackFilter必须关联使用
        enhancer.setCallbackFilter(helper);
        enhancer.setUseCache(useCache == null ? false : useCache);
        return enhancer.create(parameterTypes, arguments);
	}
}





class aaa{
	private Enhancer enhancer = null;
	
	/**
	 * @description: 返回代理对象
	 * 
	 * @param useCache 是否启用缓存(用的不多)
	 * @param arguments 传入的参数值
	 * @param parameterTypes 传入的参数类型
	 * @return Object
	 * @author Yangcl
	 * @date 2020-12-30 10:59:38
	 * @home https://github.com/PowerYangcl
	 * @version 1.0.0.1
	 */
	public Object getProxy(Boolean useCache, Object[] arguments, Class<?>... parameterTypes) {
        enhancer.setUseCache(useCache == null ? false : useCache);
        enhancer.setCallback(new MethodInterceptor() {
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				// 如果用method调用会再次进入拦截器。为了避免这种情况，应该使用接口方法中第四个参数methodProxy调用invokeSuper
				Object result =  proxy.invokeSuper(obj , args);
				return result;
			}
        });
        return enhancer.create(parameterTypes, arguments);  // enhancer.create() 要求代理类必须有默认构造函数，否则报错。
	}
}
















