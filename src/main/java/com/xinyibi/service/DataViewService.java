package com.xinyibi.service;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyibi.exception.ServiceException;
import com.xinyibi.mapper.DataSetMapper;
import com.xinyibi.mapper.DataTableInfoMapper;
import com.xinyibi.mapper.DatabaseInfoMapper;
import com.xinyibi.mapper.FileInfoMapper;
import com.xinyibi.mapper.TableFieldInfoMapper;
import com.xinyibi.mapper.TableViewMapper;
import com.xinyibi.mapper.ViewFieldItemMapper;
import com.xinyibi.mapper.ViewFieldMapper;
import com.xinyibi.mapper.ViewPathHeaderMapper;
import com.xinyibi.model.DataContainer;
import com.xinyibi.model.DataContainer.Row;
import com.xinyibi.model.PathVertexModel;
import com.xinyibi.model.ViewDetailModel;
import com.xinyibi.model.ViewGraphModel;
import com.xinyibi.pojo.DataSetExample;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.FileInfo;
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
import com.xinyibi.util.DriverUtil;
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
		int insertList4 = vphMapper.insertList(paths);
		
		if(insertList>0&&insertList2>0&&insertList3>0 && insertList4>0){
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
	
	
	public Object selectFromView(String viewId, PageEntry pageEntry) throws ServiceException{
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
		
		if(databases.size() == 1){
			StringBuffer sBuffer = new StringBuffer();
			
			selectList(tableFieldInfos, sBuffer);
			
			addFrom(graph, sBuffer);
			// 表的连接条件
			addWhere(graph, sBuffer);
			
			String sql = sBuffer.toString();
			log.debug(sql);
			DatabaseInfo databaseInfo = databases.get(0);
			FileInfo fileInfo = context.getBean(FileInfoMapper.class).selectByPrimaryKey(databaseInfo.getDriverFileId());
			String path = fileInfo.getPath();
			try {
				// 加载数据库驱动程序
				DriverUtil.loadDriverClass(databaseInfo.getDriverClassName(), path);
				return executeQuery(sql, databaseInfo, tableFieldInfos);
			} catch (ClassNotFoundException | IOException | SQLException e) {
				throw new ServiceException(e);
			}
		}else{
			throw new ServiceException("目前不支持非同源数据库的查询操作");
		}
		
	}


	// 执行SQL语句
	protected Object executeQuery(String sql, DatabaseInfo databaseInfo, List<TableFieldInfo> tableFieldInfos)
			throws SQLException { 
		String url = databaseInfo.getUrl();
		String uname = databaseInfo.getUname();
		String upwd = databaseInfo.getUpwd();
		
		try(Connection conn = DriverManager.getConnection(url,uname,upwd);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			DataContainer<Object> container = new DataContainer<>();
			
			while(rs.next()){
				Row<Object> row = container.newRow();
				
				for(TableFieldInfo tableFieldInfo : tableFieldInfos){
					Object value = rs.getObject(tableFieldInfo.getId());
					row.put(tableFieldInfo.getFieldName(), value);
				}
			}
			return container;
		}
	}


	protected void addWhere(List<ViewGraphModel> graph, StringBuffer sBuffer) {
		sBuffer.append(" where 1=1 ");
		for (ViewGraphModel model : graph) {
			List<PathVertexModel> joins = model.getJoins();
			for (PathVertexModel pathVertexModel : joins) {
				sBuffer.append(" and ").append(on(pathVertexModel.getLeftField())).append(" = ").append(on(pathVertexModel.getRightField()));
			}
		}
	}


	protected void addFrom(List<ViewGraphModel> graph, StringBuffer sBuffer) {
		// 查询的表
		sBuffer.append(" from ");
		for (ViewGraphModel model : graph) {
			sBuffer.append(model.getFrom().getTableName()).append(" ").append(model.getFrom().getId()).append(",");
		}
		sBuffer.deleteCharAt(sBuffer.length()-1);
	}


	protected void selectList(List<TableFieldInfo> tableFieldInfos, StringBuffer sBuffer) {
		// 查询的字段
		for (TableFieldInfo tableFieldInfo : tableFieldInfos) {
			sBuffer.append(tableFieldInfo.getTbId())
			.append(tableFieldInfo.getFieldName())
			.append(" ").append(tableFieldInfo.getId()).append(",");
		}
		sBuffer.deleteCharAt(sBuffer.length()-1);
	}
	
	private String on(TableFieldInfo fieldInfo){
		StringBuffer buffer = new StringBuffer();
		buffer.append(fieldInfo.getTbId()).append(".").append(fieldInfo.getFieldName());
		return buffer.toString();
	}
	
	
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
	
}
