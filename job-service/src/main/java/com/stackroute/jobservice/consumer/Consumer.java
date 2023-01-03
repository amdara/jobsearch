package com.stackroute.jobservice.consumer;

import com.stackroute.jobservice.dao.RecruiterDto;
import com.stackroute.jobservice.model.Job;
import com.stackroute.jobservice.repository.JobRepository;
import com.stackroute.jobservice.service.SequenceGeneratorService;
import com.stackroute.jobservice.util.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @RabbitListener(queues = Constants.CONSUMER_QUEUE)
    public void recruiterMessageListener(RecruiterDto recruiterDto){
        Job job = new Job();
        job.setJobId(sequenceGeneratorService.generateSequence(Job.SEQUENCE_NAME));
        job.setRecruiterId(recruiterDto.getId());
        job.setRecruiterMail(recruiterDto.getEmail());
        job.setRecruiterName(recruiterDto.getFirstName()+ " "+recruiterDto.getLastName());
        jobRepository.save(job);
    }
}
