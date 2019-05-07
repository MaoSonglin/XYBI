package com.xinyibi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix="xinyi")
@Data
public class XinyiBiResource {
	
	// 数据库文件目录
	@Value("${xinyi.database.dir:file/database}")
	private String dbdir = "upload/database";
	
	@Value("#{systemProperties['user.dir']}")
	private String workDir;
	
	@Value("${xinyi.jar.lib:file/lib}")
	private String libdir;
	
	@Value("${xinyi.file.dir:file}")
	private String filedir;
	
	@Value("${xinyi.file.packet:1048576}")
	private double packet;

}
