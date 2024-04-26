package com.tech.pgfinder.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="otp_details")
public class OtpDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pg_id")
	private int id;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="otp")
	private String otp;
	
	@Column(name="active_yn")
	private String active;
	
	@Column(name="created_date")
	private String createdDate;
	
	@Column(name="otp_tim")
	private String otpstmp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getOtpstmp() {
		return otpstmp;
	}

	public void setOtpstmp(String otpstmp) {
		this.otpstmp = otpstmp;
	}
	




}
