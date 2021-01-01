package com.matrix.dproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import net.sf.cglib.beans.BeanGenerator;

public class BeanGeneratorTest {

	public static void main(String[] args) {
		BeanGenerator generator = new BeanGenerator();
		generator.addProperty("name", String.class);

		Object bean = generator.create();   // 动态创建一个 bean
		try {		// 执行 setter 和 getter
			Method setter = bean.getClass().getMethod("setName", String.class);
			setter.invoke(bean, "Who's your daddy ?");
			Method getter = bean.getClass().getMethod("getName");
			
			Object invoke = getter.invoke(bean);
			System.out.println(invoke.toString());
			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}




















