<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Addition result</title>
</head>
<body>
	<% 
			int number01 = Integer.parseInt(request.getParameter("number01"));
			int number02 = Integer.parseInt(request.getParameter("number02"));
		%>
		Sum of <%=number01 %> and <%=number02 %> is <%=number01 + number02 %>
</body>
</html>