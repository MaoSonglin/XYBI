package com.xinyibi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.service.DataBaseService;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;

@RestController
@RequestMapping("/db")
public class DataBaseController {

	@Autowired
	private DataBaseService dataBaseService;
	/**
	 * 配置新的数据源
	 * @return
	 */
	@RequestMapping("/config")
	public Message<?> configNewDataSource(DatabaseInfo dbInfo){
		Message<?> msg = dataBaseService.addDataBase(dbInfo);
		return msg;
	}
	
	@RequestMapping("/page")
	public Message<?> queryForPage(PageEntry page,HttpServletRequest request){
		Integer curPage = page.getPage();
		if(curPage == null){
			PageEntry page2 = (PageEntry) request.getSession().getAttribute("pageEntry");
			if(page2!=null){
				page.setPage(page2.getPage());
				page.setSize(page2.getSize());
				page.setConditions(page2.getConditions());
			}
		}
		if(page.getPage()==null)page.setPage(1);
		if(page.getSize()==null)page.setSize(10);
		Message<?> queryForPage = dataBaseService.queryForPage(page);
		return queryForPage;
	}
}