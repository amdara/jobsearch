package com.stackroute.jobseekerservice.service;

import com.stackroute.jobseekerservice.entity.JobSeeker;
import com.stackroute.jobseekerservice.exception.IdNotFoundException;
import com.stackroute.jobseekerservice.repository.JobSeekerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class JobSeekerServiceApplicationTest {
    @Autowired
    private JobSeekerService jobSeekerService;

    @MockBean
    private JobSeekerRepository jobSeekerRepository;



    @Test
    void getJobSeekerByIdTest() throws IdNotFoundException {
        List<String> skill= Arrays.asList("python", "c#", "mongodb");
        Long id = 1L;
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setId(id);
        jobSeeker.setName("sakshi");
        jobSeeker.setEmail("sakshi@gmail.com");
        jobSeeker.setWorkExperience("1 year");
        jobSeeker.setSkills(skill);
        jobSeeker.setCurrentLocation("dehradun");
        jobSeeker.setPreferredLocation("nodia");
        jobSeeker.setHighestEducation("MCA");
        jobSeeker.setPhoneNumber("1234567890");
        jobSeeker.setAddress("dehradun");

        given(jobSeekerRepository.findById(jobSeeker.getId()))
                .willReturn(Optional.of(jobSeeker));
        JobSeeker expected=jobSeekerService.getJobSeekerById(jobSeeker.getId());
        assertThat(expected).isSameAs(jobSeeker);
        verify(jobSeekerRepository).findById(jobSeeker.getId());
    }

    @Test
    void getAllJobSeekers(){
        List<String> skills= Arrays.asList("java", "spring", "C++");
        List<String> skill1= Arrays.asList("python", "c#", "mongodb");
        when(jobSeekerRepository.findAll()).thenReturn(
                Stream.of(new JobSeeker(1L,"sakshi","sakshi@gmail.com","1234574098",
                                        "1 year","dehardun","delhi","MCA",skills,"pune"),
                                new JobSeeker(2L,"aman","aman@gmail.com","1234574098",
                                        "3 year","dehardun","nodia","BCA",skill1,"doon"))
                        .collect(Collectors.toList()));
        assertEquals(2,jobSeekerService.getAllJobSeeker().size());
    }

    @Test
    void deleteJobSeekerTest() throws IdNotFoundException {
        List<String> skills= Arrays.asList("java", "spring", "C++");
        JobSeeker jobSeeker=new JobSeeker(102L,"aman","aman@gmail.com","9278347899","3 year","mumbai","pune","MBA",skills,"mumbai");
        when(jobSeekerRepository.findById(jobSeeker.getId()))
                .thenReturn(Optional.of(jobSeeker));
        jobSeekerService.deleteJobSeeker(jobSeeker.getId());

        verify(jobSeekerRepository,times(1)).delete(jobSeeker);
    }



}
