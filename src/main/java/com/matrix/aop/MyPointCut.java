package com.matrix.aop;

public class MyPointCut {
	public void beforeRunning() {
		System.out.println("====<aop:before />====");
	}
	public void afterRunning() {
		System.out.println("====<aop:after />====");
	}
	/**
	 *  声明args时，指定的类型会限制目标方法必须返回指定类型的值
	 *  此处将args的类型声明为Object，意味着对目标方法的返回值不加限制
	 */
	public void afterReturning(Object args) {
		System.out.println("====<aop:after-returning />，返回结果：" + args.toString());
	}
}
