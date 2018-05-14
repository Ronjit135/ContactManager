package com.app.contactApp.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "st_address_type")
public class AddressType {
	private Long adressTypeId;
	private String addressType;

	public AddressType() {
		super();
	}

	public AddressType(String addressType) {
		super();
		this.addressType = addressType;
	}

	public AddressType(Long adressTypeId, String addressType) {
		super();
		this.adressTypeId = adressTypeId;
		this.addressType = addressType;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_type_id")
	public Long getAdressTypeId() {
		return adressTypeId;
	}

	public void setAdressTypeId(Long adressTypeId) {
		this.adressTypeId = adressTypeId;
	}

	@Column(name="address_type",length=60)
	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
}
