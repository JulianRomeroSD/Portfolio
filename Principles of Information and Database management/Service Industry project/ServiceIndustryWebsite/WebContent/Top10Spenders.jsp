<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<!--Import some libraries that have classes that we need -->	
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Top 10 Drinkers</title>
	</head>
	<body>
			<body style="background-color:cyan;">
		    <h1 style ="color:black;"> The top 10 spenders </h1>
		<% try {
			//Get the database connection
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();		

			//Create a SQL statement
			Statement stmt = con.createStatement();
			//Get the selected radio button from the index.jsp
			String entity = request.getParameter("command");
			//Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
			String str = "SELECT drinker, round(sum(total_price), 2) FROM bills WHERE bar = '"+entity+"'  GROUP BY drinker ORDER BY SUM(total_price) DESC LIMIT 10";
			//Run the query against the database.
			ResultSet result = stmt.executeQuery(str);
		%>
		
		<!--  Make an HTML table to show the results in: -->
	<table>
		<tr>    
			<td><b>Drinker</b></td>
			<td><b>Money Spent in Dollars</b></td>
		</tr>
			<%
			//parse out the results
			while (result.next()) { %>
				<tr>    
					<td><%= result.getString("drinker")%></td>
					<td><%= result.getString("round(sum(total_price), 2)")%></td>
				</tr>
				

			<% }
			//close the connection.
			db.closeConnection(con);
			%>
		</table>

			
		<%} catch (Exception e) {
			out.print(e);
		}%>
	

	</body>
</html>