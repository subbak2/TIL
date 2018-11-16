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
			System.out.println("���̵�  �Է� ����");
			list.add("���̵�� �ݵ�� �ԷµǾ�� �մϴ�.");
		}
		if(password == null || password.trim().length() == 0) {
			System.out.println(" ��й�ȣ �Է� ����");
			list.add("��й�ȣ�� �ݵ�� �ԷµǾ�� �մϴ�.");
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
					System.out.println("�α��� ����");
					response.sendRedirect(request.getContextPath()+"/main.jsp");
					return;
				}else {
					System.out.println("�α��� ����");
					request.setAttribute("failMessage", "��й�ȣ�� ��ġ���� �ʽ��ϴ�");
				}
			}else {
				System.out.println("�α��� ����");
				request.setAttribute("failMessage", "���̵� �������� �ʽ��ϴ�");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("failMessage", "�α��� ó���� ������ �߻��Ͽ����ϴ�");

		}
		
		request.getRequestDispatcher("/fail.jsp")
			.forward(request, response);
		return;
		
		
		
		
		
	}
	
	
	

}






