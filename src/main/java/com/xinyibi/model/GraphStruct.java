package com.xinyibi.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.xinyibi.model.Vertex.Arc;

import lombok.Data;


@Data
public class GraphStruct {
	
	
	private List<Vertex> vertexs = new ArrayList<>();
	
	public int count() {
		return vertexs.size();
	}
	
	public void addVertex(String value) {
		Vertex vertex = new Vertex();
		vertex.setData(value);
		vertexs.add(vertex);
	}
	
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
	
	public int firstAdjacency(String value) {
		int indexOf = indexOf(value);
		return firstAdjacency(indexOf);
	}
	public void addArc(String v1,String v2) {
		addArc(v1,v2,10000);
	}
	
	
	public void addArc(String v1,String v2,int weight) {
		int index = indexOf(v1);
		int index2 = indexOf(v2);
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
			if(!arcs.contains(arc2))
				arcs2.add(arc2);
		}else {
			throw new IllegalArgumentException("不存在的点");
		}
		
	}
	
}
