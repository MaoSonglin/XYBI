package com.xinyibi.adapter;

import java.util.Date;

import org.assertj.core.util.DateUtil;

import com.xinyibi.exception.ParserException;

public interface ValueParser<T> {
	
	T getValue(Object o) throws ParserException;
	
}

class NumberParser implements ValueParser<Double>{

	@Override
	public Double getValue(Object o) throws ParserException {
		if(o == null)
			return 0d;
		try {
			return Double.valueOf(o.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new ParserException(e);
		}
	}
	
}

class DateParser implements ValueParser<Date>{

	@Override
	public Date getValue(Object o) throws ParserException {
		if(o == null)
			throw new ParserException("不能从Null装换成Date");
		try {
			Long time = Long.valueOf(o.toString());
			return new Date(time);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			return DateUtil.parseDatetimeWithMs(o.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ParserException();
	}
	
}


class StringParser implements ValueParser<String>{

	public String getValue(Object o) {
		if( o == null)
			return "";
		return o.toString();
	}
	
}
