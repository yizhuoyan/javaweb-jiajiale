package com.vip.dto;

import java.time.LocalDateTime;

import lombok.Data;
/**
 * 用户上下文
 * @author Administrator
 *
 */
@Data
public class AccountContext {
	private static final ThreadLocal<AccountContext> ACCOUNT_HOLDER=new ThreadLocal<>();
	private String id;
	private String account;		
	private String name;		
	private String avatar;
	private LocalDateTime timeCreate;		
	private LocalDateTime timeLastAlterPassword;		
	private LocalDateTime timeLastLogin;		
	private boolean isFirstLogin;
	private String remark;		
	private String lastLoginIp;
    private String lastLoginDevice;
    //所属角色
    private String role;
    private String token;
	/**
	 * 获取当前用户账号的id
	 * @return
	 */
	public static String currentAccountId() {
		AccountContext current=ACCOUNT_HOLDER.get();
		return current.getId();
	}
	
	/**
	 * 保存当前用户对象
	 * @param ac
	 */
	public static void saveCurrentAccount(AccountContext ac) {
		ACCOUNT_HOLDER.set(ac);
	}
}
