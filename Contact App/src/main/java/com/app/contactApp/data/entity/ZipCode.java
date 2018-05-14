package com.app.contactApp.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="st_zip_code")
public class ZipCode {
	private Long zipId;
	private String zipLocation;
	private Long zipCode;
	private District district;
	public ZipCode() {
		super();
	}
	public ZipCode(Long zipCode) {
		super();
		this.zipCode = zipCode;
	}
	public ZipCode(Long zipCode, String zipLocation, District district) {
		super();
		this.zipLocation = zipLocation;
		this.zipCode = zipCode;
		this.district = district;
	}
	public ZipCode(Long zipId, String zipLocation, Long zipCode, District district) {
		super();
		this.zipId = zipId;
		this.zipLocation = zipLocation;
		this.zipCode = zipCode;
		this.district = district;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="zip_id")
	public Long getZipId() {
		return zipId;
	}

	public void setZipId(Long zipId) {
		this.zipId = zipId;
	}

	@Column(name="zip_location",length=60)
	public String getZipLocation() {
		return zipLocation;
	}
	public void setZipLocation(String zipLocation) {
		this.zipLocation = zipLocation;
	}
	@Column(name="zip_code")
	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}

	@ManyToOne
	@JoinColumn(name="district_id")
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
}
