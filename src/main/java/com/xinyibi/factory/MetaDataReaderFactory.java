package com.xinyibi.factory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xinyibi.XinyiBiResource;
import com.xinyibi.adapter.ExcelMetaDataReader;
import com.xinyibi.adapter.JdbcMetaDataReader;
import com.xinyibi.adapter.MetaDataReader;
import com.xinyibi.mapper.FileInfoMapper;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.FileInfo;

public class MetaDataReaderFactory {
	
	private MetaDataReaderFactory(){}
	
	public static MetaDataReader getMetaDataReader(DatabaseInfo databaseInfo){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		XinyiBiResource resource = context.getBean(XinyiBiResource.class);
		// 数据库连接url
		String url = databaseInfo.getUrl();
		// 数据库类型
		String databaseType = databaseInfo.getDatabaseType();
		if("sqlite".equals(databaseType)){
			// sqlite数据库
			String contextPath = "${contextPath}";
			if(url.contains(contextPath)){
				String realPath = request.getServletContext().getRealPath(resource.getDbdir());
				url = StringUtils.replace(url, contextPath, realPath);
			}
		}
		
		
		
		if("Excel".equals(databaseType)){
			// Excel工作簿
			return new ExcelMetaDataReader();
		}else{
			JdbcMetaDataReader reader = new JdbcMetaDataReader();
			reader.setUrl(databaseInfo.getUrl());
			reader.setUsername(databaseInfo.getUname());
			reader.setPassword(databaseInfo.getUpwd());
			reader.setDriverClassName(databaseInfo.getDriverClassName());
			Long driverFileId = databaseInfo.getDriverFileId();
			if(driverFileId != null){
				FileInfoMapper fileInfoMapper = context.getBean(FileInfoMapper.class);
				FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(driverFileId);
				String path = fileInfo.getPath();
				String replaceAll = path.replaceAll("${contextPath}", request.getServletContext().getRealPath(resource.getLibdir()));
				reader.setDriverFile(replaceAll);
			}
			return reader;
		}
	}
}
