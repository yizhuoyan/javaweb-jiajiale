package com.vip.service;

import com.vip.ao.AvatarAo;
import com.vip.ao.ModifyPasswordAo;
import com.vip.dto.AccountContext;
import com.vip.dto.AvatarDto;

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
     * @param ao
	 * @throws Exception
	 */
    void updatePassword(String id,ModifyPasswordAo ao)throws Exception;

    /**
     * 改变头像
     * @param id 账号id
     * @param ao
     * @throws Exception
     */
    void changeAvatar(String id,AvatarAo ao)throws Exception;
    /**
     * 改变头像
     * @param id 账号id
     * @param ao
     * @throws Exception
     */
    void changeName(String id,String name)throws Exception;

    /**
     * 获取用户头像
     * @param id
     * @return
     * @throws Exception
     */
    AvatarDto loadAvatar(String id)throws Exception;

}
