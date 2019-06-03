package com.vip.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.vip.ao.ModifyPasswordAo;
import com.vip.dto.AccountContext;
import com.vip.dto.JSONResponse;
import com.vip.service.UserAccountService;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {
	@Autowired
	UserAccountService accountService;

	@PostMapping("/session")
	public JSONResponse login(@NotBlank(message ="accont.not-blank")
                                          String account,
                              @NotBlank(message="password.not-blank") String password, HttpSession session) throws Exception {
		AccountContext ac = accountService.login(account, password);
		session.setAttribute(AccountContext.class.getName(), ac);
		return JSONResponse.ok(ac);
	}

	@DeleteMapping("/session/{uid}")
	public JSONResponse logout(@PathVariable  String uid,HttpSession session) throws Exception {
		session.invalidate();
        System.out.println(uid);
        return JSONResponse.ok();
	}

    @PatchMapping("/user/{id}")
	public JSONResponse modifyUserPassword(@PathVariable String id, ModifyPasswordAo ao) throws Exception {

		accountService.updatePassword(id, ao);
		return JSONResponse.ok();
	}
}
