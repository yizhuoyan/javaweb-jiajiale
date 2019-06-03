package com.vip.ao;

import java.time.LocalDateTime;

import com.vip.entity.SysUserEntity;
import com.vip.entity.VipRankEntity;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * VIP输入对象
 */
@Data
public class VipAo {
    String id;
	
	/**手机号，可作为会员凭证*/
	@NotBlank(message="{mobile.not-blank}")
    @Size(min =11 ,max = 11,message = "手机号应该是11位")
	private String mobile;

	/**账号名称(客户姓名)*/
    @NotBlank(message = "客户姓名不能为空")
	private String name;		

	/**性别*/
	private String sex;		

	/**出生日期(1990-01-01)*/
	private String birthday;

	/***/
	private String qq;		

	/***/
	private String email;		

	/***/
	private String address;		

	/**邮编*/
	private String zipCode;		

	/**等级*/
	private String rankId;

	/***/
	private String remark;		



}
