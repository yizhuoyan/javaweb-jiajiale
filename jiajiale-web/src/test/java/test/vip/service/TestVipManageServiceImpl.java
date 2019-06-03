package test.vip.service;

import com.vip.ao.VipAo;
import com.vip.dto.QueryResult;
import com.vip.entity.VipDetailEntity;
import com.vip.service.VipManageService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.vip.AbstractSpringTest;

import java.util.List;


class TestVipManageServiceImpl extends AbstractSpringTest  {
	@Autowired
	VipManageService service;
	
	@Test
	void testAddVip() throws Exception{
		VipAo e = new VipAo();
		e.setName("name6161");
		e.setMobile("mobile9075");
		e.setSex("1");
		e.setBirthday("1964-01-01");
		e.setQq("qq9240");
		e.setEmail("email3689");
		e.setAddress("address883");
		e.setZipCode("zipCode521");
		e.setRankId("1");
		e.setRemark("remark2965");
		
		service.addVip(e);
	}
	@Test
	void testQueryVipByMobile0() throws Exception{
		VipDetailEntity vip=service.queryVipByMobile(null);
		
	}
	@Test
	void testQueryVipByMobile1() throws Exception{
		VipDetailEntity vip=service.queryVipByMobile("");
		Assert.assertNull(vip);
	}
	@Test
	void testQueryVipByMobile2() throws Exception{
		VipDetailEntity vip=service.queryVipByMobile("13888888888");
		Assert.assertNotNull(vip);
	}

	@Test
	void testCheckVipById() {


	}

	@Test
	void testQueryVipByKey() throws Exception{
		String key=" %  ";
		QueryResult<VipDetailEntity> result=service.queryVipByKey(key, 1, 5);
		List<VipDetailEntity> rows=result.getRows();
		for (VipDetailEntity v : rows) {
			System.out.println(v);
		}
	}

	@Test
	void testModifyVip() {


	}

}
