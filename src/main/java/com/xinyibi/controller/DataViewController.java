package com.xinyibi.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinyibi.exception.ServiceException;
import com.xinyibi.pojo.TableView;
import com.xinyibi.service.DataViewService;
import com.xinyibi.vo.CreateViewVo;
import com.xinyibi.vo.Message;

/**
 * @author MaoSonglin
 * 数据视图控制器
 */
@Controller
@RequestMapping("/dataset/view")
public class DataViewController implements Serializable{

	private static final long serialVersionUID = 2723202430154121149L;
	
	private @Autowired DataViewService dataViewService;
	/**
	 * 添加数据视图
	 * @param dbid
	 * @param tbids
	 * @return
	 */
	@RequestMapping("/add")
	public @ResponseBody Object add(CreateViewVo viewVo){
		if(StringUtils.isEmpty(viewVo.getDataSetId())){
			return Message.fail("数据集ID不能为空", viewVo);
		}
		if(viewVo.getDataTableIds()==null || viewVo.getDataTableIds().isEmpty()){
			return Message.fail("请选择至少一张数据表",viewVo);
		}
		
		return dataViewService.createView(viewVo);
	}
	
	@RequestMapping("/update")
	public @ResponseBody Object update(TableView view){
		String id = view.getId();
		if(StringUtils.isEmpty(id)){
			return Message.error("ID不能为空", view);
		}
		return dataViewService.updateView(view) ? Message.success("修改成功", view):Message.fail("修改失败", view);
	}
	
	/**
	 * 删除视图的接口
	 * @param id
	 * @return
	 */
	@RequestMapping("/drop")
	public @ResponseBody Object drop(String id){
		if(StringUtils.isEmpty(id)){
			return Message.error("ID不能为空", id);
		}
		try {
			return dataViewService.dropView(id) ? Message.success("删除成功", id):Message.fail("删除失败", null);
		} catch (ServiceException e) {
			e.printStackTrace();
			return Message.error(e.getMessage(), e);
		}
	}
}
