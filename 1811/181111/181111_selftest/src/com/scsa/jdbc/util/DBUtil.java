package com.scsa.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private static final String DB_USER = "scott";
	private static final String DB_PASSWORD = "tiger";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}

	public static void close(ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
	}

	public static void close(Statement ps) throws SQLException {
		if (ps != null)
			ps.close();
	}

	public static void close(Connection con) throws SQLException {
		if (!con.getAutoCommit()) {
			con.setAutoCommit(true);
		}
		if (con != null)
			con.close();
	}
}
