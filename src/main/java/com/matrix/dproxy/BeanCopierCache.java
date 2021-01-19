package com.matrix.dproxy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.beans.BeanCopier;

public class BeanCopierCache {
	private static final Map<String, BeanCopier> cacheMap = new ConcurrentHashMap<>();
	
	public static void copyProperties(Object source, Object target) {
	    BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
	    copier.copy(source, target, null);
	}
	
	private static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass) {
	    String key = sourceClass.getName() + targetClass.getName();
	    BeanCopier copier = null;
	    if (!cacheMap.containsKey(key)) {
	        copier = BeanCopier.create(sourceClass, targetClass, false);
	        cacheMap.put(key, copier);
	    } else {
	        copier = cacheMap.get(key);
	    }
	    return copier;
	}
}
