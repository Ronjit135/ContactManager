package com.app.contactApp.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "contact")
public class Contact {
	private Long contactId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String nickName;
	private List<Mobile> userMobile = new ArrayList<>();
	private List<Address> addresses = new ArrayList<>();
	private List<ContactEmail> emails = new ArrayList<>();
	private List<ContactDate> dates = new ArrayList<>();

	public Contact() {
		super();
	}

	public Contact(String firstName, String middleName, String lastName) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public Contact(String firstName, String middleName, String lastName, List<Mobile> userMobile,
			List<Address> addresses) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.userMobile = userMobile;
		this.addresses = addresses;
	}

	public Contact(Long contactId, String firstName, String middleName, String lastName, List<Mobile> userMobile,
			List<Address> addresses) {
		super();
		this.contactId = contactId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.userMobile = userMobile;
		this.addresses = addresses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contact_id")
	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "middle_name")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "nick_name")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	public List<Mobile> getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(List<Mobile> userMobile) {
		this.userMobile = userMobile;
	}

	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	public List<ContactEmail> getEmails() {
		return emails;
	}

	public void setEmails(List<ContactEmail> emails) {
		this.emails = emails;
	}

	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	public List<ContactDate> getDates() {
		return dates;
	}

	public void setDates(List<ContactDate> dates) {
		this.dates = dates;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", firstName=" + firstName + ", middlename=" + middleName
				+ ", lastName=" + lastName + ", userMobile=" + userMobile + "]";
	}
}
