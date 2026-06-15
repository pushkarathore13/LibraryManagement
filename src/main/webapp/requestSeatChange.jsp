<%@ page import="in.sp.model.Student" %>

<%@ page language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%
Student student =
(Student) request.getAttribute("student");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Request Seat Change</title>

<style>

body{
	font-family:Arial,sans-serif;
	background:#f4f6f9;
	margin:0;
	padding:0;
}

.container{
	width:500px;
	margin:80px auto;
	background:white;
	padding:30px;
	border-radius:10px;
	box-shadow:0 4px 15px rgba(0,0,0,0.2);
}

h2{
	text-align:center;
	color:#333;
	margin-bottom:20px;
}

.info{
	background:#eef4ff;
	padding:15px;
	border-radius:8px;
	margin-bottom:20px;
}

.input-box{
	width:100%;
	padding:10px;
	font-size:16px;
	border:1px solid #ccc;
	border-radius:5px;
	margin-top:10px;
	margin-bottom:20px;
	box-sizing:border-box;
}

.btn{
	width:100%;
	padding:12px;
	background:#007bff;
	color:white;
	border:none;
	border-radius:5px;
	font-size:16px;
	cursor:pointer;
}

.btn:hover{
	background:#0056b3;
}

.link{
	display:block;
	text-align:center;
	margin-top:20px;
	text-decoration:none;
	font-weight:bold;
}

</style>

</head>

<body>

<div class="container">

<h2>Request Seat Change</h2>

<div class="info">

<p>
<b>Current Seat ID :</b>
<%= student != null ? student.getSeatId() : 0 %>
</p>

<p>
Enter the seat number you want to request.
Admin approval is required before the seat is changed.
</p>

</div>

<form action="requestSeatChange" method="post">

<label>
<b>New Seat ID :</b>
</label>

<input
type="number"
name="requestedSeatId"
class="input-box"
required>

<input
type="submit"
value="Send Request"
class="btn">

</form>

<a href="studentDashboard" class="link">
Back to Dashboard
</a>

</div>

</body>
</html>