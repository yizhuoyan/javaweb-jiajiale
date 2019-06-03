package com.vip.service;

import com.vip.ao.VipConsumeAo;
import com.vip.dto.QueryResult;
import com.vip.entity.VipConsumeEntity;

public interface VipConsumeManageService {

	/**
	 * 添加vip消费记录
	 * @param ao
	 * @throws Exception
	 */
	void addVipConsume(VipConsumeAo ao)throws Exception;
	/**
	 * 查询指定消费记录详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	VipConsumeEntity checkVipConsume(String id)throws Exception;
	QueryResult<VipConsumeEntity> queryVipConsumeByMobile(String mobile,int pageNo, int pageSize) throws Exception ;
	/**
	 * 作废指定的消费记录
	 * @param id
	 * @throws Exception
	 */
	void deleteVipConsume(String id)throws Exception;
	
	QueryResult<VipConsumeEntity> queryVipConsumeByKey(String key, int pageNo, int pageSize) throws Exception;
}
