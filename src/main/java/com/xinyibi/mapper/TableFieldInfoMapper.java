package com.xinyibi.mapper;

import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.TableFieldInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TableFieldInfoMapper {

	long countByExample(TableFieldInfoExample example);

	int deleteByExample(TableFieldInfoExample example);

	int deleteByPrimaryKey(String id);

	int insert(TableFieldInfo record);

	int insertList(List<TableFieldInfo> list);
	
	int insertSelective(TableFieldInfo record);

	List<TableFieldInfo> selectByExample(TableFieldInfoExample example);

	TableFieldInfo selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") TableFieldInfo record,
			@Param("example") TableFieldInfoExample example);

	int updateByExample(@Param("record") TableFieldInfo record, @Param("example") TableFieldInfoExample example);

	int updateByPrimaryKeySelective(TableFieldInfo record);

	int updateByPrimaryKey(TableFieldInfo record);

	/**
	 * 根据Table_view表的主键ID查找出Table_field_info表中对应的数据
	 * @param viewId
	 * @return
	 */
	List<TableFieldInfo> findByViewId(String viewId);
}