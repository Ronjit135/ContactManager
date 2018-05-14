function onLoad() {
	document.getElementById("firstName").value = "";
	document.getElementById("middleName").value = "";
	document.getElementById("lastName").value = "";
	document.getElementById("nickName").value = "";
	document.getElementById("contryCodeId").value = -1;
	document.getElementById("mobileNo").value = "";
	document.getElementById("mobileNo").disabled = true;
	document.getElementById("addMobile").disabled = true;
	document.getElementById("emailId").value = "";
	document.getElementById("addressBox").value = "";
	document.getElementById("pincode").value = "";
	document.getElementById("country").value = "";
	document.getElementById("state").value = "";
	document.getElementById("district").value = "";
	document.getElementById("pinLocation").value = -1;
	document.getElementById("date").value = "";
	document.getElementById("remarks").value = "";
	clearPinLocation();
}

function clearPinLocation(){
	var pinLocation = document.getElementById("pinLocation");
	var len = pinLocation.options.length;
	while(--len > 0){
		pinLocation.options[len] = null;
	}
	document.getElementById("country").value = "";
	document.getElementById("state").value = "";
	document.getElementById("district").value = "";
	document.getElementById("pinLocation").value = -1;
}

function sendRequest(method, url, content = '') {
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("POST", url, true);
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			response(this, method);
		}
	};
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(content);
}

function response(data, method) {
	if (method == "AddMobile") {
		addMobile(data);
	} else if (method == "ZipCode"){
		addZipData(data);
	} else if (method == "AddAddress"){
		addAddressData(data);
	} else if (method == "AddEmail"){
		addEmailData(data);
	} else if (method == "AddDate"){
		addDateData(data);
	} else if (method == "AddContact"){
		addContactData(data);
	} else if (method == "DeleteMobile"){
		deleteMobileData(data);
	} else if (method == "DeleteAddress"){
		deleteAddressData(data);
	} else if (method == "DeleteEmail"){
		deleteEmailData(data);
	} else if (method == "DeleteDate"){
		deleteDateData(data);
	} else {
		console.log(data);
	}
}

function validate(){
	var firstName = document.getElementById("firstName").value;
	var middleName = document.getElementById("middleName").value;
	var lastName = document.getElementById("lastName").value;
	var nickName = document.getElementById("nickName").value;
	if(firstName == ''){
		alert("Enter First Name");
		return false;
	} else if (lastName == ''){
		alert("Enter Last Name");
		return false;
	} else {
		sendRequest("AddContact", "AddContact", "firstName="+firstName+"&middleName="+middleName+"&lastName="+lastName+"&nickName="+nickName);
		return true;
	}
}

function addContactData(data){
	if(data.response){
		alert("Contact Added..."+data.response);
	} else {
		alert("Something Went Wrong..."+data.response);
	}
	window.location.href = "AddContact";
}

function handleCountryCodeChange() {
	var countryCode = document.getElementById("contryCodeId").value;
	if (countryCode != -1) {
		document.getElementById("mobileNo").disabled = false;
		document.getElementById("addMobile").disabled = false;
	} else {
		document.getElementById("mobileNo").disabled = true;
		document.getElementById("addMobile").disabled = true;
	}
}

function addMobileNo() {
	var countryCodeId = document.getElementById("contryCodeId").value;
	var mobileNo = document.getElementById("mobileNo").value;
	if(mobileNo == ""){
		alert("Enter Valid Mobile No");
		document.getElementById("mobileNo").value = "";
	} else{
		var content = "countryCodeId=" + countryCodeId + "&mobileNo=" + mobileNo;
		sendRequest("AddMobile", "AddMobile", content);	
	}
}

function addMobile(data) {
	var mobileData = JSON.parse(data.response);
	var mobileDiv = document.getElementById("addedMobiles");
	var row = document.createElement("div");
	row.className="row";
	var div1 = document.createElement("div");
	div1.className="col-sm-1 right";
	var radio = document.createElement("input");
	radio.type = "radio";
	radio.name = "addedMobile";
	radio.value = mobileData.mobileNo;
	var div2 = document.createElement("div");
	div2.className="col-sm-11";
	var mobileNode = document.createTextNode("+" + mobileData.countryCode + " " + mobileData.mobileNo);
	div1.appendChild(radio);
	div2.appendChild(mobileNode);
	row.appendChild(div1);
	row.appendChild(div2);
	mobileDiv.appendChild(row);
	onLoad();
}

function deleteMobile() {
	var mobileDiv = document.getElementsByName("addedMobile");
	var mobileNo;
	if(mobileDiv.length == 0){
		alert("No Mobile Added...");
	} else {
		for (var i = 0; i < mobileDiv.length; i++) {
			var mobile = mobileDiv[i];
			if (mobile.checked) {
				mobileNo = mobile.value;
				break;
			}
		}
		if (mobileNo != null){
			content = "mobileNo=" + mobileNo;
			sendRequest("DeleteMobile","DeleteMobile",content);
		}else{
			alert("No Mobile No Selected...");
		}
	}
}

function deleteMobileData(data){
	var mobileData = JSON.parse(data.response);
	var mobileNo = mobileData.mobileNo;
	var mobileDiv = document.getElementsByName("addedMobile");
	for (var i = 0; i < mobileDiv.length; i++) {
		var mobile = mobileDiv[i];
		if (mobile.value == mobileNo) {
			mobile.parentNode.parentNode.remove();
		}
	}
}

