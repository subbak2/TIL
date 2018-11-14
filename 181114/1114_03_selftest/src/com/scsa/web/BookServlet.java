package com.scsa.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scsa.web.model.Book;

public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		double p;
		if (request.getParameter("price")!=null) {
			p = Double.parseDouble(request.getParameter("price"));
		}
		else p = 0.0;
		Book book = new Book(request.getParameter("bookname"), request.getParameter("isbn"),
				request.getParameter("category"), request.getParameter("country"), request.getParameter("date"),
				request.getParameter("company"), request.getParameter("author"), 
				p,request.getParameter("description"));
		request.setAttribute("bookinfo", book);		
		if (request.getParameter("currency").equals("won")) request.setAttribute("currency", "won");
		RequestDispatcher rd = request.getRequestDispatcher("/book/BookView.jsp");
		rd.forward(request, response);		
		return;
	}
}