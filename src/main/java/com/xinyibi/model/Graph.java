package com.xinyibi.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinyibi.model.Vertex.Arc;

import lombok.Data;


/**
 * @author MaoSonglin
 * 图的数据结构，使用十字链表表示法
 */
@Data
public class Graph {
	
	public static void main(String[] args) throws JsonProcessingException {
		Graph graph = new Graph();
		for (int i = 0; i < 10; i++) {
			graph.addVertex("t"+i);
		}
		graph.addArc("t1", "t2");
		graph.addArc("t2", "t1");
		graph.addArc("t0", "t0");
		String writeValueAsString = new ObjectMapper().writeValueAsString(graph);
		System.out.println(writeValueAsString);
	}
	/**
	 * 顶点表
	 */
	private List<Vertex> vertexs = new ArrayList<>();
	
	/**
	 * 或者图中顶点的个数
	 * @return
	 */
	public int count() {
		return vertexs.size();
	}
	/**
	 * 向图中添加定点，定点的数据域存放的值为value
	 * @param value
	 */
	public void addVertex(String value) {
		Vertex vertex = new Vertex();
		vertex.setData(value);
		vertexs.add(vertex);
	}
	
	/**
	 * 定位value在的定点表中的位置
	 * @param value
	 * @return 如果value存在于某个定点的数据域中，则返回这个定点在定点表中的下标，否则返回-1
	 */
	public int indexOf(String value) {
		int index = -1;
		for (Vertex vertex : vertexs) {
			index++;
			if(vertex.getData().equals(value)) {
				break;
			}
		}
		return index;
	}
	/**
	 * 返回指定下标的两个顶点之间的弧长
	 * @param v		弧尾顶点在顶点表中的下标
	 * @param v2	弧头顶点在定点表中的下标
	 * @return	如果两个顶点之间存在至少一条弧，则返回该弧的长度，否则返回10000表示这两个顶点之间不可达
	 */
	public int getWeight(int v, int v2) {
		Vertex vertex = vertexs.get(v);
		List<Arc> arcs = vertex.getArcs();
		for (Arc arc : arcs) {
			if(arc.getAdj() == v2){
				return arc.getWeight();
			}
		}
		// 不可达
		return 10000;
	}
	
	/**
	 * first table that this table adjacent which index is vIndex
	 * @param vIndex 
	 * @return
	 */
	public int firstAdjacency(int vIndex) {
		Vertex vertex = vertexs.get(vIndex);
		List<Arc> arcs = vertex.getArcs();
		if(arcs.size()>0) return arcs.get(0).getAdj();
		return -1;
	}
	
	/**
	 * 第v个节点相对第v2个节点的下一个邻接点
	 * @param v	节点下标
	 * @param v2	节点下标
	 * @return
	 */
	public int nextAdjacency(int v,int v2) {
		Vertex vertex = vertexs.get(v);
		List<Arc> arcs = vertex.getArcs();
		Iterator<Arc> iter = arcs.iterator();
		while(iter.hasNext()) {
			Arc next = iter.next();
			if(next.getAdj() == v2) {
				if(iter.hasNext()) return iter.next().getAdj();
			}
		}
		return -1;
	}
	
	/**
	 * 指定值得顶点的第一个邻接点的下标
	 * @param value
	 * @return
	 */
	public int firstAdjacency(String value) {
		int indexOf = indexOf(value);
		return firstAdjacency(indexOf);
	}
	/**
	 * 向图中添加一条弧
	 * @param v1	弧头顶点的数据域值
	 * @param v2	弧尾顶点的数据域值
	 */
	public void addArc(String v1,String v2) {
		addArc(v1,v2,10000);
	}
	
	
	/**
	 * 向图中插入一条弧
	 * @param v1	弧头顶点值
	 * @param v2	弧尾顶点值
	 * @param weight	弧长
	 */
	public void addArc(String v1,String v2,int weight) {
		int index = indexOf(v1);
		int index2 = indexOf(v2);
		if(index == index2)
			throw new IllegalArgumentException("弧头和弧尾不能相同.....");
		if(index > -1 && index2 > -1) {
			Arc arc = new Arc();
			arc.setAdj(index2);
			arc.setWeight(weight);
			List<Arc> arcs = vertexs.get(index).getArcs();
			if(!arcs.contains(arc))
				arcs.add(arc);
			
			Arc arc2 = new Arc();
			arc2.setAdj(index);
			arc2.setWeight(weight);
			List<Arc> arcs2 = vertexs.get(index2).getArcs();
			if(!arcs2.contains(arc2))
				arcs2.add(arc2);
		}else {
			throw new IllegalArgumentException("不存在的点");
		}
		
	}
	
}
