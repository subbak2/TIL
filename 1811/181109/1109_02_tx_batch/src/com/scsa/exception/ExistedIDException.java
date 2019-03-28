package com.scsa.exception;

@SuppressWarnings("serial")
public class ExistedIDException extends Exception {
	
	public ExistedIDException(String userId) {
		super("아이디가 이미 존재합니다 : "+userId);
	}
}
