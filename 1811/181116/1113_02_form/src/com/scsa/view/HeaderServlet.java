package com.scsa.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String userAgent= request.getHeader("User-Agent");
		out.println("<html>");
		out.println("<body>");
		out.println("立加 宏扼快历 沥焊 : "+userAgent);
		Enumeration<String> names = request.getHeaderNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getHeader(name);
			out.println(name+" : "+value+"<br/>");
		}
		out.println("</body>");
		out.println("</html>");
		out.close();
		
	}

}








