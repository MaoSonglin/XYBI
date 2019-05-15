package com.xinyibi.vo;

import lombok.Data;

/**
 * 元素中的数据查询条件
 * @author MaoSonglin
 *
 */
@Data
public class DataQueryVo {

	/**
	 * 元素ID
	 */
	private Long elementId;
	
	private String where;
	
	private String groupBy;
	
	private String orderBy;
	
	private Integer limit;
	
	private Integer size;
}
