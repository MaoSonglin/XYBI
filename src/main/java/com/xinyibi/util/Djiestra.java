package com.xinyibi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinyibi.model.GraphStruct;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Djiestra {

	public static void main(String[] args) throws JsonProcessingException {
		GraphStruct graph = new GraphStruct();
		for(int i = 1; i <= 4; i++)
		graph.addVertex("t"+i);
		
		graph.addArc("t1", "t2",10);
		graph.addArc("t1", "t3",2);
		graph.addArc("t2", "t4",8);
		graph.addArc("t3", "t2",5); 
		ObjectMapper objectMapper = new ObjectMapper();
		String writeValueAsString = objectMapper.writeValueAsString(graph);
		System.out.println(writeValueAsString);
		System.out.println(new Djiestra().getPath("t4",graph));
	}
	
	public List<List<String>> getPath(String table,GraphStruct g){
		int indexOf = g.indexOf(table);	// 数据表的下标
		if(indexOf == -1) throw new IllegalArgumentException(table);
		// 存放所求节点到其他节点的最短路径上的前驱节点的下标
		List<Integer> path = new ArrayList<>();
		// 存放当前节点到各个节点的权值
		List<Integer> weight = new ArrayList<>();
		// 表示对应下标的节点是否已经求出最短路径
		List<Boolean> set = new ArrayList<>();
		// 初始化
		for(int i = 0; i < g.count(); i++) {
			path.add(-1);
			weight.add(10000);
			set.add(Boolean.FALSE);
		}
		for(int i = g.firstAdjacency(indexOf);i != -1; i = g.nextAdjacency(indexOf, i)) {
			weight.set(i, g.getWeight(indexOf, i));
		}
		set.set(indexOf, Boolean.TRUE);
		path.set(indexOf, null);
		weight.set(indexOf, 0);
		// 初始化结束
		
		for(int i = g.firstAdjacency(indexOf); i > -1; i = g.nextAdjacency(indexOf, i)) {
			path.set(i, indexOf);
		}
		
		log.debug("path"+path.toString());
		log.debug("weight"+weight.toString());
		log.debug("set"+set.toString());
		
		// 循环n-1次
		for(int i = 1; i < g.count(); i++) {
			int min = 10000;
			int curVertex = -1; // 标志当前的节点
			
			for(int j = 0; j < weight.size(); j++) {
				if(!set.get(j) && weight.get(j) < min) {
					min = weight.get(j);
					curVertex = j;
				}
			}
			
			if(curVertex == -1) throw new IllegalArgumentException("不连通图");
			
			// 加入
			set.set(curVertex, Boolean.TRUE);
			
			// 跟新其他节点的权值
			for(int j = 0; j < weight.size(); j++) {
				int tmp = g.getWeight(curVertex, j);
				int w = min + tmp;
				if(!set.get(j) && weight.get(j) > w) {
					weight.set(j, w);
					path.set(j, curVertex);
				}
			}
			
			log.debug("---------------------------------------");
			log.debug("path"+path.toString());
			log.debug("weight"+weight.toString());
			log.debug("set"+set.toString());
			
		}
		// 计算路径
		List<List<String>> list = new ArrayList<>();
		for(int i = 0; i < g.count(); i++) {
			if(i != indexOf) {
				List<Integer> path2 = getPath(path,indexOf,i);
				List<String> collect = path2.stream().map(index->g.getVertexs().get(index).getData()).collect(Collectors.toList());
				list.add(collect);
			}
		}
		
		return list;
	}
	
	private List<Integer> getPath(List<Integer> path, int startIndex, int endIndex){
		List<Integer> indexs = new ArrayList<>();
		Integer integer = endIndex ;
		do {
			indexs.add(0,integer);
			endIndex = integer;
			integer = path.get(endIndex);
		} while(integer != null && integer != -1);
		return indexs;
	}
}
