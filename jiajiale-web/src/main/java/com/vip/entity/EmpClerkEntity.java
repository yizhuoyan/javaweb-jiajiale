package com.vip.entity;

import java.time.LocalDate;
import java.io.Serializable;
import lombok.Data;
/**
 * 店员
 * @author yizhuoyan@yizhuoyan.com
 * @since 2020-03-08 14:48:55
 */
@Data
public class EmpClerkEntity  implements Serializable {
    private static final long serialVersionUID = -77675464809756772L;
    /**
     * 帐号信息
     */
    private SysAccountEntity account;

    private String id;
    /**
    * 真实姓名
    */
    private String realName;
    /**
    * 性别
    */
    private Integer sex;
    /**
    * 员工编号
    */
    private String empNo;
    /**
    * 工作手机
    */
    private String workMobile;
    /**
    * 工作邮箱
    */
    private String workEmail;
    /**
    * 生日
    */
    private LocalDate birthday;



}