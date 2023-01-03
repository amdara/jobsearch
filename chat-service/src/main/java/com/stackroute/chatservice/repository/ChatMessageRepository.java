package com.stackroute.chatservice.repository;

import org.springframework.stereotype.Repository;
import com.stackroute.chatservice.model.ChatMessage;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
	
	   List<ChatMessage> findByJobId(long jobId);


}
