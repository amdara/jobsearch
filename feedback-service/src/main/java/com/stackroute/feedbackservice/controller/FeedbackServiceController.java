package com.stackroute.feedbackservice.controller;

import com.stackroute.feedbackservice.dao.FeedbackDto;
import com.stackroute.feedbackservice.service.impl.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/feedbackservice")

public class FeedbackServiceController {

    @Autowired
    private FeedbackServiceImpl feedbackservice;

    @PostMapping("/feedback")
    public ResponseEntity<String> addFeedback(@Valid @RequestBody FeedbackDto feedbackdto){
    	return new ResponseEntity<String> (feedbackservice.addFeedback(feedbackdto), HttpStatus.CREATED);
    }
}