package com.stackroute.chatservice.serviceimpl;

import com.stackroute.chatservice.constant.Constants;
import com.stackroute.chatservice.exception.ChatMessageException;
import com.stackroute.chatservice.model.ChatMessage;
import com.stackroute.chatservice.model.MessageBody;
import com.stackroute.chatservice.repository.ChatMessageRepository;
import com.stackroute.chatservice.repository.JobseekerRepository;
import com.stackroute.chatservice.repository.RecruiterRepository;
import com.stackroute.chatservice.service.ChatMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


@Service
public class ChatMessageServiceImpl implements ChatMessageService{

	Logger log = LoggerFactory.getLogger(ChatMessageServiceImpl.class);


	@Autowired
	ChatMessageRepository chatMessageRepository;

	@Autowired
	JobseekerRepository jobseekerRepository;

	@Autowired
	RecruiterRepository recruiterRepository;

	@Autowired
	Environment environment;
	
	@Override
	public Object addRoom(ChatMessage chatMessage) {

		if(!jobseekerRepository.findByEmail(chatMessage.getJobseekerEmail()).isPresent())
		{

			return "JobSeeker Email is not exsits";
		}


		if(!recruiterRepository.findByEmail(chatMessage.getRecruiterEmail()).isPresent())
		{

			return "Recruiter Email is not exsits";
		}

		try {
			log.info("Inside addRoom");
			List<MessageBody> message=new ArrayList<>();
			message.add(chatMessage.getIMessage().get(0));
			chatMessage.setIMessage(message);
			return chatMessageRepository.save(chatMessage);
		}catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in saveChat of ChatMessageServiceImpl :{0}", e);
			log.error(errorMsg);
			throw new ChatMessageException(e.getMessage(), errorMsg);
		}

	}


	@Override
	public List<ChatMessage> getMessageByJobId(long jobId) {
		try {
			log.info("Inside addRoom");
			return chatMessageRepository.findByJobId(jobId);
		}catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getJobsbyUser of JobHandlingServiceImpl :{0}", e);
			log.error(errorMsg);
			throw new ChatMessageException(environment.getProperty(Constants.DEFAULT_EXCEPTION_CODE),e.getMessage());
		}
	}


	@Override
	public Object updateMessage(MessageBody body, long jobId) {



		try {
			log.info("Inside updateMessage");
//			if(!chatMessageRepository.findById(String.valueOf(jobId)).isPresent()) {
			if(chatMessageRepository.findByJobId(jobId).isEmpty()){
				log.info("Chat does not exist"); return new
						ChatMessageException(Constants.CHAT_DOES_NOT_EXIST, String.valueOf(jobId)); }

			ChatMessage chatMessageFromRepo= chatMessageRepository.findByJobId(jobId).get(0);
			List<MessageBody> bodies= chatMessageFromRepo.getIMessage();
			bodies.add(body);
			chatMessageFromRepo.setIMessage(bodies);
			return chatMessageRepository.save(chatMessageFromRepo);
		}catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in updateMessage :{0}", e);
			log.error(errorMsg);
			throw new ChatMessageException(environment.getProperty(Constants.DEFAULT_EXCEPTION_CODE),
					e.getMessage());		}
	}






}
