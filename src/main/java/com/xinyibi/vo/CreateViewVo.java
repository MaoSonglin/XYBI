package com.xinyibi.vo;

import java.util.List;

import lombok.Data;

/**
 * @author MaoSonglin
 * 封装前台传递的添加视图的数据
 */
@Data
public class CreateViewVo {
	
	/**
	 * 数据集ID
	 */
	private String dataSetId;
	
	private List<String> dataTableIds;
}
