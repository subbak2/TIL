<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		ArrayList<String> errorMessage 
			= (ArrayList<String>)request.getAttribute("errorMessage");
		System.out.println("login.jsp : "+errorMessage);
		if(errorMessage != null){	
			out.print("<ul>");
			for(String error : errorMessage){	%>
				<li><%=error %></li>
		<%	}	%>
			</ul>
	<%		
		}
	%>
	

	<form method="post" action="<%=request.getContextPath() %>/controller/login.do">
		아이디 : <input type="text" name="userId"><br/>
		비밀번호 : <input type="password" name="password"><br/>
		<input type="submit" value="로그인"/>
		<input type="reset" value="취소"/>
 	</form>
</body>
</html>





