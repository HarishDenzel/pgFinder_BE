package com.tech.pgfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pgfinder.config.ApplicationResponse;
import com.tech.pgfinder.model.Request;
import com.tech.pgfinder.service.UserService;


@RestController
@RequestMapping("/api/public")
public class UserController {
	
	@Autowired
	UserService userservice;
	
	/**
	 * save user details to register in application on 09April2024
	 * name
	 * email
	 * mobile
	 * password
	 * @return
	 */
	@PostMapping("/register")
	public ApplicationResponse<String> register(@RequestBody Request request) {	
		return userservice.register(request);
	}
	
	/**
	 * validate otp to register mobile number to login application on 09April2024
	 * email
	 * otp
	 * @return
	 */	
	@PostMapping("/validateotp")
	public ApplicationResponse<String> validateOtp(@RequestBody Request request) {	
		return userservice.validateOtp(request);
	}
	
	/**
	 *  login application  08May2024
	 * mobile
	 * email
	 * password
	 * @return
	 */	
	@PostMapping("/login")
	public ApplicationResponse<String> login(@RequestBody Request request) {	
		return userservice.login(request);
	}



	

}
