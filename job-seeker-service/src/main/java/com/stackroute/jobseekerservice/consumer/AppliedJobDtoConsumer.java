package com.stackroute.jobseekerservice.consumer;

import com.stackroute.jobseekerservice.dto.AppliedJobDto;
import com.stackroute.jobseekerservice.service.JobSeekerService;
import com.stackroute.jobseekerservice.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppliedJobDtoConsumer {

    @Autowired
    private JobSeekerService jobSeekerService;
    @RabbitListener(queues = Constants.QUEUE)
    public Object getJobDtoFromRabbitMQ(AppliedJobDto appliedJobDto) {
        log.info("printing "+appliedJobDto);
        return  jobSeekerService.applyJobs(appliedJobDto.getSeekerId(),appliedJobDto.getJobSearchDto());
    }
}
