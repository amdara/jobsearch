package com.stackroute.emailservice.service;

import com.stackroute.emailservice.model.Email;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

import static com.stackroute.emailservice.util.Constants.*;

@Service
public class EmailServiceImpl implements IEmailService {

    Log log = LogFactory.getLog(EmailServiceImpl.class);

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("classpath:/fitmyjob_logo.png")
    Resource resourceFile;

    /*
     * @Description: this method is used to send email to recruiter and Jobseeker
     * @Param: It takes two Email type as paramaters
     * @returns : It returns a String object
     */
    @Override
    public String sendEmail(Email emailRecruiter, Email emailJobseeker) {
        log.info("Inside send mail service");
        try{
            sendEmailToRecruiter(emailRecruiter);
            sendEmailToJobseeker(emailJobseeker);
            return SUCCESS_MESSAGE;
        }catch (Exception e){
            return FAILURE_MESSAGE;
        }
    }

    /*
     * @Description: this method is used to send a successful signup email
     * @Param: It takes 2 string type as paramaters
     * @returns : It returns a String object
     */
    @Override
    public String sendSignupMail(String email, String username) {
        log.info("Inside send successful signup mail service");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        Context context = new Context();
        context.setVariable("username", username);
        String html = templateEngine.process("emails/signupMail", context);
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject(SIGNUP_SUBJECT);
            mimeMessageHelper.addInline("attachment.png", resourceFile);
            javaMailSender.send(mimeMessage);
            log.info("Successful signup mail is sent");
            return SUCCESS_MESSAGE;
        } catch (MessagingException e) {
            log.error(FAILURE_MESSAGE);
            log.error(e.getMessage());
            return FAILURE_MESSAGE;
        }
    }

    /*
     * @Description: this method is used to send email to recruiter
     * @Param: It takes Email type as paramater
     * @returns : It has no return
     */
    private void sendEmailToRecruiter(Email email) {
        log.info("Inside mail to recruiter method");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(email.getEmailTo());
            mimeMessageHelper.setText(email.getMsgBody(), true);
            mimeMessageHelper.setSubject(email.getSubject());
            FileSystemResource file = new FileSystemResource(new File(email.getFile()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            mimeMessageHelper.addInline("attachment.png", resourceFile);
            javaMailSender.send(mimeMessage);
            log.info("Mail is sent to Recruiter");
        } catch (MessagingException e) {
            log.error(FAILURE_MESSAGE);
            log.error(e.getMessage());
        } finally {
            File file = new File(email.getFile());
            file.delete();
        }
    }

    /*
     * @Description: this method is used to send email to jobseeker
     * @Param: It takes Email type as paramater
     * @returns : It has no return
     */
    private void sendEmailToJobseeker(Email email) {
        log.info("Inside mail to Jobseeker method");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(email.getEmailTo());
            mimeMessageHelper.setText(email.getMsgBody(), true);
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.addInline("attachment.png", resourceFile);
            javaMailSender.send(mimeMessage);
            log.info("Mail is sent to Jobseeker");
        } catch (MessagingException e) {
            log.error(FAILURE_MESSAGE);
            log.error(e.getMessage());
        }
    }
}
