package com.vip.entity;

import java.time.*;

import lombok.Data;
/**
 * 
 *@Auther yizhuoyan
 * 
 */
@Data
public class VipRankEntity{
	
		/**等级代号*/
		private int id;		
	
		/**等级名称*/
		private String name;		
	
		/**升级需消费总金额(单位元)*/
		private int needConsume;		
	
		/**可享受折扣*/
		private int enjoyDiscount;		
	
		/**展示顺序*/
		private int showOrder=0;		
	
		/**备注*/
		private String remark;
}