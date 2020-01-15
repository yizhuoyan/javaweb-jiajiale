package com.vip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vip.entity.VipConsumeEntity;

public interface VipConsumeDao extends CRUDDao<VipConsumeEntity,String> {
	
	List<VipConsumeEntity> selectByKey(@Param("key") String key);
	List<VipConsumeEntity> selectByVipId(String id)throws Exception;
}
