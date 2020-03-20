package com.vip.vo;

import com.vip.entity.SysAccountEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountDetailVo {

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
     * 创建人
     */
    private String createAccountId;

    private String createAccountShowName;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public static AccountDetailVo of(SysAccountEntity e){
        AccountDetailVo accountDetailVo = new AccountDetailVo();
        accountDetailVo.setId(e.getId());
        accountDetailVo.setAccount(e.getAccount());
        accountDetailVo.setName(e.getName());
        accountDetailVo.setLastModifyPasswordTime(e.getLastModifyPasswordTime());
        accountDetailVo.setLastLoginTime(e.getLastLoginTime());
        accountDetailVo.setRemark(e.getRemark());
        accountDetailVo.setLastLoginAgent(e.getLastLoginAgent());
        accountDetailVo.setLastLoginIp(e.getLastLoginIp());
        accountDetailVo.setRole(e.getRole());
        accountDetailVo.setCreateAccountId(e.getCreateAccountId());
        accountDetailVo.setCreateAccountShowName(e.getName());
        accountDetailVo.setCreateTime(e.getCreateTime());
        return accountDetailVo;

    }
}
