package com.xinyibi.model;

import java.util.List;

import com.xinyibi.pojo.Element;
import com.xinyibi.pojo.Report;
import com.xinyibi.util.StrUtils;

public class ReportElement extends Report {

	private List<Element> elements;

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	@Override
	public String toString() {
		return StrUtils.toString(this);
	}
	
	
}
