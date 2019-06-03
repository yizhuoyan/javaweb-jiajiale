package com.vip.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vip.dao.VipRankDao;
import com.vip.entity.VipRankEntity;
import com.vip.service.VipRankManageService;

@Service
public class VipRankManageServiceImpl implements VipRankManageService {
	@Autowired
	VipRankDao vdao;

	@Override
	public List<VipRankEntity> listRanks() throws Exception {
		return vdao.selectAll("id");
	}

}
