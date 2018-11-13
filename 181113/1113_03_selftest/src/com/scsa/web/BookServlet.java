package com.scsa.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String isbn = request.getParameter("isbn");
		String bookname = request.getParameter("bookname");
		String category = request.getParameter("category");
		String country = request.getParameter("country");
		String date = request.getParameter("date");
		String company = request.getParameter("company");
		String author = request.getParameter("author");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<title>도서를 등록해주세요</title>\r\n" + 
				"<style>\r\n" + 
				"table {\r\n" + 
				"	width: 600px;\r\n" + 
				"	padding: 10px;\r\n" + 
				"	margin: 0 auto;\r\n" + 
				"	text-align: center;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"#t_header {\r\n" + 
				"	font-size: 25px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"#t_body {\r\n" + 
				"	padding-left: 15px;\r\n" + 
				"	text-align: left;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"#t_gray {\r\n" + 
				"	background-color: #cccccc;\r\n" + 
				"	width: 150px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"#t_footer {\r\n" + 
				"	align-content: center;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<form action=\"/1113_03_selftest/book.do\" method=\"post\">\r\n" + 
				"		<table id=\"t_header\">\r\n" + 
				"			<thead>\r\n" + 
				"				<tr>\r\n" + 
				"					<th>입력된 도서 정보</th>\r\n" + 
				"				</tr>\r\n" + 
				"			</thead>\r\n" + 
				"		</table>\r\n" + 
				"		<br />\r\n" + 
				"\r\n" + 
				"		<table>\r\n" + 
				"			<tbody>\r\n" + 
				"				<tr>\r\n" + 
				"					<td style=\"background-color: #cccccc; text-align: center\"\r\n" + 
				"						colspan=\"2\">도서 정보</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td id=\"t_gray\">도서명</td>\r\n" + 
				"					<td id=\"t_body\">"+bookname+"</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td id=\"t_gray\">도서번호</td>\r\n" + 
				"					<td id=\"t_body\">"+isbn+"</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td id=\"t_gray\">도서분류</td>\r\n" + 
				"					<td id=\"t_body\">"+category+"</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td id=\"t_gray\">도서국가</td>\r\n" + 
				"					<td id=\"t_body\">"+country+"</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td id=\"t_gray\">출판일</td>\r\n" + 
				"					<td id=\"t_body\">"+date+"</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td id=\"t_gray\">출판사</td>\r\n" + 
				"					<td id=\"t_body\">"+company+"</td>\r\n" + 
				"				</tr>\r\n" + 
				"\r\n" + 
				"				<tr>\r\n" + 
				"					<td id=\"t_gray\">저 자</td>\r\n" + 
				"					<td id=\"t_body\">"+author+"</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td id=\"t_gray\">도서가격</td>\r\n" + 
				"					<td id=\"t_body\">"+price+"</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td id=\"t_gray\">도서설명</td>\r\n" + 
				"					<td id=\"t_body\">"+description+"</td>\r\n" + 
				"				</tr>\r\n" + 
				"			</tbody>\r\n" + 
				"		</table>\r\n" + 
				"		<br />\r\n" + 
				"\r\n" + 
				"		<table id=\"t_footer\">\r\n" + 
				"			<thead align=\"center\">\r\n" + 
				"				<tr>\r\n" + 
				"					<td colspan=\"2\" style=\"text-decoration: underline;\">도서 등록</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"				</tr>\r\n" + 
				"			</thead>\r\n" + 
				"		</table>\r\n" + 
				"		<br />\r\n" + 
				"	</form>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>");	
	}
}
