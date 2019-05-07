package com.xinyibi.model;

import java.util.List;

import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.ViewPathHeader;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MaoSonglin
 * 数据视图的底层数据表就像一张图，底层所有相关的表作为图中的节点，表与表之间的关联关系作为图的弧<br>
 * 使用邻接表来表示这张图，邻接表分为表头和邻接点链表，图中所有的顶点存放到一个二维数组中作为表头，
 * 使用数据结构ViewGraphModel表示，每个表头都含有一个存放数据域的对象from和一个邻接点数组，
 * 邻接点数据joins表示from这张表连接的其他表
 */
@Setter @Getter
public class ViewGraphModel extends ViewPathHeader{
	
	private DataTableInfo from;
	
	private List<PathVertexModel> joins;
}
