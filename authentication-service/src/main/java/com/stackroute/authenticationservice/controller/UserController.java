package com.stackroute.authenticationservice.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	/*@PreAuthorize("hasRole('ROLE_JOB_SEEKER') or hasRole('ROLE_RECRUITER')")
	@GetMapping("/home")
	public String hello() {
		return "All can View this page";
	}

	@PreAuthorize("hasRole('ROLE_JOB-SEEKER')")
	@PostMapping("/job-seeker")
	public String jobseekerAccess()
	{

		return "Only JOB_SEEKER can view this page";
	}

	@PreAuthorize("hasRole('ROLE_RECRUITER')")
	@PostMapping("/recruiter")
	public String recruiterAccess()
	{
		return "Only RECRUITER can view this page";
	}

	 */
}
