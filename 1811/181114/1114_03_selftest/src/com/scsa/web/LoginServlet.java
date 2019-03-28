package com.scsa.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		if (id.equals("scsa") && password.equals("1111")) {
			request.setAttribute("id", "scsa");
			RequestDispatcher rd = request.getRequestDispatcher("/Result.jsp");
			rd.forward(request, response);
			
			return;
		}
		else {
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
			return;
		}
		
	}
}