function checkZipCode() {
	var zipCode = document.getElementById("pincode").value;
	if (zipCode == "") {
		alert("Enter Valid Pincode");
	} else {
		var content = "zipCode=" + zipCode;
		sendRequest("ZipCode","CheckZipCode",content);
	}
}

function addZipData(data){
	clearPinLocation();
	var zipData = JSON.parse(data.response);
	var zipList = zipData.zipLocationList;
	var districtName = null;
	var stateName = null;
	var countryName = null;
	if(zipList!=null){
		var zipLocationElement = document.getElementById("pinLocation");
		for (var i = 0; i < zipList.length; i++) {
			var zip = zipList[i];
			var option = document.createElement("option");
			option.value = zip.zipId;
			option.appendChild(document.createTextNode(zip.zipLocation));
			zipLocationElement.appendChild(option);
			districtName = zip.district.districtName;
			stateName = zip.district.state.stateName;
			countryName = zip.district.state.country.countryName;
		}
		document.getElementById("district").value = districtName;
		document.getElementById("state").value = stateName;
		document.getElementById("country").value = countryName;
	} else {
		alert("No Data Found...");
	}
}

function addAddress() {
	var addressText = document.getElementById("addressBox").value;
	var zipCode = document.getElementById("pinLocation").value;
	if(addressText == ""){
		alert("Input Address");
	} else if (zipCode == -1) {
		alert("Input Pincode");
	} else {
		var content = "addressText=" + addressText + "&zipLocation=" + zipCode;
		sendRequest("AddAddress","AddAddress",content);
	}
}

function addAddressData(data){
	var addressData = JSON.parse(data.response);
	var address = addressData.formattedAddress;
	var addressDiv = document.getElementById("addedAddresses");
	var row = document.createElement("div");
	row.className="row";
	var div1 = document.createElement("div");
	div1.className="col-sm-1 right";
	var radio = document.createElement("input");
	radio.type = "radio";
	radio.name = "addedAddress";
	radio.value = address;
	var div2 = document.createElement("div");
	div2.className="col-sm-11";
	var addressNode = document.createTextNode(address);
	div1.appendChild(radio);
	div2.appendChild(addressNode);
	row.append(div1);
	row.append(div2);
	addressDiv.append(row);
	onLoad();
}

function deleteAddress(){
	var addressDiv = document.getElementsByName("addedAddress");
	var addressText;
	if(addressDiv.length == 0){
		alert("No Address Added...");
	} else {
		for (var i = 0; i < addressDiv.length; i++) {
			var add = addressDiv[i];
			if (add.checked) {
				addressText = add.value;
				break;
			}
		}
		if (address != null){
			content = "addressText=" + addressText;
			sendRequest("DeleteAddress","DeleteAddress",content);
		}else{
			alert("No Address Selected...");
		}
	}
}

function deleteAddressData(data){
	console.log(data.response);
	if(data.response != null){
		var addressData = JSON.parse(data.response);
		var addressText = addressData.address;
		var addressDiv = document.getElementsByName("addedAddress");
		for (var i = 0; i < addressDiv.length; i++) {
			var address = addressDiv[i];
			if (address.value == addressText) {
				address.parentNode.parentNode.remove();
			}
		}
	}
}

function addEmail(){
	var email = document.getElementById("emailId").value;
	var pattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if (email == '' || !email.match(pattern)){
		alert("Enter Valid Email");
	} else{
		var content = "email=" + email;
		sendRequest("AddEmail",	"AddEmail",content);
	}
}

function addEmailData(data){
	var emailData = JSON.parse(data.response);
	var email = emailData.email;
	var emailDiv = document.getElementById("addedEmails");
	var row = document.createElement("div");
	row.className="row";
	var div1 = document.createElement("div");
	div1.className="col-sm-1 right";
	var radio = document.createElement("input");
	radio.type = "radio";
	radio.name = "addedEmail";
	var div2 = document.createElement("div");
	div2.className="col-sm-11";
	var emailNode = document.createTextNode(email);
	div1.appendChild(radio);
	div2.appendChild(emailNode);
	row.append(div1);
	row.append(div2);
	emailDiv.append(row);
	onLoad();
}

function deleteEmail(){
	
}

function deleteEmailData(data){
	
}

function addDate(){
	var date = document.getElementById("date").value;
	var remarks = document.getElementById("remarks").value;
	if(date == ''){
		alert("Input Date");
	} else if (remarks == ''){
		alert("Input Remarks");	
	} else {
		var content = "date=" + date + "&remarks=" + remarks;
		sendRequest("AddDate","AddDate",content);
	}
	
}

function addDateData(data){
	var dateData = JSON.parse(data.response);
	var date = dateData.date;
	var remarks = dateData.remarks;
	var dateDiv = document.getElementById("addedDates");
	var row = document.createElement("div");
	row.className="row";
	var div1 = document.createElement("div");
	div1.className="col-sm-1 right";
	var radio = document.createElement("input");
	radio.type = "radio";
	radio.name = "addedDates";
	var div2 = document.createElement("div");
	div2.className="col-sm-11";
	var dateNode = document.createTextNode(date + " - " + remarks);
	div1.appendChild(radio);
	div2.appendChild(dateNode);
	row.append(div1);
	row.append(div2);
	dateDiv.append(row);
	onLoad();
}

function deleteDate(){
	
}

function deleteDateData(data){
	
}