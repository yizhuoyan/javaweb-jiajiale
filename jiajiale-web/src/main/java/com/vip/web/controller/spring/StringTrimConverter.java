package com.vip.web.controller.spring;

import org.springframework.core.convert.converter.Converter;

/**
 * Created by Administrator on 5/15.
 */
public class StringTrimConverter implements Converter<String,String> {
    @Override
    public String convert(String source) {
        if(source==null){
            return null;
        }
        source=source.trim();
        if(source.length()==0){
            return null;
        }
        return source;
    }
}
