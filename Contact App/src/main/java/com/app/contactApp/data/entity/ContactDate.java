package com.app.contactApp.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "contact_date")
public class ContactDate {
	private Long dateId;
	private String dateRemarks;
	private Date date;
	private Contact contact;

	public ContactDate() {
		super();
	}

	public ContactDate(String dateRemarks, Date date, Contact contact) {
		super();
		this.dateRemarks = dateRemarks;
		this.date = date;
		this.contact = contact;
	}

	public ContactDate(Long dateId, String dateRemarks, Date date, Contact contact) {
		super();
		this.dateId = dateId;
		this.dateRemarks = dateRemarks;
		this.date = date;
		this.contact = contact;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "date_id")
	public Long getDateId() {
		return dateId;
	}

	public void setDateId(Long dateId) {
		this.dateId = dateId;
	}

	@Column(name = "date_remarks")
	public String getDateRemarks() {
		return dateRemarks;
	}

	public void setDateRemarks(String dateRemarks) {
		this.dateRemarks = dateRemarks;
	}

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne
	@JoinColumn(name = "contact_id")
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
