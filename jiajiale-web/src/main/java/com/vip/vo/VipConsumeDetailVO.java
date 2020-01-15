package com.vip.vo;

import java.time.LocalDateTime;

import com.vip.entity.SysAccountEntity;
import com.vip.entity.VipConsumeEntity;
import com.vip.entity.VipDetailEntity;

import lombok.Data;
@Data
public class VipConsumeDetailVO {
	private String id;		
	private String mobile;
	private String no;
	private String vipId;
	private String vipName;
	private LocalDateTime consumeTime;		
	/**关联订单id*/
	private String orderId;		
	/**优惠金额(元分)*/
	private int discountMoney;		
	/**享受折扣*/
	private int enjoyDiscount;	
	/**实际付款(元分)*/
	private int actualPayMoney;		
	/**消费金额(元分)*/
	private int consumeMoney;		
	/**录入人*/
	private String createUserId;
	private String createUserName;
	private String rankName;
	private String remark;		
	
	public static VipConsumeDetailVO of(VipConsumeEntity e) {
		VipConsumeDetailVO vo=new VipConsumeDetailVO();
		vo.setActualPayMoney(e.getActualPayMoney());
		SysAccountEntity createUser=e.getCreateUser();
		vo.setCreateUserName(createUser.getName());
		vo.setId(e.getId());
		VipDetailEntity vip=e.getVip();
		vo.setMobile(vip.getMobile());
		vo.setNo(vip.getNo());
		vo.setVipId(e.getVipId());
		vo.setVipName(vip.getName());
		vo.setConsumeTime(e.getConsumeTime());
		vo.setOrderId(e.getOrderId());
		vo.setDiscountMoney(e.getDiscountMoney());
		vo.setEnjoyDiscount(e.getEnjoyDiscount());
		vo.setConsumeMoney(e.getConsumeMoney());
		vo.setCreateUserId(e.getCreateUserId());
		vo.setRemark(e.getRemark());
		vo.setRankName(vip.getRank().getName());
		return vo;

	}
}
