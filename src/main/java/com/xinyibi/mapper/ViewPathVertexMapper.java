package com.xinyibi.mapper;

import com.xinyibi.pojo.ViewPathVertex;
import com.xinyibi.pojo.ViewPathVertexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ViewPathVertexMapper {
    long countByExample(ViewPathVertexExample example);

    int deleteByExample(ViewPathVertexExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ViewPathVertex record);

    int insertSelective(ViewPathVertex record);

    List<ViewPathVertex> selectByExample(ViewPathVertexExample example);

    ViewPathVertex selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ViewPathVertex record, @Param("example") ViewPathVertexExample example);

    int updateByExample(@Param("record") ViewPathVertex record, @Param("example") ViewPathVertexExample example);

    int updateByPrimaryKeySelective(ViewPathVertex record);

    int updateByPrimaryKey(ViewPathVertex record);
}