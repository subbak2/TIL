package com.scsa.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		// ��û�޽����� body���� ���ڵ� ����, post����϶��� ��ȿ
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String[] hobby = request.getParameterValues("hobby");
		String test = request.getParameter("test");
		int age = 0;
		if(request.getParameter("age") != null &&
		   request.getParameter("age").trim().length() != 0) {
			age = Integer.parseInt(request.getParameter("age"));
		}
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h2>�Է��Ͻ� ������ ������ �����ϴ�.</h2>");
		out.println("<h3>���̵� : "+id+"</h3>");
		out.println("<h3>��й�ȣ : "+password+"</h3>");
		out.println("<h3>���� : "+gender+"</h3>");
		out.println("<h3>���� : "+age+"</h3>");
		if(hobby != null) {
			for (String h : hobby) {
				out.println("<h3>��� : "+h+"</h3>");
			}
		}
		out.println("<h3>�׽�Ʈ : "+test+"</h3>");
		
		out.println("</body>");
		out.println("</html>");
		
		out.close();
		
		
	}

}








