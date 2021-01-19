package com.matrix.aop;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

public class AfterLog implements AfterReturningAdvice {
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.err.println("执行了 " + method.getName() + " 方法后，返回结果为：" + returnValue);  // returnValue 执行后的返回值
	}
}
