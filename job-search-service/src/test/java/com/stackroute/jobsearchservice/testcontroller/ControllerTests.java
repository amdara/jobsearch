package com.stackroute.jobsearchservice.testcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.jobsearchservice.controller.JobSearchController;
import com.stackroute.jobsearchservice.dto.JobSearchDto;
import com.stackroute.jobsearchservice.service.JobSearchService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobSearchController.class)
public class ControllerTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private JobSearchService jobSearchService;

	private List<JobSearchDto> jobDtoList;

	@BeforeEach
	void contextLoads() {

		this.jobDtoList = new ArrayList<>();
		JobSearchDto job1 = new JobSearchDto(3l, 3l, "IT", "Full Type", "Pune", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA",
				"Linux", "StartUp", "Need a java developer", new Date(2022, 11, 23), new Date(2022, 10, 12), 100,
				"recruiter4@gmail.com","Yusuf");
		JobSearchDto job2 = new JobSearchDto(4l, 4l, "Non IT", "Permanent", "Mumbai", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA",
				"Linux", "StartUp", "Need a java developer", new Date(2022, 11, 23), new Date(2022, 10, 12), 50,
				"recruiter4@gmail.com","Yusuf");

		this.jobDtoList.add(job1);
		this.jobDtoList.add(job2);
	}

	@Test
	void shouldReturnListOfJobs() throws Exception {
		when(jobSearchService.viewAllJobs()).thenReturn(jobDtoList);
		mockMvc.perform(get("/jobsearch/findAllJobs")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(jobDtoList.size()))).andDo(print());
	}

	@Test
	void jobSearchByJobLocationTest() throws Exception {
		String location = "Kolkata";
		List<JobSearchDto> jobs = jobSearchService.filterByLocation(location);
		System.out.println("--------Job By Location------" + jobs);
		when(jobSearchService.filterByLocation(location)).thenReturn(jobDtoList);
		mockMvc.perform(get("/jobsearch/location/{location}", location)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(jobDtoList.size()))).andDo(print());
	}

	@Test
	void jobSearchByJobCompanyNameTest() throws Exception {
		String name = "Google";
		when(jobSearchService.filterByCompanyName(name)).thenReturn(jobDtoList);
		mockMvc.perform(get("/jobsearch/company/{companyName}", name)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(jobDtoList.size()))).andDo(print());
	}

}
