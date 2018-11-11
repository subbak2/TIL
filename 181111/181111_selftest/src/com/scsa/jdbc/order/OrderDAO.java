package com.scsa.jdbc.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.scsa.jdbc.util.DBUtil;

public class OrderDAO {

	public void add(OrderInfo o) throws SQLException {

		String sql = "Insert into OrderInfo values(OSEQ.nextval,?,?,?)";

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, o.getCnum());
			ps.setInt(2, o.getPnum());
			ps.setInt(3, o.getQuant());
			int Count = ps.executeUpdate();
			System.out.println("OrderInfo에 " + Count + "행이 추가 되었습니다.");
		} finally {
			try {
				DBUtil.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DBUtil.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<OrderInfo> search() throws SQLException {

		ArrayList<OrderInfo> list = new ArrayList<OrderInfo>();
		String sql = "select * from OrderInfo";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new OrderInfo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
			}
			System.out.println("OrderInfo List 검색 성공");
			return list;
		} finally {
			try {
				DBUtil.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DBUtil.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public OrderInfo search(int onum) throws SQLException {
		OrderInfo oi = null;
		String sql = "select * from OrderInfo where ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, onum);
			rs = ps.executeQuery();
			if (rs.next()) {
				oi = new OrderInfo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
			}
			System.out.println("OrderInfo List 검색 성공");
			return oi;
		} finally {
			try {
				DBUtil.close(rs);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				DBUtil.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DBUtil.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public DeliveryInfo deliverySearch(int onum) throws SQLException {
		DeliveryInfo di = null;

		String sql = "select o.onum,o.cnum, o.pnum, o.quant,c.cname, c.caddress" 
				+ " from Customer c,OrderInfo o"
				+ " where c.cnum = o.cnum AND o.onum = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, onum);
			rs = ps.executeQuery();
			if (rs.next()) {
				di = new DeliveryInfo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6));
			}
			System.out.println("DeliveryInfo List 검색 성공");
			return di;
		} finally {
			try {
				DBUtil.close(rs);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				DBUtil.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DBUtil.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<DeliveryInfo> deliverysearch() throws SQLException {

		ArrayList<DeliveryInfo> list = new ArrayList<DeliveryInfo>();

		String sql = "select o.onum,o.cnum, o.pnum, o.quant,c.cname, c.caddress" 
				+ " from Customer c,OrderInfo o"
				+ " where c.cnum = o.cnum";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new DeliveryInfo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6)));
			}
			System.out.println("DeliveryInfo List 검색 성공");
			return list;
		} finally {
			try {
				DBUtil.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DBUtil.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getOrderPrice(int onum) throws SQLException {
		int num = 0;

		String sql = " select sum(o.QUANT*p.price) as 가격"
				+ " from Product p,OrderInfo o"
				+ " where p.Pnum = o.pnum AND o.onum = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, onum);
			rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}
			System.out.println("OrderInfo 가격 검색 성공");
			return num;
		} finally {
			try {
				DBUtil.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DBUtil.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
