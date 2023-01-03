package com.stackroute.jobservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.jobservice.dao.JobDto;
import com.stackroute.jobservice.dao.Status;
import com.stackroute.jobservice.service.JobService;
import com.stackroute.jobservice.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = JobServiceController.class)
class JobServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private JobService jobService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<JobDto> jobDtoList;

    @BeforeEach
    void contextLoads() {
        this.jobDtoList= new ArrayList<>();
        JobDto jobDto = new JobDto(1l, 1l, "IT", "Full Type", "Pune", "Java, MYSQL, Spring Boot", "0-2", "8.6 LPA", "IBM", "MNC",
                "Need a java developer", new Date(2022,11,23), new Date(2022, 10, 12), "100","recruiter1@gmail.com","rakesh");
        JobDto jobDto1= new JobDto(2l, 2l,"IT", "Full Type", "Hyderabad", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA", "Linux", "StartUp",
                "Need a java developer", new Date(2022,12,23), new Date(2022, 10, 12), "100","recruiter2@gmail.com","rakesh");

        this.jobDtoList.add(jobDto);
        this.jobDtoList.add(jobDto1);
    }
    @Test
    void testGetAllJob()throws Exception{
        when(jobService.getAllPosts()).thenReturn(jobDtoList);
        this.mockMvc.perform(get("/jobservice/get"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(jobDtoList.size())));
    }
    @Test
    void testUpdateJobById() throws  Exception{
        Long jobId= 1l;
        JobDto jobDto = new JobDto(1l,1l, "IT", "Full Type", "Pune", "Java, MYSQL, Spring Boot", "0-2", "8.6 LPA", "IBM", "MNC",
                "Need a java developer", new Date(2022,11,23), new Date(2022, 10, 12), "100","recruiter1@gmail.com","rakesh");

        when(jobService.updateJobById(1l,jobDto)).thenReturn(jobDto);
        mockMvc.perform(put("/jobservice/update/{jobId}",jobId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(jobDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobType", is(jobDto.getJobType())));
    }
    @Test
    void testDeleteById()throws  Exception{
        Long jobId= 1l;
        JobDto jobDto = new JobDto(1l,1l ,"IT", "Full Type", "Pune", "Java, MYSQL, Spring Boot", "0-2", "8.6 LPA", "IBM", "MNC",
                "Need a java developer", new Date(2022,11,23), new Date(2022, 10, 12), "100","recruiter1@gmail.com","rakesh");
        when(jobService.deleteJobById(jobId)).thenReturn(new Status(Constants.DELETE_MESSAGE, HttpStatus.OK));
        this.mockMvc.perform(delete("/jobservice/delete/{jobId}",jobId))
                .andExpect(status().isOk());
    }
}