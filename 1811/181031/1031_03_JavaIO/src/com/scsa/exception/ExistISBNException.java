package com.scsa.exception;

public class ExistISBNException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistISBNException(String isbn) {
		super("�̹� �����ϴ� isbn�Դϴ� : "+isbn);
	}
}
