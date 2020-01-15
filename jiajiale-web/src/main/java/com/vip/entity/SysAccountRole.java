package com.vip.entity;

import lombok.ToString;

public enum SysAccountRole  {
    VIP(0,"VIP"),
    CLERK(1,"店员"),
    MANAGER(2,"经理");

    public final int code;
    public final String name;
    public final static SysAccountRole[] VALUES=SysAccountRole.values();

    private  SysAccountRole(int code,String name){
        this.code=code;
        this.name=name;
    }
    public SysAccountRole valueOf(int code){
        if(code<0)return null;
        if(code>=VALUES.length)return null;
        return VALUES[code];
    }

    public String toString(){
        return this.name;
    }
}
