package com.scsa.batch;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.scsa.util.DBUtil;

public class BatchTest2 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		String sql1 = "insert into dept(deptno,dname,loc) "
					+ "values(50,'TESTING','SEOUL')";
		String sql2 = "insert into emp(empno, ename, deptno, sal) "
					+ "values(9000,'BILL',50,8000)";
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
}