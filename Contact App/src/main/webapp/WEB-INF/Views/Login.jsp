<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 
<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 -->

<script type="text/javascript" src="Bootstrap/js/tether.min.js"></script>
<link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="CSS/Login.css">
<script type="text/javascript" src="Bootstrap/jQuery/jquery_3-2-1.js"></script>
<script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>

<title>Contact Manager</title>
</head>
<body>
	<div class="container">
		<div class="card card-container">
			<h2></h2>
			<h2 class='login_title text-center'>Contact Manager</h2>
			<hr>
			<form action="Login" method="post">
				<div class="imgcontainer">
					<img src="img_avatar2.png" alt="Avatar" class="avatar">
				</div>

				<div class="container">
					<label for="uname"><b>Email</b></label>
					<input type="text" placeholder="Enter Email" name="email" required>
					<label for="password"><b>Password</b></label>
					<input type="password" placeholder="Enter Password" name="password" required>
					<button type="submit">Login</button>
				</div>
				<div class="container" style="background-color: #f1f1f1">
					<button type="button" class="cancelbtn">Cancel</button>
					<span class="psw"><a href="#">Forgot Password?</a></span>
				</div>
			</form>
		</div>
	</div>
</body>
</html>