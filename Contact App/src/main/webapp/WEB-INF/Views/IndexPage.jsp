<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>

<!-- Basic Page Needs
  ================================================== -->
<meta charset="utf-8">
<title>Contact Manager</title>
<meta name="description" content="Online Chat App">
<meta name="author" content="Bakudo & Bakudi">

<!-- Mobile Specific Metas
  ================================================== -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
  ================================================== -->
<link rel="stylesheet" href="CSS/IndexCSS.css">
<link rel="stylesheet" href="CSS/w3.css">
<script type="text/javascript" src="Bootstrap/jQuery/jquery_3-2-1.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						// constants
						var SHOW_CLASS = 'show', HIDE_CLASS = 'hide', ACTIVE_CLASS = 'active';

						$('.tabs').on(
								'click',
								'li a',
								function(e) {
									e.preventDefault();
									var $tab = $(this), $href = $tab
											.attr('href');

									$('.active').removeClass(ACTIVE_CLASS);
									$tab.addClass(ACTIVE_CLASS);

									$('.show').removeClass(SHOW_CLASS)
											.addClass(HIDE_CLASS).hide();

									$($href).removeClass(HIDE_CLASS).addClass(
											SHOW_CLASS).hide().fadeIn(550);
								});
					});
</script>
<!--//[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>
<body>
	<div class="container center">
		<div class="w3-container w3-center">
			<h2 id="logo">
				Contact Manager <small>&#9400; Jay</small>
			</h2>
		</div>
		<div class="container">
			<div class="flat-form">
				<ul class="tabs">
					<li><a href="#login" class="active">Login</a></li>
					<li><a href="#register">Register</a></li>
					<li><a href="#reset">Reset Password</a></li>
				</ul>
				<div id="login" class="form-action show">
					<h1>Login</h1>
					<p></p>
					<form action="Login" method="post">
						<ul>
							<li><input type="text" placeholder="Email" name="email" required="required"/></li>
							<li><input type="password" placeholder="Password" name="password" required="required"/></li>
							<li><input type="submit" value="Login" class="button" /></li>
						</ul>
					</form>
				</div>
				<!--/#login.form-action-->
				<div id="register" class="form-action hide">
					<h1>Register</h1>
					<p></p>
					<form action="Register" method="post">
						<ul>
							<li><input type="text" placeholder="Email" name="email" required="required"/></li>
							<li><input type="password" placeholder="Password" name="password" required="required"/></li>
							<li><input type="submit" value="Sign Up" class="button" /></li>
						</ul>
					</form>
				</div>
				<!--/#register.form-action-->
				<div id="reset" class="form-action hide">
					<h1>Reset Password</h1>
					<p></p>
					<form action="ResetPassword" method="post">
						<ul>
							<li><input type="text" placeholder="Email" name="email" required="required"/></li>
							<li><input type="submit" value="Reset" class="button"/></li>
						</ul>
					</form>
				</div>
				<!--/#register.form-action-->
			</div>
		</div>
	</div>
</body>
</html>