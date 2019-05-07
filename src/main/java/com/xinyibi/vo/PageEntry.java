package com.xinyibi.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageEntry implements Serializable {

	private static final long serialVersionUID = 920903848266388148L;

	public PageEntry(Integer page, Integer size) {
		super();
		this.page = page;
		this.size = size;
	}

	private  Integer page;
	
	private Integer size;
	
	private List<PageEntryItem> conditions = new ArrayList<>();
	
	private Integer recordCount;
	
	private Integer pageCount;
	
	public boolean isEmpty(){
		return page == null || size == null;
	}
	
	@Data
	public static class PageEntryItem implements Serializable{
		private static final long serialVersionUID = 5254512945550010053L;
		private String propName;
		private String predicate;
		private String[] parameters;
	}
}
