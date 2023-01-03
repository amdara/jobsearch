package com.stackroute.chatservice.repository;

import com.stackroute.chatservice.model.Recruiter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruiterRepository extends MongoRepository<Recruiter, Long> {
    Optional<Recruiter> findByEmail(String email);
}
