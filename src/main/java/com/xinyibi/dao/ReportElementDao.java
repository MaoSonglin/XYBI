package com.xinyibi.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReportElementDao {

	@Insert("insert into report_element (report_id, element_id) values (#{reportId},#{elementId})")
	int insert(@Param("reportId") Long reportId, @Param("ElementId") Long elementId);
}
