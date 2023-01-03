package com.stackroute.feedbackservice.service.impl;

import com.stackroute.feedbackservice.dao.FeedbackDto;
import com.stackroute.feedbackservice.dao.UserDto;
import com.stackroute.feedbackservice.model.Feedback;
import com.stackroute.feedbackservice.model.UserKey;
import com.stackroute.feedbackservice.model.UserModel;
import com.stackroute.feedbackservice.repository.UserRepository;
import com.stackroute.feedbackservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	//@Autowired
	// private FeedbackRepository feedbackRepository;

	@Autowired
	private UserRepository userRepository;


		@Override
		public String addPost(FeedbackDto feedbackDto) {
			Feedback feedback = new Feedback(feedbackDto.getUserId(),feedbackDto.getUserName(),feedbackDto.getCompanyName(),feedbackDto.getFeedback(),feedbackDto.getRating());
	       // Feedback addedFeedback = this.feedbackRepository.save(feedback);
	        return "Feedback Recorded..!";
		}
		@Override
		public String addFeedback(FeedbackDto feedbackDto) {
			Feedback feedback = new Feedback(feedbackDto.getUserId(),feedbackDto.getUserName(),feedbackDto.getCompanyName(),feedbackDto.getFeedback(),feedbackDto.getRating());
	        //  recruiter.setId(sequenceGeneratorService.generateSequence(Recruiter.SEQUENCE_NAME));
		//	Feedback addedFeedback = this.feedbackRepository.save(feedback);
	   ///      this.modelMapper.map(addedFeedback, FeedbackDto.class);
			return "Feedback Recorded..!";
			
		}

	@Override
	public String addUser(UserDto userDto) {
		try{
			UserKey userKey=new UserKey(userDto.getId(),userDto.getType());
			UserModel userModel=new UserModel(userKey,userDto.getEmail());
			userRepository.save(userModel);
		return "Success";
	}catch (Exception e){

			throw new RuntimeException(e);
		}
		}

}
