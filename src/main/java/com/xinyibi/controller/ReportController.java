package com.xinyibi.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.xinyibi.exception.ServiceException;
import com.xinyibi.model.ReportElement;
import com.xinyibi.pojo.Element;
import com.xinyibi.pojo.Report;
import com.xinyibi.service.ReportService;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ReportService service;
	
	@RequestMapping("/add")
	public Message<?> addReport(Report report){
		boolean b = service.addNewReport(report);
		return b? Message.success("添加成功", report) : Message.fail("添加失败", report);
	}
	
	
	@RequestMapping("/delete")
	public Message<?> drop(Long id){
		if(id == null) return Message.error("请输入报表ID", null);
		boolean drop = service.drop(id);
		return drop ? Message.success("删除成功", id):Message.fail("删除失败", id) ;
	}
	
	@RequestMapping("/update")
	public Message<?> update(Report report){
		boolean update = service.update(report);
		return update ? Message.success("修改成功", report) : Message.fail("修改失败", report);
	}
	
	@RequestMapping("/list")
	public Message<?> list(PageEntry entry){
		PageInfo<Report> list = service.list(entry);
		return Message.success("查询成功", list);
	}
	
	/**
	 * 向报表中添加元素
	 * @param reportId
	 * @param element
	 * @return
	 */
	@RequestMapping("/add/elemnt")
	public Message<?> addElement(Long reportId,Element element){
		ReportElement reportElement = new ReportElement();
		reportElement.setId(reportId);
		ArrayList<Element> arrayList = new ArrayList<Element>();
		reportElement.setElements(arrayList);
		try {
			boolean b = service.addReportElement(reportElement);
			return b ? Message.success("添加成功", b) : Message.fail("添加失败", b);
		} catch (ServiceException e) {
			e.printStackTrace();
			return Message.error(e.getMessage(), e);
		}
	}
}
