package com.xinyibi.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.adapter.AbactractViewAdapter;
import com.xinyibi.exception.NoSuchVertexException;
import com.xinyibi.exception.ServiceException;
import com.xinyibi.exception.UnreachableException;
import com.xinyibi.mapper.DataSetMapper;
import com.xinyibi.mapper.DataTableInfoMapper;
import com.xinyibi.mapper.DatabaseInfoMapper;
import com.xinyibi.mapper.ForeignKeyInfoMapper;
import com.xinyibi.mapper.TableFieldInfoMapper;
import com.xinyibi.mapper.TableViewMapper;
import com.xinyibi.mapper.ViewFieldItemMapper;
import com.xinyibi.mapper.ViewFieldMapper;
import com.xinyibi.mapper.ViewPathHeaderMapper;
import com.xinyibi.model.Graph;
import com.xinyibi.model.ViewDetailModel;
import com.xinyibi.model.ViewGraphModel;
import com.xinyibi.pojo.DataSetExample;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DataTableInfoExample;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.ForeignKeyInfo;
import com.xinyibi.pojo.ForeignKeyInfoExample;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.TableFieldInfoExample;
import com.xinyibi.pojo.TableView;
import com.xinyibi.pojo.TableViewExample;
import com.xinyibi.pojo.TableViewExample.Criteria;
import com.xinyibi.pojo.ViewField;
import com.xinyibi.pojo.ViewFieldExample;
import com.xinyibi.pojo.ViewFieldItem;
import com.xinyibi.pojo.ViewFieldItemExample;
import com.xinyibi.pojo.ViewPathHeader;
import com.xinyibi.util.Accessibility;
import com.xinyibi.util.StrUtils;
import com.xinyibi.vo.CreateViewVo;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;
import com.xinyibi.vo.PageEntry.PageEntryItem;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataViewService implements Serializable {

	private static final long serialVersionUID = -7276116476495305650L;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private DataTableInfoMapper tbMapper;
	
	@Autowired
	private DataSetMapper dataSetMapper;
	
	@Autowired
	private TableFieldInfoMapper tfMapper;
	
	@Autowired
	private TableViewMapper tvMapper;
	
	@Autowired
	private ViewFieldMapper vfMapper;
	
	@Autowired
	private ViewFieldItemMapper vfiMapper;
	
	@Autowired
	private ViewPathHeaderMapper vphMapper;

	@Autowired
	private ForeignKeyInfoMapper foreignKeyInfoMapper;


	/**
	 * 在数据表的基础之上新建视图
	 * @param viewVo
	 * @return
	 */
	@Transactional
	public Message<?> createView(CreateViewVo viewVo) {
		// 根据系统中已经添加的数据表新建视图
		List<String> dataTableIds = viewVo.getDataTableIds();
		// 新建的视图所属的数据集ID
		String dataSetId = viewVo.getDataSetId();
		
		// 检查数据集ID是否存在
		DataSetExample example = new DataSetExample();
		example.createCriteria().andIdEqualTo(dataSetId);
		long count = dataSetMapper.countByExample(example);
		if(count == 0)return Message.fail("数据集ID'"+dataSetId+"'不存在", viewVo);
		
		List<TableView> views = new ArrayList<>();
		List<ViewField> viewFields = new ArrayList<>();
		List<ViewFieldItem> viewFieldItems = new ArrayList<>();
		List<ViewPathHeader> paths = new ArrayList<>();
		// 遍历数据表ID，为每一个数据表建立一个新的属于数据集的视图
		for (String tabid : dataTableIds) {
			DataTableInfo dataTable = tbMapper.selectByPrimaryKey(tabid);
			if(dataTable==null){
				return Message.fail("数据表ID'"+tabid+"'不存在", viewVo);
			}
			
			TableView view = new TableView();
			view.setDataSetId(dataSetId);
			view.setViewZhChName(dataTable.getTableZhChName());
			view.setViewName(dataTable.getTableName());
			view.setId(StrUtils.getNextId("TV"));
			view.setAddTime(new Date());
			
			views.add(view);
			
			// 根据数据表id查找出所有的该数据表下的数据字段
			TableFieldInfoExample tableFieldInfoExample = new TableFieldInfoExample();
			tableFieldInfoExample.createCriteria().andTbIdEqualTo(tabid);
			List<TableFieldInfo> fields = tfMapper.selectByExample(tableFieldInfoExample);
			
			// 为每个字段新建一个对应视图中的字段
			for (TableFieldInfo tableFieldInfo : fields) {
				
				ViewField viewField = new ViewField();
				viewField.setDataType(tableFieldInfo.getJdbcType());
				viewField.setFieldName(tableFieldInfo.getFieldName());
				viewField.setFieldZhChName(tableFieldInfo.getFieldZhChName());
				viewField.setAddTime(new Date());
				viewField.setViewId(view.getId());
				viewField.setId(StrUtils.getNextId());
				
				viewFields.add(viewField);
				
				ViewFieldItem item = new ViewFieldItem();
				item.setId(StrUtils.getNextId());
				item.setType("column");
				item.setViewFieldId(viewField.getId());
				item.setOrder(1);
				item.setTableFieldId(tableFieldInfo.getId());
				
				viewFieldItems.add(item);
			}
			
			// 为每一张视图建立一条路径点
			ViewPathHeader path = new ViewPathHeader();
			path.setId(StrUtils.getNextId());
			path.setTableId(tabid);
			path.setViewId(view.getId());
			
			paths.add(path);
		}
		
		int insertList  = tvMapper.insertList(views);
		int insertList2 = vfMapper.insertList(viewFields);
		int insertList3 = vfiMapper.insertList(viewFieldItems);
//		int insertList4 = vphMapper.insertList(paths);
		
		if(insertList>0&&insertList2>0&&insertList3>0 /*&& insertList4>0*/){
			return Message.success("添加成功", views);
		}else{
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return Message.success("添加失败", null);
	}

	
	/**
	 * 根据视图id删除指定视图
	 * @param viewId
	 * @return
	 * @throws ServiceException 
	 */
	@Transactional
	public boolean dropView(String viewId) throws ServiceException{
		// 检查视图是否存在
		TableView view = tvMapper.selectByPrimaryKey(viewId);
		if(view==null) throw new ServiceException("视图不存在");// return Message.fail("视图不存在", viewId);
		
		//TODO 检查是否有图表关联视图 
		
		// 该视图下的所有字段
		ViewFieldExample example = new ViewFieldExample();
		example.createCriteria().andViewIdEqualTo(viewId);
		List<ViewField> viewFields = vfMapper.selectByExample(example);
		
		// 所有的字段关联的字段项
		ViewFieldItemExample viewFieldItemExample = new ViewFieldItemExample();
		viewFieldItemExample.createCriteria()
				.andViewFieldIdIn(viewFields.stream().map(field -> field.getId()).collect(Collectors.toList()));
		int deleteByExample = vfiMapper.deleteByExample(viewFieldItemExample);
		
		int deleteByExample2 = vfMapper.deleteByExample(example);
		return deleteByExample>0&&deleteByExample2>0;
	}


	/**
	 * 修改数据视图的属性
	 * @param view	待修改的视图
	 * @return		修改成功返回true否则返回false
	 */
	@Transactional
	public boolean updateView(TableView view){
		boolean flag = tvMapper.updateByPrimaryKeySelective(view)>0;
		return flag;
	}
	
	
//	public 
	
	/**
	 * 分页显示视图列表
	 * @param entry	分页的相关查询以及过滤条件
	 * @return	该分页中的的数据内容
	 */
	public PageInfo<TableView> queryForPage(PageEntry entry) throws ServiceException{
		Integer page = entry.getPage();
		Integer size = entry.getSize();
		PageHelper.startPage(page, size);
		
		TableViewExample example = new TableViewExample();
		List<PageEntryItem> conditions = entry.getConditions();
		if(conditions!=null){
			Criteria criteria = example.createCriteria();
			for (PageEntryItem pageEntryItem : conditions) {
				// 过滤条件属性名
				String propName = pageEntryItem.getPropName();
				// 参数
				String[] parameters = pageEntryItem.getParameters();
				if(parameters == null) break;
				// 添加查询条件 数据集ID
				if("dataSetId".equalsIgnoreCase(propName)){
					criteria.andDataSetIdEqualTo(parameters[0]);
				}
			}
		}
		// 获取查询结果
		List<TableView> list = tvMapper.selectByExample(example );
		return new PageInfo<>(list);
	}
	
	
	public DataTable<DataMap> selectFromView(String viewId, PageEntry pageEntry) throws ServiceException{
		ViewDetailModel model = getGraphInfo(viewId);
		List<DatabaseInfo> databases = model.getDatabases();
		
		return null;// TODO
	}


	public ViewDetailModel getGraphInfo(String viewId) throws ServiceException {
		// 查找视图
		TableView view = tvMapper.selectByPrimaryKey(viewId);
		if(view == null) throw new ServiceException("视图ID不存在");
		
		
		ViewDetailModel model = new ViewDetailModel();
		model.setView(view);
		
		// 查询出视图底层构建在那些数据源之上
		DatabaseInfoMapper databaseInfoMapper = context.getBean(DatabaseInfoMapper.class);
		List<DatabaseInfo> databases = databaseInfoMapper.findByViewId(viewId);
		model.setDatabases(databases);

		// 视图底层关联的字段
		// 数据视图关联的数据字段
		List<TableFieldInfo> tableFieldInfos = tfMapper.findByViewId(viewId);
		model.setRelatedFields(tableFieldInfos);
		
		// 数据视图关联的数据表
		List<DataTableInfo> tables = tbMapper.findByViewId(viewId);
		model.setRelatedTables(tables);
		
		// 视图中的字段
		ViewFieldExample example = new ViewFieldExample();
		example.createCriteria().andViewIdEqualTo(viewId);
		List<ViewField> viewFields = this.vfMapper.selectByExample(example);
		model.setViewFields(viewFields);
		
		// 获取查找视图的底层表连接关系图
		List<ViewGraphModel> graph = vphMapper.findViewGraphByViewId(viewId);
		model.setGraph(graph);
		
		return model;
	}

	

	/**
	 * 在ID为viewid的视图上添加一个id为fieldId的数据字段
	 * @param viewId
	 * @param fieldId
	 * @return	添加成功返回true，否则返回false
	 * @throws UnreachableException 
	 * @throws 如果视图不存在，或者字段不存在
	 */
	@Transactional
	public boolean addViewField(String viewId, String fieldId) throws ServiceException, UnreachableException {
		
		TableView view = tvMapper.selectByPrimaryKey(viewId);
		if(view == null) throw new ServiceException("视图不存在");
		
		TableFieldInfo fieldInfo = tfMapper.selectByPrimaryKey(fieldId);
		if(fieldInfo == null) throw new ServiceException("字段不存在");
		
		ViewField viewField = new ViewField();
		viewField.setAddTime(new Date());
		viewField.setDataType(fieldInfo.getJavaType());
		viewField.setFieldName(fieldInfo.getFieldName());
		viewField.setFieldZhChName(fieldInfo.getFieldZhChName());
		viewField.setViewId(viewId);
		viewField.setId(StrUtils.getNextId());
		
		ViewFieldItem item = new ViewFieldItem();
		item.setId(StrUtils.getNextId());
		item.setType("column");
		item.setViewFieldId(viewField.getId());
		item.setOrder(1);
		item.setTableFieldId(fieldInfo.getId());
		
		List<ViewFieldItem> items = new ArrayList<>();
		viewField.setItems(items);
		
		// 获取视图view中包含的数据表
		List<DataTableInfo> datatables = tbMapper.findByViewId(viewId);
		if(datatables.isEmpty()){
			log.debug("ID为%s的数据视图没有引用任何数据表...",viewId);
			return save(viewField, item);
		}
		else{
			Iterator<DataTableInfo> iter = datatables.stream().filter(datatable->datatable.getId().equals(fieldInfo.getId())).iterator();
			if(iter.hasNext()){
				// 数据字段fieldInfo存在于与视图view相关的某张表中
				// 直接添加
				log.debug("ID为'%s'的字段所在的数据表已经与视图关联....",fieldId);
				return save(viewField,item);
			}else{
				log.debug("ID为'%s'的字段所在的数据表没有与视图关联....",fieldId);
				// 不存在
				boolean f = isAccess(fieldInfo, datatables);
				if(f){
					return save(viewField,item);
				}else{
					UnreachableException exception = new UnreachableException(String.format("ID为'%s'的字段所在的数据表与ID为'%s'的视图关联的数据表之间没有关联关系", fieldId,viewId));
					throw exception;
				}
			}
		}
		
	}


	private boolean save(ViewField viewField, ViewFieldItem item) throws ServiceException {
		int insert = this.vfMapper.insert(viewField);
		int insert2 = this.vfiMapper.insert(item);
		if(insert > 0 && insert2 > 0){
			log.debug("新建字段成功");
			return true;
		}
		throw new ServiceException("写入数据库异常");
	}


	/**
	 * 检测数据字段fieldInfo与数据表datatables之间是否有关联
	 * @param fieldInfo
	 * @param datatables
	 * @return
	 * @throws ServiceException
	 */
	protected boolean isAccess(TableFieldInfo fieldInfo, List<DataTableInfo> datatables) throws ServiceException {
		// 数据字段所属的数据表
		DataTableInfo datatable = tbMapper.selectByPrimaryKey(fieldInfo.getTbId());

		// 数据表DataTable在同一数据库中的表和view引用的数据表所在的数据表
		DataTableInfoExample datatableExample = new DataTableInfoExample();
		datatableExample.createCriteria().andDbIdEqualTo(datatable.getDbId());
		datatableExample.createCriteria().andDbIdIn(datatables.stream().map(tbl->tbl.getDbId()).distinct().collect(Collectors.toList()));
		List<DataTableInfo> alltables = tbMapper.selectByExample(datatableExample);
		
		if(alltables.stream().anyMatch(table->table.getId().equals(datatable.getId()))) return true;
		
		// 数据库中所有的外键
		ForeignKeyInfoExample example = new ForeignKeyInfoExample();
		List<String> collect = alltables.stream().map(tbl->tbl.getId()).distinct().collect(Collectors.toList());
		example.createCriteria().andTbIdIn(collect);
		example.createCriteria().andRefTbIdIn(collect);
		List<ForeignKeyInfo> foreignKeys = foreignKeyInfoMapper.selectByExample(example);
		
		// 用外键和表构建一张图
		Graph graph = new Graph();
		graph.addVertex(datatable.getId());
		alltables.forEach(dt->graph.addVertex(dt.getId()));
		foreignKeys.forEach(foreignKey->graph.addArc(foreignKey.getTbId(), foreignKey.getRefTbId()));
		
		boolean f = false;
		try {
			Accessibility access = new Accessibility(graph);
			for (DataTableInfo dataTableInfo : datatables) {
				if(access.prediction(datatable.getId(), dataTableInfo.getId())){
					f = true;
					break;
				}
			}
		} catch (NoSuchVertexException e) {
			throw new ServiceException("测试连通性是发生异常",e);
		}
		return f;
	}
	
}
