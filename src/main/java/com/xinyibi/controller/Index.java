package com.xinyibi.controller;

import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinyibi.exception.ServiceException;
import com.xinyibi.pojo.Account;
import com.xinyibi.service.AccountService;
import com.xinyibi.vo.AccountState;
import com.xinyibi.vo.Message;

@Controller
public class Index {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/",produces="text/html")
	public @ResponseBody String index(){
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		StringBuffer sb = new StringBuffer();
		for (String name : beanDefinitionNames) {
			sb.append(name).append("<br>");
		}
		return sb.toString();
	}
	
	@RequestMapping("/toLogin")
	public @ResponseBody Object notLogin(){
		return Message.unaccessable();
	}
	
	/**
	 * 用户登录
	 * @param account
	 * @return
	 * @throws ServiceException 
	 */
	@PostMapping("/user/login")
	public @ResponseBody Message<?> login(Account account,String valiateCode,HttpServletRequest request) throws ServiceException{
		Message<String> message = accountService.login(account);
		if(message.getCode()==Message.SUCCESS_CODE){
			AccountState accountState = new AccountState();
			accountState.setAccount(account);
			accountState.setToken(message.getData());
			request.getSession().setAttribute("Account", accountService.getUserInfo(account.getUserName()));
			request.getServletContext().setAttribute(message.getData(), accountState);
		}
		return message;
	}
	
	/**
	 * 用户注册，或者添加用户
	 * @param account
	 * @return
	 */
	@RequestMapping("/user/register")
	public @ResponseBody Message<?> register(Account account){
		return accountService.createAccount(account);
	}
	
	/**
	 * 检查系统中是否存在管理员用户
	 * @return
	 */
	@RequestMapping("/user/existAdmin")
	public @ResponseBody Message<?> hasAdministor(){
		return accountService.existAdmin();
	}
	
	@RequestMapping("/user/info")
	public @ResponseBody Message<?> userInfo(String token ,HttpServletRequest request){
		AccountState state = (AccountState) request.getServletContext().getAttribute(token);
		Message<Account> msg = new Message<Account>();
		if(state != null){
			msg.setCode(20000);
			Account userInfo;
			try {
				userInfo = accountService.getUserInfo(state.getAccount().getUserName());
				msg.setData(userInfo);
			} catch (ServiceException e) {
				e.printStackTrace();
				msg.setCode(Message.ERROR_CODE);
				msg.setMessage(e.getMessage());
			}
		}else{
			msg.setCode(50008);
			msg.setMessage("用户没有登录，或者登录已经过期");
		}
		return msg;
	}

	@RequestMapping("/user/logout")
	public @ResponseBody Message<?> logout(String token,HttpServletRequest request){
		if(StringUtils.isEmpty(token)){
			token = request.getHeader("token");
			if(StringUtils.isEmpty(token)){
				Cookie[] cookies = request.getCookies();
				if(cookies != null){
					Optional<Cookie> findAny = Stream.of(cookies).filter(cookie->cookie.getName().equalsIgnoreCase("token")).findAny();
					if(findAny.isPresent()) token = findAny.get().getValue();
				}
				
			}
		}
		if(StringUtils.isEmpty(token)){
			return Message.fail("您还没有登录服务器", token);
		}
		request.getSession().removeAttribute("Account");
		request.getServletContext().removeAttribute(token);
		return Message.success("退出成功", token);
	}
}
