<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>  
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Beer Page!</title>
	</head>
	<body>
		<body style="background-color:cyan;">

		<h1 style ="color:black;"> Hello, and  Welcome to The Beer Page! </h1>
		<p><b> The options of the beer page are as follows: </b></p>
		
		<form method="get" action="Top5Bars.jsp">
		<p> See the 5 top bars where this beer sells the most </p>
		<input style="text" name = "command"/>
		<input type="submit" value="submit" />
		</form>
		
		<p> See all the drinkers who are the biggest consumers of this beer </p>
		<form method="get" action="TopConsumer.jsp">
			<input style="text" name = "command"/>
			<input type="submit" value="submit" />
		</form>
		
		
		<p> See a time distribution of when this beer sells the most.(weekly)</p>
		<form method="get" action="BeerTimeDistribution.jsp">
			<input style="text" name = "graph"/>
			<input type="submit" value="submit" />
		</form>
		
	                                      
	</body>
	</html>