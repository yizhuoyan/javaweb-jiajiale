package com.vip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yizhuoyan.common.*;
import com.vip.ao.ModifyPasswordAo;
import com.vip.dao.SysUserDao;
import com.vip.dto.AccountContext;
import com.vip.entity.SysUserEntity;
import com.vip.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
	SysUserDao udao;

	@Override
	public AccountContext login(String account, String password) throws Exception {
		// 1
		account = $("账号", account);
		password = $("密码", password);

		// 2 执行业务逻辑
		SysUserEntity u = udao.select("account", account);
		assertNotNull("账号和密码不匹配", u);
		assertEquals("账号和密码不匹配", u.getPassword(), password);

		AccountContext ac = new AccountContext();
		ac.setAccount(account);
		ac.setAvatar(u.getAvatar());
		ac.setId(u.getId());
		ac.setLastLoginIp(u.getLastLoginIp());
		ac.setName(u.getName());
		return ac;
	}

	@Override
	@Transactional
	public void updatePassword(String id, ModifyPasswordAo ao)
			throws Exception {
		// 1 验证参数
		// 1.0 四个参数都不能为空
		id = $("id不能为空", id);
		String oldPassword = $("旧密码", ao.getOldPassword());
		String newPassword = $("新密码", ao.getNewPassword());
		String newPasswordConfirm = $("新密码确认", ao.getNewPasswordConfirm());
		// 1.1 两次密码一致 新密码是否符合规范(6-16位)
		assertEquals("两次密码不一致", newPassword, newPasswordConfirm);
			SysUserEntity u = udao.select("id", id);
			// 1.2 id存在
			assertNotNull("id不存在", u);
			// 1.3 old密码必须和原密码一致
			assertEquals("旧密码错误", u.getPassword(), oldPassword);
			// 1.4 新密码不能和旧密码一样
			assertNotEquals("新密码不能和旧密码一样", oldPassword, newPassword);

			// 2 执行业务逻辑
			// 2.1 更新数据库

			udao.update(id, BeanMap.of("password", newPassword));
	}

}
