package com.scsa.jdbc.cms;

import java.sql.SQLException;

public class CustTest {

	public static void main(String[] args) {
		CustomerDAO dao = new CustomerDAO();
		try {
			dao.add(new Customer(1,"김덕배","여주군"));
			dao.add(new Customer(2,"마동탁","울진군"));
			dao.add(new Customer(3,"이다도","시리아"));
		} catch (SQLException e) {
			System.out.println("Customer 데이터 추가 실패");
		}
		try {
			for(Customer c:dao.search()) {
				System.out.println(c);
			}
		} catch (SQLException e) {
			System.out.println("Customer 데이터 검색 실패");
		}
	}
}
