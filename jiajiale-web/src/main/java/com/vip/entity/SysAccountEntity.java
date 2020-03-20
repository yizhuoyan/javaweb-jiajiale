package com.vip.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
/**
 * (SysAccount)实体类
 *
 * @author yizhuoyan@yizhuoyan.com
 * @since 2020-01-04 08:37:06
 */
@Data
public class SysAccountEntity implements Serializable {
    private static final long serialVersionUID = -59496196109846524L;
    
    private String id;
    /**
    * 账号
    */
    private String account;
    /**
    * 账号名称
    */
    private String name;
    /**
    * 密码
    */
    private String password;
    /**
    * 头像
    */
    private String avatar;
    /**
    * 最后密码修改时间
    */
    private LocalDateTime lastModifyPasswordTime;
    /**
    * 最后登录时间
    */
    private LocalDateTime lastLoginTime;
    /**
    * 备注
    */
    private String remark;
    /**
    * 最后登录设备
    */
    private String lastLoginAgent;
    /**
    * 最后登录IP
    */
    private String lastLoginIp;
    /**
    * 所属角色（0=vip 1=店员 2=经理）
    */
    private Integer role;
    /**
    * 用户状态 0正常，1=锁定
    */
    private Integer status;
    /**
    * 创建人
    */
    private String createAccountId;
    private SysAccountEntity createAccount;
    /**
    * 创建时间
    */
    private LocalDateTime createTime;



}