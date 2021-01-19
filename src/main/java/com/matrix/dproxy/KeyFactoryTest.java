package com.matrix.dproxy;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.core.KeyFactory;
public class KeyFactoryTest {
	public static void main(String[] args) {
		ITestFactory factory = (ITestFactory) KeyFactory.create(ITestFactory.class);
		Object key = factory.newInstance("foo", "bar"); // create multi-valued
		Map<Object, String> map = new HashMap<>();
		map.put(key, "Hello, cglib!");
		
		System.out.println("key = foo | map = " + map.get(factory.newInstance("foo")));
		System.out.println("key = bar | map = " + map.get(factory.newInstance("bar")));
		System.out.println("key = foo , bar | map = " + map.get(factory.newInstance("foo", "bar")));
	}
}


interface ITestFactory {
    Object newInstance(String... keys);
}







