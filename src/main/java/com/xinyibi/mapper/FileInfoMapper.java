package com.xinyibi.mapper;

import com.xinyibi.pojo.FileInfo;
import com.xinyibi.pojo.FileInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileInfoMapper {

	long countByExample(FileInfoExample example);

	int deleteByExample(FileInfoExample example);

	int deleteByPrimaryKey(Long id);

	int insert(FileInfo record);

	int insertSelective(FileInfo record);

	List<FileInfo> selectByExampleWithBLOBs(FileInfoExample example);

	List<FileInfo> selectByExample(FileInfoExample example);

	FileInfo selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

	int updateByExampleWithBLOBs(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

	int updateByExample(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

	int updateByPrimaryKeySelective(FileInfo record);

	int updateByPrimaryKeyWithBLOBs(FileInfo record);

	int updateByPrimaryKey(FileInfo record);
}