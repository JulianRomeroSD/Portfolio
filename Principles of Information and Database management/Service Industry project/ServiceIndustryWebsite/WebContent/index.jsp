<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Homepage</title>
	</head>
	<body>
	<body style="background-color:cyan;">

		<h1 style ="color:black;">Welcome to the Homepage of Julian Romero's Service Industry Website!</h1>
							  
		<br>
		<form method="get" action="DrinkerPage.jsp">
		   Click to here to see the Drinkers.
		  <br>
		  <input type="submit" value="submit" />
		</form>
		<br>
		
		<form method="get" action="BarPage.jsp">
		 Click to here to see the bars.
		  <br>
		  <input type="submit" value="submit" />
		</form>
		<br>
		<form method="get" action="BeerPage.jsp">
		  Click to here to see the beers.
		  <br>
		  <input type="submit" value="submit" />
		</form>
		<br>
		
		Please enter an sql query to update data base
		<form method="get" action="SQLentry.jsp">
		<input style="text" name = "command"/>
		<input type="submit" value="submit" />
		</form>
		<br>
		If you dont like SQL like most do... 
		<br>
		you can use add beers and bars in English instead!
		<br>
		<br>
		
	An existing bar wants to sell an existing beer! Type the bar, the beer and the price of the beer:
	<br>
		<form method="get" action="sellsNewBeer.jsp">
			<table>
				<tr>    
					<td>Bar</td><td><input type="text" name="bar"></td>
				</tr>
				<tr>
					<td>Beer</td><td><input type="text" name="beer"></td>
				</tr>
				<tr>
					<td>Price</td><td><input type="text" name="price"></td>
				</tr>
			</table>
			<input type="submit" value="Add the selling beer!">
		</form>
	<br>
	
	
	Alternatively, lets type in a new bar, a new beer, and a price that this bar will sell the beer for.
	<br>
		<form method="post" action="newBarBeerPrice.jsp">
		<table>
		<tr>    
		<td>Bar</td><td><input type="text" name="bar"></td>
		</tr>
		<tr>
		<td>Beer</td><td><input type="text" name="beer"></td>
		</tr>
		<tr>
		<td>Price</td><td><input type="text" name="price"></td>
		</tr>
		</table>
		<input type="submit" value="Add me!">
		</form>
	<br>
	
	We can also delete an entry by adding a bar, a beer, and a price that exists.
	<br>
		<form method="post" action="DeleteEntry.jsp">
		<table>
		<tr>    
		<td>Bar</td><td><input type="text" name="bar"></td>
		</tr>
		<tr>
		<td>Beer</td><td><input type="text" name="beer"></td>
		</tr>
		<tr>
		<td>Price</td><td><input type="text" name="price"></td>
		</tr>
		</table>
		<input type="submit" value="Remove me!">
		</form>
	<br>
	
	Some querying of beers with price:
	<br>
		<form method="get" action="query.jsp">
			<select name="price" size=1>
				<option value="3.0">$3.0 and under</option>
				<option value="5.0">$5.0 and under</option>
				<option value="8.0">$8.0 and under</option>
			</select>&nbsp;<br> <input type="submit" value="submit">
		</form>
	<br>
	
	
</body>
</html>