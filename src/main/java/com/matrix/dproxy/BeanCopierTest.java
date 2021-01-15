package com.matrix.dproxy;

import com.alibaba.fastjson.JSONObject;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;


public class BeanCopierTest {
	public static void main(String[] args) {
		Apple apple = new Apple("黄色" , 100 , "香蕉苹果" , true);
		Orange orange = new Orange();
		// 具备同名且【相同的类型】属性定义，所以boolean useConverter = false;
		// 因为要从Apple拷贝属性值给Orange，故Apple在前，否则报错
		BeanCopier copy = BeanCopier.create(Apple.class, Orange.class, false);  
		copy.copy(apple, orange, null); // 无需converter转换，故为null
		System.out.println("相同属性转换后：" + orange.toString());
		
		// 具备同名但【不同类型】属性定义
		Peach peach = new Peach();
		copy = BeanCopier.create(Apple.class, Peach.class, true);  
		copy.copy(apple, peach, new Converter() {
			@Override
			public Object convert(Object value, Class target, Object context) {
				
				System.out.println(target.getClass());
				if (target instanceof JSONObject) {
					
				}
				
				return null;
			}
		});
		
		
		
	}
}


/**
copier = BeanCopier.create(SampleBean.class, SampleBean3.class, true);
		// SampleBean3 与 SampleBean 具备同名但不同类型属性定义
		SampleBean3 bean3 = new SampleBean3();
		copier.copy(bean, bean3, new Converter() {
		    @Override
		    public Object convert(Object value, Class target, Object context) {
		        return NumberUtils.isCreatable(String.valueOf(value)) ? NumberUtils.toInt(String.valueOf(value)) : value;
		    }
		});
*/