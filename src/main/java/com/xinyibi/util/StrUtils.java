package com.xinyibi.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StrUtils {

	public static String randStr(int length){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			char ch = (char)(Math.random()*255);
			buffer.append(ch);
		}
		return buffer.toString();
	}
	
	private static volatile long start = System.currentTimeMillis() % 1000000000;
	
	public static synchronized String getNextId(String prefix){
//		String pattern = prefix+"%10d";
		DecimalFormat decimalFormat = new DecimalFormat(prefix+"0000000000");
		String format = decimalFormat.format(++start);
		return format;
	}
	
	public static synchronized long getNextId(){
		return ++start;
	}

	public static String toString(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static String createToken(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
