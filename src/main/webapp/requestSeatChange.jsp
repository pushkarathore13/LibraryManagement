<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

if(session.getAttribute("student_email") == null){
	response.sendRedirect("login.jsp");
	return;
}
%>
<%@ page import="java.util.List" %>
<%@ page import="in.sp.model.Student" %>
<%@ page import="in.sp.model.Seat" %>

<%@ page language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%
Student student =
(Student) request.getAttribute("student");

List<Seat> seats =
(List<Seat>) request.getAttribute("seats");

String message =
(String) request.getAttribute("message");
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
	margin:20px;
}

.container{
	background:white;
	padding:25px;
	border-radius:12px;
	box-shadow:0 5px 20px rgba(0,0,0,0.12);
	max-width:1000px;
	margin:auto;
	text-align:center;
}

.info{
	background:#eef4ff;
	padding:15px;
	border-radius:10px;
	margin-bottom:20px;
	text-align:left;
}

.error{
	color:red;
	font-weight:bold;
}

.legend{
	margin-top:20px;
	margin-bottom:20px;
	text-align:center;
}

.legend-item{
	display:inline-flex;
	align-items:center;
	margin-right:25px;
	font-weight:bold;
}

.legend-seat{
	width:40px;
	height:30px;
	border-radius:8px;
	margin-right:8px;
	color:white;
	text-align:center;
	line-height:30px;
	font-weight:bold;
	border:1px solid #333;
}

.available-color{
	background:#007bff;
}

.occupied-color{
	background:#dc3545;
}

.current-color{
	background:#6c757d;
}

.selected-color{
	background:#28a745;
}

.floor-title{
	margin-top:25px;
	margin-bottom:10px;
	text-align:center;
}

.library-block{
	margin:0 auto 25px auto;
	width:800px;
}

.seat-row{
	display:grid;
	grid-template-columns:repeat(15, 45px);
	gap:6px;
	margin-bottom:8px;
	justify-content:center;
}

.table-row{
	width:789px;
	height:32px;
	line-height:32px;
	background:#dcdcdc;
	border:1px solid #aaa;
	border-radius:8px;
	text-align:center;
	font-weight:bold;
	color:#333;
	margin-bottom:8px;
}

.seat{
	width:45px;
	height:32px;
	text-align:center;
	line-height:32px;
	border-radius:7px;
	font-size:8px;
	font-weight:bold;
	border:1px solid #222;
}

.available{
	background:#007bff;
	color:white;
	cursor:pointer;
}

.occupied{
	background:#dc3545;
	color:white;
	cursor:not-allowed;
}

.current{
	background:#6c757d;
	color:white;
	cursor:not-allowed;
}

.seat input{
	display:none;
}

.seat span{
	display:block;
	border-radius:7px;
}

.seat input:checked + span{
	background:#28a745;
	color:white;
}

.btn{
	padding:10px 20px;
	background:#007bff;
	color:white;
	border:none;
	border-radius:6px;
	cursor:pointer;
	font-size:15px;
}

.link{
	display:inline-block;
	margin-top:20px;
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
Select an available seat below. Your request will be sent to admin for approval.
</p>

<%
if(message != null){
%>

<p class="error">
<%= message %>
</p>

<%
}
%>

</div>

<div class="legend">

<span class="legend-item">
<span class="legend-seat available-color">A</span>
Available
</span>

<span class="legend-item">
<span class="legend-seat occupied-color">O</span>
Occupied
</span>

<span class="legend-item">
<span class="legend-seat current-color">C</span>
Current Seat
</span>

<span class="legend-item">
<span class="legend-seat selected-color">S</span>
Selected
</span>

</div>

<form action="requestSeatChange" method="post">

<%
if(seats != null && !seats.isEmpty()){

int currentFloor = 0;

for(int i = 0; i < seats.size(); i = i + 30){

	Seat firstSeat = seats.get(i);

	if(currentFloor != firstSeat.getFloorNo()){

		currentFloor = firstSeat.getFloorNo();
%>

<div class="floor-title">
<h3>Floor <%= currentFloor %></h3>
</div>

<%
	}
%>

<div class="library-block">

<div class="seat-row">

<%
	for(int j = i; j < i + 15 && j < seats.size(); j++){

		Seat seat = seats.get(j);

		if(seat.getFloorNo() != currentFloor){
			break;
		}

		if(student != null && seat.getSeatId() == student.getSeatId()){
%>

<div class="seat current">
<%= seat.getSeatNumber() %>
</div>

<%
		}else if(seat.isOccupied()){
%>

<div class="seat occupied">
<%= seat.getSeatNumber() %>
</div>

<%
		}else{
%>

<label class="seat available">
<input type="radio" name="requestedSeatId" value="<%= seat.getSeatId() %>" required>
<span><%= seat.getSeatNumber() %></span>
</label>

<%
		}
	}
%>

</div>

<div class="table-row">
TABLE
</div>

<div class="seat-row">

<%
	for(int j = i + 29; j >= i + 15; j--){

		if(j < 0 || j >= seats.size()){
			continue;
		}

		Seat seat = seats.get(j);

		if(seat.getFloorNo() != currentFloor){
			continue;
		}

		if(student != null && seat.getSeatId() == student.getSeatId()){
%>

<div class="seat current">
<%= seat.getSeatNumber() %>
</div>

<%
		}else if(seat.isOccupied()){
%>

<div class="seat occupied">
<%= seat.getSeatNumber() %>
</div>

<%
		}else{
%>

<label class="seat available">
<input type="radio" name="requestedSeatId" value="<%= seat.getSeatId() %>" required>
<span><%= seat.getSeatNumber() %></span>
</label>

<%
		}
	}
%>

</div>

</div>

<%
}
}else{
%>

<p>No seats found.</p>

<%
}
%>

<br>

<input type="submit" value="Send Seat Change Request" class="btn">

</form>

<a href="studentDashboard" class="link">
Back to Dashboard
</a>

</div>

</body>
</html>