package com.xinyibi.exception;

public class ViewNotFoundException extends Exception {

	public ViewNotFoundException(String viewId) {
		super("ID为'"+viewId+"'的数据视图没有找到");
	}

	private static final long serialVersionUID = 8789092306888164163L;

}
