package com.matrix.aop;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class BeforeLog implements MethodBeforeAdvice {
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.err.println(target.getClass().getName() + " 的 " + method.getName() + " 方法开始执行。");
	}
}
