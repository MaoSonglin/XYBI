package com.xinyibi.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinyibi.exception.ServiceException;
import com.xinyibi.pojo.ViewField;
import com.xinyibi.service.ViewFieldService;
import com.xinyibi.vo.Message;

@Controller
@RequestMapping("/dataset/view/field")
public class ViewFieldController implements Serializable {

	private static final long serialVersionUID = -3015085658743271599L;
	
	@Autowired
	private ViewFieldService viewFieldService;
	
	/**
	 * 添加视图字段
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object addField(ViewField view){
		return null; 
	}
	
	/**
	 * 删除指定id的视图字段
	 * @param id	视图字段ID
	 * @return
	 */
	@RequestMapping("/drop")
	@ResponseBody
	public Object dropField(String id){
		try {
			boolean flag = viewFieldService.dropViewField(id);
			return flag ? Message.success("删除成功", id) : Message.fail("删除失败", id);
		} catch (ServiceException e) {
			e.printStackTrace();
			return Message.error(e.getMessage(), e);
		}
	}
	
	@ResponseBody
	@RequestMapping("/listByView/{viewId}")
	public Object listByView(@PathVariable String viewId){
		try {
			List<ViewField> list = viewFieldService.getByViewId(viewId);
			return Message.success("查询成功", list);
		} catch (ServiceException e) {
			e.printStackTrace();
			return Message.error(e.getMessage(), e);
		} 
	}
	
}
