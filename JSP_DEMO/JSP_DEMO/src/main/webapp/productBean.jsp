<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage="errorHandler.jsp"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="product" class="e2101089.java.server.Product">
		<jsp:setProperty name="product" property="*"/>
	</jsp:useBean>
	
	Product details:
	<br/> Name: <jsp:getProperty property="name" name="product"/>
	<br/> Price: <jsp:getProperty property="price" name="product"/>
	
	Name: <%=product.getName() %>
</body>
</html>