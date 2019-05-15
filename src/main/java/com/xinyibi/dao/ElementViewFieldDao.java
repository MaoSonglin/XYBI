package com.xinyibi.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ElementViewFieldDao {

	@Insert("insert into element_field (element_id, field_id) values (#{elementId}, #{viewFieldId})")
	int insert(@Param("elementId") Long elementId,@Param("viewFieldId") Long viewFieldId);

	@Delete("delete from element_field where element_id = #{elementId} and field_id = #{viewFieldId}")
	int delete(@Param("elementId") Long elementId,@Param("viewFieldId") Long viewFieldId);
}
