package com.app.contactApp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.contactApp.common.utils.Constants;
import com.app.contactApp.common.utils.ContactAppUtils;
import com.app.contactApp.data.entity.Address;
import com.app.contactApp.data.entity.AddressType;
import com.app.contactApp.data.entity.Contact;
import com.app.contactApp.data.entity.ContactDate;
import com.app.contactApp.data.entity.ContactEmail;
import com.app.contactApp.data.entity.Country;
import com.app.contactApp.data.entity.CountryCode;
import com.app.contactApp.data.entity.District;
import com.app.contactApp.data.entity.Mobile;
import com.app.contactApp.data.entity.State;
import com.app.contactApp.data.entity.User;
import com.app.contactApp.data.entity.ZipCode;
import com.app.contactApp.service.ifc.ServiceIfc;

@Controller
public class MainController {
	@Autowired
	ServiceIfc service;

	@PostConstruct
	public void initData() {
		List<AddressType> addressTypeList = service.getAllAddressTypes();
		List<Country> countryList = service.getAllCountries();
		List<CountryCode> countryCodeList = service.getAllCoutryCodes();
		ContactAppUtils.initData(addressTypeList, countryList, countryCodeList);
	}

	@GetMapping("/")
	public String showIndex() {
		return "IndexPage";
	}

