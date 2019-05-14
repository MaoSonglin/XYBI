package com.xinyibi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xinyibi.pojo.Report;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;

@RestController
@RequestMapping("/report")
public class ReportController {

	@RequestMapping("/add")
	public Message<?> addReport(Report report){
		return null;
	}
	
	
	@RequestMapping("/delete")
	public Message<?> drop(Long id){
		return null;
	}
	
	@RequestMapping("/update")
	public Message<?> update(Report report){
		return null;
	}
	
	@RequestMapping("/list")
	public Message<?> list(PageEntry entry){
		return null;
	}
}
