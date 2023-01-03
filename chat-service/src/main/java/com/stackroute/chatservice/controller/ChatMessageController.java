package com.stackroute.chatservice.controller;

import java.util.List;

import com.stackroute.chatservice.exception.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.chatservice.model.ChatMessage;
import com.stackroute.chatservice.model.MessageBody;
import com.stackroute.chatservice.service.ChatMessageService;


@RestController
@RequestMapping(value ="/chatservice")
public class ChatMessageController {

	@Autowired
	ChatMessageService chatMessageService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value="/saveChat")
	public ResponseEntity<Object> addRoom(@RequestBody ChatMessage chatMessage)
	{


		MessageBody messageBody =  chatMessage.getIMessage().get(0);
		if(!messageBody.getSenderMail().equals(chatMessage.getRecruiterEmail()) && !messageBody.getSenderMail().equals(chatMessage.getJobseekerEmail()))
		{
				throw new InvalidEmailException("Email is not matching");
		}
		Object response = chatMessageService.addRoom(chatMessage);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getMessagesbyJobId/{jobId}")
	public ResponseEntity<List<ChatMessage>> getMessagesbyJobId(@PathVariable long jobId) {
		return new ResponseEntity<>(chatMessageService.getMessageByJobId(jobId), HttpStatus.OK);
	}


	@PutMapping(value = "/chatMessage/{jobId}")
	public ResponseEntity<Object> updateMessage(@RequestBody MessageBody messageBody, @PathVariable long jobId) {



		String RecruiterEmail = chatMessageService.getMessageByJobId(jobId).get(0).getRecruiterEmail();
		String JobSeekerEmail = chatMessageService.getMessageByJobId(jobId).get(0).getJobseekerEmail();

		if(!messageBody.getSenderMail().equals(RecruiterEmail) && !messageBody.getSenderMail().equals(JobSeekerEmail))
		{
			throw new InvalidEmailException("Email is not matching");
		}
		Object response = chatMessageService.updateMessage(messageBody, jobId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
