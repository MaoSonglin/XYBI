package com.xinyibi.service;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xinyibi.exception.ServiceException;
import com.xinyibi.mapper.FileInfoMapper;
import com.xinyibi.pojo.FileInfo;
import com.xinyibi.pojo.FileInfoExample;

@Service
public class FileService {
	
	@Autowired
	private FileInfoMapper fileMapper;
	
	public InputStream getFile(long id) throws ServiceException{
		FileInfo fileInfo = fileMapper.selectByPrimaryKey(id);
		if(fileInfo == null) throw new ServiceException("ID不存在");
		
		return null;
	}
	/**
	 * 获取获得指定ID的文件的URL路径
	 * @param photo
	 * @return
	 */
	public String getFileUrl(Long photo) {
		// 设置访问头像的url
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		String scheme = request.getScheme();
		String contextPath = request.getContextPath();
		String url = scheme+"://"+serverName+":"+serverPort+contextPath+"/file/src/"+photo;
		return url;
	}
	
	
	@Transactional
	public boolean saveFile(FileInfo fileInfo) {
		int insert = fileMapper.insert(fileInfo);
		
		FileInfoExample example = new FileInfoExample();
		example.createCriteria().andCreateDateEqualTo(fileInfo.getCreateDate());
		List<FileInfo> list = fileMapper.selectByExample(example);
		if(list.isEmpty()){
			return false;
		}
		FileInfo next = list.iterator().next();
		fileInfo.setId(next.getId());
		fileInfo.setPath(getFileUrl(fileInfo.getId()));
		
		
		
		return insert>0;
	}
}
