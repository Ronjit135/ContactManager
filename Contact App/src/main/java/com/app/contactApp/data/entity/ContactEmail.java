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
@Table(name = "contact_email")
public class ContactEmail {
	private Long emailId;
	private String email;
	private Contact contact;

	public ContactEmail() {
		super();
	}

	public ContactEmail(String email, Contact contact) {
		super();
		this.email = email;
		this.contact = contact;
	}

	public ContactEmail(Long emailId, String email, Contact contact) {
		super();
		this.emailId = emailId;
		this.email = email;
		this.contact = contact;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "email_id")
	public Long getEmailId() {
		return emailId;
	}

	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
