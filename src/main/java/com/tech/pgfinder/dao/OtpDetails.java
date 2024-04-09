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
	




}
