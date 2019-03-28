package com.scsa.exception;

public class ExistedIDException extends Exception {

	public ExistedIDException(String id) {
		super("아이디가 이미 존재합니다.: "+id);
	}
}
