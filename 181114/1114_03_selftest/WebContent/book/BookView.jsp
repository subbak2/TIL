<%@page import="com.scsa.web.model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>입력된 도서 정보</title>
<style>
table {
	width: 600px;
	padding: 10px;
	margin: 0 auto;
	text-align: center;
}

#t_header {
	font-size: 25px;
}

#t_body {
	padding-left: 15px;
	text-align: left;
}

#t_gray {
	background-color: #cccccc;
	width: 150px;
}

#t_footer {
	align-content: center;
}
</style>
</head>
<body>
	<%Book book = (Book)request.getAttribute("bookinfo");%>
	<table id="t_header">
		<thead>
			<tr>
				<th>입력된 도서 정보</th>
			</tr>
		</thead>
	</table>
	<br />

	<table>
		<tbody>
			<tr>
				<td style="background-color: #cccccc; text-align: center"
					colspan="2">도서 정보</td>
			</tr>
			<tr>
				<td id="t_gray">도서명</td>
				<td id="t_body"><%= book.getBookname() %></td>
			</tr>
			<tr>
				<td id="t_gray">도서번호</td>
				<td id="t_body"><%=book.getIsbn() %></td>
			</tr>
			<tr>
				<td id="t_gray">도서분류</td>
				<td id="t_body"><%=book.getCategory() %></td>
			</tr>
			<tr>
				<td id="t_gray">도서국가</td>
				<td id="t_body"><%=book.getCountry() %></td>
			</tr>
			<tr>
				<td id="t_gray">출판일</td>
				<td id="t_body"><%=book.getDate() %></td>
			</tr>
			<tr>
				<td id="t_gray">출판사</td>
				<td id="t_body"><%=book.getCompany() %></td>
			</tr>

			<tr>
				<td id="t_gray">저 자</td>
				<td id="t_body"><%=book.getAuthor() %></td>
			</tr>
			<tr>
				<td id="t_gray">도서가격</td>
				<td id="t_body">
				<%=book.getPrice()%>
				</td>
			</tr>
			<tr>
				<td id="t_gray">도서설명</td>
				<td id="t_body"><%=book.getDescription() %></td>
			</tr>
		</tbody>
	</table>
	<br />

	<table id="t_footer">
		<thead align="center">
			<tr>
				<td colspan="2"><a
					href="<%=request.getContextPath()%>/book/BookInput.html">도서 등록</a></td>
			</tr>
			<tr>
			</tr>
		</thead>
	</table>
	<br />
</body>
</html>