package com.stackroute.feedbackservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.feedbackservice.controller.FeedbackServiceController;
import com.stackroute.feedbackservice.dao.FeedbackDto;
import com.stackroute.feedbackservice.repository.FeedbackRepository;
import com.stackroute.feedbackservice.service.impl.FeedbackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FeedbackServiceController.class)

class ControllerTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
    private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FeedbackServiceImpl feedbackService;

	@MockBean
	private FeedbackRepository feedbackRepository;

	private List<FeedbackDto> feedbackDtoList;

	@BeforeEach
	void contextLoads() {

		this.feedbackDtoList = new ArrayList<>();
		FeedbackDto feedback1 = new FeedbackDto(468, "TEST_NAME3", "TEST_COMP2", "TEST_FEEDBACK2", 9);
		FeedbackDto feedback2 = new FeedbackDto(498, "TEST_NAME4", "TEST_COMP3", "TEST_FEEDBACK3", 10);

		this.feedbackDtoList.add(feedback1);
		this.feedbackDtoList.add(feedback2);
		//this.jobDtoList.add(job2);
	}




    @Test
    void testAddFeedback() throws Exception
    {

    	FeedbackDto feedback=new FeedbackDto(498, "TEST_NAME3", "TEST_COMP2", "TEST_FEEDBACK2", 9);
        //when(feedbackService.addFeedback(feedback)).thenReturn(new Status("", HttpStatus.CREATED));
        when(feedbackService.addFeedback(feedback)).thenReturn("Created");
        String status = feedbackService.addFeedback(feedback);
        mockMvc.perform(post("/feedbackservice/feedback")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(feedback)))
		        .andExpect(status().isCreated());
		        //.andExpect(jsonPath("$.feedback", is("TEST_FEEDBACK1")));
       System.out.println();


    }






}
