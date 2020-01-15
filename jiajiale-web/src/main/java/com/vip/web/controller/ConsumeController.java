package com.vip.web.controller;

import com.yizhuoyan.common.StringUtil;
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
public class ConsumeController implements StringUtil {
	@Autowired
	VipConsumeManageService service;

	@PostMapping("/vipconsume")
	public JSONResponse add(VipConsumeAo ao) throws Exception {
		service.addVipConsume(ao);
		return JSONResponse.ok();
	}

	@GetMapping("/vipconsume/{idOrMobile}")
	public JSONResponse check(@PathVariable  String idOrMobile) throws Exception {
		VipConsumeEntity e = service.checkVipConsume(idOrMobile);
		return JSONResponse.ok(VipConsumeDetailVO.of(e));
	}
	@DeleteMapping("/vipconsume/{id}")
	public JSONResponse handleRequest(@PathVariable String id) throws Exception {

		service.deleteVipConsume(id);
		return JSONResponse.ok();

	}

	@GetMapping("/vipconsume")
	public JSONResponse list(String key, String pageNo, String pageSize) throws Exception {
        Integer pageNoInt = parseInt(pageNo,1);
        Integer pageSizeInt =parseInt(pageSize,10);
		QueryResult<VipConsumeEntity> queryResult = service.queryVipConsumeByKey(key, pageNoInt, pageSizeInt);
		QueryResult<VipConsumeListVO> queryVoResult = queryResult.rowsMap(VipConsumeListVO::of);
		return JSONResponse.ok(queryVoResult);

	}
}
