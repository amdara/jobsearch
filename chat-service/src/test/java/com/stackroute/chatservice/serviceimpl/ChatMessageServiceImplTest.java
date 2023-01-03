package com.stackroute.chatservice.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.stackroute.chatservice.model.ChatMessage;
import com.stackroute.chatservice.model.MessageBody;
import com.stackroute.chatservice.repository.ChatMessageRepository;


class ChatMessageServiceImplTest {


	@Mock
	ChatMessageRepository chatMessageRepository;

	@Mock
	MongoTemplate mongoTemplate;

	@InjectMocks
	ChatMessageServiceImpl chatMessageServiceImpl;



	@BeforeEach
	void  setUp(){
		MockitoAnnotations.openMocks(this);
	}


//	@Test
//	void addRoomTest() {
//		ChatMessage chatMessage=new ChatMessage();
//		chatMessage.setJobId(1);
//		chatMessage.setJobseekerEmail("jobseeker@gmail.com");
//		chatMessage.setRecruiterEmail("recruiter@gmail.com");
//
//		Mockito.when(chatMessageRepository.save(chatMessage)).thenReturn(chatMessage);
//		assertEquals(chatMessage,chatMessageServiceImpl.addRoom(chatMessage));
//	}

	@Test
	void getMssgbyTicketIdTest(){
		List<ChatMessage> chatMessagesList=new ArrayList<>();
		ChatMessage chatMessage=new ChatMessage();
		chatMessage.setJobId(1);
		chatMessage.setJobseekerEmail("jobseeker@gmai.com");
		chatMessage.setRecruiterEmail("recruiter@gmail.com");
		chatMessagesList.add(chatMessage);

		Mockito.when(chatMessageRepository.findByJobId(1)).thenReturn(chatMessagesList);
		assertEquals(chatMessagesList,chatMessageServiceImpl.getMessageByJobId(1));

	}

	@Test
	void updateMessageTest()
	{

		ChatMessage chatMessage=new ChatMessage();
		chatMessage.setJobId(1);

		MessageBody body = new MessageBody();
		body.setSenderMail("test@gmail.com");
		body.setMessageBody("Testing");

		chatMessageServiceImpl.updateMessage(body, 1);
        verify(chatMessageRepository).findByJobId(1);

	}

}
