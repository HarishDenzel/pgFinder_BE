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
	 * save user details to register in application using param name,email,mobile 09April2024
	 * 
	 * @return
	 */
	@PostMapping("/register")
	public ApplicationResponse<String> register(@RequestBody Request request) {	
		return userservice.register(request);
	}
	
	/**
	 * send otp to register mobile number to login application using param email 09April2024
	 * 
	 * @return
	 */	
	@PostMapping("/sendotp")
	public ApplicationResponse<String> sendOtp(@RequestBody Request request) {	
		return userservice.sendOtp(request);
	}
	
	/**
	 * validate otp to register mobile number to login application using param email,otp 09April2024
	 * 
	 * @return
	 */	
	@PostMapping("/validateotp")
	public ApplicationResponse<String> validateOtp(@RequestBody Request request) {	
		return userservice.validateOtp(request);
	}


	

}
