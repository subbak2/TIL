package com.scsa.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/login.do")
@WebServlet("/controller/login.do")
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
		//4. select view(move page) by result
		if(userId.equals("scsa") && password.equals("1111")) {
			System.out.println("�α��� ����");
			response.sendRedirect(request.getContextPath()+"/main.jsp");
			return;
		}else {
			System.out.println("�α��� ����");
			response.sendRedirect(request.getContextPath()+"/fail.jsp");
			return;
		}
		
		
	}
	
	
	

}






