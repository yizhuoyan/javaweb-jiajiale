package com.vip.vo;

import com.vip.entity.EmpClerkEntity;
import com.vip.entity.SysAccountEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClerkDetailVo {
    /**
     * 帐号信息
     */
    private AccountDetailVo account;

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

    public static ClerkDetailVo of(EmpClerkEntity e){
        ClerkDetailVo clerkDetailVo = new ClerkDetailVo();
        clerkDetailVo.setAccount(AccountDetailVo.of(e.getAccount()));
        clerkDetailVo.setId(e.getId());
        clerkDetailVo.setRealName(e.getRealName());
        clerkDetailVo.setSex(e.getSex());
        clerkDetailVo.setEmpNo(e.getEmpNo());
        clerkDetailVo.setWorkMobile(e.getWorkMobile());
        clerkDetailVo.setWorkEmail(e.getWorkEmail());
        clerkDetailVo.setBirthday(e.getBirthday());
        return clerkDetailVo;
    }
    public static ClerkDetailVo ofOnlyClerk(EmpClerkEntity e){
        ClerkDetailVo clerkDetailVo = new ClerkDetailVo();
        clerkDetailVo.setId(e.getId());
        clerkDetailVo.setRealName(e.getRealName());
        clerkDetailVo.setSex(e.getSex());
        clerkDetailVo.setEmpNo(e.getEmpNo());
        clerkDetailVo.setWorkMobile(e.getWorkMobile());
        clerkDetailVo.setWorkEmail(e.getWorkEmail());
        clerkDetailVo.setBirthday(e.getBirthday());
        return clerkDetailVo;
    }
}
