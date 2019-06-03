package com.vip.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.vip.entity.SysUserEntity;
import com.vip.entity.VipDetailEntity;
import com.vip.entity.VipRankEntity;

import lombok.Data;

@Data
public class VipListVO {
	private String id;
	/** vip账号(目前客户手机号) */
	private String account;
	/** 手机号，可作为会员凭证 */
	private String mobile;

	/** 账号名称(客户姓名) */
	private String name;
	/** 性别 */
	private Boolean sex;
	/** 等级 */
	private String rankName;
	/** 创建人 */
	private String createUserName;
	
	public static VipListVO of(VipDetailEntity e) {
		if(e==null)return null;
		VipListVO vo = new VipListVO();
		vo.setName(e.getName());
		vo.setId(e.getId());
		vo.setAccount(e.getAccount());
		vo.setMobile(e.getMobile());
		vo.setSex(e.getSex());
		if(e.getRank()!=null) {
            vo.setRankName(e.getRank().getName());
        }

		vo.setCreateUserName(e.getCreateUser().getName());
		return vo;
	}
}
