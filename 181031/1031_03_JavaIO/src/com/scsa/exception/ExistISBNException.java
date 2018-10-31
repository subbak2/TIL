package com.scsa.exception;

public class ExistISBNException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistISBNException(String isbn) {
		super("이미 존재하는 isbn입니다 : "+isbn);
	}
}
