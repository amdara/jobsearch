package com.stackroute.jobservice.service.impl;

import com.stackroute.jobservice.dao.JobDto;
import com.stackroute.jobservice.exception.IdNotFoundException;
import com.stackroute.jobservice.model.Job;
import com.stackroute.jobservice.repository.JobRepository;
import com.stackroute.jobservice.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class JobServiceImplTest {

    @Autowired
    private JobServiceImpl jobService;

    @MockBean
    private JobRepository jobRepository;

    @Test
    public void getAllPostTest() {
        List<Job> job= Arrays.asList(new Job(1l, 1l, "IT", "Full Type", "Pune", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA", "Linux", "StartUp",
                "Need a java developer", new Date(2022,11,23), new Date(2022, 10, 12), "100", "recruiter4@gmail.com","Pankaj"));
        when(jobRepository.findAll()).thenReturn(job);
        assertEquals(1,jobService.getAllPosts().size());
    }

    @Test
    public void getUpdatePostByIdTest() throws IdNotFoundException {
        Long jobId = 1l;
        Job job = new Job(1l, 2l, "IT", "Part Type", "Pune", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA", "Linux", "StartUp",
                "Need a java developer", new Date(2020, 11, 12), new Date(2020, 10, 12), "100", "recruiter4@gmail.com","Pankaj");

        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));

        Job jobs = new Job(1l, 2l, "IT", "Full time", "Chennai", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA", "Linux", "StartUp",
                "Need a java developer", new Date(2020, 11, 12), new Date(2020, 10, 12), "100", "recruiter4@gmail.com","Pankaj");
        JobDto updatedDto = new JobDto(1l, 2l, "IT", "Full time", "Chennai", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA", "Linux", "StartUp",
                "Need a java developer", new Date(2020, 11, 12), new Date(2020, 10, 12), "100","recruiter4@gmail.com","Pankaj");

        when(jobRepository.save(jobs)).thenReturn(jobs);
        assertEquals(1, jobService.updateJobById(jobId,updatedDto).getJobId());

    }

    @Test
    public void getDeleteByIdTest() throws IdNotFoundException {

        Job jobs= new Job(3l, 3l, "IT", "Full Type", "Pune", "Java, MYSQL, Spring Boot", "0-2", "4.6 LPA", "Linux", "StartUp",
                "Need a java developer", new Date(2020, 11, 12), new Date(2020, 10, 12), "100", "recruiter4@gmail.com","Pankaj");
        when(jobRepository.findById(jobs.getJobId())).thenReturn(Optional.of(jobs));
        jobService.deleteJobById(jobs.getJobId());
        verify(jobRepository, times(1)).delete(jobs);
    }
}