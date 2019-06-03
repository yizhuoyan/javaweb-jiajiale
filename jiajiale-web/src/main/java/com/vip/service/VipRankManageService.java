package com.vip.service;

import java.util.List;

import com.vip.entity.VipRankEntity;

public interface VipRankManageService extends CommonService {

	List<VipRankEntity> listRanks()throws Exception;
}
