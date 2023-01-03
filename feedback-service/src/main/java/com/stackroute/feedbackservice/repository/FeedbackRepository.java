package com.stackroute.feedbackservice.repository;

import com.stackroute.feedbackservice.model.Feedback;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, Long> {
}