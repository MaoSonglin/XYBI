package com.xinyibi.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.xinyibi.pojo.DataTableInfo;
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
	
	/**
	 * 获取指定数据库中的数据表
	 * @param id	数据库的ID
	 * @return
	 */
	@RequestMapping("/tables")
	public Message<?> getTables(String id){
		List<DataTableInfo> tables = dataBaseService.getTables(id);
		return Message.success("查询成功", tables);
	}
	
	@RequestMapping("/update")
	public Message<?> update(DatabaseInfo databaseInfo){
		boolean update = dataBaseService.update(databaseInfo);
		return update ? Message.success("修改成功", databaseInfo) : Message.fail("修改失败", databaseInfo);
	}
	
	@RequestMapping("/read")
	public Message<?> readStructure(String id){
		Message<?> message = dataBaseService.getStructure(id);
		return message;
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
