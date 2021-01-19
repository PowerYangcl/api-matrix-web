package com.matrix.dproxy;

import com.alibaba.fastjson.JSONObject;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.core.Converter;

public class BeanCopierTest {
	public static void main(String[] args) {
		Apple apple = new Apple("黄色" , 100 , "香蕉苹果" , true);
		Orange orange = new Orange();
		// 具备同名且【相同的类型】属性定义，所以boolean useConverter = false;
		BeanCopier copy = BeanCopier.create(Apple.class, Orange.class, false);  // 因为要从Apple拷贝属性值给Orange，故Apple在前，否则报错
		copy.copy(apple, orange, null); // 无需converter转换，故为null
		System.out.println("相同属性转换后：" + orange.toString());
		
		// 具备同名但【不同类型】属性定义
		Peach peach = new Peach();
		copy = BeanCopier.create(Apple.class, Peach.class, true);  
		copy.copy(apple, peach, new Converter() {
			@SuppressWarnings("rawtypes")
			@Override
			public Object convert(Object value, Class target, Object context) {
				if(target == JSONObject.class) {
					JSONObject size2a = new JSONObject();  // 不同类型属性
					size2a.put("重量", value);
					size2a.put("单位", "克");
					size2a.put("数量", "1");
					return size2a;
				}
				return value;	// 相同属性直接返回，不需要转换
			}
		});
		System.out.println("不同属性转换后：" + peach.toString());
		
		
		BeanMap map = BeanMap.create(peach);
	}
}


