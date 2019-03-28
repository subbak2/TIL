package com.scsa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scsa.model.dao.UserDAO;
import com.scsa.model.vo.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		//1. get parameter
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		ArrayList<String> list = new ArrayList<String>();
		
		//2. verify parameter
		if(userId == null || userId.trim().length()==0) {
			System.out.println("아이디  입력 오류");
			list.add("아이디는 반드시 입력되어야 합니다.");
		}
		if(password == null || password.trim().length() == 0) {
			System.out.println(" 비밀번호 입력 오류");
			list.add("비밀번호는 반드시 입력되어야 합니다.");
		}
		
		if(list.size()>0) {
			request.setAttribute("errorMessage", list);
			RequestDispatcher rd 
				= request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
			//response.sendRedirect("./login.jsp");
			return;
		}
		
		
		
		//3. process Business Logic(call Model)
		UserDAO userDao = new UserDAO();
		try {
			User user = userDao.selectUser(userId);
			
			//4. select view(move page) by result
			if(user != null) {
				if(user.getPassword().equals(password)) {
					System.out.println("로그인 성공");
					response.sendRedirect(request.getContextPath()+"/main.jsp");
					return;
				}else {
					System.out.println("로그인 실패");
					request.setAttribute("failMessage", "비밀번호가 일치하지 않습니다");
				}
			}else {
				System.out.println("로그인 실패");
				request.setAttribute("failMessage", "아이디가 존재하지 않습니다");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("failMessage", "로그인 처리중 문제가 발생하였습니다");

		}
		
		request.getRequestDispatcher("/fail.jsp")
			.forward(request, response);
		return;
		
		
		
		
		
	}
	
	
	

}






