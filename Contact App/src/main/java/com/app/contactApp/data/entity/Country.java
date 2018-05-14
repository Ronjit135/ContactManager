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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "st_country")
public class Country {
	private Long countryId;
	private String countryName;
	private List<State> states = new ArrayList<>();

	public Country() {
		super();
	}

	public Country(String countryName) {
		super();
		this.countryName = countryName;
	}

	public Country(String countryName, List<State> states) {
		super();
		this.countryName = countryName;
		this.states = states;
	}

	public Country(Long countryId, String countryName, List<State> states) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.states = states;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	@Column(name = "country_name", length = 60)
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
}