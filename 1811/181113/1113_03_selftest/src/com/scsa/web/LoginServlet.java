package com.scsa.web;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		System.out.println(id);
		System.out.println(password);
		if (id.equals("scsa") && password.equals("1111")) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>");
			out.println("�α��� ����");
			out.println("</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>");
			out.println("scsa �� �α��� �Ǿ����ϴ�.!!!");
			out.println("</h3>");
			out.println("<br/>");
			out.println("<a href=\"/1113_03_selftest/book/BookInput.html\">");
			out.println("���� ���");			
			out.println("</a>");
			out.println("</body>");
			out.println("</html>");		
		}
		else {
			out.println("<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"UTF-8\">\r\n" + 
					"<title>Insert title here</title>\r\n" + 
					"<style type=\"text/css\">\r\n" + 
					".d_form {\r\n" + 
					"	margin: 0 auto;\r\n" + 
					"	width: 380px;\r\n" + 
					"	background-color: #cccccc;\r\n" + 
					"	border: 1px solid black;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<div class=\"d_body\">\r\n" + 
					"		<form action=\"/1113_03_selftest/login.do\" method=\"post\">\r\n" + 
					"			<div>\r\n" + 
					"				<table class=\"d_form\">\r\n" + 
					"					<thead class=\"d_form\" style=\"background-color: #bbbbbb;\">\r\n" + 
					"						<tr>\r\n" + 
					"							<th colspan=\"2\">�α���</th>\r\n" + 
					"						</tr>\r\n" + 
					"					</thead>\r\n" + 
					"					<tbody class=\"d_form\">\r\n" + 
					"						<tr>\r\n" + 
					"							<td>���̵�</td>\r\n" + 
					"							<td><input type=\"text\" name=\"id\" required=\"required\"></td>\r\n" + 
					"						</tr>\r\n" + 
					"						<tr>\r\n" + 
					"							<td>��й�ȣ</td>\r\n" + 
					"							<td><input type=\"password\" name=\"password\" required=\"required\"></td>\r\n" + 
					"						</tr>\r\n" + 
					"					</tbody>\r\n" + 
					"					<tfoot class=\"d_form\">\r\n" + 
					"						<tr style=\"text-align: center;\">\r\n" + 
					"							<td colspan=\"2\"><input type=\"submit\" value=\"�α���\"> <input\r\n" + 
					"								type=\"reset\" value=\"���\"></td>\r\n" + 
					"						</tr>\r\n" + 
					"					</tfoot>\r\n" + 
					"				</table>\r\n" + 
					"			</div>\r\n" + 
					"		</form>\r\n" + 
					"	</div>\r\n" + 
					"</body>\r\n" + 
					"</html>");			
		}
		out.close();
		
	}
}
