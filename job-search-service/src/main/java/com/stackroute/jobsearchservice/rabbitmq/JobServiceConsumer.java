package com.stackroute.jobsearchservice.rabbitmq;

import com.stackroute.jobsearchservice.dto.JobDto;
import com.stackroute.jobsearchservice.dto.JobSearchDto;
import com.stackroute.jobsearchservice.model.JobSearch;
import com.stackroute.jobsearchservice.repository.JobSearchRepository;
import com.stackroute.jobsearchservice.service.JobSearchServiceImpl;
import com.stackroute.jobsearchservice.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobServiceConsumer {

    @Autowired
    private JobSearchRepository jobSearchRepository;

    @Autowired
    private ModelMapper modelMapper;

    @RabbitListener(queues = Constants.JOBSERVICE_QUEUE_1)
    public void getJobDtoFromRabbitMQ(JobDto jobDto){
        log.info("Message received from Job Service queue "+jobDto);
        JobSearchDto job=new JobSearchDto();
        job.setJobId(jobDto.getJobId());
        job.setJobCategory(jobDto.getJobCategory());
        job.setJobLocation(jobDto.getJobLocation());
        job.setJobType(jobDto.getJobType());
        job.setJobSkills(jobDto.getJobSkills());
        job.setCloseDate(jobDto.getCloseDate());
        job.setCompanyName(jobDto.getCompanyName());
        job.setCompanyType(jobDto.getCompanyType());
        job.setDescription(jobDto.getDescription());
        job.setExperience(jobDto.getExperienced());
        job.setPostedDate(jobDto.getPostedBy());
        job.setRecruiterId(jobDto.getRecruiterId());
        job.setRecruiterMailId(jobDto.getRecruiterMail());
        job.setSalary(jobDto.getSalary());
        job.setVacancy(Integer.valueOf(jobDto.getNoOfVacancies()));
        job.setRecruiterName(jobDto.getRecruiterName());
        JobSearch job1 = modelMapper.map(job, JobSearch.class);
        jobSearchRepository.save(job1);
   }
}
