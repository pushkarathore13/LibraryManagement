<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Student Login</title>
</head>
<body>

<h2>Student Login</h2>

<%
String message = (String) request.getAttribute("message");

if(message != null) {
%>
<h3 style="color:red;"><%= message %></h3>
<%
}
%>

<form action="studentLogin" method="post">

	Email:
	<input type="text" name="email">
	<br><br>

	Password:
	<input type="password" name="password">
	<br><br>

	<input type="submit" value="Login">

</form>

<br>

<a href="forgotPassword.jsp">Forgot Password?</a>
<br><br>

<a href="studentRegister.jsp">New Student? Register</a>

</body>
</html>