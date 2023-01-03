package com.stackroute.jobservice.repository;


import com.stackroute.jobservice.model.Job;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends MongoRepository<Job, Long> {
}
