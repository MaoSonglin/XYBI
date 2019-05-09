package com.xinyibi.exception;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UnreachableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8638733956505076647L;
	
	public UnreachableException(String msg){
		super(msg);
	}
	
	private Object data;
}
