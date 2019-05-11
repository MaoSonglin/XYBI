package com.xinyibi;

/**
 * @author MaoSonglin
 * 此接口包含系统提供的所有访问路径接口URL
 */
public interface UrlMappingConfig {
	/**
	 * 用户登录的路径
	 */
	String USER_LOGIN 						= "/user/login";
	String USER_LOGOUT 						= "/user/logout";
	String USER_REGISTER					= "/user/register";
	/**
	 * 使用token获取登录的用户的信息
	 */
	String USER_INFO						= "/user/info";
	/**
	 * 提示用户登录
	 */
	String TO_LOGIN							= "/toLogin";
	
	
}
