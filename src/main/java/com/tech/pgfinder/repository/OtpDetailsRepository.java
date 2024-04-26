package com.tech.pgfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tech.pgfinder.dao.OtpDetails;


@Repository
public interface OtpDetailsRepository extends JpaRepository<OtpDetails,Integer>{

	@Query(value="select otp_tim from otp_details where email=? and otp=?",nativeQuery = true)
	String validateOtp(String email, String otp);

}
