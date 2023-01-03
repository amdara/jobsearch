package com.stackroute.jobservice.service.impl;

import com.stackroute.jobservice.dao.JobDto;
import com.stackroute.jobservice.dao.Status;
import com.stackroute.jobservice.exception.IdNotFoundException;
import com.stackroute.jobservice.model.Job;
import com.stackroute.jobservice.repository.JobRepository;
import com.stackroute.jobservice.service.JobService;
import com.stackroute.jobservice.service.SequenceGeneratorService;
import com.stackroute.jobservice.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @Autowired
    RabbitTemplate rabbitTemplate;
    /*
     * @Description : To add Job into database
     * @Returns : It returns Job which has been added
     * @Params : It takes Job as parameter
     * @createdBy : Rakesh Kumar Guru
     * @CreatedDate : 20/11/2022
     */

    @Override
    public JobDto postJob(JobDto jobDto){
        Job job1 = jobRepository.findById(sequenceGeneratorService.generateSequence(Job.SEQUENCE_NAME)-1).get();
        job1.setCompanyName(jobDto.getCompanyName());
        job1.setCompanyType(jobDto.getCompanyType());
        job1.setJobCategory(jobDto.getJobCategory());
        job1.setJobType(jobDto.getJobType());
        job1.setJobLocation(jobDto.getJobLocation());
        job1.setJobSkills(jobDto.getJobSkills());
        job1.setExperienced(jobDto.getExperienced());
        job1.setSalary(jobDto.getSalary());
        job1.setDescription(jobDto.getDescription());
        job1.setCloseDate(jobDto.getCloseDate());
        job1.setPostedDate(jobDto.getPostedDate());
        job1.setNoOfVacancies(jobDto.getNoOfVacancies());
        Job postJob = jobRepository.save(job1);
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_1, Constants.ROUTING_KEY_1, postJob);
        return this.modelMapper.map(postJob, JobDto.class);
}

    @Override
    public List<JobDto> getAllPosts() {
        List<Job> job= this.jobRepository.findAll();
        List<JobDto> jobDto = job.stream().map((jobs) -> this.modelMapper.map(jobs, JobDto.class)).collect(Collectors.toList());
        return jobDto;
    }
    /*
     * @Description : To update Jobs by using it's id
     * @Returns : It returns Updated Product
     * @Params : It takes Job ID and Job as parameters
     * @createdBy : Rakesh Kumar Guru
     * @CreatedDate : 20/11/2022
     */

    @Override
    public JobDto updateJobById(Long jobId, JobDto jobDto) {
        Job job1 = jobRepository.findById(jobId)
                .orElseThrow(()-> new IdNotFoundException(jobId+Constants.EMPTY_DATA_RESPONSE));

        job1.setJobCategory(jobDto.getJobCategory());
        job1.setJobType(jobDto.getJobType());
        job1.setJobLocation(jobDto.getJobLocation());
        job1.setJobSkills(jobDto.getJobSkills());
        job1.setExperienced(jobDto.getExperienced());
        job1.setSalary(jobDto.getSalary());
        job1.setDescription(jobDto.getDescription());
        job1.setCloseDate(jobDto.getCloseDate());
        job1.setPostedDate(jobDto.getPostedDate());
        job1.setNoOfVacancies(jobDto.getNoOfVacancies());
        Job updateJob = jobRepository.save(job1);
        return this.modelMapper.map(updateJob, JobDto.class);
    }
    /*
     * @Description : To delete Job by using it's id
     * @Params : It takes Job ID as parameter
     * @createdBy : Rakesh Kumar Guru
     * @CreatedDate : 20/11/2022
     */
    @Override
    public Status deleteJobById(Long jobId) {
        Job job = this.jobRepository.findById(jobId)
                .orElseThrow(()-> new IdNotFoundException(jobId+Constants.EMPTY_ID));
        this.jobRepository.delete(job);
        return new Status(Constants.DELETE_MESSAGE, HttpStatus.OK);
    }
    /*
     * @Description : this method is used for convert dto to entity.
     * @Params : It takes JobDto as parameter
     * @createdBy : Rakesh Kumar Guru
     * @CreatedDate : 20/11/2022
     */
    public Job dtoToJob(JobDto jobDto){
        Job job = this.modelMapper.map(jobDto, Job.class);
        return job;
    }
    /*
     * @Description : this method is used for convert entity to dto.
     * @Params : It takes job as parameter
     * @createdBy : Rakesh Kumar Guru
     * @CreatedDate : 20/11/2022
     */
    public JobDto jobToDto(Job job){
        JobDto jobDto = this.modelMapper.map(job, JobDto.class);
        return jobDto;
    }
}
