<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인을 해주세요</title>
<style type="text/css">
.d_form {
	margin: 0 auto;
	width: 380px;
	background-color: #cccccc;
	border: 1px solid black;
}
</style>
</head>
<body>
	<div class="d_body">
		<form action="<%=request.getContextPath()%>/login.do" method="post">
			<div>
				<table class="d_form">
					<thead class="d_form" style="background-color: #bbbbbb;">
						<tr>
							<th colspan="2">로그인</th>
						</tr>
					</thead>
					<tbody class="d_form">
						<tr>
							<td>아이디</td>
							<td><input type="text" name="id" required="required"></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="password"
								required="required"></td>
						</tr>
					</tbody>
					<tfoot class="d_form">
						<tr style="text-align: center;">
							<td colspan="2"><input type="submit" value="로그인"> <input
								type="reset" value="취소"></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</form>
	</div>
</body>
</html>