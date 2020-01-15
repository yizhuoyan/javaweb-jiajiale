package com.vip.entity;

import java.time.*;

import lombok.Data;


/**
 * 客户实体
 *@Auther yizhuoyan
 * 
 */
@Data
public class VipDetailEntity{
	    /***/
		private String id;		
	   /**vip-会员号码*/
		private String no;
	
		/**手机号，可作为会员凭证*/
		private String mobile;		
	
		/**账号名称(客户姓名)*/
		private String name;		
	
		/**性别*/
		private Boolean sex;
	
		/**生日*/
		private LocalDate birthday;		
	
		/***/
		private String qq;		
	
		/***/
		private String email;		
	
		/***/
		private String address;		
	
		/**邮编*/
		private String zipCode;		
	
		/**等级*/
		private VipRankEntity rank;
		/*冗余*/
		private Integer rankId;
	
		/**总优惠金额*/
		private Integer totalDiscountMoney;
	
		/**目前消费总金额，单位分元*/
		private Integer totalConsumeMoney;
	
		/***/
		private String remark;		
	
		/**创建人*/
		private SysAccountEntity createUser;
		private String createUserId;
	
		/**创建时间*/
		private LocalDateTime createTime;		
		/**
		 * 最后消费时间
		 */
		private LocalDateTime lastConsumeTime;		
}