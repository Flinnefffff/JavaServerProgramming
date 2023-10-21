<%@ page import="java.sql.Connection,java.sql.DriverManager,java.sql.ResultSet,java.sql.Statement,java.util.ArrayList,java.util.List" %>
<%@ page import="e2101089.java.server.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>


<%
    // Define the database connection parameters
    Connection conn = null;
    Statement stm = null;
    ResultSet rs = null;

    List<Product> products = new ArrayList<Product>();

    try {
        // Load the MariaDB JDBC driver and establish a connection
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi/e2101089_java_server.product","e2101089","MFVfJ8ckjcC");

        // Create and execute a SQL query to fetch all products
        stm = conn.createStatement();
        String sql = "SELECT * FROM product";
        rs = stm.executeQuery(sql);

        // Iterate through the result set and populate the 'products' list
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("ID"));
            product.setName(rs.getString("Name"));
            product.setPrice(rs.getFloat("Price"));
            products.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }finally{
    	try{
        // Close database resources
        rs.close();
        stm.close();
        conn.close();
    	} catch (Exception e) {
            e.printStackTrace();
    }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>View Products</title>
</head>
<body>
    <h1>Products</h1>
       <% for (Product product : products) {%>
        <tr>
                <td><%= product.getId() %></td>
                <td><%= product.getName() %></td>
                <td><%= product.getPrice() %></td>
            </tr>
<%}%>
    </table>
</body>
</html>
