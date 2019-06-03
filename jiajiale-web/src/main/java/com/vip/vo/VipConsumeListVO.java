package com.vip.vo;

import java.time.LocalDateTime;

import com.vip.entity.SysUserEntity;
import com.vip.entity.VipConsumeEntity;
import com.vip.entity.VipDetailEntity;

import lombok.Data;
@Data
public class VipConsumeListVO {
	private String id;		
	private String mobile;
	private String vipName;
	private LocalDateTime consumeTime;		
	/**关联订单id*/
	private String orderId;		
	/**消费金额(元分)*/
	private int consumeMoney;		
	private String createUserName;
	
	public static VipConsumeListVO of(VipConsumeEntity e) {
		if(e==null)return null;
		VipConsumeListVO vo=new VipConsumeListVO();
		SysUserEntity createUser=e.getCreateUser();
		vo.setCreateUserName(createUser.getName());
		vo.setId(e.getId());
		VipDetailEntity vip=e.getVip();
		vo.setMobile(vip.getMobile());
		vo.setVipName(vip.getName());
		vo.setConsumeTime(e.getConsumeTime());
		vo.setOrderId(e.getOrderId());
		vo.setConsumeMoney(e.getConsumeMoney());
		return vo;

	}
}
