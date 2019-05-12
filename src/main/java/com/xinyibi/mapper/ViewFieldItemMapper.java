package com.xinyibi.mapper;

import com.xinyibi.pojo.ViewFieldItem;
import com.xinyibi.pojo.ViewFieldItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ViewFieldItemMapper {
    long countByExample(ViewFieldItemExample example);

    int deleteByExample(ViewFieldItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ViewFieldItem record);

    int insertSelective(ViewFieldItem record);

    List<ViewFieldItem> selectByExample(ViewFieldItemExample example);

    ViewFieldItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ViewFieldItem record, @Param("example") ViewFieldItemExample example);

    int updateByExample(@Param("record") ViewFieldItem record, @Param("example") ViewFieldItemExample example);

    int updateByPrimaryKeySelective(ViewFieldItem record);

    int updateByPrimaryKey(ViewFieldItem record);

	int insertList(List<ViewFieldItem> viewFieldItems);
	
	List<ViewFieldItem> selectByViewId(Long id);
}