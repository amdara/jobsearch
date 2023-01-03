package com.stackroute.jobsearchservice.testserviceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.stackroute.jobsearchservice.exceptions.ValueEmptyException;
import com.stackroute.jobsearchservice.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import com.stackroute.jobsearchservice.dto.JobSearchDto;
import com.stackroute.jobsearchservice.model.JobSearch;
import com.stackroute.jobsearchservice.repository.JobSearchRepository;
import com.stackroute.jobsearchservice.service.JobSearchService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

	@Autowired
	private JobSearchService jobSearchService;

	@MockBean
	private JobSearchRepository jobSearchRepository;

	List<JobSearch> jobList;

	@BeforeEach
	public void contextLoads() {

		this.jobList = new ArrayList<>();
		JobSearch job1 = new JobSearch(3l, 3l, "IT", "Full Type", "Pune", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA", "Google",
				"Startup", "Need a java developer", new Date(2022, 11, 23), new Date(2022, 10, 12), 100,
				"recruiter4@gmail.com", "Haif");
		JobSearch job2 = new JobSearch(4l, 4l, "Non IT", "Permanent", "Mumbai", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA",
				"Linux", "MNC", "Need a backend developer", new Date(2022, 11, 23), new Date(2022, 10, 12), 10,
				"recruiter5@gmail.com", "Heena");

		this.jobList.add(job1);
		this.jobList.add(job2);
	}

	/*
	 * Method test to get all the jobs
	 */
	@Test
	public void testGetAllJobs() {

		when(jobSearchRepository.findAll()).thenReturn(Stream.of(
						new JobSearch(3l, 3l, "IT", "Full Type", "Pune", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA", "Linux",
								"StartUp", "Need a java developer", new Date(2022, 11, 23), new Date(2022, 10, 12), 100,
								"recruiter4@gmail.com", "Leena"),
						new JobSearch(4l, 4l, "Non IT", "Permanent", "Mumbai", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA", "Linux",
								"StartUp", "Need a java developer", new Date(2022, 11, 23), new Date(2022, 10, 12), 50,
								"recruiter4@gmail.com", "Heena"))
				.collect(Collectors.toList()));
		assertEquals(2, jobSearchService.viewAllJobs().size());
	}

	/*
	 * Method test to get job by location
	 */
	@Test
	public void testGetJobByLocation() throws ValueEmptyException{
		String location = "Mumbai";
		Mockito.when(jobSearchRepository.findByJobLocation(location)).thenReturn(jobList);
		String name1 = "Mumbai";
		String name2 = "Pune";
		List<JobSearchDto> jobDto = jobSearchService.filterByLocation(location);
		assertEquals(name1, jobDto.get(1).getJobLocation());
		assertEquals(name2, jobDto.get(0).getJobLocation());
		assertNotNull(jobDto.get(0).getJobLocation());
	}

	/*
	 * Method test to get job by company name
	 */
	@Test
	public void testGetJobByCompanyName() throws Exception{
		String name = "Google";
		Mockito.when(jobSearchRepository.findByCompanyName(name)).thenReturn(jobList);
		String name1 = "Linux";
		String name2 = "Google";
		List<JobSearchDto> jobDto = jobSearchService.filterByCompanyName(name);
		assertEquals(name1, jobDto.get(1).getCompanyName());
		assertEquals(name2, jobDto.get(0).getCompanyName());
		assertNotNull(jobDto.get(0).getCompanyName());
	}

}
