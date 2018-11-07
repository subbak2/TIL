package com.scsa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectSalary {

	public static void main(String[] args) {
		final String DB_URL="jdbc:oracle:thin:@127.0.0.1:1521:XE";
		final String DB_USER ="hr";
		final String DB_PASSWORD ="hr";

		//String sql="select first_name,last_name from employees where substr(hire_date,1,2)='07'";
		//String sql="select first_name,last_name from employees where extract(year from hire_date)=2007";
		String sql="select first_name,last_name from employees where to_char(hire_date,'YYYY')='2007'";
		
		Connection conn = null;
		Statement stmt= null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt= conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}
