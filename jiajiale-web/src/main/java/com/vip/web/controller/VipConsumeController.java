package com.vip.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vip.ao.VipConsumeAo;
import com.vip.dto.JSONResponse;
import com.vip.dto.QueryResult;
import com.vip.entity.VipConsumeEntity;
import com.vip.service.VipConsumeManageService;
import com.vip.vo.VipConsumeDetailVO;
import com.vip.vo.VipConsumeListVO;

@RestController
@RequestMapping("/api")
public class VipConsumeController {
	@Autowired
	VipConsumeManageService service;

	@PostMapping("/vipconsume")
	public JSONResponse add(VipConsumeAo ao) throws Exception {
		service.addVipConsume(ao);
		return JSONResponse.ok();
	}

	@GetMapping("/vipconsume/{id}")
	public JSONResponse check(@PathVariable  String idorMobile) throws Exception {
		VipConsumeEntity e = service.checkVipConsume(idorMobile);
		return JSONResponse.ok(VipConsumeDetailVO.of(e));
	}
    //_method=Delete
	@DeleteMapping("/vipconsume/{id}")
	public JSONResponse handleRequest(@PathVariable String id) throws Exception {

		service.deleteVipConsume(id);
		return JSONResponse.ok();

	}

	@GetMapping("/vipconsume")
	public JSONResponse list(String key, int pageNo, int pageSize) throws Exception {

		QueryResult<VipConsumeEntity> queryResult = service.queryVipConsumeByKey(key, pageNo, pageSize);
		QueryResult<VipConsumeListVO> queryVoResult = queryResult.rowsMap(VipConsumeListVO::of);
		return JSONResponse.ok(queryVoResult);

	}
}
