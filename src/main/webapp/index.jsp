<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Library Management System</title>

<style>

*{
	margin:0;
	padding:0;
	box-sizing:border-box;
	font-family:Arial,sans-serif;
}

body{
	height:100vh;
	background:linear-gradient(135deg,#1e3c72,#2a5298);
	display:flex;
	justify-content:center;
	align-items:center;
}

.container{
	width:650px;
	background:white;
	padding:60px;
	border-radius:20px;
	text-align:center;
	box-shadow:0 10px 35px rgba(0,0,0,0.35);
}

h1{
	color:#1e3c72;
	font-size:48px;
	margin-bottom:50px;
}

.btn{
	display:inline-block;
	width:220px;
	padding:16px;
	margin:15px;
	text-decoration:none;
	font-size:22px;
	font-weight:bold;
	border-radius:10px;
	transition:0.3s;
}

.register{
	background:#28a745;
	color:white;
}

.login{
	background:#007bff;
	color:white;
}

.btn:hover{
	transform:scale(1.05);
	opacity:0.9;
}

</style>

</head>

<body>

<div class="container">

<h1>Library Management System</h1>

<a href="studentRegister.jsp"
class="btn register">
Register
</a>

<a href="login.jsp"
class="btn login">
Login
</a>

</div>

</body>
</html>