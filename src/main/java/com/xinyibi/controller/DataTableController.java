package com.xinyibi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.service.TableInfoService;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;

@Controller
@RequestMapping("/db/table")
public class DataTableController {
	
	@Autowired
	private TableInfoService tableService;

	/**
	 * 分页查询显示记录
	 * @param entry
	 * @param session
	 * @return
	 */
	@RequestMapping("/page")
	public @ResponseBody Object queryForPage(PageEntry entry,HttpSession session){
		String name = PageEntry.class.getName();
		// 如果没有分页信息，获取session中存储的上次分页参数作为本次的查询条件
		if(entry.isEmpty()){
			PageEntry sessionPageEntry = (PageEntry) session.getAttribute(name);
			if(sessionPageEntry != null) entry = sessionPageEntry;
		}
		// 将查询条件存到session中
		session.setAttribute(name, entry);
		
		return tableService.queryForPage(entry);
	}
	
	/**
	 * 获取指定id的数据表中的字段
	 * @param id
	 * @return
	 */
	@RequestMapping("/columns")
	public @ResponseBody Object getTableFields(String id){
		return tableService.getByTableId(id);
	}
	
	/**
	 * 获取数据表中的数据
	 * @param id
	 * @return
	 */
	@RequestMapping("/data")
	public @ResponseBody Message<?> getData(String id){
		try {
			DataTable<DataMap> data = tableService.getData(id);
			return Message.success("查询成功", data);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error(e.getMessage(), e);
		}
	}
}
