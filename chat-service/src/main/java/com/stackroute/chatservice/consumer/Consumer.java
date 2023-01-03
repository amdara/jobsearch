package com.stackroute.chatservice.consumer;

import com.stackroute.chatservice.constant.Constants;
import com.stackroute.chatservice.model.Jobseeker;
import com.stackroute.chatservice.model.Recruiter;
import com.stackroute.chatservice.repository.JobseekerRepository;
import com.stackroute.chatservice.repository.RecruiterRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
//    @Autowired
//    private ChatMessageServiceImpl messageServiceImpl;

	@Autowired
	private JobseekerRepository jobseekerRepository;

	@Autowired
	private RecruiterRepository recruiterRepository;

	// this is for jobseeker service
	@RabbitListener(queues="chat_queue")
	public void getChatDetails1(Jobseeker jobseeker){

		System.out.println("We can able to do msg for jobseeker"+jobseeker);
		jobseekerRepository.save(jobseeker);
//		ChatMessage chatMessage=new ChatMessage();
//		chatMessage.setJobseekerEmail(jobseeker.getEmail());
//		messageServiceImpl.addRoom(chatMessage);

	}

	// this is for recruiter service
	@RabbitListener(queues = Constants.PRODUCER_RECRUITER_CHAT_QUEUE)
	public void getChatDetails(Recruiter recruiter){

		System.out.println("We can able to do msg for recruiter"+recruiter);
		recruiterRepository.save(recruiter);
//		ChatMessage chatMessage=new ChatMessage();
//		chatMessage.setRecruiterEmail(recruiter.getEmail());
//		messageServiceImpl.addRoom(chatMessage);

	}

}
