package com.stackroute.chatservice.repository;

import com.stackroute.chatservice.model.Jobseeker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobseekerRepository extends MongoRepository<Jobseeker, Long> {
    Optional<Jobseeker> findByEmail(String email);

}
