package com.stackroute.jobseekerservice.service;

import com.stackroute.jobseekerservice.dto.JobSearchDto;
import com.stackroute.jobseekerservice.entity.JobSeeker;
import com.stackroute.jobseekerservice.exception.IdNotFoundException;
import java.util.List;
import java.util.Set;


public interface JobSeekerService {

     JobSeeker updateJobSeeker(JobSeeker jobSeeker,Long id) throws IdNotFoundException;
     void deleteJobSeeker(long id) throws IdNotFoundException;
     JobSeeker getJobSeekerById(Long id) throws IdNotFoundException;
     List<JobSeeker> getAllJobSeeker();
     String applyJobs(Long id,JobSearchDto jobSearchDto);
     Set<JobSearchDto> appliedJobs(Long jobSeekerId) throws IdNotFoundException;
}
