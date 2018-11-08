package com.scsa.wjdbc.day2;

import java.sql.SQLException;
import java.util.List;

public class BookDaoTest {
	public static void main(String[] args) {
		BookDao bd = null;
		try {
			bd = new BookDao();
			//bd.insertBook(new Book("a1101", "Java 기본", "자앤 기술연구소", "자앤 출판사", 23000, "자바 기본을 쉽게 설명~"));
			//bd.insertBook(new Book("a1102", "Java 중급", "자앤 기술연구소", "자앤 출판사", 25000, "자바 중급 기술을 쉽게 설명~"));
			//bd.insertBook(new Book("a1103", "Java 실전", "자앤 기술연구소", "자앤 출판사", 30000, "자바 실전 기법을 쉽게 설명~"));
			System.out.println("************** 도서 목록 **************");		
			System.out.println("등록된 도서 개수 ="+bd.count());
			printAllBooks(bd.listBooks());
			System.out.println("도서정보: "+bd.findBook("a1101"));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void printAllBooks(List<Book> list) {
		for (Book book : list) {
			System.out.println(book.toString());
		}
	}
}
