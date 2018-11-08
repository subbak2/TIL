package com.scsa.wjdbc.day2;

import java.sql.SQLException;
import java.util.List;

public class BookDaoTest {
	public static void main(String[] args) {
		BookDao bd = null;
		try {
			bd = new BookDao();
			//bd.insertBook(new Book("a1101", "Java �⺻", "�ھ� ���������", "�ھ� ���ǻ�", 23000, "�ڹ� �⺻�� ���� ����~"));
			//bd.insertBook(new Book("a1102", "Java �߱�", "�ھ� ���������", "�ھ� ���ǻ�", 25000, "�ڹ� �߱� ����� ���� ����~"));
			//bd.insertBook(new Book("a1103", "Java ����", "�ھ� ���������", "�ھ� ���ǻ�", 30000, "�ڹ� ���� ����� ���� ����~"));
			System.out.println("************** ���� ��� **************");		
			System.out.println("��ϵ� ���� ���� ="+bd.count());
			printAllBooks(bd.listBooks());
			System.out.println("��������: "+bd.findBook("a1101"));
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
