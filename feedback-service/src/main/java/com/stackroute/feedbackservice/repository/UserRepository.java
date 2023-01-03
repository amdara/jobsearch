package com.stackroute.feedbackservice.repository;

import com.stackroute.feedbackservice.model.UserKey;
import com.stackroute.feedbackservice.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, UserKey> {

     UserModel findByUserKey(UserKey key);
}