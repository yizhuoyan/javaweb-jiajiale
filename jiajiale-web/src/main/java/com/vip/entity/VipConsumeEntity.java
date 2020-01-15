package com.vip.entity;

import java.time.*;

import lombok.Data;


/**
 * 
 *@Auther yizhuoyan
 * 
 */
@Data
public class VipConsumeEntity{
		public static final int STATUS_NORMAL=0,STATUS_DELETED=1;
	
		/***/
		private String id;		
	
		/**会员ID*/
		private VipDetailEntity vip;	
		private String vipId;
	
		/**消费时间*/
		private LocalDateTime consumeTime;		
	
		/**关联订单id*/
		private String orderId;		
	
		/**优惠金额(元分)*/
		private int discountMoney;		
	
		/**实际付款(元分)*/
		private int actualPayMoney;		
	
		/**消费金额(元分)*/
		private int consumeMoney;		
	
		/**录入人*/
		private SysAccountEntity createUser;
		private String createUserId;
	
		/**状态0=正常 1=作废*/
		private int status;		
	
		/***/
		private String remark;		
	
		/**享受折扣*/
		private int enjoyDiscount;		
		
}
	