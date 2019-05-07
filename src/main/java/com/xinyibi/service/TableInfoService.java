package com.xinyibi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyibi.mapper.DataTableInfoMapper;
import com.xinyibi.mapper.TableFieldInfoMapper;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DataTableInfoExample;
import com.xinyibi.pojo.DataTableInfoExample.Criteria;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.TableFieldInfoExample;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;
import com.xinyibi.vo.PageEntry.PageEntryItem;

@Service
public class TableInfoService implements Serializable {

	private static final long serialVersionUID = -6517227397342027817L;

	@Autowired
	private DataTableInfoMapper tbMapper;
	
	@Autowired
	private TableFieldInfoMapper tfMapper;
	
	/**
	 * 分页查询显示数据表信息
	 * @param page
	 * @return
	 */
	public Message<PageInfo<DataTableInfo>> queryForPage(PageEntry page){
		Integer page2 = page.getPage();
		Integer size = page.getSize();
		// mybatis分页插件
		PageHelper.startPage(page2, size);
		// 匹配条件
		DataTableInfoExample example = new DataTableInfoExample();
		// 查询参数
		List<PageEntryItem> conditions = page.getConditions();
		if(conditions!=null){
			Criteria criteria = example.createCriteria();
			for (PageEntryItem pageEntryItem : conditions) {
				if("dbid".equalsIgnoreCase(pageEntryItem.getPropName())){
					criteria.andDbIdEqualTo(pageEntryItem.getParameters()[0]);
				}
			}
		}
		// 查询结果
		List<DataTableInfo> list = tbMapper.selectByExample(example);
		// 分页显示内容
		PageInfo<DataTableInfo> pageInfo = new PageInfo<>(list); 
		return Message.success("查询成功", pageInfo);
	}
	public Message<List<TableFieldInfo>> getByTableId(String id) {
		if(id==null) return Message.fail("数据表ID不能为空", null);
		
		TableFieldInfoExample example = new TableFieldInfoExample();
		example.createCriteria().andTbIdEqualTo(id);
		
		List<TableFieldInfo> list = tfMapper.selectByExample(example);
		return Message.success("查询成功", list);
	}
}
