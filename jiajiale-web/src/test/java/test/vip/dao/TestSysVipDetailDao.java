package test.vip.dao;

import com.vip.dao.VipDetailDao;
import com.vip.entity.VipDetailEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.vip.AbstractSpringTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
public class TestSysVipDetailDao extends AbstractSpringTest {
	@Autowired
	VipDetailDao dao;
	
	@Test
	public void test00Insert() throws Exception{
		VipDetailEntity e = new VipDetailEntity();
		e.setName("name9024");
		e.setTotalDiscountMoney(73);
		e.setTotalConsumeMoney(27);
		e.setLastConsumeTime(LocalDateTime.now());
		e.setId("id3813");
		e.setAccount("account3148");
		e.setMobile("mobile8685");
		e.setSex(false);
		e.setBirthday(LocalDate.now());
		e.setQq("qq9734");
		e.setEmail("email7592");
		e.setAddress("address4963");
		e.setZipCode("zipCode7067");
		e.setRemark("remark2960");
		
		e.setRankId("1");
		e.setCreateUserId("00000000000000000000000000000000");
		e.setCreateTime(LocalDateTime.now());


		dao.insert(e);
		
	}
	
	@Test
	public void test55Select() throws Exception{
		VipDetailEntity e=dao.select("id", "id");
		Assert.assertNotNull("不能查到",e);
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
