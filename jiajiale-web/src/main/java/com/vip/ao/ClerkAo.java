package com.vip.ao;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClerkAo {
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 性别
     */
    private String sex;
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
    private String birthday;
}
