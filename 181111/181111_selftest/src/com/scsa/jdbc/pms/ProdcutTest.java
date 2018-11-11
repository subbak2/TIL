package com.scsa.jdbc.pms;

import java.sql.SQLException;

public class ProdcutTest {

	public static void main(String[] args) {

		ProductDAO dao = new ProductDAO();

		try {
			dao.add(new Product(1, "야구공", 4000, "윌슨"));
			dao.add(new Product(2, "축구공", 20000, "나이키"));
			dao.add(new Product(3, "당구공", 6000, "멀티당구장"));
		} catch (SQLException e) {
			System.out.println("Product 데이터 추가 실패");
		}
		try {
			for (Product p : dao.search()) {
				System.out.println(p);
			}
		} catch (SQLException e) {
			System.out.println("Product 데이터 검색 실패");
		}
	}

}
