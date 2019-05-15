package com.xinyibi.mapper;

import com.xinyibi.pojo.Relationship;
import com.xinyibi.pojo.RelationshipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationshipMapper {
    long countByExample(RelationshipExample example);

    int deleteByExample(RelationshipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Relationship record);

    int insertSelective(Relationship record);

    List<Relationship> selectByExample(RelationshipExample example);

    Relationship selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Relationship record, @Param("example") RelationshipExample example);

    int updateByExample(@Param("record") Relationship record, @Param("example") RelationshipExample example);

    int updateByPrimaryKeySelective(Relationship record);

    int updateByPrimaryKey(Relationship record);
}