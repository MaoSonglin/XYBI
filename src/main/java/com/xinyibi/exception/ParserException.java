package com.xinyibi.exception;
/**
 * 解析异常
 * @author MaoSonglin
 *
 */
public class ParserException extends Exception {

	public ParserException(NumberFormatException e) {
		super(e);
	}

	public ParserException(String string) {
		super(string);
	}

	public ParserException() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7612980255476984741L;

}
