package com.xinyibi.mapper;

import com.xinyibi.pojo.TableView;
import com.xinyibi.pojo.TableViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TableViewMapper {

	long countByExample(TableViewExample example);

	int deleteByExample(TableViewExample example);

	int deleteByPrimaryKey(String id);

	int insert(TableView record);

	int insertSelective(TableView record);

	List<TableView> selectByExample(TableViewExample example);

	TableView selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") TableView record, @Param("example") TableViewExample example);

	int updateByExample(@Param("record") TableView record, @Param("example") TableViewExample example);

	int updateByPrimaryKeySelective(TableView record);

	int updateByPrimaryKey(TableView record);

	int insertList(List<TableView> views);
}