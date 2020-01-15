package com.vip.web.controller;

import com.vip.ao.ModifyPasswordAo;
import com.vip.dto.AccountContext;
import com.vip.dto.JSONResponse;
import com.vip.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(path = "/account/avatar/{id}")
    public JSONResponse modifyUserAvatar(@PathVariable String id, ModifyPasswordAo ao) throws Exception {
        accountService.updatePassword(id, ao);
        return JSONResponse.ok();
    }

    @PostMapping("/session")
    public JSONResponse login(String account,
                              String password, HttpSession session) throws Exception {
        AccountContext ac = accountService.login(account, password);
        session.setAttribute(AccountContext.class.getName(), ac);
        return JSONResponse.ok(session.getId());
    }

    @DeleteMapping("/session/{token}")
    public JSONResponse logout(@PathVariable  String token,HttpSession session) throws Exception {
        session.invalidate();
        System.out.println(token);
        return JSONResponse.ok();
    }


    @GetMapping("/session")
    public ResponseEntity getSession(HttpSession session){
        AccountContext ac= (AccountContext) session.getAttribute(AccountContext.class.getName());
        if(ac!=null) {
            return ResponseEntity.ok(ac);
        }
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

}
