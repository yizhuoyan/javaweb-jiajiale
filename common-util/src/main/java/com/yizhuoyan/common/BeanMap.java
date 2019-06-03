package com.yizhuoyan.common;

import java.util.HashMap;

public class BeanMap extends HashMap<String, Object> {

	public static BeanMap of(String key,Object value) {
		return new BeanMap().and(key, value);
	}
	
	public BeanMap and(String key,Object value) {
		this.put(key, value);
		return this;
	}
}
