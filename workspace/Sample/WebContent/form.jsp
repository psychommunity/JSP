<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String failMsg = (String) request.getAttribute("FAILMSG");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
</head>
<body>
	<center>
		<h1>회원가입</h1>
		<form action="Register" name="f1" method="post">
			<table border="1" cellspacing="0" cellpadding="5">
				<tr>
					<td>id</td>
					<td><input type="text" maxlength="12" name="id" /></td>
				</tr>
				<tr>
					<td>name</td>
					<td><input type="text" maxlength="10" name="name" /></td>
				</tr>
				<tr>
					<td>p/w</td>
					<td><input type="password" maxlength="12" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" name="submit"
						value="가입하기" />&nbsp;<input type="reset" value="취소"
						onclick="window.close()" /></td>
				</tr>
			</table>
		</form>
		<p>
			<%
				//가입 정보를 잘못 입력하였을경우 화면에 출력.			
				if (failMsg != null)
					out.print(failMsg);
			%>
		</p>
	</center>
</body>
</html>