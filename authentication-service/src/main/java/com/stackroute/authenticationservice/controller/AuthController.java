package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.Exception.RoleNotFoundException;
import com.stackroute.authenticationservice.constant.Constant;
import com.stackroute.authenticationservice.dto.JwtResponse;
import com.stackroute.authenticationservice.dto.LoginDto;
import com.stackroute.authenticationservice.dto.ResetPasswordDto;
import com.stackroute.authenticationservice.dto.SignUpDto;
import com.stackroute.authenticationservice.model.ERole;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.security.JwtTokenProvider;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Optional;


@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping("/authentication")
public class AuthController {

	//Requirements for signup

	@Autowired
	private UserRepository userRepository;


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	//JWT
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	//Requirements for login

	@Autowired
	private AuthenticationManager authenticationManager;

	//@Autowired
	//IEmailService emailService;

	@Autowired
	JavaMailSender javaMailSender;

	@PostMapping("/signup")
	public ResponseEntity<?> signUpUser(@RequestBody SignUpDto signUpDto) throws UnknownHostException
	{

		// Check for username already exists or not

		if (userRepository.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("username is already taken", HttpStatus.BAD_REQUEST);
		}

		//Check for email already exists or not

		if (userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<String>("email is already taken", HttpStatus.BAD_REQUEST);
		}

		//create User object and set values using signUpDto

		User user = new User();
		user.setUsername(signUpDto.getUsername());
		user.setEmail(signUpDto.getEmail());
		// encode password
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

		//user.setRoles(roles);
		if (signUpDto.getRole().equals("JOB_SEEKER")) {
			user.setRole(ERole.valueOf(signUpDto.getRole()));
		} else if (signUpDto.getRole().equals("RECRUITER")) {
			user.setRole(ERole.valueOf(signUpDto.getRole()));
		} else {
			throw new RoleNotFoundException("Given Role can not [signUp");
		}

//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setTo(user.getEmail());
//		mailMessage.setSubject(;/'"Complete Registration!");
//		mailMessage.setFrom("fitmyjobofficial@gmail.com");
//		mailMessage.setText("You have sucessfully Resigisted with FitMyJob");
//		javaMailSender.send(mailMessage);
//

		//modelAndView.addObject("emailId", user.getEmail());

		if (signUpDto.getRole().equals("JOB_SEEKER"))
		{
			System.out.println("job_Seeker  "+signUpDto.getRole());
			rabbitTemplate.convertSendAndReceive(Constant.PRODUCER_AUTHENTICATION_JOBSEEKER_EXCHANGE, Constant.PRODUCER_AUTHENTICATION_JOBSEEKER_ROUTING_KEY,  userRepository.save(user));

		} else
		{
			System.out.println(" Recruiter  "+signUpDto.getRole());
			rabbitTemplate.convertSendAndReceive(Constant.PRODUCER_AUTHENTICATION_RECRUITER_EXCHANGE, Constant.PRODUCER_AUTHENTICATION_RECRUITER_ROUTING_KEY,  userRepository.save(user));
		}

			rabbitTemplate.convertSendAndReceive(Constant.PRODUCER_AUTHENTICATION_SIGNUPEMAIL_EXCHANGE, Constant.PRODUCER_AUTHENTICATION_SIGNUPEMAIL_ROUNTING_KEY,  userRepository.save(user));



		return new ResponseEntity<String>("Sign up for is successful", HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginDto loginDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// get token from token provider class

		String token=jwtTokenProvider.generateToken(authentication);
		//return new ResponseEntity<String>("Login Sucessfull", new JwtResponse(token), HttpStatus.OK);

		 return ResponseEntity.ok(new JwtResponse(token));

	}

	@PutMapping("/forgetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) //throws OTPException

	{
		if (userRepository.existsByEmail(resetPasswordDto.getEmail())&& userRepository.existsByUsername(resetPasswordDto.getUsername()))
		{
			Optional<User> user = userRepository.findByEmail(resetPasswordDto.getEmail());

			if (resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword()))
			{
				user.get().setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));

				//otp
				/*Optional<User> byEmailIdAndPassword = this.userRepository.findbyEmailIdAndPassword(resetPasswordDto.getEmail(),resetPasswordDto.getPassword(), resetPasswordDto.getOtp());

				if   (byEmailIdAndPassword.isPresent()){


					if (otp == OTP)
					{
						User user = byEmailIdAndPassword.get();
						user.setPassword(resetPasswordDto.getConfirmPassword());
						userRepository.save(user);
						rabbitTemplate.convertAndSend(directExchange.getName(),"routing_E",user);
						return "Password Change successFully";
					} else {

						throw new OTPException("OTP is not Valid");
					}

				} else {
					throw new UserNotFoundException("Invalid Credentials");
				}
*/
				//otp end

				userRepository.save(user.get());
				return new ResponseEntity<>("YOUR PASSWORD HAS BEEN RESET",HttpStatus.OK);
			}

		}
		return new ResponseEntity<>("YOUR EMAIL ID IS NOT FOUND",HttpStatus.OK);

	}




}
