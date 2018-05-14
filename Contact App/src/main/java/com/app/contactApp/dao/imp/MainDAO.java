package com.app.contactApp.dao.imp;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.contactApp.dao.ifc.MainDAOIfc;
import com.app.contactApp.data.entity.AddressType;
import com.app.contactApp.data.entity.Contact;
import com.app.contactApp.data.entity.Country;
import com.app.contactApp.data.entity.CountryCode;
import com.app.contactApp.data.entity.User;

@Repository
public class MainDAO implements MainDAOIfc {
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;

	private Session getSession() throws HibernateException {
		session = sessionFactory.openSession();
		return session;
	}

	private void closeSession() throws HibernateException {
		if (session != null) {
			session.close();
		}
	}

	@Override
	public User registerUser(User user) {
		Long userId = null;
		try {
			userId = (Long) getSession().save(user);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeSession();
		}
		user.setUserId(userId);
		return user;
	}

	@Override
	public User validateUser(User user) {
		String sql = "SELECT u FROM User u WHERE u.email=:em AND u.password=:pwd";
		try {
			user = getSession().createQuery(sql, User.class).setParameter("em", user.getEmail())
					.setParameter("pwd", user.getPassword()).getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeSession();
		}
		return user;
	}

	@Override
	public Contact registerContact(Contact contact) {
		Long contactId = null;
		try {
			contactId = (Long) getSession().save(contact);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeSession();
		}
		contact.setContactId(contactId);
		return contact;
	}

	@Override
	public Contact getContact(Long contactId) {
		Contact contact = null;
		try {
			contact = getSession().get(Contact.class, contactId);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeSession();
		}
		return contact;
	}

	@Override
	public Contact searchContactByFirstName(String firstName) {
		String sql = "SELECT u FROM Contact u WHERE u.firstName=:fn";
		Contact contact = null;
		try {
			contact = getSession().createQuery(sql, Contact.class).setParameter("fn", firstName).getSingleResult();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeSession();
		}
		return contact;
	}

	@Override
	public Contact createContact(Contact contact) {
		Long contactId = null;
		try {
			contactId = (Long) getSession().save(contact);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeSession();
		}
		contact.setContactId(contactId);
		return contact;
	}

	@Override
	public Contact updateContact(Contact contact) {
		try {
			getSession().update(contact);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeSession();
		}
		return contact;
	}

	@Override
	public List<Country> getAllCountries() {
		String sql = "SELECT c FROM Country c";
		List<Country> countries = null;
		try {
			countries = getSession().createQuery(sql, Country.class).getResultList();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeSession();
		}
		return countries;
	}

	@Override
	public List<CountryCode> getAllCoutryCodes() {
		String sql = "SELECT c FROM CountryCode c";
		List<CountryCode> countryCodes = null;
		try {
			countryCodes = getSession().createQuery(sql, CountryCode.class).getResultList();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeSession();
		}
		return countryCodes;
	}

	@Override
	public List<AddressType> getAllAddressTypes() {
		String sql = "SELECT a FROM AddressType a";
		List<AddressType> addressTypes = null;
		try {
			addressTypes = getSession().createQuery(sql, AddressType.class).getResultList();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeSession();
		}
		return addressTypes;
	}

	@Override
	public List<Contact> getAllContacts() {
		String sql = "SELECT c FROM Contact c";
		List<Contact> contactList = null;
		try {
			contactList = getSession().createQuery(sql, Contact.class).getResultList();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeSession();
		}
		return contactList;
	}
}