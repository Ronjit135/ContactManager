package com.app.contactApp.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mobile")
public class Mobile {
	private Long mobileId;
	private CountryCode countryCode;
	private Long mobileNo;
	private Contact contact;

	public Mobile() {
		super();
	}

	public Mobile(CountryCode countryCode, Long mobileNo, Contact contact) {
		super();
		this.countryCode = countryCode;
		this.mobileNo = mobileNo;
		this.contact = contact;
	}

	public Mobile(Long mobileId, CountryCode countryCode, Long mobileNo, Contact contact) {
		super();
		this.mobileId = mobileId;
		this.countryCode = countryCode;
		this.mobileNo = mobileNo;
		this.contact = contact;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mobile_id")
	public Long getMobileId() {
		return mobileId;
	}

	public void setMobileId(Long mobileId) {
		this.mobileId = mobileId;
	}

	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="country_code_id")
	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "mobile_number")
	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@ManyToOne
	@JoinColumn(name="contact_id")
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@JsonIgnore
	@Override
	public String toString() {
		return "Mobile [mobileId=" + mobileId + ", countryCode=" + countryCode + ", mobileNo=" + mobileNo + ", contact="
				+ contact.getFirstName() + "]";
	}
}
