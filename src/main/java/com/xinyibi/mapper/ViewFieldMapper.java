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
	
	/**
	 * 删除视图viewId中与字段fieldIds相关联的字段
	 * @param viewId	视图ID
	 * @param fieldIds	数据字段ID
	 * @return
	 */
	int deleteByViewIdAndDataFieldIds(@Param("viewId") String viewId, @Param("fieldIds") List<String> fieldIds);
}