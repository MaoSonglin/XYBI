package com.xinyibi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xinyibi.model.ViewGraphModel;
import com.xinyibi.pojo.ViewPathHeader;
import com.xinyibi.pojo.ViewPathHeaderExample;

public interface ViewPathHeaderMapper {
    long countByExample(ViewPathHeaderExample example);

    int deleteByExample(ViewPathHeaderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ViewPathHeader record);

    int insertSelective(ViewPathHeader record);

    List<ViewPathHeader> selectByExample(ViewPathHeaderExample example);

    ViewPathHeader selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ViewPathHeader record, @Param("example") ViewPathHeaderExample example);

    int updateByExample(@Param("record") ViewPathHeader record, @Param("example") ViewPathHeaderExample example);

    int updateByPrimaryKeySelective(ViewPathHeader record);

    int updateByPrimaryKey(ViewPathHeader record);
    
    List<ViewGraphModel> findViewGraphByViewId(String viewId);

	int insertList(List<ViewPathHeader> paths);
}