package com.stackroute.emailservice.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.emailservice.exception.InvalidEmailException;
import com.stackroute.emailservice.model.Signup;
import com.stackroute.emailservice.service.EmailValidationService;
import com.stackroute.emailservice.service.IEmailService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import static com.stackroute.emailservice.util.Constants.INVALID_EMAIL;
import static com.stackroute.emailservice.util.Constants.SIGNUP_QUEUE;

@Service
public class SignUpConsumer {
    Log log = LogFactory.getLog(SignUpConsumer.class);

    @Autowired
    IEmailService emailService;

    @Autowired
    EmailValidationService emailValidationService;
    @Autowired
    ObjectMapper mapper;

    @RabbitListener(queues = SIGNUP_QUEUE)
    public void listener(String user) throws JsonProcessingException {
        log.info("Inside consumer");
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Signup signup = mapper.readValue(user, Signup.class);
        if (!emailValidationService.isValidEmailAddress(signup.getEmail())) {
            log.error(INVALID_EMAIL);
            throw new InvalidEmailException(INVALID_EMAIL);
        }
        emailService.sendSignupMail(signup.getEmail(), signup.getUsername());
    }


}
