package com.stackroute.emailservice.controller;

import com.stackroute.emailservice.dto.EmailDto;
import com.stackroute.emailservice.exception.InvalidEmailException;
import com.stackroute.emailservice.model.Email;
import com.stackroute.emailservice.model.Signup;
import com.stackroute.emailservice.service.EmailValidationService;
import com.stackroute.emailservice.service.IEmailService;
import com.stackroute.emailservice.util.EmailConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

import static com.stackroute.emailservice.util.Constants.INVALID_EMAIL;
import static com.stackroute.emailservice.util.Constants.INVALID_FILE;

@RestController
@RequestMapping("/email")
@Api(value = "Email Service", tags = {"Methods to send emails"})
public class EmailController {

    Log log = LogFactory.getLog(EmailController.class);

    @Autowired
    IEmailService emailService;
    @Autowired
    EmailConverter emailConverter;
    @Autowired
    EmailValidationService emailValidationService;

    @ApiOperation(value = "Send a notification mail to recruiter and jobseeker", response = ResponseEntity.class)
    @PostMapping("/sendNotificationMail")
    public ResponseEntity<String> sendNotificationMail(EmailDto emailDto, MultipartFile file)  throws FileNotFoundException{
        log.info("Inside send mail controller");
        if (file==null || file.isEmpty()) {
            log.error("Invalid file");
            throw new FileNotFoundException(INVALID_FILE);
        }
        if (!emailValidationService.isValidEmailAddress(emailDto.getJobSearchDto().getRecruiterMailId()) ||
                !emailValidationService.isValidEmailAddress(emailDto.getJobSeekerMail())) {
            log.error(INVALID_EMAIL);
            throw new InvalidEmailException(INVALID_EMAIL);
        }
        emailConverter.convertToFile(file,emailDto.getJobSeekerName()+".pdf");
        Email emailRecruiter = emailConverter.convertToEmailForRecruiter(emailDto);
        Email emailJobseeker = emailConverter.convertToEmailForJobseeker(emailDto);
        String status = emailService.sendEmail(emailRecruiter,emailJobseeker);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @ApiOperation(value = "Send a mail with signup notification", response = ResponseEntity.class)
    @PostMapping("/signup/mail")
    public ResponseEntity<String> sendSignupMail(@RequestBody Signup signup){
        if(!emailValidationService.isValidEmailAddress(signup.getEmail())){
            log.error(INVALID_EMAIL);
            throw new InvalidEmailException(INVALID_EMAIL);
        }
        String status = emailService.sendSignupMail(signup.getEmail(),signup.getUsername());
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}