<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Admin Login</title>
</head>
<body>

<h2>Admin Login</h2>

<%
String message = (String) request.getAttribute("message");

if(message != null) {
%>
<h3 style="color:red;"><%= message %></h3>
<%
}
%>

<form action="adminLogin" method="post">

	Email:
	<input type="text" name="email">
	<br><br>

	Password:
	<input type="password" name="password">
	<br><br>

	<input type="submit" value="Login">

</form>

</body>
</html>