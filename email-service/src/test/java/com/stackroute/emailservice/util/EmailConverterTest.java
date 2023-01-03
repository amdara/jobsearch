package com.stackroute.emailservice.util;

import com.stackroute.emailservice.dto.EmailDto;
import com.stackroute.emailservice.dto.JobSearchDto;
import com.stackroute.emailservice.model.Email;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;

import static com.stackroute.emailservice.util.Constants.JOBSEEKER_SUBJECT;
import static com.stackroute.emailservice.util.Constants.RECRUITER_SUBJECT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmailConverterTest {

    @Autowired
    private EmailConverter emailConverter;

    private MockMultipartFile file = new MockMultipartFile(
            "file",
            "hello.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".getBytes()
    );

    @Test
    void successfullyConvertToEmailForRecruiter() throws Exception {
        JobSearchDto jobSearchDto = new JobSearchDto("test","test","test","test","test","test","test","test","test","recruiter@gmail.com","Rambo");
        EmailDto emailDto = new EmailDto("jobseeker@gmail.com","John",jobSearchDto,new byte[0]);
        Email email = emailConverter.convertToEmailForRecruiter(emailDto);
        assertEquals("Rambo", email.getRecruiterName());
        assertEquals(RECRUITER_SUBJECT, email.getSubject());
        assertEquals("recruiter@gmail.com",email.getEmailTo());
    }

    @Test
    void successfullyConvertToEmailForJobseeker() throws Exception {
        JobSearchDto jobSearchDto = new JobSearchDto("test","test","test","test","test","test","test","test","test","recruiter@gmail.com","Rambo");
        EmailDto emailDto = new EmailDto("jobseeker@gmail.com","John", jobSearchDto,new byte[0]);
        Email email = emailConverter.convertToEmailForJobseeker(emailDto);
        assertEquals("Rambo", email.getRecruiterName());
        assertEquals(JOBSEEKER_SUBJECT, email.getSubject());
        assertEquals("jobseeker@gmail.com",email.getEmailTo());
    }


    @AfterAll
    private static void deleteFiles() throws Exception{
        File file = new File("hello.txt");
        file.delete();
    }
}
