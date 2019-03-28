<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 성공</title>
<style>
.b_box {margin 0 auto;
	text-align: center;
}
</style>
</head>
<body>
	<div class="b_box">
		<h3><%=request.getAttribute("id")%> 님 로그인 되었습니다.!!!
		</h3>
		<a href="<%=request.getContextPath()%>/book/BookInput.html">도서 등록</a>
	</div>
</body>
</html>