package com.xinyibi.model;

import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.ViewPathVertex;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MaoSonglin
 * 视图十字链表中的节点类，table表示该节点上的头节点连接表，leftField表示连接条件中
 * 的左边的字段，rightField表示右边的字段。
 */
@Setter @Getter
public class PathVertexModel extends ViewPathVertex {
	
	private DataTableInfo table;
	
	private TableFieldInfo leftField;
	
	private TableFieldInfo rightField;
}
