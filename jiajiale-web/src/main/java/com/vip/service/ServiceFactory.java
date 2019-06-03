package com.vip.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vip.service.impl.UserAccountServiceImpl;

public class ServiceFactory {
	
	private static final ApplicationContext APPLICATION_CONTEXT
	=new ClassPathXmlApplicationContext("/spring.xml"); 
	
	
	
	
	
	public static <T> T get(Class<T> serviceType) {
		
		return APPLICATION_CONTEXT.getBean(serviceType);
	}
	

}
