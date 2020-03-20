package com.vip.web.controller;

import com.vip.ao.AvatarAo;
import com.vip.ao.ModifyPasswordAo;
import com.vip.dto.AccountContext;
import com.vip.dto.JSONResponse;
import com.vip.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@Validated
public class SessionController {
	@Autowired
	UserAccountService accountService;





    @PostMapping("/session")
    public JSONResponse login(String account,
                              String password, HttpServletRequest req, HttpSession session) throws Exception {
        //获取登录IP
        String ip=req.getRemoteHost();
        String ip2=req.getRemoteAddr();
        //获取登录设备
        String userAgent=req.getHeader("user-agent");
        if(userAgent.contains("android")){

        }else if(userAgent.contains("")){

        }
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
