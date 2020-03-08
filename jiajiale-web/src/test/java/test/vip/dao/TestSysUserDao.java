package test.vip.dao;

import com.vip.dao.SysAccountDao;
import com.vip.entity.SysAccountEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.vip.AbstractSpringTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class TestSysUserDao extends AbstractSpringTest {
	@Autowired
	SysAccountDao dao;
	
	@Test
	public void test00Insert() throws Exception{
		SysAccountEntity e = new SysAccountEntity();
		e.setName("name");
		e.setId("id");
		e.setAccount("account");
		e.setPassword("password");
		e.setAvatar("avatar");
		e.setCreateTime(LocalDateTime.now());
		e.setLastLoginTime(LocalDateTime.now());
		e.setRemark("remark");
		e.setLastLoginIp("lastLoginIp");
		e.setStatus(0);
		e.setLastModifyPasswordTime(LocalDateTime.now());
		dao.insert(e);
		
	}
	
	@Test
	public void test55Select() throws Exception{
		SysAccountEntity e=dao.select("id", "id");

		Assert.assertNotNull("不能为空" ,e);
	}
	
	@Test
	public void test99Delete() throws Exception{
		dao.delete("id");
	}
	
	@Test
	public void test88updte() throws Exception{
		Map<String,Object> map=new HashMap<>();
		map.put("name", "张三");
		map.put("password", "123456");
		dao.update("0001", map);
	}
}
