package com.vip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vip.entity.VipConsumeEntity;
import com.vip.entity.VipDetailEntity;

public interface VipConsumeDao extends TemplateDao<VipConsumeEntity> {
	
	List<VipConsumeEntity> selectByKey(@Param("key") String key);
	List<VipConsumeEntity> selectByVipId(String id)throws Exception;
}
