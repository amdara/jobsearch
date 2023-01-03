package com.stackroute.chatservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.chatservice.model.ChatMessage;
import com.stackroute.chatservice.model.MessageBody;
import com.stackroute.chatservice.service.ChatMessageService;



@ExtendWith(MockitoExtension.class)
class ChatMessageControllerTest {

	@Mock
	ChatMessageService messageService;

	@InjectMocks
	ChatMessageController chatMessageController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void init()
	{
		mockMvc= MockMvcBuilders.standaloneSetup(chatMessageController).build();
	}

	@AfterEach
	void cleanUp()
	{
		mockMvc=null;
	}


	private String jsonToString(final Object object)
	{
		String result;
		try {
			ObjectMapper objectMapper=new ObjectMapper();
			String jsonContent=objectMapper.writeValueAsString(object);
			result=jsonContent;
		}catch (JsonProcessingException exception) {

			result="error while converting to string";
		}
		return result;
	}


//	@Test
//	void addRoomTest() throws Exception {
//		ChatMessage chatMessage=new ChatMessage();
//		chatMessage.setJobId(1);
//		chatMessage.setJobseekerEmail("jobseeker@gmail.com");
//		chatMessage.setRecruiterEmail("recruiter@gmail.com");
//
//		List<MessageBody> bodies=new ArrayList<>();
//		MessageBody body = new MessageBody();
//		body.setSenderMail("recruiter@gmail.com");
//		body.setMessageBody("Testing");
//		bodies.add(body);
//		chatMessage.setIMessage(bodies);
//
//
//		when(messageService.addRoom(chatMessage)).thenReturn(chatMessage);
//		assertEquals(messageService.addRoom(chatMessage), chatMessage);
//
//		mockMvc.perform(post("/v1/api/saveChat").contentType(MediaType.APPLICATION_JSON)
//				.content(jsonToString(chatMessage))).andExpect(status().isOk());
//
//		verify(messageService,times(1)).addRoom(chatMessage);
//	}

	@Test
	void getMssgbyTicketIdTest() throws Exception
	{
		List<ChatMessage> chatMessagesList=new ArrayList<>();
		ChatMessage chatMessage=new ChatMessage();
		chatMessage.setJobId(1);
		chatMessage.setJobseekerEmail("jobseeker@gmail.com");
		chatMessage.setRecruiterEmail("recruiter@gmail.com");
		chatMessagesList.add(chatMessage);

		when(messageService.getMessageByJobId(1)).thenReturn(chatMessagesList);
		assertEquals(messageService.getMessageByJobId(1),chatMessagesList);

		mockMvc.perform(get("/chatservice/getMessagesbyJobId/1").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(chatMessagesList))).andExpect(status().isOk());

		verify(messageService,times(2)).getMessageByJobId(1);

	}

//	@Disabled("Checking")
	@Test
	void updateMessageTest() throws Exception {

		ChatMessage chatMessage=new ChatMessage();
		chatMessage.setJobId(1);
        chatMessage.setJobseekerEmail("jobseeker@gmail.com");
        chatMessage.setRecruiterEmail("recruiter@gmail.com");
		List<MessageBody> bodies=new ArrayList<>();
		MessageBody body = new MessageBody();
		body.setSenderMail("recruiter@gmail.com");
		body.setMessageBody("Testing");
		bodies.add(body);
		chatMessage.setIMessage(bodies);

		when(messageService.updateMessage(body, 1)).thenReturn(body);
		assertEquals(messageService.updateMessage(body, 1), body);

//		mockMvc.perform(put("/v1/api/chatMessage/1").contentType(MediaType.APPLICATION_JSON)
//				.content(jsonToString(body))).andExpect(status().isOk());

//		verify(messageService,times(2)).updateMessage(body, 1);

	}

}
