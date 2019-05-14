package com.xinyibi.mapper;

import com.xinyibi.pojo.Element;
import com.xinyibi.pojo.ElementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface ElementMapper {
    long countByExample(ElementExample example);

    int deleteByExample(ElementExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Element record);

    int insertSelective(Element record);

    List<Element> selectByExample(ElementExample example);

    Element selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Element record, @Param("example") ElementExample example);

    int updateByExample(@Param("record") Element record, @Param("example") ElementExample example);

    int updateByPrimaryKeySelective(Element record);

    int updateByPrimaryKey(Element record);

	@Select("select e.* from element e join report_element re on e.id = re.element_id join report r on r.id = re.report_id where r.id = #{id}")
	@ResultMap("com.xinyibi.mapper.ElementMapper.BaseResultMap")
	List<Element> findByReportId(Long id);
}