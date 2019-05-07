package com.xinyibi.exception;

public class ServiceException extends Exception {

	public ServiceException(String string) {
		super(string);
	}
	
	public ServiceException(){
		
	}
	
	public ServiceException(Throwable e){
		super(e);
	}
	
	public ServiceException(String msg,Throwable e){
		super(msg,e);
	}

	private static final long serialVersionUID = 7479247587489260297L;

}
