package com.scsa.jdbc.order;

import java.sql.SQLException;

public class OrderTest {

	public static void main(String[] args) {
		OrderDAO dao = new OrderDAO();
		try {
			dao.add(new OrderInfo(0,1,1,5));
			dao.add(new OrderInfo(0,1,2,2));
			dao.add(new OrderInfo(0,1,3,1));
			
			dao.add(new OrderInfo(0,2,1,5));
			dao.add(new OrderInfo(0,2,2,2));
			dao.add(new OrderInfo(0,2,3,1));
			
			dao.add(new OrderInfo(0,3,1,5));
			dao.add(new OrderInfo(0,3,2,2));
			dao.add(new OrderInfo(0,3,3,1));
		} catch (SQLException e) {
			System.out.println("OrderInfo 데이터 추가 실패");
		}
		
		try {
			System.out.println("============== 데이터 등록 테스트 ==============");
			System.out.println();
			for (DeliveryInfo d:dao.deliverysearch()) {
				System.out.println(d);
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("============== 데이터 검색 테스트 ==============");
			System.out.println();
			System.out.println(dao.deliverySearch(1));
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("============== 가격 검색 테스트 ==============");
			System.out.println();
			System.out.println(dao.getOrderPrice(2));
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