	@PostMapping("/Login")
	public String validateLogin(String email, String password, HttpSession session) {
		User user = new User(email, password);
		user = service.validateUser(user);
		if (user != null) {
			session.setAttribute(Constants.CURRENT_USER, user);
			return "redirect:/Home";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/Home")
	public String showHome(HttpSession session, ModelMap map) {
		if (!ContactAppUtils.isAuthorized(session))
			return "redirect:/";
		map.addAttribute(Constants.COUNTRY_LIST, ContactAppUtils.getCountryList());
		map.addAttribute("contact", service.getContact(new Long(1)));
		return "HomePage";
	}

	@GetMapping("/AddContact")
	public String showAddContact(HttpSession session, ModelMap map) {
		if (!ContactAppUtils.isAuthorized(session))
			return "redirect:/";
		session.setAttribute(Constants.TEMP_CONTACT, new Contact());
		session.setAttribute(Constants.TEMP_MOBILE_LIST, new ArrayList<Mobile>());
		session.setAttribute(Constants.TEMP_ADDRESS_LIST, new ArrayList<Address>());
		session.setAttribute(Constants.TEMP_EMAIL_LIST, new ArrayList<ContactEmail>());
		session.setAttribute(Constants.TEMP_DATE_LIST, new ArrayList<ContactDate>());
		map.addAttribute(Constants.COUNTRY_CODE_LIST, ContactAppUtils.getCountryCodesList());
		return "AddContact";
	}

	@PostMapping("/AddContact")
	public @ResponseBody Boolean addContact(@RequestParam String firstName, @RequestParam String middleName,
			@RequestParam String lastName, @RequestParam(required = false) String nickName, HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return false;
		Contact contact = (Contact) session.getAttribute(Constants.TEMP_CONTACT);
		contact.setFirstName(firstName);
		contact.setMiddleName(middleName);
		contact.setLastName(lastName);
		contact.setNickName(nickName);
		AddressType addressType = ContactAppUtils.getAddressType(Constants.ADDRESS_TYPE_HOME);
		List<Mobile> mobileList = (List<Mobile>) session.getAttribute(Constants.TEMP_MOBILE_LIST);
		for (Mobile mobile : mobileList) {
			mobile.setContact(contact);
		}
		List<Address> addressList = (List<Address>) session.getAttribute(Constants.TEMP_ADDRESS_LIST);
		for (Address address : addressList) {
			address.setAddressType(addressType);
			address.setContact(contact);
		}
		List<ContactEmail> emailList = (List<ContactEmail>) session.getAttribute(Constants.TEMP_EMAIL_LIST);
		for (ContactEmail email : emailList) {
			email.setContact(contact);
		}
		List<ContactDate> dateList = (List<ContactDate>) session.getAttribute(Constants.TEMP_DATE_LIST);
		for (ContactDate date : dateList) {
			date.setContact(contact);
		}
		contact.setUserMobile(mobileList);
		contact.setAddresses(addressList);
		contact.setEmails(emailList);
		contact.setDates(dateList);
		contact = service.registerContact(contact);
		return true;
	}

	@PostMapping("/AddMobile")
	public @ResponseBody HashMap<String, Long> addMobile(@RequestParam Long countryCodeId, @RequestParam Long mobileNo,
			HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return null;
		HashMap<String, Long> responseMap = new HashMap<>();
		CountryCode countryCode = ContactAppUtils.getCountryCode(countryCodeId);
		((List<Mobile>) session.getAttribute(Constants.TEMP_MOBILE_LIST)).add(new Mobile(countryCode, mobileNo, null));
		responseMap.put("countryCode", countryCode.getCountryCode());
		responseMap.put("mobileNo", mobileNo);
		return responseMap;
	}

	@PostMapping("/DeleteMobile")
	public @ResponseBody HashMap<String, Long> deleteMobile(@RequestParam Long mobileNo, HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return null;
		List<Mobile> mobileList = ((List<Mobile>) session.getAttribute(Constants.TEMP_MOBILE_LIST));
		for (Mobile mobile : mobileList) {
			if (mobile.getMobileNo().equals(mobileNo)) {
				mobileList.remove(mobile);
			}
		}
		session.setAttribute(Constants.TEMP_MOBILE_LIST, mobileList);
		HashMap<String, Long> responseMap = new HashMap<>();
		responseMap.put("mobileNo", mobileNo);
		return responseMap;
	}

	@PostMapping("/CheckZipCode")
	public @ResponseBody HashMap<String, List<ZipCode>> checkZipCode(@RequestParam Long zipCode, HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return null;
		HashMap<String, List<ZipCode>> responseMap = new HashMap<>();
		List<ZipCode> zipList = ContactAppUtils.getZipCodeList(zipCode);
		if (zipList != null && !zipList.isEmpty()) {
			ZipCode zip = zipList.get(0);
			session.setAttribute(Constants.TEMP_COUNTRY, zip.getDistrict().getState().getCountry());
			session.setAttribute(Constants.TEMP_STATE, zip.getDistrict().getState());
			session.setAttribute(Constants.TEMP_DISTRICT, zip.getDistrict());
		}
		responseMap.put("zipLocationList", zipList);
		return responseMap;
	}

	@PostMapping("/AddAddress")
	public @ResponseBody HashMap<String, String> addAddress(@RequestParam String addressText,
			@RequestParam Long zipLocation, HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return null;
		Address address = new Address();
		address.setAddress(addressText);
		Country country = (Country) session.getAttribute(Constants.TEMP_COUNTRY);
		State state = (State) session.getAttribute(Constants.TEMP_STATE);
		District district = (District) session.getAttribute(Constants.TEMP_DISTRICT);
		ZipCode zipCode = ContactAppUtils.getZipCode(zipLocation);
		session.setAttribute(Constants.TEMP_COUNTRY, null);
		session.setAttribute(Constants.TEMP_STATE, null);
		session.setAttribute(Constants.TEMP_DISTRICT, null);
		session.setAttribute(Constants.TEMP_ZIP_CODE, null);
		address.setCountry(country);
		address.setState(state);
		address.setDistrict(district);
		address.setZipCode(zipCode);
		((List<Address>) session.getAttribute(Constants.TEMP_ADDRESS_LIST)).add(address);
		HashMap<String, String> response = new HashMap<>();
		response.put("addressText", addressText);
		response.put("zipCode", zipCode != null ? zipCode.getZipCode().toString() : null);
		response.put("zipLocation", zipCode != null ? zipCode.getZipLocation() : null);
		response.put("district", district != null ? district.getDistrictName() : null);
		response.put("state", state != null ? state.getStateName() : null);
		response.put("country", country != null ? country.getCountryName() : null);
		response.put("formattedAddress", address.getFormattedAddress());
		return response;
	}

	@PostMapping("/DeleteAddress")
	public @ResponseBody HashMap<String, String> deleteAddress(@RequestParam String addressText, HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return null;
		List<Address> addressList = ((List<Address>) session.getAttribute(Constants.TEMP_ADDRESS_LIST));
		for (Address address : addressList) {
			if (address.getFormattedAddress().equals(addressText)) {
				addressList.remove(address);
			}
		}
		session.setAttribute(Constants.TEMP_ADDRESS_LIST, addressList);
		HashMap<String, String> response = new HashMap<>();
		response.put("address", addressText);
		return response;
	}

	@PostMapping("/AddEmail")
	public @ResponseBody HashMap<String, String> addEmail(@RequestParam String email, HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return null;
		HashMap<String, String> response = new HashMap<>();
		ContactEmail emailObj = new ContactEmail(email, null);
		((List<ContactEmail>) session.getAttribute(Constants.TEMP_EMAIL_LIST)).add(emailObj);
		response.put("email", email);
		return response;
	}

	@PostMapping("/DeleteEmail")
	public @ResponseBody Boolean deleteEmail(@RequestParam String emailId, HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return false;
		List<ContactEmail> emailList = ((List<ContactEmail>) session.getAttribute(Constants.TEMP_EMAIL_LIST));
		for (ContactEmail email : emailList) {
			if (email.getEmail().equals(emailId)) {
				emailList.remove(email);
			}
		}
		session.setAttribute(Constants.TEMP_EMAIL_LIST, emailList);
		return true;
	}

	@PostMapping("/AddDate")
	public @ResponseBody HashMap<String, String> addDate(@RequestParam String date, @RequestParam String remarks,
			HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return null;
		HashMap<String, String> response = new HashMap<>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		Date dateObj = null;
		String formattedDate = null;
		try {
			dateObj = sdf1.parse(date);
			formattedDate = sdf2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ContactDate contactDate = new ContactDate(remarks, dateObj, null);
		((List<ContactDate>) session.getAttribute(Constants.TEMP_DATE_LIST)).add(contactDate);
		response.put("date", formattedDate);
		response.put("remarks", remarks);
		return response;
	}

	@PostMapping("/DeleteDate")
	public @ResponseBody Boolean deleteDate(@RequestParam String dateString, HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return false;
		List<ContactDate> dateList = ((List<ContactDate>) session.getAttribute(Constants.TEMP_DATE_LIST));
		for (ContactDate date : dateList) {
			if (date.getDate().toString().concat(" - ").concat(date.getDateRemarks()).equals(dateString)) {
				dateList.remove(date);
			}
		}
		session.setAttribute(Constants.TEMP_DATE_LIST, dateList);
		return true;
	}

	@GetMapping("/SearchContact")
	public String showSearchContact(HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return "redirect:/";
		return "SearchContact";
	}

	@PostMapping("/SearchContact")
	public String processSearchContact(Long contactId, HttpSession session, ModelMap map) {
		if (!ContactAppUtils.isAuthorized(session))
			return "redirect:/";
		Contact contact = service.getContact(contactId);
		map.addAttribute(Constants.SEARCH_CONTACT, contact);
		return "SearchContact";
	}

	@GetMapping("/ViewContacts")
	public String showAllContacts(HttpSession session, ModelMap map) {
		if (!ContactAppUtils.isAuthorized(session))
			return "redirect:/";
		List<Contact> contactList = service.getAllContacts();
		map.addAttribute(Constants.ALL_CONTACTS, contactList);
		return "ViewContacts";
	}

	@GetMapping("/Profile")
	public String showProfile(HttpSession session) {
		if (!ContactAppUtils.isAuthorized(session))
			return "redirect:/";
		return "redirect:/Home";
	}

	@PostMapping("/Register")
	public String registerUser(String email, String password) {
		System.out.println("Email : " + email);
		System.out.println("Password : " + password);
		return "redirect:/";
	}

	@PostMapping("/ResetPassword")
	public String resetPassword(String email) {
		System.out.println("Email : " + email);
		return "redirect:/";
	}

	@GetMapping("/Logout")
	public String performLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
