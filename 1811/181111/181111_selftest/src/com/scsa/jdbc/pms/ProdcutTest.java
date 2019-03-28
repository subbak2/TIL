package com.scsa.jdbc.pms;

import java.sql.SQLException;

public class ProdcutTest {

	public static void main(String[] args) {

		ProductDAO dao = new ProductDAO();

		try {
			dao.add(new Product(1, "�߱���", 4000, "����"));
			dao.add(new Product(2, "�౸��", 20000, "����Ű"));
			dao.add(new Product(3, "�籸��", 6000, "��Ƽ�籸��"));
		} catch (SQLException e) {
			System.out.println("Product ������ �߰� ����");
		}
		try {
			for (Product p : dao.search()) {
				System.out.println(p);
			}
		} catch (SQLException e) {
			System.out.println("Product ������ �˻� ����");
		}
	}

}
