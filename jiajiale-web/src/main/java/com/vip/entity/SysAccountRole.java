package com.vip.entity;

import lombok.ToString;

import java.util.Arrays;

public enum SysAccountRole  {
    VIP(0,"VIP"),
    CLERK(1,"店员"),
    MANAGER(2,"经理");

    public final int code;
    public final String showText;
    public final static SysAccountRole[] VALUES=SysAccountRole.values();

    private  SysAccountRole(int code,String showText){
        this.code=code;
        this.showText=showText;
    }
    public static  SysAccountRole valueOf(int code){
        if(code<0)return null;
        if(code>=VALUES.length)return null;
        return Arrays.stream(VALUES).filter(r->r.code==code).findFirst().orElse(null);
    }

    public String toString(){
        return this.name();
    }
}
