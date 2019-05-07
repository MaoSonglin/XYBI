package com.xinyibi.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinyibi.exception.ServiceException;
import com.xinyibi.service.ViewFieldService;
import com.xinyibi.vo.Message;

@Controller
@RequestMapping("/dataset/view/field")
public class ViewFieldController implements Serializable {

	private static final long serialVersionUID = -3015085658743271599L;
	
	@Autowired
	private ViewFieldService viewFieldService;
	
	/**
	 * 在数据字段的基础上新建视图字段
	 * @param viewId	新建视图字段所属的视图的ID
	 * @param fieldId	数据字段ID
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object addField(String viewId,String fieldId){
		try {
			boolean flag = viewFieldService.createViewFieldByTableField(viewId, fieldId);
			return Message.success(flag ? "添加成功":"添加失败", flag);
		} catch (ServiceException e) {
			e.printStackTrace();
			return Message.error(e.getMessage(), e);
		} 
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
}
