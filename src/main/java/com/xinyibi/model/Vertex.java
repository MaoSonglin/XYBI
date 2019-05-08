package com.xinyibi.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author MaoSonglin
 * 图的数据结构，使用十字链表表示的图，使用这个类来定义图的定点表结构
 */
@Data
public class Vertex {
	
	/**
	 * 定点中存放的数据
	 */
	private String data;
	
	/**
	 * 定点的邻接表
	 */
	private List<Arc> arcs = new ArrayList<>();
	
	/**
	 * @author MaoSonglin
	 * 表示图的定点的邻接表数据结构
	 */
	@Data
	@EqualsAndHashCode
	public static class Arc{
		
		private int adj;
		
		private int weight = 10000;
	}
}
