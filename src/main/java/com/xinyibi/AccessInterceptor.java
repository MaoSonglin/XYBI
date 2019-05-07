package com.xinyibi;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.xinyibi.pojo.Account;
import com.xinyibi.vo.AccountState;
import com.xinyibi.vo.RequestLogInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String method = request.getMethod();
		if("options".equalsIgnoreCase(method))
			return true;
		// 第一步查看session中是否存在用户信息，如果存在说明用户已经登录
		Account account = (Account) request.getSession().getAttribute("Account");
		if(account != null)
			return true;
		// 如果session中没有用户，在请求头中查找token
		String token = request.getHeader("token");
		if(token == null){
			// 如果请求头中没有token，在参数中查找token
			token = request.getParameter("token");
		}
		if(token == null && request.getCookies() != null){
			// 如果参数中也没有在cookies中查找token
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equalsIgnoreCase("token")){
					token = cookie.getValue();
					break;
				}
			}
		}
		// 如果token不为null，在容器中查找用户状态
		if(token != null){
			AccountState accountState = (AccountState) request.getServletContext().getAttribute(token);
			// 如果用户状态不为null，
			if(accountState != null ){
				if(accountState.isOver()){
					// 用户登录已经过期
					request.getServletContext().removeAttribute(token);
				}else{
					// 将用户保存到本次session中
					request.getSession().setAttribute("Account", accountState.getAccount());
					accountState.refresh();
					return true;
				}
			}
		}
		writeLog(request);
		// 跳转到登录页面
		request.getRequestDispatcher("/toLogin").forward(request, response);
		return false;
	}
	
	protected void writeLog(HttpServletRequest request){
		String requestURI = request.getRequestURI();
		String remoteHost = request.getRemoteHost();
		int remotePort = request.getRemotePort();
		String remoteUser = request.getRemoteUser();
		Account account = (Account) request.getSession().getAttribute("Account");
		RequestLogInfo logInfo = new RequestLogInfo();
		logInfo.setHost(remoteHost);
		logInfo.setPort(remotePort);
		logInfo.setRemoteUser(remoteUser);
		logInfo.setSysUser(account!=null?account.getUserName():null);
		logInfo.setResource(requestURI);
		log.info(logInfo.toString());
	}

}
