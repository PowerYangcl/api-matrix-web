package com.matrix.dproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.sf.cglib.beans.BeanGenerator;
public class BeanGeneratorTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
				IllegalArgumentException, InvocationTargetException {
		BeanGenerator generator = new BeanGenerator();
		generator.addProperty("name", String.class);
		// 动态创建一个 bean
		Object bean = generator.create();
		// 执行 setter 和 getter
		Method setter = bean.getClass().getMethod("setName", String.class);
		setter.invoke(bean, "詹姆斯.高斯林");
		Method getter = bean.getClass().getMethod("getName");
		Object invoke = getter.invoke(bean);
		System.out.println(invoke.toString());
	}
}
