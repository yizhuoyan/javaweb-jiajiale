package com.vip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vip.entity.VipDetailEntity;
import com.vip.entity.VipRankEntity;

public interface VipDetailDao extends TemplateDao<VipDetailEntity> {

	List<VipDetailEntity> selectByKey(@Param("key") String key);
}
