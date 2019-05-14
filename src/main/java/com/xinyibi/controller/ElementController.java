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
	
	/**
	 * 获取指定图表中的所有元素
	 * @param reportId
	 * @return
	 */
	@RequestMapping("/getByReport")
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
	
	
	/**
	 * 向元素中添加一个子弹
	 * @param elementId		元素ID
	 * @param viewFieldId	字段ID
	 * @return
	 */
	@RequestMapping("/add/data")
	public Message<?> addViewField(Long elementId, Long viewFieldId){
		boolean f = service.addViewField(elementId,viewFieldId);
		
		return f ? Message.success("添加成功", null) : Message.fail("添加失败", null);
	}
	
	/**
	 * 从元素中移除一个字段
	 * @param elementId
	 * @param viewFieldId
	 * @return
	 */
	@RequestMapping("/remove/field")
	public Message<?> removeViewField(Long elementId, Long viewFieldId){
		boolean b = service.removeViewField(elementId,viewFieldId);
		return b? Message.success("移除成功", b) : Message.fail("移除失败", b);
	}
	
}
