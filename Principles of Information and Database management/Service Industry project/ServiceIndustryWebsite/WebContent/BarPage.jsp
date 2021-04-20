<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<!--Import some libraries that have classes that we need -->
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bar Page!</title>
	</head>
	<body>
		<body style="background-color:cyan;">

		<h1 style ="color:black;"> Hello, and  Welcome to The Bar Page! </h1>
		<p><b> The options of the bar page are as follows: </b></p>
		
		
		<form method="get" action="Top10Beers.jsp">
		<p> See the top 10 beers which are the most popular </p>
		<input style="text" name = "command"/>
		<input type="submit" value="submit" />
		</form>
		
		<p> See the top 10 drinkers who are the largest spenders </p>
		<form method="get" action="Top10Spenders.jsp">
			<input style="text" name = "command"/>
			<input type="submit" value="submit" />
		</form>
		
		
		<p> See the top 5 manufacturers who sell the most beers. </p>
		<form method="get" action="Top5Manufacturer.jsp">
			<input style="text" name = "command"/>
			<input type="submit" value="submit" />
		</form>
		
			<p> See the busiest periods of the day </p>
		<form method="get" action="BarDailyTransactions.jsp">
			<input style="text" name = "graph"/>
			<input type="submit" value="submit" />
		</form>
		
			<p> See the busiest periods of the week </p>
		<form method="get" action="BarWeeklyTransactions.jsp">
			<input style="text" name = "graph"/>
			<input type="submit" value="submit" />
		</form>
	                                      
	</body>
	</html>