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
<%@ page import="in.sp.model.Seat" %>
<%@ page import="in.sp.model.Student" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%
Student student =
(Student) request.getAttribute("student");

Boolean activeMember =
(Boolean) request.getAttribute("activeMember");

Boolean renewAllowed =
(Boolean) request.getAttribute("renewAllowed");

Boolean seatChangePending =
(Boolean) request.getAttribute("seatChangePending");

List<Seat> seats =
(List<Seat>) request.getAttribute("availableSeats");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Dashboard</title>

<style>

body{
	font-family:Arial,sans-serif;
	margin:20px;
	background:#f4f6f9;
}

.dashboard{
	background:white;
	padding:25px;
	border-radius:12px;
	box-shadow:0 5px 20px rgba(0,0,0,0.12);
	max-width:900px;
	margin:auto;
	text-align:center;
}

.info-box{
	background:#eef4ff;
	padding:15px;
	border-radius:10px;
	margin-bottom:20px;
	text-align:left;
}

.pending-message{
	color:#ff9800;
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

.selected-color{
	background:#28a745;
}

.screen{
	width:70%;
	margin:20px auto;
	padding:10px;
	text-align:center;
	background:#ddd;
	border-radius:25px;
	font-weight:bold;
	letter-spacing:1px;
}

.floor-title{
	margin-top:25px;
	margin-bottom:10px;
	color:#222;
	text-align:center;
}

.library-block{
	margin:0 auto 25px auto;
	width:800px;
}

.seat-row{
	display:grid;
	grid-template-columns:repeat(15, 45px);
	gap:8px;
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
	width:50px;
	height:35px;
	text-align:center;
	line-height:35px;
	border-radius:8px;
	font-size:9px;
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

.seat input{
	display:none;
}

.seat span{
	display:block;
	border-radius:8px;
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

.shift-box{
	margin-top:20px;
	margin-bottom:20px;
}

.shift-box select{
	padding:8px;
	border-radius:6px;
	border:1px solid #999;
	font-size:15px;
}

.links{
	margin-top:20px;
	text-align:center;
}

.links a{
	display:inline-block;
	margin-right:15px;
	margin-top:15px;
}

</style>

</head>

<body>

<div class="dashboard">

<h2>Student Dashboard</h2>

<div class="info-box">

<h3>
Welcome :
<%= student != null ? student.getName() : "" %>
</h3>

<p>
<b>Email :</b>
<%= student != null ? student.getEmail() : "" %>
</p>

<%
if(activeMember != null && activeMember && student.getSeatId() > 0){
%>

<p>
<b>Membership Start :</b>
<%= student.getMembershipStart() %>
</p>

<p>
<b>Membership End :</b>
<%= student.getMembershipEnd() %>
</p>

<p>
<b>Current Seat ID :</b>
<%= student.getSeatId() %>
</p>

<p>
<b>Shift :</b>
<%= student.getShiftName() != null ? student.getShiftName() : "" %>
</p>

<%
}
%>

<%
if(seatChangePending != null && seatChangePending){
%>

<p class="pending-message">
Seat Change Status : Waiting for admin approval
</p>

<%
}
%>

</div>

<%
if(activeMember == null || !activeMember || student.getSeatId() == 0){
%>

<%
if(renewAllowed != null && renewAllowed){
%>

<h3>Your membership has expired recently.</h3>

<p>
You can renew your same seat within 5 days.
</p>

<form action="renewMembership" method="post">
<input type="submit" value="Renew Same Seat" class="btn">
</form>

<br>

<%
}
%>

<h3>Select Your Seat</h3>

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
<span class="legend-seat selected-color">S</span>
Selected
</span>

</div>

<div class="screen">
LIBRARY SEATING AREA
</div>

<form action="selectSeat" method="post">

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

		if(seat.isOccupied()){
%>

<div class="seat occupied">
<%= seat.getSeatNumber() %>
</div>

<%
		}else{
%>

<label class="seat available">
<input type="radio" name="seatId" value="<%= seat.getSeatId() %>" required>
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

		if(seat.isOccupied()){
%>

<div class="seat occupied">
<%= seat.getSeatNumber() %>
</div>

<%
		}else{
%>

<label class="seat available">
<input type="radio" name="seatId" value="<%= seat.getSeatId() %>" required>
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

<p>No seats available right now.</p>

<%
}
%>

<div class="shift-box">

<label>
<b>Select Shift :</b>
</label>

<select name="shiftName" required>
	<option value="">Select Shift</option>
	<option value="Morning">Morning</option>
	<option value="Afternoon">Afternoon</option>
	<option value="Evening">Evening</option>
	<option value="Full Day">Full Day</option>
</select>

</div>

<input type="submit" value="Select Seat" class="btn">

</form>

<%
}
%>

<div class="links">

<a href="changePassword.jsp">Change Password</a>

<a href="editProfile.jsp">Edit Profile</a>

<%
if(seatChangePending == null || !seatChangePending){
%>

<a href="requestSeatChangePage">Request Seat Change</a>

<%
}
%>

<a href="index.jsp">Back to Homepage</a>

<a href="logout">Logout</a>

</div>

</div>

</body>
</html>