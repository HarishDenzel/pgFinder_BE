package com.tech.pgfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tech.pgfinder.dao.User;

@Repository
public interface UsersRepository extends JpaRepository<User,Integer>{

	@Query(value="select pg_id from pg_users where email=?",nativeQuery = true)
	String getLoginId(String emailid);

	@Query(value="select email from pg_users where mobile=?",nativeQuery = true)
	String getEmailByMobile(String mobile);

	@Query(value="select email from pg_users where email=? and password=?",nativeQuery = true)
	String getPasswordByEmail(String email, String password);

	@Query(value="select email from pg_users where mobile=? and password=?",nativeQuery = true)
	String getPasswordByMobile(String mobile, String password);
	

}
