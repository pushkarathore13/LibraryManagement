<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Forgot Password</title>
</head>
<body>

<h2>Forgot Password</h2>

<%
String message = (String) request.getAttribute("message");

if(message != null) {
%>
<h3 style="color:red;"><%= message %></h3>
<%
}
%>

<form action="forgotPassword" method="post">

	Email:
	<input type="text" name="email">
	<br><br>

	New Password:
	<input type="password" name="newPassword">
	<br><br>

	<input type="submit" value="Reset Password">

</form>

<br>

<a href="studentLogin.jsp">Back to Login</a>

</body>
</html>