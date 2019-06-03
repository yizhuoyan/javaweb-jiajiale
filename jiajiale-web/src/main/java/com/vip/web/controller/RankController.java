package com.vip.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.vip.dto.JSONResponse;
import com.vip.entity.VipRankEntity;
import com.vip.service.VipRankManageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RankController {
	@Autowired
	VipRankManageService service;
	
	@GetMapping("/rank")
	public JSONResponse handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<VipRankEntity> list = service.listRanks();
		return JSONResponse.ok(list);
	}
}
