package com.scsa.exception;

public class ExistedIDException extends Exception {

	public ExistedIDException(String id) {
		super("���̵� �̹� �����մϴ�.: "+id);
	}
}
