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
@Table(name="pg_users")
public class User implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pg_id")
	private int id;
	
	@Column(name="user_name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="view_cnt")
	private String viewcnt;
	
	@Column(name="created_date")
	private String createdDate;
	
	@Column(name="updated_date")
	private String updateddate;
	
	@Column(name="last_login")
	private String lastlogin;
	
	@Column(name="active_yn")
	private String active;
	
	@Column(name="pg_flex1")
	private String flex1;
	
	@Column(name="pg_flex2")
	private String flex2;
	
	@Column(name="pg_flex3")
	private String flex3;
	

}
