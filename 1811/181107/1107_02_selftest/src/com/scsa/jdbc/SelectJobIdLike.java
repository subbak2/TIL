package com.scsa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectJobIdLike {
	public static void main(String[] args) {
		final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		final String DB_USER = "hr";
		final String DB_PASSWORD = "hr";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * "
				+ "from employees "
				+ "where job_id like 'ST%'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString(1)+"/"+rs.getString(2)+"/"
						+rs.getString(3)+"/"+rs.getString(4)+"/"+rs.getString(5)
						+rs.getString(6)+"/"+rs.getString(7)+"/"+rs.getString(8)
						+rs.getString(9)+"/"+rs.getString(10)+"/"+rs.getString(11));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
