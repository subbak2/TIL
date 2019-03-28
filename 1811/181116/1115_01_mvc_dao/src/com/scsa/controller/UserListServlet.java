package com.scsa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scsa.model.dao.UserDAO;
import com.scsa.model.vo.User;


public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//1. process Business Logic(call Model)
		UserDAO userDao = new UserDAO();
		try {
			List<User> userList = userDao.selectUserList();
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("/user/list.jsp")
			.forward(request, response);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", 
			"사용자 목록 조회처리 중 문제가 발생하였습니다. : "+e.getMessage());
			request.getRequestDispatcher("/error.jsp")
				.forward(request, response);
			return;
		}
		
	}

}








