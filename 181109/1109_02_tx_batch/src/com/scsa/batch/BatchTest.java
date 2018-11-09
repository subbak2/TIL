package com.scsa.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.scsa.util.DBUtil;

public class BatchTest {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "insert into emp(empno, ename, deptno, sal) " + "values(?,?,?,?)";

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, 9003);
			stmt.setString(2, "Luna");
			stmt.setInt(3, 50);
			stmt.setInt(4, 2000);
			stmt.addBatch();
			
			stmt.setInt(1, 9004);
			stmt.setString(2, "Luna2");
			stmt.setInt(3, 50);
			stmt.setInt(4, 3000);
			stmt.addBatch();		//sql 주지 않는게 중요
						
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