<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Student Registration</title>
</head>
<body>

<h2>Student Registration</h2>

<%
String message = (String) request.getAttribute("message");

if(message != null) {
%>
<h3 style="color:red;"><%= message %></h3>
<%
}
%>

<form action="studentRegister" method="post">

	Name:
	<input type="text" name="name">
	<br><br>

	Email:
	<input type="text" name="email">
	<br><br>

	Mobile:
	<input type="text" name="mobile">
	<br><br>

	Gender:
	<input type="radio" name="gender" value="Male"> Male
	<input type="radio" name="gender" value="Female"> Female
	<br><br>

	Occupation:
	<input type="text" name="occupation">
	<br><br>

	Aadhaar ID:
	<input type="text" name="aadhaar_id">
	<br><br>

	Password:
	<input type="password" name="password">
	<br><br>

	<input type="submit" value="Send Request to Admin">

</form>

<br>

<a href="studentLogin.jsp">Already registered? Login</a>

</body>
</html>