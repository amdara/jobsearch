package com.stackroute.jobsearchservice.service;

import java.util.List;
import com.stackroute.jobsearchservice.dto.JobSearchDto;

public interface JobSearchService {

	List<JobSearchDto> viewAllJobs();
	//JobSearchDto saveJob(JobSearchDto jobDto);
	List<JobSearchDto> filterByVacancy(Integer vacancy);
	List<JobSearchDto> filterByLocation(String location);
	List<JobSearchDto> filterByCompanyName(String companyName);
	String invokeApplyJob(Long seekerId, Long id);
}
