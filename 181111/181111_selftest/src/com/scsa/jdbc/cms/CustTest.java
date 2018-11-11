package com.scsa.jdbc.cms;

import java.sql.SQLException;

public class CustTest {

	public static void main(String[] args) {
		CustomerDAO dao = new CustomerDAO();
		try {
			dao.add(new Customer(1,"�����","���ֱ�"));
			dao.add(new Customer(2,"����Ź","������"));
			dao.add(new Customer(3,"�̴ٵ�","�ø���"));
		} catch (SQLException e) {
			System.out.println("Customer ������ �߰� ����");
		}
		try {
			for(Customer c:dao.search()) {
				System.out.println(c);
			}
		} catch (SQLException e) {
			System.out.println("Customer ������ �˻� ����");
		}
	}
}
