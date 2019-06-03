package com.vip.service;

import com.vip.exception.VipException;
import com.yizhuoyan.common.StringUtil;
import com.yizhuoyan.common.validation.AssertThrowService;

public interface CommonService extends AssertThrowService,StringUtil{

	@Override
	default void throwException(String message, Object... args) {
		throw new VipException("validate",String.format(message, args));
	}
}
