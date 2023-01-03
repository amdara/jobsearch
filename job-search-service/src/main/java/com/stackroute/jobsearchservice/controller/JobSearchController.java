package com.stackroute.jobsearchservice.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stackroute.jobsearchservice.dto.JobSearchDto;
import com.stackroute.jobsearchservice.service.JobSearchService;


@RestController
@RequestMapping("/jobsearch")
public class JobSearchController {

	@Autowired
	private JobSearchService jobSearchService;

	@GetMapping("/applyJob/{seekerId}/{jobId}")
	public ResponseEntity<String> getAppliedJobs(@PathVariable Long seekerId,@PathVariable Long jobId) {
		return new ResponseEntity<String>(jobSearchService.invokeApplyJob(seekerId,jobId),HttpStatus.OK);
	}

	@GetMapping("/findAllJobs")
	public ResponseEntity<List<JobSearchDto>> getAllJobs() {
		return new ResponseEntity<List<JobSearchDto>>(jobSearchService.viewAllJobs(), HttpStatus.OK);
	}

	@GetMapping("/vacancy/{vacancy}")
	public ResponseEntity<List<JobSearchDto>> getJobsByVacancy(@PathVariable Integer vacancy) {
		return new ResponseEntity<List<JobSearchDto>>(jobSearchService.filterByVacancy(vacancy), HttpStatus.OK);
	}

	@GetMapping("/location/{location}")
	public ResponseEntity<List<JobSearchDto>> getJobsByLocation(@PathVariable String location) {
		return new ResponseEntity<List<JobSearchDto>>(jobSearchService.filterByLocation(location), HttpStatus.OK);
	}

	@GetMapping("/company/{companyName}")
	public ResponseEntity<List<JobSearchDto>> getJobsByCompanyName(@PathVariable String companyName) {
		return new ResponseEntity<List<JobSearchDto>>(jobSearchService.filterByCompanyName(companyName), HttpStatus.OK);
	}

}
