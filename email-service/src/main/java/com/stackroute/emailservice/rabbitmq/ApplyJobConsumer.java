package com.stackroute.emailservice.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.emailservice.dto.EmailDto;
import com.stackroute.emailservice.exception.InvalidEmailException;
import com.stackroute.emailservice.model.Email;
import com.stackroute.emailservice.service.EmailValidationService;
import com.stackroute.emailservice.service.IEmailService;
import com.stackroute.emailservice.util.EmailConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.stackroute.emailservice.util.Constants.*;

@Service
public class ApplyJobConsumer {

    Log log = LogFactory.getLog(ApplyJobConsumer.class);

    @Autowired
    IEmailService emailService;
    @Autowired
    EmailConverter emailConverter;
    @Autowired
    EmailValidationService emailValidationService;
    @Autowired
    ObjectMapper mapper;

    @RabbitListener(queues = APPLY_JOB_QUEUE)
    public void listener(String details) throws FileNotFoundException, JsonProcessingException {
        log.info("Inside consumer");
        EmailDto emailDto = mapper.readValue(details,EmailDto.class);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        if (emailDto.getFileData()==null) {
            log.error("Invalid file");
            throw new FileNotFoundException(INVALID_FILE);
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(emailDto.getJobSeekerName()+".pdf")){
            fileOutputStream.write(emailDto.getFileData());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!emailValidationService.isValidEmailAddress(emailDto.getJobSearchDto().getRecruiterMailId()) ||
                !emailValidationService.isValidEmailAddress(emailDto.getJobSeekerMail())) {
            log.error(INVALID_EMAIL);
            throw new InvalidEmailException(INVALID_EMAIL);
        }
        Email emailRecruiter = emailConverter.convertToEmailForRecruiter(emailDto);
        Email emailJobseeker = emailConverter.convertToEmailForJobseeker(emailDto);
        emailService.sendEmail(emailRecruiter,emailJobseeker);
    }
}
