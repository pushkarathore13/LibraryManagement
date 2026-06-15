<%@ page import="in.sp.model.Student" %>

<%@ page language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%
Student student =
(Student) request.getAttribute("student");

String message =
(String) request.getAttribute("message");

String reason = "";

if(message != null){
	reason = message.toLowerCase();
}

boolean nameRejected = reason.contains("name");
boolean mobileRejected = reason.contains("mobile");
boolean genderRejected = reason.contains("gender");
boolean occupationRejected = reason.contains("occupation");
boolean aadhaarRejected = reason.contains("aadhaar");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Register</title>
</head>
<body>

<h2>Student Registration</h2>

<%
if(message != null){
%>

<h3 style="color:red;">
<%= message %>
</h3>

<%
}
%>

<form action="studentRegister" method="post">

Name:
<input type="text" name="name"
value="<%= student != null && !nameRejected ? student.getName() : "" %>"
required>

<br><br>

Email:
<input type="email" name="email"
value="<%= student != null ? student.getEmail() : "" %>"
<%= student != null ? "readonly" : "" %>
required>

<br><br>

Mobile:
<input type="text" name="mobile"
value="<%= student != null && !mobileRejected ? student.getMobile() : "" %>"
required>

<br><br>

Gender:
<select name="gender" required>
	<option value="">Select Gender</option>

	<option value="Male"
	<%= student != null && !genderRejected && "Male".equals(student.getGender()) ? "selected" : "" %>>
	Male
	</option>

	<option value="Female"
	<%= student != null && !genderRejected && "Female".equals(student.getGender()) ? "selected" : "" %>>
	Female
	</option>

	<option value="Other"
	<%= student != null && !genderRejected && "Other".equals(student.getGender()) ? "selected" : "" %>>
	Other
	</option>
</select>

<br><br>

Occupation:
<input type="text" name="occupation"
value="<%= student != null && !occupationRejected ? student.getOccupation() : "" %>"
required>

<br><br>

Aadhaar ID:
<input type="text" name="aadhaar_id"
value="<%= student != null && !aadhaarRejected ? student.getAadhaarId() : "" %>"
required>

<br><br>

Password:
<input type="password" name="password" required>

<br><br>

<input type="submit" value="Register">

</form>

<br>

<a href="index.jsp">Back to Homepage</a>

</body>
</html>