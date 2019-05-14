package com.xinyibi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinyibi.exception.ServiceException;
import com.xinyibi.mapper.ElementMapper;
import com.xinyibi.pojo.Element;
import com.xinyibi.util.StrUtils;

@Service
public class ElementService {

	@Autowired
	private ApplicationContext context;
	
	@Transactional
	public boolean addElement(Element e) {
		e.setId(StrUtils.getNextId());
		int insert = context.getBean(ElementMapper.class).insert(e);
		return insert > 0;
	}
	
	@Transactional
	public boolean updateElement(Element e) {
		int i = context.getBean(ElementMapper.class).updateByPrimaryKeySelective(e);
		return i > 0;
	}
	
	@Transactional
	public boolean dropElement(Long id) {
		int i = context.getBean(ElementMapper.class).deleteByPrimaryKey(id);
		return i > 0;
	}
	
	/**
	 * 获取指定报表下的所有元素
	 * @param reportId
	 * @return
	 * @throws ServiceException 
	 */
	public List<Element> getByReportId(Long reportId) throws ServiceException{
		if(reportId == null) throw new ServiceException("请指定报表ID");
		List<Element> elements = context.getBean(ElementMapper.class).findByReportId(reportId);
		return elements;
	}
}
