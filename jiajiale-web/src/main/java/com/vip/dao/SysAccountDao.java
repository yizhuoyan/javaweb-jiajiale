package com.vip.dao;

import com.vip.entity.SysAccountEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysAccountDao extends CRUDDao<SysAccountEntity,String> {

	
}
