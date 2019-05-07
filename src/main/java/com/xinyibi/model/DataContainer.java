package com.xinyibi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataContainer<T> extends LinkedList<Map<String, T>> {

	private static final long serialVersionUID = 7661517214625956970L;

	public void add(int index,String key,T value){
		if(index < size()){
			get(index).put(key, value);
		}
		else{
			HashMap<String, T> map = new HashMap<>();
			map.put(key, value);
			add(map);
		}
	}
	
	public T get(int index,String key){
		return get(index).get(key);
	}
	
	public int rowNum(){ return size();}
	
	public int colNum(){
		int s = 0;
		for (Map<String,T> map : this) {
			int size2 = map.size();
			if(size2 > s) s = size2;
		}
		return s;
	}
	
	public List<String> columnNames(){
		int s = 0;
		List<String> keys = new ArrayList<>();
		for (Map<String,T> map : this) {
			int size2 = map.size();
			if(size2 > s) {
				s = size2;
				keys = new ArrayList<>(map.keySet());
			}
		}
		return keys;
	}
	
	public Row<T> newRow(){
		Row<T> row = new Row<T>();
		add(row);
		return row;
	}
	
	public static class Row<R> extends HashMap<String,R>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 6242374908087928458L;
		
	}
}
