package com.vip.web.controller;

import com.vip.ao.VipAo;
import com.vip.dto.JSONResponse;
import com.vip.dto.QueryResult;
import com.vip.entity.VipDetailEntity;
import com.vip.service.VipManageService;
import com.vip.vo.VipDetailVO;
import com.vip.vo.VipListVO;
import com.yizhuoyan.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class VipController implements StringUtil {

    @Autowired
    VipManageService service;

    @PostMapping("/vip")
    public JSONResponse addVip(@Valid VipAo ao) throws Exception {

        System.out.println(service.getClass() + "----------------------");
        service.addVip(ao);

        return JSONResponse.ok();
    }

    @GetMapping("/vip/{id}")
    public JSONResponse checkVipById(@PathVariable String id) throws Exception {
        VipDetailEntity e = service.checkVipById(id);
        return JSONResponse.ok(VipDetailVO.of(e));
    }
    //vip?mobile=11
    @GetMapping(path = "/vip", params = "mobile")
    public JSONResponse queryVipByMobile(String mobile) throws Exception {
        service.queryVipByMobile(mobile);
        VipDetailEntity e = service.queryVipByMobile(mobile);
        return JSONResponse.ok(VipDetailVO.of(e));
    }

    //vip?key=11&pageNo=1
    @GetMapping(path = "/vip", params = {"rankId","key"})
    public JSONResponse queryVipByKey(String rankId, String key, String pageNo, String pageSize) throws Exception {
        Integer pageNoInt = parseInt(pageNo,1);
        Integer pageSizeInt =parseInt(pageSize,10);
        Integer rankIdInt=parseInt(rankId,null);
        QueryResult<VipDetailEntity> queryEntityResult = service.queryVipByKey(rankIdInt, key, pageNoInt, pageSizeInt);
        QueryResult<VipListVO> queryResult = queryEntityResult.rowsMap(VipListVO::of);
        return JSONResponse.ok(queryResult);
    }


    @PutMapping("/vip/{id}")
    public JSONResponse modifyVip(@PathVariable String id, VipAo ao) throws Exception {
        service.modifyVip(id, ao);
        return JSONResponse.ok();
    }
}
