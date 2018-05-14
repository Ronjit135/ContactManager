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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="st_district")
public class District {
	private Long districtId;
	private String districtName;
	private List<ZipCode> zipCodes = new ArrayList<>();
	private State state;
	public District() {
		super();
	}
	public District(String districtName) {
		super();
		this.districtName = districtName;
	}
	public District(String districtName, List<ZipCode> zipCodes, State state) {
		super();
		this.districtName = districtName;
		this.zipCodes = zipCodes;
		this.state = state;
	}
	public District(Long districtId, String districtName, List<ZipCode> zipCodes, State state) {
		super();
		this.districtId = districtId;
		this.districtName = districtName;
		this.zipCodes = zipCodes;
		this.state = state;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	@Column(name="district_name",length=60)
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@JsonIgnore
	@OneToMany(mappedBy="district",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<ZipCode> getZipCodes() {
		return zipCodes;
	}

	public void setZipCodes(List<ZipCode> zipCodes) {
		this.zipCodes = zipCodes;
	}

	@ManyToOne
	@JoinColumn(name="state_id")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
