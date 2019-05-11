package com.xinyibi.service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyibi.XinyiBiResource;
import com.xinyibi.adapter.MetaDataReader;
import com.xinyibi.factory.MetaDataReaderFactory;
import com.xinyibi.mapper.DataTableInfoMapper;
import com.xinyibi.mapper.DatabaseInfoMapper;
import com.xinyibi.mapper.ForeignKeyInfoMapper;
import com.xinyibi.mapper.TableFieldInfoMapper;
import com.xinyibi.model.Graph;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DataTableInfoExample;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.DatabaseInfoExample;
import com.xinyibi.pojo.ForeignKeyInfo;
import com.xinyibi.pojo.ForeignKeyInfoExample;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.util.StrUtils;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;

@Service
@EnableConfigurationProperties(XinyiBiResource.class)
public class DataBaseService implements Serializable{

	private static final long serialVersionUID = -8057646422716164308L;

	@Autowired
	private DatabaseInfoMapper dbMapper;

	@Autowired
	private DataTableInfoMapper tbMapper;

	@Autowired
	private TableFieldInfoMapper tfMapper;

	@Autowired
	private ForeignKeyInfoMapper fkMapper;

	/**
	 * 向数据库中添加记录
	 * 
	 * @param dbInfo
	 * @return
	 */
	@Transactional
	public Message<?> addDataBase(DatabaseInfo dbInfo) {

		// 检查是否存在同名的记录
		DatabaseInfoExample example = new DatabaseInfoExample();
		example.createCriteria().andNameEqualTo(dbInfo.getName());
		long countByExample = dbMapper.countByExample(example);
		if (countByExample > 0) {
			return Message.fail("数据库名称已存在", dbInfo.getName());
		}

		// 设置ID和插入时间
		dbInfo.setId(StrUtils.getNextId("DB"));
		dbInfo.setAddTime(new Date());
		int insert = dbMapper.insert(dbInfo);

		return Message.success(insert > 0 ? "添加成功" : "添加失败", dbInfo);
	}

