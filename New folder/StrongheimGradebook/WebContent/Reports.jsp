<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Strongheim's Reports</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
body {
	font-family: "Bookman Old Style";
	color: black;
	background-color: #a6d2d2;
}
</style>
</head>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
      <div class="navbar-header"style=font-family:Bookman>
      <a class="navbar-brand" style=color:red>Prof. Strongheim's Gradebook  </a>
    </div>
    <div>
      <ul class="nav navbar-nav" style=font-family:Bookman>
        <li ><a href="GradeBook.jsp"style=color:white><b>Home</b></a></li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Reports<span class="caret"></span></a>
          <ul class="dropdown-menu">
	        <li><a href="Reports?reportType=A" style=color:black><b>All assignments by a student</b></a></li>
	        <li><a href="Reports?reportType=B" style=color:black><b>All assignments of a particular type by anyone</b></a></li>
	        <li><a href="Reports?reportType=C" style=color:black><b>All assignments of a particular type by a particular student</b></a></li>
	        <li><a href="Reports?reportType=D" style=color:black><b>The average for a student</b></a></li>
	        <li><a href="Reports?reportType=E" style=color:black><b>The average for a student by assignment type</b></a></li>
	        <li><a href="Reports?reportType=F" style=color:black><b>Highest and lowest grade for a particular assignment type </b></a></li>
          </ul>
        </li>
        
      </ul>
    </div>
  </div>
</nav>
<body>
		<div class="container">
		<div class="jumbotron">
			<h1 align=center>
				<b>Strongheim's Grade Book </b>
			</h1>
			<br></br>
			<form action=Reports method="post">
				<label style="font-size: 20px">Student Id:</label> <input
					type="text" name="studentId"></input><br></br> 
				<label
					style="font-size: 20px">Assignment Type</label> 
				<input type="text"
					name="assignType"></input><br></br>
			
				<input type="submit" value="Get Reports"></input>
			</form>
			 ${message}
		</div>
	</div>
</body>
</html>