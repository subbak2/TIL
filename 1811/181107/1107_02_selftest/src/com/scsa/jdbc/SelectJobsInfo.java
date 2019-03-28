package com.scsa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectJobsInfo {
	public static void main(String[] args) {
		final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		final String DB_USER = "hr";
		final String DB_PASSWORD = "hr";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * "
				+ "from jobs "
				+ "order by 'JOB_ID'";
		
		try {
			// 1. Driver Class Loading 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Code Connection
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			//3. Create Statement
			stmt = conn.createStatement();
			//4. Execute(send)
			rs = stmt.executeQuery(sql);
			//5. ResultSet handling
			while(rs.next()) {
				System.out.println(rs.getString(1)+"/"+rs.getString(2)+"/"+
						rs.getString(3)+"/"+rs.getString(4));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
