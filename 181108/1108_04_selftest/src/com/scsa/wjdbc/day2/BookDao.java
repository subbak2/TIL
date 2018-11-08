package com.scsa.wjdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	final String DB_USER = "scott";
	final String DB_PASSWORD = "tiger";

	public BookDao() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}

	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		return conn;
	}

	public void close(Connection connection) {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(ResultSet resultSet) {
		if (resultSet != null)
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public void insertBook(Book book) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "insert into book values(?,?,?,?,?,?)";
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, book.getIsbn());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, book.getPublisher());
			stmt.setInt(5, book.getPrice());
			stmt.setString(6, book.getDescription());

			int rowCount = stmt.executeUpdate();
			System.out.println(rowCount + "행이 삽입되었습니다.");
		} finally {
			close(stmt);
			close(conn);
		}
	}

	public void updateBook(Book book) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "update book set title = ?, author = ?, publisher = ?, "
					+ "price = ?, description = ? where isbn = ?";
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getPublisher());
			stmt.setInt(4, book.getPrice());
			stmt.setString(5, book.getDescription());
			stmt.setString(6, book.getIsbn());

			int rowCount = stmt.executeUpdate();
			System.out.println(rowCount + "행이 수정되었습니다.");
		} finally {
			close(stmt);
			close(conn);
		}
	}

	public void deleteBook(Book book) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "delete from book where isbn = ?";
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, book.getIsbn());

			int rowCount = stmt.executeUpdate();
			System.out.println(rowCount + "행이 삭제되었습니다.");
		} finally {
			close(stmt);
			close(conn);
		}
	}

	public Book findBook(String isbn) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from book where isbn = ?";
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, isbn);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return (new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6)));
			}
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		return null;
	}

	public List<Book> listBooks() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		try {
			String sql = "select * from book";
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bookList.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6)));
			}
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		return bookList;
	}

	public int count() throws SQLException {
		return listBooks().size();
	}

}
