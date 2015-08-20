<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ms Mackleberry's Gradebook </title>
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
      <a class="navbar-brand" style=color:red>Ms Mackleberry's Gradebook </a>
    </div>
    <div>
      <ul class="nav navbar-nav" style=font-family:Bookman>
        <li class="active"><a href="GradeBook.jsp"style=color:white><b>Home</b></a></li>
        <li><a href="GradeRecord"style=color:white><b>Grades</b></a></li>
      </ul>
    </div>
  </div>
</nav>
<body>
	<div class="container">
		<div class="jumbotron">
			<h1 align = center><b>Enter your grades</b></h1><br></br>
			<form action="GradeBook" method="post">
				<label style=font-size:20px>Assignment:</label><br></br>
				<input type="text" name="assign"></input>
				<br></br>
				<label style=font-size:20px>Grade:</label><br></br>
				<input type="text" name="grade"></input>
				<br></br>
				<input type="submit" value="Submit"></input>
			</form>
		</div>
	</div>
</body>
</html>