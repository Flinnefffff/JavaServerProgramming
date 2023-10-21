<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%!
	Connection conn;
	PreparedStatement ps;
	
	public void jspInit(){
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi/e2101089_java_server.product","e2101089","MFVfJ8ckjcC");
			ps = conn.prepareStatement("INSERT INTO product (Name, price) VALUE(?, ?)");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void jspDestroy(){
		try{
			ps.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
%>

<%
	String name = request.getParameter("name");
	float price = Float.parseFloat(request.getParameter("price"));
	
			ps.setString(1, name);
			ps.setFloat(2, price);
	
			ps.executeUpdate();
%>

<%@ include file="Product.html"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Product Added</h1>
	<a href="Product.html">Add Another Product</a>
</body>
</html>