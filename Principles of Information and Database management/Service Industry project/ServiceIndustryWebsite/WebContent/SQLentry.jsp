<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Update/Insert</title>
	</head>
	<body>
			<body style="background-color:cyan;">
	
		<% try {
	
			//Get the database connection
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();		
			Statement stmt = con.createStatement();
			String entity = request.getParameter("command");
			String str = entity;
			int i = stmt.executeUpdate(str);
			//Run the query against the database.
			
		%>
		
		db.closeConnection(con);
		
		out.print("Your update/insert was successful!");
		
			
		<%} catch (Exception e) {
			out.print("foreign key constraint violated!");
		}%>
	</body>
</html>