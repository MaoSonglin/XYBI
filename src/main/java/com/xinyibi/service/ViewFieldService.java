package com.xinyibi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.xinyibi.exception.ServiceException;
import com.xinyibi.mapper.DataTableInfoMapper;
import com.xinyibi.mapper.ForeignKeyInfoMapper;
import com.xinyibi.mapper.TableFieldInfoMapper;
import com.xinyibi.mapper.TableViewMapper;
import com.xinyibi.mapper.ViewFieldItemMapper;
import com.xinyibi.mapper.ViewFieldMapper;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.TableView;
import com.xinyibi.pojo.ViewField;
import com.xinyibi.pojo.ViewFieldExample;
import com.xinyibi.pojo.ViewFieldItem;
import com.xinyibi.util.StrUtils;

@Service
@SuppressWarnings("unused")
public class ViewFieldService {

	@Autowired
	private ViewFieldMapper viewFieldMapper;
	
	@Autowired
	private TableViewMapper tableViewMapper;
	
	@Autowired
	private TableFieldInfoMapper tableFieldMapper;
	
	@Autowired
	private ViewFieldItemMapper viewFieldItemMapper;
	
	@Autowired
	private DataTableInfoMapper dataTableInfoMapper;
	
	@Autowired
	private ForeignKeyInfoMapper foreignKeyInfoMapper;
	
	@Autowired
	private DataViewService dataViewService;
	
	/**
	 * 在数据字段的基础之上新建视图字段
	 * @param viewId		新建视图字段所属视图的ID
	 * @param tableFieldId	数据字段ID
	 * @return
	 * @throws ServiceException 服务层异常
	 * @deprecated 使用DataViewService.addViewField(String viewId,String fieldId)
	 */
	@Deprecated
	@Transactional
	public boolean createViewFieldByTableField(String viewId,String tableFieldId) throws ServiceException{
//		String viewId = field.getViewId();
		TableView tableView = tableViewMapper.selectByPrimaryKey(viewId);
		if(tableView==null)  throw new ServiceException("数据视图不存在");//return Message.fail("数据视图ID不存在", viewId);
		
		TableFieldInfo tableFieldInfo = tableFieldMapper.selectByPrimaryKey(tableFieldId);
		if(tableFieldInfo == null) new ServiceException("数据字段不存在");//return Message.fail("数据字段不存在", tableFieldId);
		
		ViewField viewField = tableFieldToViewField(tableView, tableFieldInfo);
		
		ViewFieldItem item = viewFieldItem(viewField, tableFieldInfo);
		
		// 标记字段tableFieldInfo相同表中的其他字段是否与此视图相关
		boolean flag = true;
		// 和这个视图相关的数据表
		List<DataTableInfo> tables = dataTableInfoMapper.findByViewId(viewId);
		// 数据字段所属的数据表ID
		String tableFieldTbId = tableFieldInfo.getTbId();

		DataTableInfo orElse = tables.stream().peek(table->table.getId().equals(tableFieldTbId)).findFirst().orElse(null);
		
		// 保存
		int insert = viewFieldMapper.insert(viewField);
		int insert2 = viewFieldItemMapper.insert(item);
		
		return insert > 0 && insert2 > 0; 
	}


	protected ViewFieldItem viewFieldItem(ViewField viewField, TableFieldInfo tableFieldInfo) {
		// 视图字段项
		ViewFieldItem item = new ViewFieldItem();
		item.setId(StrUtils.getNextId());
		item.setType("column");
		item.setViewFieldId(viewField.getId());
		item.setOrder(1);
		item.setTableFieldId(tableFieldInfo.getId());
		return item;
	}


	protected ViewField tableFieldToViewField(TableView tableView, TableFieldInfo tableFieldInfo) {
		// 新建视图字段
		ViewField viewField = new ViewField();
		viewField.setDataType(tableFieldInfo.getJdbcType());
		viewField.setFieldName(tableFieldInfo.getFieldName());
		viewField.setFieldZhChName(tableFieldInfo.getFieldZhChName());
		viewField.setAddTime(new Date());
		viewField.setViewId(tableView.getId());
		viewField.setId(StrUtils.getNextId());
		return viewField;
	}


	
	public void addTableFieldInfo(String viewId,String fieldId) throws ServiceException{
		
	}
	


	/**
	 * 删除视图中的某个字段
	 * @param viewFieldId
	 * @return	删除成功返回true，否则返回false
	 * @throws ServiceException	数据视图字段ID不存在
	 */
	@Transactional
	public boolean dropViewField(String id) throws ServiceException{
		// TODO 如果没有任何图表引用该字段则成功，否则就失败
		// 删除字段项
		// 删除字段
		return false;
	}


	/**
	 * 根据视图ID获取字段
	 * @param viewId 视图ID
	 * @return 字段列表
	 * @throws ServiceException 如果ID为空字符串或者为null
	 */
	public List<ViewField> getByViewId(String viewId) throws ServiceException {
		
		if(StringUtils.isEmpty(viewId)) throw new ServiceException("视图ID不能为空");
		
		ViewFieldExample example = new ViewFieldExample();
		example.createCriteria().andViewIdEqualTo(viewId);
		List<ViewField> list = viewFieldMapper.selectByExample(example);
		
		return list;
	}


	public boolean update(ViewField viewField) {
		int i = viewFieldMapper.updateByPrimaryKeySelective(viewField);
		return i > 0;
	}
	
}
