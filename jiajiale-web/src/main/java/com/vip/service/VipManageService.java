package com.vip.service;

import com.vip.ao.VipAo;
import com.vip.dto.QueryResult;
import com.vip.entity.VipDetailEntity;

/**
 * vip管理服务
 * @author Administrator
 *
 */
public interface VipManageService extends CommonService{
	/**
	 * 录入VIP客户信息
	 * @throws Exception
	 */
	void addVip(VipAo ao)throws Exception;
	/**
	 * 通过手机号查看VIP客户信息
	 * @param mobile 手机号
	 * @return 
	 * @throws Exception
	 */
	VipDetailEntity queryVipByMobile(String mobile)throws Exception;
	/**
	 * 通过id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	VipDetailEntity checkVipById(String id)throws Exception;
	
	/**
	 * 模糊分页查询vip
	 * @param key
	 * @param pageNo
	 * @param pageSize
	 * @throws Exception
	 */
	QueryResult<VipDetailEntity> queryVipByKey(String key,int pageNo,int pageSize)throws Exception;
	
	/**
	 * 更新客户基础信息
	 * @param id 客户id
	 * @param ao  要更新的信息
	 * @throws Exception
	 */
	void modifyVip(String id,VipAo ao)throws Exception;
	
	
	
}
