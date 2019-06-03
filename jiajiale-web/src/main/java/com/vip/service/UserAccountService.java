package com.vip.service;

import com.vip.ao.ModifyPasswordAo;
import com.vip.dto.AccountContext;

public interface UserAccountService extends CommonService {
	/**
	 * 工作人员登录
	 * @param account 账号
	 * @param password 密码
	 * @return 账号上下文
	 * @throws Exception
	 */
	AccountContext login(String account,String password)throws Exception;
	/**
	 * 修改密码
	 * @param id 账号id
	 * @param oldPassword 旧密码
	 * @param newPasswrod 新密码
	 * @param newPasswordConfirm 新密码确认
	 * @throws Exception
	 */
    void updatePassword(String id,ModifyPasswordAo ao)throws Exception;
    
}
