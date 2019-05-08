package com.xinyibi.exception;

public class NoSuchVertexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7002867509625345983L;
	

	public NoSuchVertexException(String vertex) {
		super("没有找到顶点"+vertex);
	}
}
