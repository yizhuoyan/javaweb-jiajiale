package com.vip.ao;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VipConsumeAo {
	/**会员手机号*/
	@NotBlank
	private String vipMobile;
	/**关联订单id*/
	private String orderId;	
	/**消费金额(元分)*/
	private String consumeMoney;
	/**备注*/
	private String remark;
}
