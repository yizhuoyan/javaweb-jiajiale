package com.vip.entity;

import java.time.*;

import lombok.Data;


/**
 * 
 *@Auther yizhuoyan
 * 
 */
@Data
public class SysUserEntity{
		/***/
		private String id;		
	
		/**登录账号*/
		private String account;		
	
		/**账号名称*/
		private String name;		
	
		/**密码，必须3个月修改1次*/
		private String password;		
	
		/**头像*/
		private String avatar;
	
		/**创建时间*/
		private LocalDateTime timeCreate;		
	
		/**最后登录时间*/
		private LocalDateTime timeLastAlterPassword;		
	
		/***/
		private LocalDateTime timeLastLogin;		
	
		/***/
		private String remark;		
	
		/**最后登录IP*/
		private String lastLoginIp;		
	
		/**用户状态 0正常，1=锁定*/
		private int status=0;	
		
		
}
	