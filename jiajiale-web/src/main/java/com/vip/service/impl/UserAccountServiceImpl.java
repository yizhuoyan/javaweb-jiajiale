package com.vip.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vip.ao.AvatarAo;
import com.vip.ao.ModifyPasswordAo;
import com.vip.dao.SysAccountDao;
import com.vip.dto.AccountContext;
import com.vip.dto.AvatarDto;
import com.vip.entity.SysAccountEntity;
import com.vip.service.UserAccountService;
import com.yizhuoyan.common.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
    SysAccountDao udao;


	@Override
	public AccountContext login(String account, String password) throws Exception {
		// 1
		account = $("账号", account);
		password = $("密码", password);

		// 2 执行业务逻辑
		SysAccountEntity u = udao.select("account", account);
		assertNotNull("账号和密码不匹配", u);
		assertEquals("账号和密码不匹配", u.getPassword(), password);



		AccountContext ac = new AccountContext();
		ac.setAccount(account);
		ac.setAvatar("/api/avatar");
		ac.setId(u.getId());
		ac.setLastLoginIp(u.getLastLoginIp());
		ac.setName(u.getName());
		ac.setCreateTime(u.getCreateTime());
		ac.setLastLoginTime(u.getLastLoginTime());
		ac.setLastLoginAgent(u.getLastLoginAgent());
		ac.setLastModifyPasswordTime(u.getLastModifyPasswordTime());
        ac.setFirstLogin(u.getLastLoginTime()==null);
        ac.setRole(u.getRole());
		//获取登录ip
        //ThreadLocal

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
			SysAccountEntity u = udao.select("id", id);
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

    @Override
    @Transactional
    public void changeName(@NotNull String id, String newName)
            throws Exception {
        // 1 验证参数
        String oldPassword = $("新名称", newName);
        SysAccountEntity u = udao.select("id", id);
        // 1.2 id存在
        assertNotNull("{0}不存在", u,id);
        // 1.3 old密码必须和原密码一致
        assertLessThan("名称长度",newName,16);
        // 1.4 新密码不能和旧密码一样
        assertNotEquals("新名称不能和旧名称一样", u.getName(), newName);
        // 2 执行业务逻辑
        // 2.1 更新数据库
        udao.update(id, BeanMap.of("name", newName));
    }

	@Autowired
    ObjectMapper objectMapper;
    @Override
    public void changeAvatar(@NotNull String id, @NotNull AvatarAo ao) throws Exception {
	    AvatarDto dto=new AvatarDto();
	    dto.setAccountId(id);
	    dto.setContentType(ao.getContentType());
	    dto.setLocalSave(ao.isLocalSave());
	    dto.setSaveLocation(ao.getSaveLocation());
	    dto.setTotalBytes(ao.getTotalBytes());
	    dto.setHeight(ao.getHeight());
	    dto.setWidth(ao.getWidth());
        String jsonString=objectMapper.writeValueAsString(dto);
        udao.update(id,BeanMap.of("avatar",jsonString));
    }

    private AvatarDto createDefaultAvatar(String accountId){
        AvatarDto avatarDto = new AvatarDto();
        avatarDto.setAccountId(accountId);
        avatarDto.setSaveLocation("/avatar/1.jpg");
        avatarDto.setContentType("image/jpeg");
        avatarDto.setTotalBytes(0);
        avatarDto.setWidth(0);
        avatarDto.setHeight(0);
        avatarDto.setLocalSave(false);
        return avatarDto;
    }

    @Override
    public AvatarDto loadAvatar(String id) throws Exception {
        AvatarDto avatarDto=null;
        SysAccountEntity e = udao.selectById(id);
        String avatarJsonString = e.getAvatar();
        if(avatarJsonString==null){
            avatarDto=createDefaultAvatar(id);
        }else{
            avatarDto = objectMapper.readValue(avatarJsonString, AvatarDto.class);
        }
        return avatarDto;
    }
}
