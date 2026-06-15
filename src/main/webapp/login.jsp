<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<style>

body{
	margin:0;
	font-family:Arial,sans-serif;
	background:linear-gradient(135deg,#1e3c72,#2a5298);
	height:100vh;
	display:flex;
	justify-content:center;
	align-items:center;
}

.container{
	width:450px;
	background:white;
	padding:40px;
	border-radius:15px;
	box-shadow:0 10px 30px rgba(0,0,0,0.3);
	text-align:center;
}

h2{
	color:#1e3c72;
	margin-bottom:25px;
}

.input-box{
	width:100%;
	padding:12px;
	margin-top:8px;
	margin-bottom:20px;
	border:1px solid #ccc;
	border-radius:6px;
	font-size:15px;
}

.btn{
	width:100%;
	padding:12px;
	background:#007bff;
	color:white;
	border:none;
	border-radius:6px;
	font-size:17px;
	font-weight:bold;
	cursor:pointer;
}

.btn:hover{
	background:#0056b3;
}

.error{
	color:red;
	margin-bottom:15px;
	font-weight:bold;
}

.link{
	display:block;
	margin-top:15px;
	text-decoration:none;
}

</style>

</head>
<body>

<div class="container">

<h2>Login</h2>

<%
String message = (String) request.getAttribute("message");

if(message != null){
%>

<div class="error">
<%= message %>
</div>

<%
}
%>

<form action="login" method="post">

<input
type="email"
name="email"
class="input-box"
placeholder="Enter Email"
required>

<input
type="password"
name="password"
class="input-box"
placeholder="Enter Password"
required>

<input
type="submit"
value="Login"
class="btn">

</form>

<a href="forgotPassword.jsp" class="link">
Forgot Password?
</a>

<a href="index.jsp" class="link">
Back to Homepage
</a>

</div>

</body>
</html>
