package com.scsa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.scsa.exception.ExistedIDException;
import com.scsa.model.vo.User;
import com.scsa.util.DBUtil;

public class UserDAO {
	public UserDAO() throws ClassNotFoundException {
	}

	public void insertUser(User[] user) throws SQLException, ExistedIDException {

		for (User user2 : user) {
			insertUser(user2);
		}
		// 트랜젝션 개념 적용 안 됨 --> 배치 짱짱맨
	}

	public void insertUser2(User[] user) throws SQLException, ExistedIDException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into userinfo(userid, password, name, email) values(?,?,?,?)";
		
		conn = DBUtil.getConnection();
		
		stmt = conn.prepareStatement(sql);
		for (User user2 : user) {
			stmt.setString(1, user2.getUserId());
			stmt.setString(2, user2.getPassword());
			stmt.setString(3, user2.getName());
			stmt.setString(4, user2.getEmail());			
			stmt.addBatch();	
		}
		stmt.executeBatch();		
	}

	
	public boolean insertUser(User user) throws SQLException, ExistedIDException {
		return insertUser(user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public boolean insertUser(String userId, String password, String name, String email)
			throws SQLException, ExistedIDException {
		// 메소드 단위로 conn 생성하고 stmt준비하고 실행 후 release를 하고 있음
		// ex)회원가입을 할때 여러명이 회원가입하고, 동시 로그인, 여러명이 회원정보 할 것..
		// 객체가 여러 쓰레드에 의해 공유되는 상황이 발생할 수 있음.
		// 그렇기 때문에 메소드 단위로 conn,stmt 생성후 처리..전역으로 처리하지 않는다.
		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "insert into userinfo(userid,password,name,email) " + "values(?,?,?,?)";

		try {

			if (selectUser(userId) != null) {
				throw new ExistedIDException(userId);
			}

			conn = DBUtil.getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setString(4, email);
			int rowCount = stmt.executeUpdate();

			System.out.println(rowCount + " 행이 처리되었습니다.");

			return rowCount > 0;

		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public boolean insertUser2(String userId, String password, String name, String email)
			throws SQLException, ExistedIDException {
		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "insert into userinfo(userid,password,name,email) " + "values(?,?,?,?)";

		try {

			conn = DBUtil.getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setString(4, email);
			int rowCount = stmt.executeUpdate();

			System.out.println(rowCount + " 행이 처리되었습니다.");

			return rowCount > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			if (e instanceof SQLIntegrityConstraintViolationException) {
				throw new ExistedIDException(userId);
			} else {
				throw e;
			}
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public void deleteUser(String userId) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from userinfo where userid = ?";

		try {

			// 2. create connection
			conn = DBUtil.getConnection();
			// 3. create statement
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			// 4. execute(send) SQL
			int rowCount = stmt.executeUpdate();
			System.out.println(rowCount + " 행이 처리되었습니다.");
		} finally {
			// 5. resource release
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public void updateUser(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update userinfo " + "set password=?, name=?, email=?" + " where userid = ?";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getPassword());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getUserId());
			int rowCount = stmt.executeUpdate();
			System.out.println(rowCount + "행이 처리되었습니다.");
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public User selectUser(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "select userid, password, name, email " + "from userinfo where userid = ? ";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return user;
	}

	public List<User> selectUserList() throws SQLException {
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select * from userinfo";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				userList.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return userList;
	}
}