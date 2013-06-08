<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//	LoginCheck.java에서 로그인 정보를 잘못입력하였을 경우 이 페이지로 되돌아 와서 메세지를 뿌려준다.
	String failMsg = (String) request.getAttribute("FAILMSG");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body>
	<center>
		<h1>로그인 페이지</h1>
	</center>
	<form method="post" action="LoginCheck" name="loginFrm">
		<table width="250" border="1" align="center" bordercolor="skyblue"
			cellspacing="0" cellpadding="5">
			<tr bgcolor="mistyrose">
				<td colspan="2" height="22" align="center"><b><font
						size="3">로그인</font></b></td>
			</tr>
			<tr bgcolor="lightcyan">
				<td>아이디</td>
				<td><input type="text" name="userId" size=10></td>
			</tr>
			<tr bgcolor="lightcyan">
				<td>암호</td>
				<td><input type="password" name="userPwd" size=10></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					name="submit" value="로그인"> <input type="reset" name="reset"
					value="취소"></td>
			</tr>
			<tr>
				<th colspan="2"><a href="form.jsp">회원가입</a></th>
			</tr>
		</table>
	</form>
	<%
		//로그인 정보를 잘못 입력하였을경우 화면에 출력.
		if (failMsg != null)
			out.print(failMsg);
	%>

</body>
</html>