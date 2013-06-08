<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String loginId = (String) session.getAttribute("userId");
	String loginName = (String) session.getAttribute("userNm");
%>

<%
	//세션에 로그인정보가 없을경우 초기페이지로이동
	if (loginId == null || loginId.equals("")) {
		request.setAttribute("FAILMSG", "세션정보가 없습니다. 다시 로그인해주십시오.");
		response.sendRedirect("login.jsp");
	}
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main page</title>
<script language="javascript">
	function goLogout() {
		if (confirm("로그아웃 하시겠습니까?")) {
			location.href = "LogOut";
		}
	}
</script>
</head>
<body>
	<div id="header" style="float: right;">
		<b><%=loginName%></b>&nbsp; 님 환영합니다. <input type="button"
			name="outBtn" value="Logout" onClick="javascript:goLogout();">
	</div>
	<br /><br /><br />
	<div id="content">
		<center><h2>세션조회</h2>
		<%
		
		out.print("세션userId : ");
		out.println(session.getAttribute("userId"));
		out.println("<br/>");
		out.print("세션userNm : ");
		out.println(session.getAttribute("userNm"));
		
		out.println("<br/>");
		long ms=session.getCreationTime();
		Date time=new Date(ms);
		
		out.println(time.toLocaleString());
		%>
		</center>
	</div>
</body>
</html>