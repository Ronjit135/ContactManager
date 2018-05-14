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
<link rel="stylesheet" href="CSS/AddContact.css">
<link rel="stylesheet" href="CSS/w3.css">
<script type="text/javascript" src="Bootstrap/jQuery/jquery_3-2-1.js"></script>
<script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="JS/AddContact.js"></script>
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
<body onload="onLoad()">
	<div id="mySidenav" class="sidenav">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<a href="AddContact">Add Contact</a> <a href="SearchContact">Search
			Contact</a> <a href="ViewContacts">View All Contacts</a>
	</div>
	<span style="font-size: 30px; cursor: pointer" onclick="openNav()">&#9776;</span>
	<div id="main" class="container-fluid">
		<div class="w3-bar w3-border w3-light-grey">
			<a href="Home" class="w3-bar-item w3-button"> Home</a> <a
				href="Logout" class="w3-bar-item w3-button w3-green w3-right">Log
				Out</a> <a href="Profile" class="w3-bar-item w3-button w3-right">Hi,
				${sessionScope.user.name}</a>
		</div>
	</div>
	<div id="content" class="container">
		<div class="row">
			<div class="col-sm-4" id="primaryDetails">
				<!-- <form class="form-horizontal" action="AddContact" method="post" id="form"> -->
					<!-- First Name -->
					<div class="form-group">
						<label for="firstName">First Name :</label> <input type="text"
							class="form-control" id="firstName" name="firstName"
							placeholder="Enter First Name">
					</div>
					<!-- Middle Name -->
					<div class="form-group">
						<label for="middleName">Middle Name :</label> <input type="text"
							class="form-control" id="middleName" name="middleName"
							placeholder="Enter Middle Name">
					</div>
					<!-- Last Name -->
					<div class="form-group">
						<label for="lastName">Last Name :</label> <input type="text"
							class="form-control" id="lastName" name="lastName"
							placeholder="Enter Last Name">
					</div>
					<div class="form-group">
						<label for="nickName">Nickname :</label> <input type="text"
							class="form-control" id="nickName" name="nickName"
							placeholder="Enter Nickname">
					</div>
					<!-- Add Contact -->
					<button class="btn btn-default" onclick="validate();">Add
						Contact</button>
				<!-- </form> -->
			</div>
			<div class="col-sm-8" id="otherDetails">
				<div class="row" id="mobile">
					<div class="col-sm-2">
						<p class="right">Mobile No :</p>
					</div>
					<div class="col-sm-4">
						<select class="form-control" id="contryCodeId"
							onchange="handleCountryCodeChange();">
							<option value="-1">- Select Country Code -</option>
							<c:forEach var="countryCode" items="${countryCodeList}">
								<option value="${countryCode.countryCodeId}">${countryCode.countryName}
									(+${countryCode.countryCode})</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-5">
						<input type="number" class="form-control" id="mobileNo"
							name="mobileNo" min="0" placeholder="Enter Mobile No">
					</div>
					<div class="col-sm-1">
						<button type="button" id="addMobile" class="btn btn-default"
							onclick="addMobileNo();">Add Mobile</button>
					</div>
				</div>
				<hr>
				<div class="row" id="email">
					<div class="col-sm-2">
						<p class="right">Email ID :</p>
					</div>
					<div class="col-sm-9">
						<input type="email" class="form-control" id="emailId"
							name="emailId" placeholder="Enter Email ID">
					</div>
					<div class="col-sm-1">
						<button type="button" id="addEmail" class="btn btn-default"
							onclick="addEmail();">Add Email</button>
					</div>
				</div>
				<hr>
				<div class="row" id="address">
					<div class="col-sm-2">
						<p class="right">Address :</p>
					</div>
					<div class="col-sm-9">
						<textarea class="form-control" rows="5" id="addressBox"></textarea>
					</div>
					<div class="col-sm-1">
						<button type="button" id="addAddress" class="btn btn-default"
							onclick="addAddress();">Add Address</button>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<p class="right">Pincode :</p>
					</div>
					<div class="col-sm-4">
						<input type="number" class="form-control" id="pincode"
							name="pincode" min="0" placeholder="Enter Pincode">
					</div>
					<div class="col-sm-6">
						<button type="button" id="checkZipcode" class="btn btn-default"
							onclick="checkZipCode();">Check Pincode</button>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<p class="right">Country :</p>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="country"
							name="country" disabled="disabled">
					</div>
					<div class="col-sm-2">
						<p class="right">State :</p>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="state" name="state"
							disabled="disabled">
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<p class="right">District :</p>
					</div>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="district"
							name="district" disabled="disabled">
					</div>
					<div class="col-sm-2">
						<p class="right">Location :</p>
					</div>
					<div class="col-sm-4">
						<select id="pinLocation" class="form-control" name="pinLocation">
							<option value="-1">-- Select Location --</option>
						</select>
						<!-- <input type="text" class="form-control" id="zipCode"
							name="zipCode" disabled="disabled"> -->
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-2">
						<p class="right">Date :</p>
					</div>
					<div class="col-sm-3">
						<input type="date" class="form-control" id="date" name="date">
					</div>
					<div class="col-sm-2">
						<p class="right">Remarks :</p>
					</div>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="remarks"
							name="remarks">
					</div>
					<div class="col-sm-2">
						<button type="button" id="addDate" class="btn btn-default"
							onclick="addDate();">Add Date</button>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<hr>
		<div class="row" id="addedDetails">
			<div class="col-sm-4" id="addedMobiles">
				<div class="row">
					<div class="col-sm-6">
						<p>Added Mobiles:</p>
					</div>
					<div class="col-sm-6">
						<button type="button" id="deleteMobile" class="btn btn-default"
							onclick="deleteMobile();">Delete Mobile</button>
					</div>
				</div>
			</div>
			<div class="col-sm-8" id="addedAddresses">
				<div class="row">
					<div class="col-sm-6">
						<p>Added Addresses:</p>
					</div>
					<div class="col-sm-6">
						<button type="button" id="deleteAddress" class="btn btn-default"
							onclick="deleteAddress();">Delete Address</button>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="row" id="addedDetails">
			<div class="col-sm-4" id="addedDates">
				<div class="row">
					<div class="col-sm-6">
						<p>Added Dates:</p>
					</div>
					<div class="col-sm-6">
						<button type="button" id="deleteDate" class="btn btn-default"
							onclick="deleteDate();">Delete Date</button>
					</div>
				</div>
			</div>
			<div class="col-sm-8" id="addedEmails">
				<div class="row">
					<div class="col-sm-6">
						<p>Added Emails:</p>
					</div>
					<div class="col-sm-6">
						<button type="button" id="deleteEmail" class="btn btn-default"
							onclick="deleteEmail();">Delete Email</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>