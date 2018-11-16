<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인에 성공하였습니다.</h2>
	<ul>
	<li><a href="<%=request.getContextPath()%>/controller/user/list.do">사용자 목록</a></li>
	<li><a href="">로그아웃</a></li>
	</ul>
</body>
</html>