package com.scsa.exception;

@SuppressWarnings("serial")
public class ExistedIDException extends Exception {
	
	public ExistedIDException(String userId) {
		super("���̵� �̹� �����մϴ� : "+userId);
	}
}
