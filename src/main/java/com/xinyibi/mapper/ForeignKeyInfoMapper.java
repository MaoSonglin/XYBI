package com.xinyibi.mapper;

import com.xinyibi.pojo.ForeignKeyInfo;
import com.xinyibi.pojo.ForeignKeyInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ForeignKeyInfoMapper {

	long countByExample(ForeignKeyInfoExample example);

	int deleteByExample(ForeignKeyInfoExample example);

	int deleteByPrimaryKey(String id);

	int insert(ForeignKeyInfo record);

	int insertSelective(ForeignKeyInfo record);

	List<ForeignKeyInfo> selectByExample(ForeignKeyInfoExample example);

	ForeignKeyInfo selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") ForeignKeyInfo record,
			@Param("example") ForeignKeyInfoExample example);

	int updateByExample(@Param("record") ForeignKeyInfo record, @Param("example") ForeignKeyInfoExample example);

	int updateByPrimaryKeySelective(ForeignKeyInfo record);

	int updateByPrimaryKey(ForeignKeyInfo record);

	int insertList(List<ForeignKeyInfo> foreignKeys);
}