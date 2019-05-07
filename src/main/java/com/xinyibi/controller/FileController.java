package com.xinyibi.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xinyibi.pojo.Account;
import com.xinyibi.pojo.FileInfo;
import com.xinyibi.service.FileService;
import com.xinyibi.vo.Message;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	private ApplicationContext context;

	/**
	 * 读取文件返回给浏览者
	 * @param id		文件ID
	 * @param request
	 * @param respose
	 */
	@RequestMapping("/src/{id}")
	public void getFile(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse respose) throws Exception{
		DataSource ds = context.getBean(DataSource.class);
		String sql = "select "
				+ "id, file_name, file_size, create_date, mime, status, path, account_id, body "
				+ "from file_info "
				+ "where id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setLong(1, id);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()){
					String contentType = rs.getString("mime");
					respose.setContentType(contentType);
					String path = rs.getString("path");
					if(StringUtils.isEmpty(path)){
						InputStream is = rs.getBinaryStream("body");
						StreamUtils.copy(is, respose.getOutputStream());
					}else{
						if(path.contains("${contextPath}")){
							path = path.replace("${contextPath}", request.getContextPath());
							request.getRequestDispatcher(path).forward(request, respose);
						}else{
							FileUtils.copyFile(new File(path), respose.getOutputStream());
						}
					}
				}
			}
		}
		
	}

	
	@PostMapping("/upload")
	public @ResponseBody Object uploadFile(MultipartFile file,HttpServletRequest request) throws IOException{
		String fileName = file.getOriginalFilename();
		String contentType = new MimetypesFileTypeMap().getContentType(fileName);
		long fileSize = file.getSize();
		Account user = (Account) request.getSession().getAttribute("Account");
		
		FileInfo fileInfo = new FileInfo();
		fileInfo.setAccountId(user.getId());
		fileInfo.setAccountId(null);
		fileInfo.setFileName(fileName);
		fileInfo.setMime(contentType);
		fileInfo.setStatus(0);
		fileInfo.setCreateDate(new Date());
		fileInfo.setBody(StreamUtils.copyToByteArray(file.getInputStream()));
		fileInfo.setFileSize(fileSize*1d);
		FileService service = context.getBean(FileService.class);
		boolean b = service.saveFile(fileInfo);
		if(b)
			return Message.success("保存成功", fileInfo);
		else
			return Message.fail("保存失败", fileName);
	}
	
}
