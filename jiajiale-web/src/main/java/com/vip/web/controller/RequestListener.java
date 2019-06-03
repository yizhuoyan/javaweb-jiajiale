package com.vip.web.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.vip.dto.AccountContext;

@WebListener
public class RequestListener implements ServletRequestListener{

	@Override
	public void requestInitialized(ServletRequestEvent evt) {
		HttpServletRequest req = (HttpServletRequest) evt.getServletRequest();
		HttpSession session = req.getSession();
		AccountContext ac=(AccountContext)session.getAttribute(AccountContext.class.getName());
		String ip=req.getRemoteHost();
		if(ac!=null) {
			//把当前用户对象绑定到线程中
			AccountContext.saveCurrentAccount(ac);
		}
		
	}
}
