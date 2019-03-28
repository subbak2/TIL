package com.scsa.tx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.scsa.util.DBUtil;

public class TxTest {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt1 = null, stmt2 = null;

		String sql1 = "insert into dept(deptno,dname,loc) " + "values(?,?,?)";
		String sql2 = "insert into emp(empno, ename, deptno, sal) " + "values(?,?,?,?)";
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			stmt1 = conn.prepareStatement(sql1);
			stmt2 = conn.prepareStatement(sql2);
			stmt1.setInt(1, 90);
			stmt1.setString(2, "Big Data");
			stmt1.setString(3, "SEOUL");

			int rowCount1 = stmt1.executeUpdate();
			
			stmt2.setInt(1, 9999);
			stmt2.setString(2, "Jack");
			stmt2.setInt(3, 90);
			stmt2.setInt(4, 5000);

			int rowCount2 = stmt2.executeUpdate();

			System.out.println("등록완료 : "+rowCount1+", "+rowCount2);
			
			if (rowCount1>0 && rowCount2>0) {
				conn.commit();
			} else conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtil.close(stmt1);
			DBUtil.close(stmt2);
			DBUtil.close(conn);
		}
	}
}