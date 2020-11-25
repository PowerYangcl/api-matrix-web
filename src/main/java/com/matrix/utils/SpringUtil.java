package com.matrix.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;

/**
 * @description: Spring上下文获取。解决在普通类中存在无法获取Service、Dao实例的情况。
 * 		需要在项目启动类上加入@Import(SpringUtil.class)标签才能生效。
 *	
 * @howToUse private IOutsourcingEmployeeDao outsourcingEmployeeDao = SpringUtil.getBean(IOutsourcingEmployeeDao.class);	
 * @author Yangcl
 * @date 2020年5月19日 下午8:00:26 
 * @version 1.0.0.1
 */
public final class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
	
    public static <T> T getBean(Class<T> clazz){
           return getApplicationContext().getBean(clazz);
    }
}
