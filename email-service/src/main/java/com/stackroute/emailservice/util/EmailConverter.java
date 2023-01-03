package com.stackroute.emailservice.util;

import com.stackroute.emailservice.dto.EmailDto;
import com.stackroute.emailservice.model.Email;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.stackroute.emailservice.util.Constants.JOBSEEKER_SUBJECT;
import static com.stackroute.emailservice.util.Constants.RECRUITER_SUBJECT;

@Component
public class EmailConverter {
    Log log = LogFactory.getLog(EmailConverter.class);

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    ModelMapper modelMapper;
    /*
     * @Description: this method is used to convert emailDto to email for recruiter
     * @Param: It takes EmailDto type as paramater
     * @returns : It returns a email object
     */
    public Email convertToEmailForRecruiter(EmailDto emailDto) {
        log.info("Converting emailDto to email for recruiter");
        Email email = modelMapper.map(emailDto,Email.class);
        email.setRecruiterName(emailDto.getJobSearchDto().getRecruiterName());
        email.setJobName(emailDto.getJobSearchDto().getJobCategory());
        email.setSubject(RECRUITER_SUBJECT);
        email.setEmailTo(emailDto.getJobSearchDto().getRecruiterMailId());
        Context context = new Context();
        context.setVariable("email", email);
        String html = templateEngine.process("emails/recruiterMail", context);
        email.setMsgBody(html);
        email.setFile(email.getJobSeekerName()+".pdf");
        return email;
    }

    /*
     * @Description: this method is used to convert emailDto to email for jobseeker
     * @Param: It takes EmailDto type as paramater
     * @returns : It returns a email object
     */
    public Email convertToEmailForJobseeker(EmailDto emailDto) {
        log.info("Converting emailDto to email for jobseeker");
        Email email = modelMapper.map(emailDto,Email.class);
        email.setRecruiterName(emailDto.getJobSearchDto().getRecruiterName());
        email.setJobName(emailDto.getJobSearchDto().getJobCategory());
        email.setSubject(JOBSEEKER_SUBJECT);
        email.setEmailTo(emailDto.getJobSeekerMail());
        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("job", email.getJob());
        String html = templateEngine.process("emails/jobseekerMail", context);
        email.setMsgBody(html);
        return email;
    }

    /*
     * @Description: this method is used to convert multipartFile to file
     * @Param: It takes MultipartFile and String type as paramaters
     * @returns : It returns a file object
     */
    public void convertToFile(MultipartFile multipartFile, String fileName) {
        log.info("Generating the file...");
        File file = new File(fileName);
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
            log.info("File created!");
        } catch (IOException e) {
            log.error("File not found");
        } catch (Exception e) {
            log.error("Error while converting file!");
        }
    }
}
