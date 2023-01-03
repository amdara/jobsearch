package com.stackroute.jobseekerservice.consumer;

import com.stackroute.jobseekerservice.dto.SignUpDto;
import com.stackroute.jobseekerservice.entity.JobSeeker;
import com.stackroute.jobseekerservice.repository.JobSeekerRepository;
import com.stackroute.jobseekerservice.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SignUpConsumer {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @RabbitListener(queues = Constants.CONSUMER_AUTHENTICATION_JOBSEEKER_QUEUE)
    public void consumer(SignUpDto signUpDto){
        log.info("consuming data from authentication service"+signUpDto);
        JobSeeker jobSeeker =new JobSeeker();
        jobSeeker.setUsername(signUpDto.getUsername());
        jobSeeker.setEmail(signUpDto.getEmail());
        jobSeeker.setId(signUpDto.getId());
        jobSeekerRepository.save(jobSeeker);

    }
}
