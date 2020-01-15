package com.vip.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 针对所有表的Dao操作
 * @author Administrator
 *
 */
public interface CRUDDao<E,ID> {
	
	void insert(E e)throws SQLException;
	void update(@Param("id")String id,@Param("e") Map<String,Object> map)throws SQLException;
	void delete(ID id)throws SQLException;
    E selectById(ID id)throws SQLException;
	E select(@Param("column") String column,@Param("value")Object value)throws SQLException;
	boolean exists(@Param("column") String column,@Param("value")Object value)throws SQLException;
	List<E> selectAll(@Param("orderBy")String orderBy)throws SQLException;
	
}
