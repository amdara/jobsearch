package com.stackroute.jobservice.service;

import com.stackroute.jobservice.dao.JobDto;

import com.stackroute.jobservice.dao.Status;

import java.util.List;

public interface JobService {

    JobDto postJob(JobDto jobDto);

    List<JobDto> getAllPosts();

    JobDto updateJobById(Long jobId, JobDto jobDto);

    Status deleteJobById(Long jobId);
}
