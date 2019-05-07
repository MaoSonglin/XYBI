package com.xinyibi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLogInfo {
	// 请求的主机
	private String host;
	
	private int port;
	// 访问的资源
	private String resource;
	
	//远程用户
	private String remoteUser;
	
	// 系统用户
	private String sysUser;
}
