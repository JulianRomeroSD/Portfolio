<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<!--Import some libraries that have classes that we need -->
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Top 10 Beers</title>
	</head>
	<body>
			<body style="background-color:cyan;">
		    <h1 style ="color:black;"> The top 10 most popular beers </h1>
	
		<% try {
	
			//Get the database connection
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();		

			//Create a SQL statement
			Statement stmt = con.createStatement();
			//Get the selected radio button from the index.jsp
			String entity = request.getParameter("command");
			//Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
			String str = "SELECT transactions.item, sum(quantity) FROM transactions LEFT JOIN bills ON transactions.bill_id = bills.bill_id WHERE transactions.type = 'beer' AND bar = '"+entity+"' GROUP BY item ORDER BY sum(quantity) DESC LIMIT 10";
			//Run the query against the database.
			ResultSet result = stmt.executeQuery(str);
		%>
		
		<!--  Make an HTML table to show the results in: -->
	<table>
		<tr>    
			<td><b>Beer</b></td>
			<td><b>Number of Beers bought</b></td>
		</tr>
			<%
			//parse out the results
			while (result.next()) { %>
				<tr>    
					<td><%= result.getString("transactions.item")%></td>
					<td><%= result.getString("sum(quantity)")%></td>
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