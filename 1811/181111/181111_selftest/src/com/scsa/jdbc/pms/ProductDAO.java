package com.scsa.jdbc.pms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.scsa.jdbc.util.DBUtil;

public class ProductDAO {

	public void add(Product p) throws SQLException {

		String sql = "Insert into Product values(?,?,?,?)";

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, p.getPnum());
			ps.setString(2, p.getTitle());
			ps.setInt(3, p.getPrice());
			ps.setString(4, p.getBrand());
			int Count = ps.executeUpdate();
			System.out.println("Product에 " + Count + "행이 추가 되었습니다.");
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

	public ArrayList<Product> search() throws SQLException {

		ArrayList<Product> list = new ArrayList<Product>();
		String sql = "select * from Product";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
			}
			System.out.println("Product List 검색 성공");
			return list;
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
}
