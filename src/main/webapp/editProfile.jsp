<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Edit Profile</title>
</head>
<body>

<h2>Edit Profile</h2>

<%
String message = (String) request.getAttribute("message");

if(message != null) {
%>
<h3 style="color:red;"><%= message %></h3>
<%
}
%>

<form action="updateProfile" method="post">

	Name:
	<input type="text" name="name">
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

	<input type="submit" value="Update Profile">

</form>

<br>

<a href="studentDashboard">Back to Dashboard</a>

</body>
</html>