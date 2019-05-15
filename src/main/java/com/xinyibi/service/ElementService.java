package com.xinyibi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.adapter.ViewAdapter;
import com.xinyibi.dao.ElementViewFieldDao;
import com.xinyibi.exception.ServiceException;
import com.xinyibi.factory.ViewAdapterFactory;
import com.xinyibi.mapper.ElementMapper;
import com.xinyibi.mapper.TableViewMapper;
import com.xinyibi.mapper.ViewFieldMapper;
import com.xinyibi.pojo.Element;
import com.xinyibi.pojo.TableView;
import com.xinyibi.pojo.ViewField;
import com.xinyibi.util.StrUtils;
import com.xinyibi.vo.DataQueryVo;

@Service
public class ElementService {

	@Autowired
	private ApplicationContext context;
	
	@Transactional
	public boolean addElement(Element e) {
		e.setId(StrUtils.getNextId());
		int insert = context.getBean(ElementMapper.class).insert(e);
		return insert > 0;
	}
	
	@Transactional
	public boolean updateElement(Element e) {
		int i = context.getBean(ElementMapper.class).updateByPrimaryKeySelective(e);
		return i > 0;
	}
	
	/**
	 * 通过元素的ID删除一个元素
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean dropElement(Long id) {
		int i = context.getBean(ElementMapper.class).deleteByPrimaryKey(id);
		return i > 0;
	}
	
	/**
	 * 获取指定报表下的所有元素
	 * @param reportId
	 * @return
	 * @throws ServiceException 
	 */
	public List<Element> getByReportId(Long reportId) throws ServiceException{
		if(reportId == null) throw new ServiceException("请指定报表ID");
		List<Element> elements = context.getBean(ElementMapper.class).findByReportId(reportId);
		return elements;
	}
	
	
	/**
	 * 为元素和字段建立关联关系
	 * @param elementId		元素ID
	 * @param viewFieldId	字段id
	 * @return	建立成功返回true，否则返回false
	 */
	public boolean addViewField(Long elementId, Long viewFieldId) {
		Element element = context.getBean(ElementMapper.class).selectByPrimaryKey(elementId);
		if(element == null){
			return false;
		}
		ViewField viewField = context.getBean(ViewFieldMapper.class).selectByPrimaryKey(viewFieldId);
		if(viewField == null){
			return false;
		}
		int insert = context.getBean(ElementViewFieldDao.class).insert(elementId, viewFieldId);
		return insert > 0;
	}

	/**
	 * 删除元素与字段之间的关联关系	
	 * @param elementId		元素ID
	 * @param viewFieldId	字段id
	 * @return	移除成功返回true，否则返回false
	 */
	public boolean removeViewField(Long elementId, Long viewFieldId) {
		int i = context.getBean(ElementViewFieldDao.class).delete(elementId, viewFieldId);
		return i > 0;
	}
	

	/**
	 * 查询指定元素所属要的数据
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	public DataTable<DataMap> getData(DataQueryVo vo) throws Exception{
		// 获取元素需要查询的视图
		Element element = context.getBean(ElementMapper.class).selectByPrimaryKey(vo.getElementId());
		List<ViewField> viewFields = context.getBean(ElementMapper.class).findRelatedViewFields(vo.getElementId());
		List<TableView> tableViews = viewFields.stream().map(viewField-> context.getBean(TableViewMapper.class).selectByPrimaryKey(viewField.getViewId())).distinct().collect(Collectors.toList());
		// 存放需要的数据的dataTable
		DataTable<DataMap> dataTable = null; 
		// 如果只有一张视图
		int size = tableViews.size();
		if(size == 1) {
			ViewAdapter viewAdapter = ViewAdapterFactory.getViewAdapter();
			dataTable = viewAdapter.getDataTable(tableViews.get(0).getId());
		}
		
		// TODO 如果有多张视图
		return dataTable;
	}
}
