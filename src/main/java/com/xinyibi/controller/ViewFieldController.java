package com.xinyibi.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

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
	
	/**
	 * 根性视图字段的属性
	 * @param viewField
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Message<?> update(ViewField viewField){
		boolean update = viewFieldService.update(viewField);
		return update ? Message.success("修改成功", viewField): Message.fail("修改失败", viewField);
	}
	
	/**
	 * 根据视图ID查询该视图下的所有字段
	 * @param viewId
	 * @return
	 */
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
	
	
	/**
	 * 处理前台传递的日期字符串
	 * @param binder
	 * @param request
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
