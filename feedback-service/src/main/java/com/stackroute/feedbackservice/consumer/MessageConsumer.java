package com.stackroute.feedbackservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.feedbackservice.dao.UserDto;
import com.stackroute.feedbackservice.service.FeedbackService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    private static ObjectMapper objectMapper=new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
     @Autowired
    FeedbackService feedbackService;
    @RabbitListener(queues ="recruiter_to_feedback_message_queue")
    public void recruiterMessageListener(String message) {
       System.out.println("Feedback Service User");
        System.out.println("recruiter service"+message);
        try {
            UserDto userDto=objectMapper.readValue(message,UserDto.class);
            userDto.setType("Recruiter");
            feedbackService.addUser(userDto);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @RabbitListener(queues ="feedback_queue")
    public void jobSeekerMessageListener(String message) {
        System.out.println("Feedback Service User");
        System.out.println("job seeker service"+message);
        try {
            UserDto userDto=objectMapper.readValue(message,UserDto.class);
            userDto.setType("JobSeeker");
            feedbackService.addUser(userDto);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
