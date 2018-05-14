package com.app.contactApp.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "st_country_code")
public class CountryCode {
	private Long countryCodeId;
	private String iso;
	private String countryName;
	private String countryNiceName;
	private String iso3;
	private Long numCode;
	private Long countryCode;
	public CountryCode() {
		super();
	}
	public CountryCode(String iso, String countryName, String countryNiceName, String iso3, Long numCode,
			Long countryCode) {
		super();
		this.iso = iso;
		this.countryName = countryName;
		this.countryNiceName = countryNiceName;
		this.iso3 = iso3;
		this.numCode = numCode;
		this.countryCode = countryCode;
	}
	public CountryCode(Long countryCodeId, String iso, String countryName, String countryNiceName, String iso3,
			Long numCode, Long countryCode) {
		super();
		this.countryCodeId = countryCodeId;
		this.iso = iso;
		this.countryName = countryName;
		this.countryNiceName = countryNiceName;
		this.iso3 = iso3;
		this.numCode = numCode;
		this.countryCode = countryCode;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="country_code_id")
	public Long getCountryCodeId() {
		return countryCodeId;
	}

	public void setCountryCodeId(Long countryCodeId) {
		this.countryCodeId = countryCodeId;
	}

	@Column(name="iso",length=2)
	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	@Column(name="country_name",length=60)
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column(name="country_nice_name",length=60)
	public String getCountryNiceName() {
		return countryNiceName;
	}

	public void setCountryNiceName(String countryNiceName) {
		this.countryNiceName = countryNiceName;
	}

	@Column(name="iso3",length=3)
	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	@Column(name="num_code")
	public Long getNumCode() {
		return numCode;
	}

	public void setNumCode(Long numCode) {
		this.numCode = numCode;
	}

	@Column(name="country_code")
	public Long getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Long countryCode) {
		this.countryCode = countryCode;
	}
}
