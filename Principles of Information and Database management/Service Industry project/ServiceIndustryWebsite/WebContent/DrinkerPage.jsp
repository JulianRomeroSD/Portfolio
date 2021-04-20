<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cs336.pkg.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Drinker Page!</title>
	</head>
	<body>
		<body style="background-color:cyan;">

		<h1 style ="color:black;"> Hello, and  Welcome to The Drinker Page!  </h1>
		<p><b> The options of the Drinker page are as follows:</b></p>
		
		<form method="get" action="DrinkerTransactions.jsp">
		<p> See all his/her transactions ordered by time and grouped by bars. </p>
		<input style="text" name = "command"/>
		<input type="submit" value="submit" />
		
		</form>
		
		<p> See a graph with the beers s/he orders the most </p>
		<form method="get" action="OrderGraphs.jsp">
			<input style="text" name = "graph"/>
			<input type="submit" value="submit" />
			</select> 
		</form>
		
		
		<p> See graphs with his/her spending in different bars, per days of the week </p>
		<form method="get" action="DrinkerWeekGraph.jsp">
			<input style="text" name = "graph"/>
			<input type="submit" value="submit" />
			</select> 
		</form>
		
		<p> See graphs with his/her spending in different bars, per month </p>
		<form method="get" action="DrinkerMonthlyGraph.jsp">
			<input style="text" name = "graph"/>
			<input type="submit" value="submit" />
			</select> 
		</form>
		
	                                      
	</body>
	</html>