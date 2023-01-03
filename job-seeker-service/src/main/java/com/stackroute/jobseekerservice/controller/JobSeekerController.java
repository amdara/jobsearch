package com.stackroute.jobseekerservice.controller;


import com.stackroute.jobseekerservice.dto.JobSearchDto;
import com.stackroute.jobseekerservice.dto.JobSeekerDTO;
import com.stackroute.jobseekerservice.entity.JobSeeker;
import com.stackroute.jobseekerservice.exception.IdNotFoundException;
import com.stackroute.jobseekerservice.service.JobSeekerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jobseeker")
public class JobSeekerController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JobSeekerService jobSeekerService;

    @GetMapping("/getJobseeker/{id}")
    public ResponseEntity<JobSeekerDTO> getJobSeekerById(@PathVariable Long id) throws IdNotFoundException {
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(id);
        // convert entity to DTO
        JobSeekerDTO jobSeekerDTO = modelMapper.map(jobSeeker, JobSeekerDTO.class);
        return new ResponseEntity<>(jobSeekerDTO,HttpStatus.OK);
    }

    @GetMapping("/getAllJobSeeker")
    public ResponseEntity<List<JobSeekerDTO>> getAllJobSeekers(){
        List<JobSeekerDTO> jobSeekerDTOList = jobSeekerService.getAllJobSeeker().stream().map(jobSeeker -> modelMapper.map(jobSeeker, JobSeekerDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(jobSeekerDTOList,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<JobSeekerDTO> updateJobSeeker(JobSeekerDTO jobSeekerDTO,@RequestPart MultipartFile file) throws IdNotFoundException, IOException {
        // convert DTO to entity
        JobSeeker jobSeekerRequest = modelMapper.map(jobSeekerDTO, JobSeeker.class);
        jobSeekerRequest.setResume(file.getBytes());
        JobSeeker jobSeeker=jobSeekerService.updateJobSeeker(jobSeekerRequest,jobSeekerRequest.getId());
        // convert entity to DTO
        JobSeekerDTO jobSeekerResponse = modelMapper.map(jobSeeker, JobSeekerDTO.class);
        return  new ResponseEntity<>(jobSeekerResponse,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJobSeekerById(@PathVariable long id) throws IdNotFoundException {
        jobSeekerService.deleteJobSeeker(id);
        return new ResponseEntity<>("jobSeeker deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/appliedjobs/{jobseekerId}")
    public ResponseEntity<Set<JobSearchDto>> getAppliedJobs(Long jobSeekerId) throws IdNotFoundException {
        return new ResponseEntity<>(jobSeekerService.appliedJobs(jobSeekerId),HttpStatus.OK);
    }

}
