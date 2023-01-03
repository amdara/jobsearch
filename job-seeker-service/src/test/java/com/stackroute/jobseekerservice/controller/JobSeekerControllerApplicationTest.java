package com.stackroute.jobseekerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.jobseekerservice.entity.JobSeeker;
import com.stackroute.jobseekerservice.service.JobSeekerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = JobSeekerController.class)
class JobSeekerControllerApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private JobSeekerService jobSeekerService;


    private List<JobSeeker> jobSeekerList;
    private List<String> skills= Arrays.asList("java", "spring", "C++");

    @BeforeEach
    void setUp() {
        this.jobSeekerList = new ArrayList<>();

        JobSeeker jobSeeker=new JobSeeker(101L,"sakshi","abc@gmail.com","1232347890","2 year","pune","pune","MCA",skills,"pune");
        JobSeeker jobSeeker1=new JobSeeker(102L,"aman","aman@gmail.com","9278347899","3 year","mumbai","pune","MBA",skills,"mumbai");
        jobSeekerList.add(jobSeeker);
        jobSeekerList.add(jobSeeker1);


    }


    @Test
    void allJobSeekerTest() throws Exception {
        when(jobSeekerService.getAllJobSeeker()).thenReturn(jobSeekerList);

        this.mockMvc.perform(get("/jobseeker/getAllJobSeeker"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(jobSeekerList.size())));
    }

    @Test
    void jobSeekerByIdTest() throws Exception {
        JobSeeker jobSeeker1=new JobSeeker(102L,"aman","aman@gmail.com","9278347899","3 year","mumbai","pune","MBA",skills,"mumbai");
        given(jobSeekerService.getJobSeekerById(jobSeeker1.getId())).willReturn(jobSeeker1);

        this.mockMvc.perform(get("/jobseeker/getJobseeker/{id}", jobSeeker1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(jobSeeker1.getEmail())))
                .andExpect(jsonPath("$.name", is(jobSeeker1.getName()))).andDo(print());
    }


    @Test
    void deleteJobSeekerTest() throws Exception {
        Long id = 1L;
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setId(id);
        jobSeeker.setName("sakshi");
        jobSeeker.setEmail("sakshi@gmail.com");
        jobSeeker.setWorkExperience("1 year");
        jobSeeker.setSkills(skills);
        jobSeeker.setCurrentLocation("dehradun");
        jobSeeker.setPreferredLocation("nodia");
        jobSeeker.setHighestEducation("MCA");
        jobSeeker.setPhoneNumber("1234567890");
        jobSeeker.setAddress("dehradun");

        //given(jobSeekerService.deleteJobSeeker(id)).willReturn(jobSeeker);
        doNothing().when(jobSeekerService).deleteJobSeeker(jobSeeker.getId());

        this.mockMvc.perform(delete("/jobseeker/delete/{id}", jobSeeker.getId()))
                .andExpect(status().isOk()).andDo(print());


    }

}
