package com.stackroute.authenticationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.authenticationservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{


	Optional<User> findByEmail(String email);
	//Optional<User> findByEmailIdIgnoreCase(String emailId);
	Optional<User> findByUsernameOrEmail(String username,String email);

	//Optional<User> findbyEmailIdAndPassword(String email, String password,int otp);
	
	//Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	/*Optional<User> findByforgetPassword(String forgetPassword);*/


}
