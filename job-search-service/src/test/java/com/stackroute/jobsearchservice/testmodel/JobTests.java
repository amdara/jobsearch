package com.stackroute.jobsearchservice.testmodel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.jobsearchservice.model.JobSearch;
import com.stackroute.jobsearchservice.repository.JobSearchRepository;
import com.stackroute.jobsearchservice.service.JobSearchService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobTests {

	@Autowired
	private JobSearchService jobSearchService;

	@MockBean
	private JobSearchRepository jobSearchRepository;

	/*
	 * Method to test the job by location
	 */

	@Test
	public void jobLocationTest() {
		JobSearch job=new JobSearch();
		job.setJobLocation("Hyderabad");
		jobSearchRepository.save(job);
		assertThat(job.getJobLocation()).isNotNull();
	}

	/*
	 * Method to test the job by company name
	 */

	@Test
	public void companyNameTest() {
		JobSearch job=new JobSearch();
		job.setCompanyName("Google");
		jobSearchRepository.save(job);
		assertThat(job.getCompanyName()).isNotNull();
	}

}
