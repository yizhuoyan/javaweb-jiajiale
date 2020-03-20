package com.yizhuoyan.common;

import java.util.HashMap;

public class BeanMap extends HashMap<String, Object> {

    public BeanMap(int initialCapacity) {
        super(initialCapacity,1);
    }



    public static BeanMap of(String key, Object value) {
		return new BeanMap(8).and(key, value);
	}
	
	public BeanMap and(String key,Object value) {
		this.put(key, value);
		return this;
	}
}
