<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Strongheim's Gradebook</title>
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
	align:center;
}
</style>
</head>
<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-header" style="font-family: Bookman">
		<a class="navbar-brand" style="color: red">Prof. Strongheim's
			Gradebook </a>
	</div>
	<div>
		<ul class="nav navbar-nav" style="font-family: Bookman">
			<li><a href="GradeBook.jsp" style="color: white"><b>Home</b></a></li>
			<li class="active"><a href="Reports" style="color: white"><b>Reports</b></a></li>
		</ul>
	</div>
</div>
</nav>
<body>
	${message}
	<div class="col-sm-offset-2 col-sm-10">
		<p>
			<a href="GradeBook.jsp" class="btn btn-primary btn-lg" role="button">Go
				To Home</a>
		</p>
	</div>
</body>
</html>