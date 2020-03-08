package com.vip.web.interceptor;

import com.vip.dto.AccountContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 5/21.
 */
@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("{1}判断是否已登录",request.getRequestURI());
        if(isLogin(request)){
            log.debug("已登录");
            return true;
        }
        log.debug("未登录，403返回");
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
