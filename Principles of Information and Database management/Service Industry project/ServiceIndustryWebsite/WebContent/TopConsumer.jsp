<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Top Consumers</title>
	</head>
	<body>
			<body style="background-color:cyan;">
		    <h1 style ="color:black;"> The Top Consumers Of Said Beer </h1>
	
		<% try {
	
			//Get the database connection
			ApplicationDB db = new ApplicationDB();	
			Connection con = db.getConnection();		

			//Create a SQL statement
			Statement stmt = con.createStatement();
			//Get the selected radio button from the index.jsp
			String entity = request.getParameter("command");
			//Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
			String str = "SELECT bills.drinker, sum(quantity) FROM transactions LEFT JOIN bills ON transactions.bill_id = bills.bill_id WHERE transactions.item = '"+entity+"' GROUP BY drinker ORDER BY sum(quantity) DESC";
			//Run the query against the database.
			ResultSet result = stmt.executeQuery(str);
		%>
		
		<!--  Make an HTML table to show the results in: -->
	<table>
		<tr>    
			<td><b>Drinker</b></td>
			<td><b>Amount Consumed</b></td>
		</tr>
			<%
			//parse out the results
			while (result.next()) { %>
				<tr>    
					<td><%= result.getString("bills.drinker")%></td>
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