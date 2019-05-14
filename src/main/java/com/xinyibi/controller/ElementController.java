package com.xinyibi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinyibi.exception.ServiceException;
import com.xinyibi.pojo.Element;
import com.xinyibi.service.ElementService;
import com.xinyibi.vo.Message;

@Controller
@RequestMapping("/element")
public class ElementController {
	
	@Autowired
	private ElementService service;
	
	@RequestMapping("/add")
	public Message<?> add(Element e){
		boolean addElement = service.addElement(e);
		
		return addElement ? Message.success("添加成功", e) : Message.fail("添加失败", e);
	}
	
	@RequestMapping("/list")
	public Message<?> list(Long reportId){
		List<Element> list = null;
		try {
			list = service.getByReportId(reportId);
		} catch (ServiceException e) {
			e.printStackTrace();
			return Message.fail(e.getMessage(), e);
		}
		return Message.success("查询成功", list);
	}
	
	
	@RequestMapping("/delete")
	public Message<?> drop(Long id){
		boolean dropElement = service.dropElement(id);
		return dropElement ? Message.success("删除成功", id) : Message.fail("删除失败", id);
	}
	
	@RequestMapping("/update")
	public Message<?> update(Element e){
		boolean b = service.updateElement(e);
		return b ? Message.success("修改成功", e) : Message.fail("修改失败", e);
	}
}
