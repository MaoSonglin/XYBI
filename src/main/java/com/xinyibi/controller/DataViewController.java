package com.xinyibi.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xinyibi.exception.ServiceException;
import com.xinyibi.exception.UnreachableException;
import com.xinyibi.model.ViewDetailModel;
import com.xinyibi.pojo.TableView;
import com.xinyibi.service.DataViewService;
import com.xinyibi.vo.CreateViewVo;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;

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
	
	/**
	 * 为数据视图添加一个新的字段，该字段的id为数据字段的fieldid
	 * @param viewId
	 * @param fieldId
	 * @return
	 */
	@RequestMapping("/add/field")
	public @ResponseBody Object addViewField(String viewId,String fieldId){
		try {
			dataViewService.addViewField(viewId,fieldId);
		} catch (ServiceException e) {
			e.printStackTrace();
			return Message.error(e.getMessage(),null);
		} catch (UnreachableException e) {
			e.printStackTrace();
			
		}
		return null;
	}
	
	@RequestMapping("/add/fields")
	public @ResponseBody Message<?> addViewFields(String view,List<String> fields){
		try {
			boolean fields2 = dataViewService.addViewFields(view, fields);
			return Message.success(fields2?"添加成功":"添加失败", null);
		} catch (ServiceException | UnreachableException e) {
			return Message.error(e.getMessage(), e);
		}
	}
	
	
	/**
	 * 从视图中删除指定的数据字段
	 * @param view	视图ID
	 * @param fields data_table_info的ID
	 * @return
	 */
	public @ResponseBody Message<?> removeViewFields(String view, List<String> fields){
		try {
			dataViewService.remove(view,fields);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
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
	
	@RequestMapping("/list")
	public @ResponseBody Message<?> list(PageEntry entry){
		try {
			PageInfo<TableView> queryForPage = dataViewService.queryForPage(entry);
			
			return Message.success("查询成功", queryForPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			return Message.error(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/info/{id}")
	public @ResponseBody Message<?> info(@PathVariable String id){
		try {
			ViewDetailModel viewDetailModel = dataViewService.getGraphInfo(id);
			return Message.success("查找成功", viewDetailModel);
		} catch (ServiceException e) {
			e.printStackTrace();
			return Message.error(e.getMessage(), e);
		}
	}
}
