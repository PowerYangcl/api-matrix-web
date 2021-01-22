package com.matrix.aop;

import java.beans.Transient;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnnotationPointCut {
	
	@Around("execution(* com.matrix.aop..*.*(..))")
	public Boolean process(ProceedingJoinPoint point) throws Throwable {
		System.out.println("【Around】：执行目标方法之前... 原始参数：" + Arrays.toString(point.getArgs()));
		Object[] args = point.getArgs(); 
		if (args != null && args.length != 0) {
			args[0] = "马化疼";		// 故意改变入参的值
		}
		Object returnValue = point.proceed(args);	// 用改变后的参数执行目标方法
		System.out.println("【Around】：原返回值：" + returnValue + "，这是返回结果的后缀"); 		// return "原返回值：" + returnValue + "，这是返回结果的后缀";
		return true;
	}

	@Before("execution(* com.matrix.aop..*.*(..))")
	public void permissionCheck(JoinPoint point) {
		System.out.println("Before---- 原始参数：" + Arrays.toString(point.getArgs())); 
	}
	
	@After("execution(* com.matrix.aop..*.*(..))")
	public void releaseResource(JoinPoint point) {
		System.out.println("After----参数为：" + Arrays.toString(point.getArgs()));
	}

	@AfterReturning(pointcut = "execution(* com.matrix.aop..*.*(..))", returning = "returnValue")
	public void log(JoinPoint point, Object returnValue) {
		System.out.println("AfterReturning----返回值为：" + returnValue);
	}
	
}





















