package com.vip.web.controller.userhome;

import com.vip.ao.ModifyPasswordAo;
import com.vip.dto.AccountContext;
import com.vip.dto.JSONResponse;
import com.vip.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@Validated
public class AccountController {
	@Autowired
	UserAccountService accountService;



    @PatchMapping(path = "/account/password/{id}")
	public JSONResponse modifyUserPassword(@PathVariable String id, ModifyPasswordAo ao) throws Exception {
		accountService.updatePassword(id, ao);
		return JSONResponse.ok();
	}
    @PatchMapping(path = "/account/name/{id}")
    public JSONResponse setRecommendAvatar(@PathVariable String id,String name
            , HttpSession session)throws Exception{
        //获取当前登录用户
        AccountContext ac= (AccountContext) session.getAttribute(AccountContext.class.getName());
        //更新帐号
        accountService.changeName(ac.getId(),name);
        return JSONResponse.ok();
    }
}
