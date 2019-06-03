package test.vip.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vip.ao.VipConsumeAo;
import com.vip.service.VipConsumeManageService;

import test.vip.AbstractSpringTest;

class TestVipConsumeManageServiceImpl extends AbstractSpringTest {
	@Autowired
	VipConsumeManageService service;
	@Test
	final void testAddVipConsume()throws Exception {
		VipConsumeAo e = new VipConsumeAo();
		e.setVipMobile("13888888888");
		e.setOrderId("orderId9004");
		e.setConsumeMoney("434");
		e.setRemark("remark7257");
		
		service.addVipConsume(e);
	}

	@Test
	final void testQueryVipConsumeByMobile() throws Exception{
		service.queryVipConsumeByMobile("13888888888", 1, 5);
	}

	@Test
	final void testCheckVipConsume() throws Exception{
		service.checkVipConsume("a85af898b781ae891374aa8beeba8f15");
	}

	@Test
	final void testQueryVipConsumeByKey()throws Exception {
		service.queryVipConsumeByKey("138888", 1, 5);
	}

	@Test
	final void testDeleteVipConsume() throws Exception {
		service.deleteVipConsume("a85af898b781ae891374aa8beeba8f15");
	}

}
