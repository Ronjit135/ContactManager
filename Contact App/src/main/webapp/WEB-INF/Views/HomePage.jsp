<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  -->

<script type="text/javascript" src="Bootstrap/js/tether.min.js"></script>
<link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="CSS/HomePage.css">
<link rel="stylesheet" href="CSS/w3.css">
<script type="text/javascript" src="Bootstrap/jQuery/jquery_3-2-1.js"></script>
<script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	/* Set the width of the side navigation to 250px */
	function openNav() {
		document.getElementById("mySidenav").style.width = "20%";
		document.getElementById("main").style.margin = "0% 0% 0% 20%";
	}

	/* Set the width of the side navigation to 0 */
	function closeNav() {
		document.getElementById("mySidenav").style.width = "0%";
		document.getElementById("main").style.margin = "0% 0% 0% 0%";
	}
</script>
<title>Contact Manager</title>
</head>
<body>
	<div id="mySidenav" class="sidenav">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<a href="AddContact">Add Contact</a> <a href="SearchContact">Search
			Contact</a> <a href="ViewContacts">View All Contacts</a>
	</div>
	<span style="font-size: 30px; cursor: pointer" onclick="openNav()">&#9776;</span>
	<div id="main">
		<div class="w3-bar w3-border w3-light-grey">
			<a href="Home" class="w3-bar-item w3-button"> Home</a> <a
				href="Logout" class="w3-bar-item w3-button w3-green w3-right">Log
				Out</a> <a href="Profile" class="w3-bar-item w3-button w3-right">Hi,
				${sessionScope.user.name}</a>
		</div>
		<div id="contant">
			
		</div>
	</div>
</body>
</html>