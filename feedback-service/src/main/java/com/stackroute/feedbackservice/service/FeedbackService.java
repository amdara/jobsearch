package com.stackroute.feedbackservice.service;

import com.stackroute.feedbackservice.dao.FeedbackDto;
import com.stackroute.feedbackservice.dao.UserDto;


public interface FeedbackService {
		String addPost(FeedbackDto feedbackDto);
	    
		String addFeedback(FeedbackDto feedbackDto);

		String addUser(UserDto userDto);


}
