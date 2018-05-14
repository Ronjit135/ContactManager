package com.app.contactApp.service.ifc;

import java.util.List;

import com.app.contactApp.data.entity.AddressType;
import com.app.contactApp.data.entity.Contact;
import com.app.contactApp.data.entity.Country;
import com.app.contactApp.data.entity.CountryCode;
import com.app.contactApp.data.entity.User;

public interface ServiceIfc {
	public User registerUser(User user);

	public User validateUser(User user);
	
	public Contact registerContact(Contact contact);

	public Contact getContact(Long contactId);

	public Contact searchContactByFirstName(String firstName);

	public Contact createContact(Contact contact);

	public Contact updateContact(Contact contact);

	public List<Country> getAllCountries();

	public List<CountryCode> getAllCoutryCodes();
	
	public List<AddressType> getAllAddressTypes();
	
	public List<Contact> getAllContacts();
}
