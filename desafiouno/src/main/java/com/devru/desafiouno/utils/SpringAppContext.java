package com.devru.desafiouno.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class SpringAppContext implements InitializingBean {

	private static SpringAppContext instance;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		setInstance(this);
	}
	
	private static void setInstance(SpringAppContext instanceParam) {
		SpringAppContext.instance = instanceParam;
	}
	
	public <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	
	public <T> T getBean(String qualifier, Class<T> clazz) {
		return applicationContext.getBean(qualifier, clazz);
	}
	
	public static <T> T getBeanFromContext(Class<T> clazz) {
		if (instance == null) {
			throw new IllegalStateException("no se ha inicializado el bean");
		}
		return instance.getBean(clazz);
	}
	
	public static <T> T getBeanFromContext(String qualifier, Class<T> clazz) {
		if (instance == null) {
			throw new IllegalStateException("no se ha inicializado el bean");
		}
		return instance.getBean(qualifier, clazz);
	}
	

}
