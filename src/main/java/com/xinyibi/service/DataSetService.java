package com.xinyibi.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyibi.exception.ServiceException;
import com.xinyibi.mapper.AccountMapper;
import com.xinyibi.mapper.DataSetMapper;
import com.xinyibi.mapper.TableViewMapper;
import com.xinyibi.pojo.DataSet;
import com.xinyibi.pojo.DataSetExample;
import com.xinyibi.pojo.DataSetExample.Criteria;
import com.xinyibi.pojo.TableView;
import com.xinyibi.pojo.TableViewExample;
import com.xinyibi.vo.Message;
import com.xinyibi.vo.PageEntry;

@Service
public class DataSetService implements Serializable{

	private static final long serialVersionUID = -7490683917732662321L;

	private @Autowired ApplicationContext context;
	
	private @Autowired DataSetMapper dataSetMapper;
	
	private @Autowired AccountMapper accountMapper;

	/**
	 * 添加数据集
	 * @param dataSet
	 * @return
	 */
	@Transactional
	public Message<DataSet> add(DataSet dataSet) {
		DataSetExample example = new DataSetExample();
		Criteria criteria = example.createCriteria();
		
		// 检查名称是否存在
		criteria.andDataSetNameEqualTo(dataSet.getDataSetName());
		long countByName = dataSetMapper.countByExample(example);
		if(countByName>0){
			return Message.fail("数据集名称已经存在", dataSet);
		}
		
		// 检查用户是否存在
		if(accountMapper.selectByPrimaryKey(dataSet.getAccountId()) == null){
			return Message.fail("用户ID不存在", dataSet);
		}
		
		dataSet.setAddTime(new Date());
		
		int insert = dataSetMapper.insert(dataSet);
		
		if(insert>0)return Message.success("保存成功", dataSet);
		
		return Message.fail("保存失败", dataSet);
	}

	
	/**
	 * 修改数据集对象
	 * @param dataSet 修改的对象，其中ID不支持修改
	 * @return
	 */
	@Transactional
	public Message<DataSet> update(DataSet dataSet){
		String dataSetName = dataSet.getDataSetName();
		DataSetExample example = new DataSetExample();
		
		// 检查是否存在相同名称的数据包
		Criteria criteria = example.createCriteria();
		criteria.andIdNotEqualTo(dataSet.getId());
		criteria.andDataSetNameEqualTo(dataSetName);
		long countByName = dataSetMapper.countByExample(example);
		if(countByName > 0) return Message.fail("名称已存在", dataSet);
		
		int result = dataSetMapper.updateByPrimaryKeySelective(dataSet);
		
		if(result > 0) return Message.success("修改成功", dataSet);
		
		return Message.fail("修改失败", dataSet);
	}
	
	/**
	 * 通过数据集的id删除指定的数据集，并且删除数据集中的所有视图和字段
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public boolean dropById(String id) throws ServiceException{
		TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
		DataSet dataSet = dataSetMapper.selectByPrimaryKey(id);
		if(dataSet == null)
			throw new ServiceException("数据集不存在");
		
		// 查找出数据集下的所有视图
		TableViewMapper tableViewMapper = context.getBean(TableViewMapper.class);
		TableViewExample example = new TableViewExample();
		example.createCriteria().andDataSetIdEqualTo(id);
		List<TableView> tableViews = tableViewMapper.selectByExample(example);
		
		// 删除所有视图
		for (TableView tableView : tableViews) {
			boolean dropView = context.getBean(DataViewService.class).dropView(tableView.getId());
			if(!dropView){// 如果有一张视图删除失败，则回滚
				transactionStatus.setRollbackOnly();
				return false;
			}
		}
		int row = dataSetMapper.deleteByPrimaryKey(id);
		if(row == 0){
			transactionStatus.setRollbackOnly();
		}
		return true;
	}


	public PageInfo<DataSet> queryForPage(PageEntry pageEntry){
		PageHelper.startPage(pageEntry.getPage(), pageEntry.getSize());
		DataSetExample example = new DataSetExample();
//		pageEntry.getConditions()
		List<DataSet> list = dataSetMapper.selectByExample(example);
		
		PageInfo<DataSet> pageInfo = new PageInfo<>(list);
		
		return pageInfo;
	}
}
