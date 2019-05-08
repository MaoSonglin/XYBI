package com.xinyibi.util;

import java.util.ArrayList;
import java.util.List;

import com.xinyibi.exception.NoSuchVertexException;
import com.xinyibi.model.Graph;
import com.xinyibi.model.Vertex;
import com.xinyibi.model.Vertex.Arc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 可达性预测类型
 * 用来预测某张图中两个顶点之间是否连通的
 * @author MaoSonglin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accessibility {

	public static void main(String[] args) throws NoSuchVertexException {
		Graph graph = new Graph();
		for(int i = 1; i <= 4; i++)
		graph.addVertex("t"+i);
		
		graph.addArc("t1", "t2",10);
		graph.addArc("t1", "t3",2);
		graph.addArc("t2", "t4",8);
		graph.addArc("t3", "t2",5); 
		graph.addVertex("t5");
		
		Accessibility access = new Accessibility(graph);
		boolean prediction = access.prediction("t1", "t4");
		System.out.println(prediction);
		System.out.println(access.prediction("t2", "t4"));
		System.out.println(access.prediction("t3", "t4"));
		System.out.println(access.prediction("t3", "t5"));
		System.out.println(access.prediction("t2", "t5"));
		
	}
	
	private Graph graph;
	
	/**
	 * 预测订单vertex1和顶点vertex2之间是否是连通的
	 * @param vertex1
	 * @param vertex2
	 * @return
	 * @throws NoSuchVertexException 顶点没找到
	 */
	public boolean prediction(String vertex1,String vertex2) throws NoSuchVertexException {
		
		int vIndex1 = graph.indexOf(vertex1);
		if(vIndex1 == -1) {
			throw new NoSuchVertexException(vertex1);
		}
		int vIndex2 = graph.indexOf(vertex2);
		if(vIndex2 == -1)
			throw new NoSuchVertexException(vertex2);
		List<Boolean> flag = new ArrayList<>(graph.count());
		for (int i = 0; i < graph.count(); i++) {
			flag.add(Boolean.FALSE);
		}
		flag.set(vIndex1, Boolean.TRUE);
		Vertex vertex = graph.getVertexs().get(vIndex1);
		return test(vertex, flag, vIndex2) ? true:flag.get(vIndex2);
	}

	private boolean test(Vertex vertex, List<Boolean> flag, int vIndex2) {
		List<Arc> arcs = vertex.getArcs();
		for (Arc arc : arcs) {
			int adj = arc.getAdj();
			if(adj == vIndex2) { // 如果是弧尾
				flag.set(adj, Boolean.TRUE);
				return true;
			}
			if(!flag.get(adj)) { // 如果该顶点没有访问
				flag.set(adj,Boolean.TRUE);
				if(test(graph.getVertexs().get(adj),flag,vIndex2)) return true;
			}
		}
		return false;
	}
}