	/**
	 * 读取数据库的表结构
	 * 
	 * @param dbId
	 *            数据库ID
	 * @return
	 */
	@Transactional
	public Message<?> getStructure(String dbId) {
		DatabaseInfoExample example = new DatabaseInfoExample();
		example.createCriteria().andIdEqualTo(dbId);
		List<DatabaseInfo> databaseInfo = dbMapper.selectByExample(example);
		if (databaseInfo.isEmpty())
			return Message.fail("数据库不存在", dbId);
		MetaDataReader reader = MetaDataReaderFactory.getMetaDataReader(databaseInfo.get(0));
		// 获取数据表
		List<DataTableInfo> dataTableList = reader.getDataTableList();
		// 第一次遍历数据表
		for (DataTableInfo dataTableInfo : dataTableList) {
			// 设置数据表属于的数据库的ID
			dataTableInfo.setDbId(databaseInfo.get(0).getId());
		}
		// 插入数据库
		int insertList = tbMapper.insertList(dataTableList);

		List<TableFieldInfo> fieldList = new LinkedList<>();
		// 第二次遍历数据表查询出字段
		for (DataTableInfo dataTableInfo : dataTableList) {
			List<TableFieldInfo> all = reader.getTableFieldList(dataTableInfo.getTableName());
			all.forEach(item -> {
				item.setTbId(dataTableInfo.getId());
			});
			fieldList.addAll(all);
		}
		// 保存字段
		int insertList2 = tfMapper.insertList(fieldList);

		List<ForeignKeyInfo> foreignKeys = new LinkedList<>();
		// 第三次遍历读取外键
		dataTableList.forEach(dataTable -> {
			List<ForeignKeyInfo> foreignKeyList = reader.getForeignKeyList(dataTable.getTableName());
			foreignKeys.addAll(foreignKeyList);
			foreignKeyList.forEach(foreignKey -> {
				// 外键所属的数据表
				foreignKey.setTbId(dataTable.getId());

				// 外键关联的字段名称暂存在ID属性中，在这里查找到响应的ID
				for (TableFieldInfo tableFieldInfo : fieldList) {
					boolean equals = tableFieldInfo.getFieldName().equalsIgnoreCase(foreignKey.getFieldId()) && tableFieldInfo.getTbId().equals(tableFieldInfo.getTbId());
					if (equals) {
						foreignKey.setFieldId(tableFieldInfo.getId());
						break;
					}
				}
				// 设置外键引用的字段
				for (TableFieldInfo tableFieldInfo : fieldList) {
					if (tableFieldInfo.getFieldName().equalsIgnoreCase(foreignKey.getRefFdId()) && tableFieldInfo.getTbId().equals(foreignKey.getRefTbId())) {
						foreignKey.setRefFdId(tableFieldInfo.getId());
						break;
					}
				}
				// 设置外键引用的数据表
				for (Iterator<?> iterator = dataTableList.iterator(); iterator.hasNext();) {
					DataTableInfo dataTableInfo = (DataTableInfo) iterator.next();
					if (dataTableInfo.getTableName().equalsIgnoreCase(foreignKey.getRefTbId())) {
						foreignKey.setRefTbId(dataTableInfo.getId());
					}
				}
			});
		});

		int insertList3 = fkMapper.insertList(foreignKeys);
		Map<String, Object> map = new HashMap<>();
		map.put("table", dataTableList);
		map.put("column", fieldList);
		map.put("foreignKeys", foreignKeys);

		Message<Map<String, Object>> success = Message
				.success(String.format("成功读取%d条数据表记录，%d条字段记录，%d条外键", insertList, insertList2, insertList3), map);

		return success;
	}

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public Message<?> queryForPage(PageEntry page) {
		PageHelper.startPage(page.getPage(), page.getSize(), true);
		List<DatabaseInfo> selectByExample = dbMapper.selectByExample(new DatabaseInfoExample());
		PageInfo<DatabaseInfo> pageInfo = new PageInfo<DatabaseInfo>(selectByExample);
		List<DatabaseInfo> list = pageInfo.getList();
		page.setPageCount(pageInfo.getPageSize());
		page.setRecordCount((int)pageInfo.getTotal());
		return Message.success("查询成功", new PageInfo<DatabaseInfo>(list));
	}

	/**
	 * 获取系统中所有数据表组成的网
	 * @return
	 */
	public Graph getGraph(){
		Graph graph = new Graph();
		List<DataTableInfo> tables = this.tbMapper.selectByExample(new DataTableInfoExample());
		tables.forEach(dt->graph.addVertex(dt.getId()));
		List<ForeignKeyInfo> foreignKeys = this.fkMapper.selectByExample(new ForeignKeyInfoExample());
		foreignKeys.forEach(key->graph.addArc(key.getTbId(), key.getRefTbId()));
		return graph;
	}

	/**
	 * 获取指定ID的数据库中的数据表
	 * @param id 数据库ID
	 * @return	数据表列表
	 */
	public List<DataTableInfo> getTables(String id) {
		DataTableInfoExample example = new DataTableInfoExample();
		example.createCriteria().andDbIdEqualTo(id);
		List<DataTableInfo> list = tbMapper.selectByExample(example);
		return list;
	}

	@Transactional
	public boolean update(DatabaseInfo databaseInfo) {
		int updateByPrimaryKeySelective = dbMapper.updateByPrimaryKeySelective(databaseInfo);
		if(updateByPrimaryKeySelective > 0){
			DatabaseInfo database = dbMapper.selectByPrimaryKey(databaseInfo.getId());
			if(database!=null)
			BeanUtils.copyProperties(database, databaseInfo);
		}
		return updateByPrimaryKeySelective>0;
		
	}
	
}
