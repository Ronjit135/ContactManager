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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "address")
public class Address {
	private Long addressId;
	private AddressType addressType;
	private String address;
	private ZipCode zipCode;
	private District district;
	private State state;
	private Country country;
	private Contact contact;

	public Address() {
		super();
	}

	public Address(AddressType addressType, String address) {
		super();
		this.addressType = addressType;
		this.address = address;
	}

	public Address(AddressType addressType, String address, ZipCode zipCode, District district, State state, Country country,
			Contact contact) {
		super();
		this.addressType = addressType;
		this.address = address;
		this.zipCode = zipCode;
		this.district = district;
		this.state = state;
		this.country = country;
		this.contact = contact;
	}

	public Address(Long addressId, AddressType addressType, String address, ZipCode zipCode, District district, State state,
			Country country, Contact contact) {
		super();
		this.addressId = addressId;
		this.addressType = addressType;
		this.address = address;
		this.zipCode = zipCode;
		this.district = district;
		this.state = state;
		this.country = country;
		this.contact = contact;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="address_type_id")
	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	@Column(name = "address", length = 250)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	@JoinColumn(name="zip_code_id")
	public ZipCode getZipCode() {
		return zipCode;
	}

	public void setZipCode(ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	@JoinColumn(name="district_id")
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	@JoinColumn(name="state_id")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	@JoinColumn(name="country_id")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@ManyToOne
	@JoinColumn(name = "contact_id")
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	@Transient
	public String getFormattedAddress() {
		String formattedAddress = getAddress();
		if(getZipCode()!=null) {
			formattedAddress += ", " + getZipCode().getZipLocation();
			formattedAddress += ", " + getDistrict().getDistrictName();
			formattedAddress += ", " + getState().getStateName();
			formattedAddress += ", " + getCountry().getCountryName();
			formattedAddress += ", Pincode : " + getZipCode().getZipCode();
		}
		return formattedAddress;
	}
}
