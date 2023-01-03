package com.stackroute.emailservice.service;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.stackroute.emailservice.model.Email;
import com.stackroute.emailservice.model.Job;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import javax.mail.internet.MimeMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static com.stackroute.emailservice.util.Constants.FAILURE_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmailServiceTest {

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("raktim", "springboot"))
            .withPerMethodLifecycle(false);

    @Autowired
    private IEmailService emailService;

    private Job job;
    private Email emailRecruiter;
    private Email emailJobseeker;

    @BeforeEach
    void loadEmails() throws Exception{
        job = new Job("test","test","test","test","test","test","test","test","test","recruiter@gmail.com","Rambo");
        emailRecruiter = new Email();
        emailRecruiter.setJobSeekerName("John");
        emailRecruiter.setRecruiterName("Rambo");
        emailRecruiter.setJobName("Software Engineer");
        emailRecruiter.setJob(job);
        emailRecruiter.setSubject("New Applicant");
        emailRecruiter.setMsgBody("Successfully Applied!");
        emailRecruiter.setEmailTo("recruiter@gmail.com");
        MockMultipartFile sampleFile = new MockMultipartFile("file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes());
        File file = new File("hello.txt");
        OutputStream os = new FileOutputStream(file);
        os.write(sampleFile.getBytes());
        emailRecruiter.setFile("hello.txt");

        emailJobseeker = new Email();
        emailJobseeker.setJobSeekerName("John");
        emailJobseeker.setRecruiterName("Rambo");
        emailJobseeker.setJobName("Software Engineer");
        emailJobseeker.setJob(job);
        emailJobseeker.setSubject("New Applicant");
        emailJobseeker.setMsgBody("Successfully Applied!");
        emailJobseeker.setEmailTo("jobseeker@gmail.com");
    }

    @Test
    void SuccessfullySendEmailTest() throws Exception{
        String response = emailService.sendEmail(emailRecruiter,emailJobseeker);
        assertEquals("Mail Sent Successfully!",response);
        MimeMessage receivedMessage1 = greenMail.getReceivedMessages()[0];
        assertEquals(1, receivedMessage1.getAllRecipients().length);
        assertEquals("recruiter@gmail.com", receivedMessage1.getAllRecipients()[0].toString());

        MimeMessage receivedMessage2 = greenMail.getReceivedMessages()[1];
        assertEquals(1, receivedMessage2.getAllRecipients().length);
        assertEquals("jobseeker@gmail.com", receivedMessage2.getAllRecipients()[0].toString());
    }

    @Test
    void failsToSendEmailTest() throws Exception{
        emailJobseeker.setEmailTo(null);
        String response = emailService.sendEmail(emailRecruiter,emailJobseeker);
        assertEquals(FAILURE_MESSAGE,response);
    }

    @AfterAll
    private static void deleteFiles() throws Exception{
        File file = new File("hello.txt");
        file.delete();
    }
}
