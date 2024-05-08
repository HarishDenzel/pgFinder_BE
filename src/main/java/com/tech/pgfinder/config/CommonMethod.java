package com.tech.pgfinder.config;

import java.security.GeneralSecurityException;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.pgfinder.service.UserService;

@Component
public class CommonMethod {

	@Autowired
	UserService usrservice;

	public String SendEmail(String Emailid) throws GeneralSecurityException {
		String strResult = "";
		// Sender's email ID and password
		String subject = "", messageText = "";
		String toAddress = "gowthamvenkats@gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "false");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.transport.protocol", "smtp");
		// props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		// props.put("mail.smtp.ssl.ciphersuites",
		// "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.debug", "true");
		String senderEmail = "techmindershub@gmail.com"; // Update with your email address
//	     String password = "techmindershub@678"; // Update with your email password
		String password = "svpu dghi unql knob"; // Update with your email password
		try {

			Session mailsession = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderEmail, password);
				}
			});
			long currentTimestamp = System.currentTimeMillis();
			String otp = generateOTP(currentTimestamp, Emailid);
			subject = "Pgfinder OTP"; // Get subject
			messageText = "Use OTP " + otp
					+ " to log into your pgfinder account. Do not share the OTP or your number with anyone.This OTP will expire in 15 minutes.";
			MimeMessage message = new MimeMessage(mailsession);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setSubject(subject);

			BodyPart msgBody = new MimeBodyPart();
			msgBody.setContent(messageText, "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(msgBody);
			message.setContent(multipart);

			Transport.send(message);
			strResult = Constants.SUCCESS;
		} catch (MessagingException e) {
			strResult = "Error sending email: " + e.getMessage();
			e.printStackTrace();
		}
		return strResult;
	}

	public static String generateOTP() {
		Random random = new Random();
		int otp = 1000 + random.nextInt(9000); // Generate a random 6-digit number
		return String.valueOf(otp);
	}

	public String generateOTP(long timestamp, String Emailid) {
		String otp = generateOTP();
		if (!Emailid.isEmpty()) {
			try {
				usrservice.saveOtp(otp, Emailid, timestamp + otp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return otp;
		}
		return timestamp+otp;
	}

	public boolean isOTPValid(String otp) {
	    long currentTime = System.currentTimeMillis();
	    long otpTime = Long.parseLong(otp.substring(0, 13)); // Extract timestamp from OTP
	    long timeDifference = currentTime - otpTime;
	    long fifteenMinutesInMillis = TimeUnit.MINUTES.toMillis(15);
	    return timeDifference >= 0 && timeDifference <= fifteenMinutesInMillis;
	}


}
