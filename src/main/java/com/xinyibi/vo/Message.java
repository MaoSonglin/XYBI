package com.xinyibi.vo;

import com.xinyibi.util.StrUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {
	
	public static final int SUCCESS_CODE = 20000;
	
	public static final int FAIL_CODE = 20005;
	
	public static final int ERROR_CODE = 20002;
	
	public static void main(String[] args) {
		Message.success("", null);
	}
	private int code;
	
	private String message;
	
	private T data;
	
	public static <T> Message<T> fail(String message,T data){
		return new Message<T>(20005,message,data);
	}
	
	public static<T> Message<T> success(String message,T data){
		return new Message<T>(20000,message,data);
	}
	
	public static Message<String> unaccessable(){
		return new Message<String>(25323,"您没有登录获取登录已过期",null);
	}
	
	public static<T> Message<T> error(String message,T data){
		return new Message<T>(20002,message,data);
	}
	
	@Override
	public String toString(){
		
		return StrUtils.toString(this);
	}
}
