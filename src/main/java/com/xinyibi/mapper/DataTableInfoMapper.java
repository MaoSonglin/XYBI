package com.xinyibi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DataTableInfoExample;

public interface DataTableInfoMapper {

	long countByExample(DataTableInfoExample example);

	int deleteByExample(DataTableInfoExample example);

	int deleteByPrimaryKey(String id);

	int insert(DataTableInfo record);

	int insertSelective(DataTableInfo record);

	List<DataTableInfo> selectByExample(DataTableInfoExample example);

	DataTableInfo selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") DataTableInfo record, @Param("example") DataTableInfoExample example);

	int updateByExample(@Param("record") DataTableInfo record, @Param("example") DataTableInfoExample example);

	int updateByPrimaryKeySelective(DataTableInfo record);

	int updateByPrimaryKey(DataTableInfo record);
	
	int insertList(List<DataTableInfo> tables);
 
	List<DataTableInfo> findByViewId(String viewId);
}