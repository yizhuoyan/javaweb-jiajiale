package com.vip.web.controller.spring;

import com.vip.dto.AccountContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 5/21.
 */
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("判断是否已登录");
        if(isLogin(request)){
            System.out.println("已登录");
            return true;
        }
        System.out.println("未登录");

        //response.sendRedirect(request.getContextPath()+"/login.html");
        response.sendError(403);
        return false;
    }

    private boolean isLogin(HttpServletRequest req){
        HttpSession session=req.getSession(false);
        if(session==null){
            return false;
        }

        return session.getAttribute(AccountContext.class.getName())!=null;
    }
}
