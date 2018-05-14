package com.app.contactApp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.contactApp.dao.ifc.MainDAOIfc;
import com.app.contactApp.data.entity.AddressType;
import com.app.contactApp.data.entity.Contact;
import com.app.contactApp.data.entity.Country;
import com.app.contactApp.data.entity.CountryCode;
import com.app.contactApp.data.entity.User;
import com.app.contactApp.service.ifc.ServiceIfc;

@Service
@Transactional
public class ServiceImp implements ServiceIfc {
	@Autowired
	MainDAOIfc dao;
	
	@Override
	public User registerUser(User user) {
		return dao.registerUser(user);
	}

	@Override
	public User validateUser(User user) {
		return dao.validateUser(user);
	}

	@Override
	public Contact registerContact(Contact contact) {
		return dao.registerContact(contact);
	}

	@Override
	public Contact getContact(Long contactId) {
		return dao.getContact(contactId);
	}

	@Override
	public Contact searchContactByFirstName(String firstName) {
		return dao.searchContactByFirstName(firstName);
	}

	@Override
	public Contact createContact(Contact contact) {
		return dao.createContact(contact);
	}

	@Override
	public Contact updateContact(Contact contact) {
		return dao.updateContact(contact);
	}

	@Override
	public List<Country> getAllCountries() {
		return dao.getAllCountries();
	}

	@Override
	public List<CountryCode> getAllCoutryCodes() {
		return dao.getAllCoutryCodes();
	}

	@Override
	public List<AddressType> getAllAddressTypes() {
		return dao.getAllAddressTypes();
	}

	@Override
	public List<Contact> getAllContacts() {
		return dao.getAllContacts();
	}
}
