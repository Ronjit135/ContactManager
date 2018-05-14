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
@Table(name="st_state")
public class State {
	private Long stateId;
	private String stateName;
	private List<District> districts = new ArrayList<>();
	private Country country;
	public State() {
		super();
	}
	public State(String stateName) {
		super();
		this.stateName = stateName;
	}
	public State(String stateName, List<District> districts, Country country) {
		super();
		this.stateName = stateName;
		this.districts = districts;
		this.country = country;
	}
	public State(Long stateId, String stateName, List<District> districts, Country country) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.districts = districts;
		this.country = country;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="state_id")
	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateid) {
		this.stateId = stateid;
	}

	@Column(name="state_name",length=60)
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	@ManyToOne
	@JoinColumn(name="country_id")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
