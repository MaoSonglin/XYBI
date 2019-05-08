package com.xinyibi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinyibi.exception.ServiceException;
import com.xinyibi.mapper.DataTableInfoMapper;
import com.xinyibi.mapper.ForeignKeyInfoMapper;
import com.xinyibi.mapper.TableFieldInfoMapper;
import com.xinyibi.mapper.TableViewMapper;
import com.xinyibi.mapper.ViewFieldItemMapper;
import com.xinyibi.mapper.ViewFieldMapper;
import com.xinyibi.model.ViewDetailModel;
import com.xinyibi.model.ViewGraphModel;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.ForeignKeyInfo;
import com.xinyibi.pojo.ForeignKeyInfoExample;
import com.xinyibi.pojo.ForeignKeyInfoExample.Criteria;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.TableView;
import com.xinyibi.pojo.ViewField;
import com.xinyibi.pojo.ViewFieldItem;
import com.xinyibi.pojo.ViewPathHeader;
import com.xinyibi.pojo.ViewPathVertex;
import com.xinyibi.util.StrUtils;

@Service
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
	 */
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
		
		if(orElse == null){
			createPath(tables, tableFieldTbId);
//			foreignKeyInfoExample.createCriteria().andRefTbIdEqualTo(tableFieldTbId);
			
			
		}
		
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


	protected void createPath(List<DataTableInfo> tables, String tableFieldTbId) {
		ForeignKeyInfoExample foreignKeyInfoExample = new ForeignKeyInfoExample();
		foreignKeyInfoExample.createCriteria().andTbIdEqualTo(tableFieldTbId);
		List<ForeignKeyInfo> list = foreignKeyInfoMapper.selectByExample(foreignKeyInfoExample);
		for (ForeignKeyInfo foreignKeyInfo : list) {
			String refTbId = foreignKeyInfo.getRefTbId();
			DataTableInfo refTable = tables.stream().peek(table->table.getId().equals(tableFieldTbId)).findFirst().orElse(null);
			if(refTable != null){
				
			}else{
				createPath(tables,refTbId);
				ViewPathVertex viewPathVertex = new ViewPathVertex();
				
			}
		}
	}

	public void addTableFieldInfo(String viewId,String fieldId) throws ServiceException{
		ViewDetailModel model = dataViewService.getGraphInfo(viewId);
		// 关联的表
		List<DataTableInfo> relatedTables = model.getRelatedTables();
		TableFieldInfo tableFieldInfo = tableFieldMapper.selectByPrimaryKey(fieldId);
		if(tableFieldInfo==null) throw new ServiceException("数据字段不存在");

		DataTableInfo table = null;
		for (DataTableInfo dataTableInfo : relatedTables) {
			if(dataTableInfo.getId().equals(tableFieldInfo.getTbId())){
				table = dataTableInfo;
			}
		}
		// 创建视图字段
		ViewField viewField = tableFieldToViewField(model.getView(), tableFieldInfo);
		ViewFieldItem viewFieldItem = viewFieldItem(viewField,tableFieldInfo);
		
		if(table != null){
			// 什么也不用
		}else{
			// 添加路径
			table = dataTableInfoMapper.selectByPrimaryKey(tableFieldInfo.getTbId());
			buildPath(table,relatedTables);
			
		}
		
	}
	
	private ViewDetailModel buildPath(DataTableInfo table, List<DataTableInfo> relatedTables) {
		ViewDetailModel viewDetailModel = new ViewDetailModel();
		ArrayList<ViewPathHeader> arrayList = new ArrayList<ViewPathHeader>();
		ViewPathHeader headler = new ViewPathHeader();
		headler.setId(StrUtils.getNextId());
		headler.setTableId(table.getId());
		arrayList.add(headler);

		if(relatedTables.isEmpty()){
			
		}else{
			List<String> tableIds = relatedTables.stream().map(item->item.getId()).collect(Collectors.toList());
			ForeignKeyInfoExample foreignKeyInfoExample = new ForeignKeyInfoExample();
			Criteria criteria = foreignKeyInfoExample.createCriteria();
			criteria.andRefTbIdIn(tableIds);
			criteria.andTbIdEqualTo(table.getId());
			Criteria createCriteria = foreignKeyInfoExample.createCriteria();
			createCriteria.andTbIdIn(tableIds);
			createCriteria.andRefTbIdEqualTo(table.getId());
			// 数据表table中的外键，该外键引用了relatedTables中某个表的某个字段
			List<ForeignKeyInfo> selectByExample = foreignKeyInfoMapper.selectByExample(foreignKeyInfoExample);
			for (ForeignKeyInfo foreignKeyInfo : selectByExample) {
				String refTbId = foreignKeyInfo.getRefTbId();
				ViewPathVertex vertex = new ViewPathVertex();
				vertex.setHeaderId(headler.getId());
				vertex.setTableId(refTbId);
				vertex.setRightFieldId(foreignKeyInfo.getRefFdId());
				vertex.setLeftFieldId(foreignKeyInfo.getFieldId());
			}
		}
		return viewDetailModel;
	}


	/**
	 * 删除视图中的某个字段
	 * @param viewFieldId
	 * @return	删除成功返回true，否则返回false
	 * @throws ServiceException	数据视图字段ID不存在
	 */
	@Transactional
	public boolean dropViewField(String viewFieldId) throws ServiceException{
		// TODO 如果没有任何图表引用该字段则成功，否则就失败
		return false;
	}
	
}