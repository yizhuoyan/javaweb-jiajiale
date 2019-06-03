package com.vip.web.controller;

import com.vip.vo.VipDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.vip.ao.VipAo;
import com.vip.dto.JSONResponse;
import com.vip.dto.QueryResult;
import com.vip.entity.VipDetailEntity;
import com.vip.service.VipManageService;
import com.vip.vo.VipListVO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VipController {

	@Autowired
	VipManageService service;

	@PostMapping("/vip")
	public JSONResponse addVip(@Valid VipAo ao) throws Exception {

        System.out.println(service.getClass()+"----------------------");
        service.addVip(ao);

		return JSONResponse.ok();
	}

    @GetMapping("/vip/{id}")
    public JSONResponse checkVipById(@PathVariable String id) throws Exception {
        VipDetailEntity e = service.checkVipById(id);
        return JSONResponse.ok(e);
    }

	//vip?key=11&pageNo=1
    //vip?mobile=11&pageNo=1
	@GetMapping("/vip")
	public JSONResponse queryVipByKey(String mobile, String key, String pageNo, String pageSize) throws Exception {
        if(mobile!=null){
            service.queryVipByMobile(mobile);
            VipDetailEntity e = service.queryVipByMobile(mobile);
            return JSONResponse.ok(VipDetailVO.of(e));
        }
		int pageNoInt = Integer.parseInt(pageNo);
		int pageSizeInt = Integer.parseInt(pageSize);
		QueryResult<VipDetailEntity> queryEntityResult = service.queryVipByKey(key, pageNoInt, pageSizeInt);
		QueryResult<VipListVO> queryResult=queryEntityResult.rowsMap(VipListVO::of);
		return JSONResponse.ok(queryResult);
	}



	@PutMapping("/vip/{id}")
	public JSONResponse modifyVip(@PathVariable String id, VipAo ao) throws Exception {
		service.modifyVip(id, ao);
		return JSONResponse.ok();
	}
}
