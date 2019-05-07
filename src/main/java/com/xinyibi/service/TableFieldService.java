package com.xinyibi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyibi.mapper.TableFieldInfoMapper;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.TableFieldInfoExample;
import com.xinyibi.pojo.TableFieldInfoExample.Criteria;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;
import com.xinyibi.vo.PageEntry.PageEntryItem;

@Service
public class TableFieldService implements Serializable{

	private static final long serialVersionUID = 2772835862536912924L;
	
	@Autowired
	private TableFieldInfoMapper tfMapper;

	public Message<PageInfo<TableFieldInfo>> queryForPage(PageEntry pageEntry){
		PageHelper.startPage(pageEntry.getPage(), pageEntry.getSize());
		TableFieldInfoExample example = new TableFieldInfoExample();
		List<PageEntryItem> conditions = pageEntry.getConditions();
		if(conditions != null){
			Criteria criteria = example.createCriteria();
			for (PageEntryItem pageEntryItem : conditions) {
				String propName = pageEntryItem.getPropName();
				if(propName.equalsIgnoreCase("tbid")){
					criteria.andTbIdEqualTo(pageEntryItem.getParameters()[0]);
				}
			}
		}
		// 查询结果
		List<TableFieldInfo> list = tfMapper.selectByExample(example);
		// 分页内容
		PageInfo<TableFieldInfo> pageInfo = new PageInfo<>(list);
		return Message.success("查询成功", pageInfo);
	}
}
