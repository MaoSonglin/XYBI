package com.xinyibi.mapper;

import com.xinyibi.pojo.ViewField;
import com.xinyibi.pojo.ViewFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ViewFieldMapper {
    long countByExample(ViewFieldExample example);

    int deleteByExample(ViewFieldExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ViewField record);

    int insertSelective(ViewField record);

    List<ViewField> selectByExample(ViewFieldExample example);

    ViewField selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ViewField record, @Param("example") ViewFieldExample example);

    int updateByExample(@Param("record") ViewField record, @Param("example") ViewFieldExample example);

    int updateByPrimaryKeySelective(ViewField record);

    int updateByPrimaryKey(ViewField record);

	int insertList(List<ViewField> viewFields);
}