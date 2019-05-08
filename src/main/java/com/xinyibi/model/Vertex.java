package com.xinyibi.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Vertex {
	
	private String data;
	
	private List<Arc> arcs = new ArrayList<>();
	
	@Data
	@EqualsAndHashCode
	public static class Arc{
		
		private int adj;
		
		private int weight = 10000;
	}
}
