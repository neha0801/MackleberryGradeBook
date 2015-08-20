<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Grades</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
<style>
body {
	text-align:center;
	font-family: "Edwardian Script ITC";
	font-size: 30px;
	color: black;
	background-color: #a6d2d2;
}
p {
	text-align:center;
	font-family: "Bookman Old Style";
	color: black;
	font-size: 40px;
}
table {
	border-color: #00ffff;
	background-color: grey;
	border-collapse: seperate;
	width:30%;
}

table td {
	border-color: red;
}
</style>
</head>
</head>
<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-header" style="font-family: Bookman">
		<a class="navbar-brand" style="color: red">Ms Mackleberry's
			Gradebook </a>
	</div>
	<div>
		<ul class="nav navbar-nav" style="font-family: Bookman">
			<li><a href="GradeBook.jsp" style="color: white"><b>Home</b></a></li>
			<li class="active"><a href="GradeRecord.jsp"
				style="color: white"><b>Grades</b></a></li>
		</ul>
	</div>
</div>
</nav>
<body>${message}
</body>
</html>