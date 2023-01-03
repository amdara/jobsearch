package com.stackroute.emailservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.stackroute.emailservice.model.Signup;
import com.stackroute.emailservice.service.EmailServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.mail.internet.MimeMessage;
import java.io.File;

import static com.stackroute.emailservice.util.Constants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmailControllerTest {

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("raktim", "springboot"))
            .withPerMethodLifecycle(false);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void createFile() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private String notificationUrl = "/email/sendNotificationMail";
    private String signupnUrl = "/email/signup/mail";
    private MockMultipartFile file = new MockMultipartFile(
            "file",
            "hello.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".getBytes()
    );

    @Test
    void successfullySendNotificationEmail() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart(notificationUrl)
                        .file(file)
                        .param("jobSeekerMail", "ravi@gmail.com")
                        .param("jobSeekerName", "John")
                        .param("jobSearchDto.companyName", "Test")
                        .param("jobSearchDto.description", "Test")
                        .param("jobSearchDto.experienced", "Test")
                        .param("jobSearchDto.jobCategory", "Test")
                        .param("jobSearchDto.jobLocation", "Test")
                        .param("jobSearchDto.jobSkills", "Test")
                        .param("jobSearchDto.jobType", "Test")
                        .param("jobSearchDto.salary", "Test")
                        .param("jobSearchDto.recruiterName", "Rambo")
                        .param("jobSearchDto.recruiterMailId", "xyz@gmail.com"))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();
        assertEquals(SUCCESS_MESSAGE, response);
        MimeMessage receivedMessage = greenMail.getReceivedMessages()[0];
        assertEquals(1, receivedMessage.getAllRecipients().length);
    }

    @Test
    void failsToSendNotificationEmail() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart(notificationUrl)
                        .file(file)
                        .param("jobSeekerMail", "abcd..gmail.com")
                        .param("jobSeekerName", "John")
                        .param("jobSearchDto.companyName", "Test")
                        .param("jobSearchDto.description", "Test")
                        .param("jobSearchDto.experienced", "Test")
                        .param("jobSearchDto.jobCategory", "Test")
                        .param("jobSearchDto.jobLocation", "Test")
                        .param("jobSearchDto.jobSkills", "Test")
                        .param("jobSearchDto.jobType", "Test")
                        .param("jobSearchDto.salary", "Test")
                        .param("jobSearchDto.recruiterName", "Rambo")
                        .param("jobSearchDto.recruiterMailId", "xyz@gmail.com"))
                .andExpect(status().is(406))
                .andReturn().getResponse().getContentAsString();
        assertEquals(INVALID_EMAIL, response);
    }

    @Test
    void successfullySendSignupMail() throws Exception{
        Signup signup = new Signup(1,"ravi@gmail.com","ravi","ravi","RECRUITER");
        String response = mockMvc.perform(post(signupnUrl)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(signup)))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();
        assertEquals(SUCCESS_MESSAGE, response);
        MimeMessage receivedMessage = greenMail.getReceivedMessages()[0];
        assertEquals(1, receivedMessage.getAllRecipients().length);
        assertEquals("ravi@gmail.com", receivedMessage.getAllRecipients()[0].toString());
    }

    @Test
    void failsToSendSignupMail() throws Exception{
        Signup signup = new Signup(1,"ravi..gmail.com","ravi","ravi","RECRUITER");
        String response = mockMvc.perform(post(signupnUrl)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(signup)))
                .andExpect(status().is(406))
                .andReturn().getResponse().getContentAsString();
        assertEquals(INVALID_EMAIL, response);
    }

    @AfterAll
    private static void deleteFiles() throws Exception {
        File file = new File("hello.txt");
        file.delete();
    }
}
