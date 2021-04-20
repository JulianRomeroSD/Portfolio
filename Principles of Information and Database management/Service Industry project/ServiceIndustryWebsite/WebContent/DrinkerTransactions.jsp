<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Drinker Transactions</title>
	</head>
	<body>
			<body style="background-color:cyan;">
		    <h1 style ="color:black;"> Drinkers Transactions </h1>
	
		<% try {
	
			//Get the database connection
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();		

			//Create a SQL statement
			Statement stmt = con.createStatement();
			//Get the selected radio button from the index.jsp
			String entity = request.getParameter("command");
			//Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
			String str = "SELECT bar, date, time, quantity, item, items_price, tax_price, tip, total_price FROM Bills B INNER JOIN Transactions T ON B.bill_id = T.bill_id WHERE drinker = '"+ entity + "' ORDER BY bar, date, time;";
			//Run the query against the database.
			ResultSet result = stmt.executeQuery(str);
		%>
		
		<!--  Make an HTML table to show the results in: -->
	<table>
		<tr>    
			<td><b>Bar Name</b></td>
			<td><b>Date</b></td>
			<td><b>Time</b></td>
			<td><b>Item</b></td>
			<td><b>Item Price</b></td>
			<td><b>Tax Price</b></td>
			<td><b>Tip</b></td>
			<td><b>Total Price</b></td>
		</tr>
			<%
			//parse out the results
			while (result.next()) { %>
				<tr>    
					<td><%= result.getString("bar")%></td>
					<td><%= result.getString("date")%></td>
					<td><%= result.getString("time")%></td>
					<td><%= result.getString("item")%></td>
					<td><%= result.getString("items_price")%></td>
					<td><%= result.getString("tax_price")%></td>
					<td><%= result.getString("tip")%></td>
					<td><%= result.getString("total_price")%></td>
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
</html>html>