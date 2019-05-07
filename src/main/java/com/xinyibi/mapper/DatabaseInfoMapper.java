package com.xinyibi.mapper;

import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.DatabaseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatabaseInfoMapper {

	long countByExample(DatabaseInfoExample example);

	int deleteByExample(DatabaseInfoExample example);

	int deleteByPrimaryKey(String id);

	int insert(DatabaseInfo record);

	int insertSelective(DatabaseInfo record);

	List<DatabaseInfo> selectByExample(DatabaseInfoExample example);

	DatabaseInfo selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") DatabaseInfo record, @Param("example") DatabaseInfoExample example);

	int updateByExample(@Param("record") DatabaseInfo record, @Param("example") DatabaseInfoExample example);

	int updateByPrimaryKeySelective(DatabaseInfo record);

	int updateByPrimaryKey(DatabaseInfo record);

	List<DatabaseInfo> findByViewId(String viewId);
}