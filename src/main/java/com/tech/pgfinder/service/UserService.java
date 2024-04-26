package com.tech.pgfinder.service;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tech.pgfinder.config.ApplicationResponse;
import com.tech.pgfinder.config.CommonMethod;
import com.tech.pgfinder.config.Constants;
import com.tech.pgfinder.dao.OtpDetails;
import com.tech.pgfinder.dao.User;
import com.tech.pgfinder.model.Request;
import com.tech.pgfinder.repository.OtpDetailsRepository;
import com.tech.pgfinder.repository.UsersRepository;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class.getName());
	private static final DateFormat sdf = new SimpleDateFormat(Constants.YEAR_MON);

	@Autowired
	UsersRepository userrepos;

	@Autowired
	OtpDetailsRepository otprepos;
	
	@Autowired
	CommonMethod common;

	public ApplicationResponse<String> register(Request request) {
		ApplicationResponse<String> defaultDetails = new ApplicationResponse<>();
		try {
			Date curdate = new Date();
			User pguser = new User();
			pguser.setName(request.getUsername());
			pguser.setEmail(request.getEmail());
			pguser.setMobile(request.getMobile());
			pguser.setCreatedDate(sdf.format(curdate));
			pguser.setUpdateddate(sdf.format(curdate));
			pguser.setActive("Y");
			userrepos.save(pguser);
			defaultDetails.setMessage(Constants.STATUS_SUCCESS_MSG);
			defaultDetails.setResult(Constants.SUCCESS);
			defaultDetails.setStatuscode(Constants.STATUS_SUCCESS_CODE);
		} catch (Exception e) {
			LOGGER.error("register Service" + e.toString());
			defaultDetails.setMessage(Constants.STATUS_FAILURE_MSG);
			defaultDetails.setResult(Constants.FAILURE);
			defaultDetails.setStatuscode(Constants.STATUS_FAILURE_CODE);
		}
		return defaultDetails;
	}

	public ApplicationResponse<String> sendOtp(Request request) {
		ApplicationResponse<String> defaultDetails = new ApplicationResponse<>();
		try {
			String res = common.SendEmail(request.getEmail());
			if ((Constants.SUCCESS).equalsIgnoreCase(res)) {
				defaultDetails.setMessage(Constants.STATUS_SUCCESS_MSG);
				defaultDetails.setResult(Constants.SUCCESS);
				defaultDetails.setStatuscode(Constants.STATUS_SUCCESS_CODE);
			} else {
				defaultDetails.setMessage(Constants.STATUS_FAILURE_MSG);
				defaultDetails.setResult(Constants.FAILURE);
				defaultDetails.setStatuscode(Constants.STATUS_FAILURE_CODE);
			}
		} catch (Exception e) {
			LOGGER.error("sendOtp Service" + e.toString());
			defaultDetails.setMessage(Constants.STATUS_FAILURE_MSG);
			defaultDetails.setResult(Constants.FAILURE);
			defaultDetails.setStatuscode(Constants.STATUS_FAILURE_CODE);
		}
		return defaultDetails;
	}

	public void saveOtp(String otp, String emailid,String time) {
		try {
			Date curdate = new Date();
			OtpDetails pgotp = new OtpDetails();
			String user_id = userrepos.getLoginId(emailid);
			pgotp.setActive("Y");
			pgotp.setCreatedDate(sdf.format(curdate));
			pgotp.setEmail(emailid);
			pgotp.setUserId(user_id);
			pgotp.setOtp(otp);
			pgotp.setOtpstmp(time);
			otprepos.save(pgotp);
		} catch (Exception e) {
			LOGGER.error("saveOtp Service" + e.toString());
		}
	}

	public ApplicationResponse<String> validateOtp(Request request) {
		ApplicationResponse<String> defaultDetails = new ApplicationResponse<>();
		try {
			// Adding 1000ms for demo
			String res = otprepos.validateOtp(request.getEmail(),request.getOtp());
	        boolean isValid = common.isOTPValid(res);
	        if (isValid && res != null) {
	        	defaultDetails.setMessage(Constants.STATUS_SUCCESS_MSG);
				defaultDetails.setResult(Constants.SUCCESS_OTP);
				defaultDetails.setStatuscode(Constants.STATUS_SUCCESS_CODE);
	        } else {
	        	defaultDetails.setMessage(Constants.STATUS_FAILURE_MSG);
				defaultDetails.setResult(Constants.FAILURE_OTP);
				defaultDetails.setStatuscode(Constants.STATUS_FAILURE_CODE);
	        }
			
		} catch (Exception e) {
			LOGGER.error("validateOtp Service" + e.toString());
			defaultDetails.setMessage(Constants.STATUS_FAILURE_MSG);
			defaultDetails.setResult(Constants.FAILURE_OTP);
			defaultDetails.setStatuscode(Constants.STATUS_FAILURE_CODE);
		}
		return defaultDetails;
		}

}
