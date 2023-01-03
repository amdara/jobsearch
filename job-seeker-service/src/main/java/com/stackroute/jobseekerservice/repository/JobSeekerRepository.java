package com.stackroute.jobseekerservice.repository;

import com.stackroute.jobseekerservice.entity.JobSeeker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepository extends MongoRepository<JobSeeker,Long> {
}
