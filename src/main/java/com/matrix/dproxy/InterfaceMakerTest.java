package com.matrix.dproxy;

import net.sf.cglib.core.Signature;

import java.util.Arrays;

import org.objectweb.asm.Type;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.util.ParallelSorter;


public class InterfaceMakerTest {
	
	
	public static void main(String[] args) {
		//  方法名称，返回类型，参数类型列表 
		Signature signature = new Signature("functionName" , Type.INT_TYPE , new Type[] {Type.BOOLEAN_TYPE});
		InterfaceMaker maker = new InterfaceMaker();
		maker.add(signature, new Type[0]);
		Class<?> iface = maker.create();
		
		System.out.println("接口方法数量 = " + iface.getMethods().length);
		System.out.println("接口方法名称 = " + iface.getMethods()[0].getName());
		System.out.println("方法返回类型 = " + iface.getMethods()[0].getReturnType());
		System.out.println("方法入参数量 = " + iface.getMethods()[0].getParameterCount());
		System.out.println("方法的返回值 = " + iface.getMethods()[0].getParameters()[0].getType());
	}
	
	
	
	/**
	 * @description: 比较der一个东西，排序居然不准。。。
	 * 
	 * @author Yangcl
	 * @date 2021-1-18 16:32:42
	 * @version 1.0.0.1
	 */
	public static void main2(String[] args) {
		Integer[][] value = { 
				{4, 3, 9, 0,11,11,12,14,16,22,28}, 
				{2, 1, 6, 0,1,10,14,24,29,89,7}, 
				{3, 1, 6, 0,5,10,14,24,26,88,72} 
			};
		
		int size = 10;
		
		Integer[] arr = new Integer[size];
		Integer[] arr2 = new Integer[size];
		for(int i = size; i > 0; i -- ) {
			arr[size - i] = i;
			arr2[i - 1] = i + 10;
		}
		
		Integer[][] value1 = {arr , arr2};
		
		for (final Integer[] v : value1) {
		    System.err.println(Arrays.toString(v));
		}
		
		ParallelSorter.create(value1).mergeSort(0);  // 0、1、2...指定对数组的哪个维度进行
		for (final Integer[] v : value1) {
		    System.out.println(Arrays.toString(v));
		}
		
		System.err.println("--------------------------------------------------------");
		
		ParallelSorter.create(value).mergeSort(0);  // 0、1、2...指定对数组的哪个维度进行
		for (final Integer[] v : value) {
		    System.out.println(Arrays.toString(v));
		}
	}
}












