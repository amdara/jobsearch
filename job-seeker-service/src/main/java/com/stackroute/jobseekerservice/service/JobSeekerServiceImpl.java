package com.stackroute.jobseekerservice.service;


import com.stackroute.jobseekerservice.dto.*;
import com.stackroute.jobseekerservice.entity.JobSeeker;
import com.stackroute.jobseekerservice.exception.IdNotFoundException;
import com.stackroute.jobseekerservice.repository.JobSeekerRepository;
import com.stackroute.jobseekerservice.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class JobSeekerServiceImpl implements JobSeekerService{


    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private RabbitTemplate template;


    /*
     * @Description: this method is used to update JobSeeker
     * @Param: It takes JobSeeker and id as parameter
     * @returns : It returns JobSeeker object
     * @throws:IdNotFoundException
     * @created by: Sakshi
     * @created date: 21 nov 2022
     *
     */
    @Override
    public JobSeeker updateJobSeeker(JobSeeker jobSeeker, Long id) throws IdNotFoundException {
        Optional<JobSeeker> optionalJobSeeker =jobSeekerRepository.findById(id);
        if(optionalJobSeeker.isEmpty())
            throw new IdNotFoundException("id not found for update");
        JobSeeker jobSeeker1=optionalJobSeeker.get();
        jobSeeker1.setName(jobSeeker.getName());
        jobSeeker1.setPhoneNumber(jobSeeker.getPhoneNumber());
        jobSeeker1.setWorkExperience(jobSeeker.getWorkExperience());
        jobSeeker1.setCurrentLocation(jobSeeker.getCurrentLocation());
        jobSeeker1.setPreferredLocation(jobSeeker.getPreferredLocation());
        jobSeeker1.setCurrentLocation(jobSeeker.getCurrentLocation());
        jobSeeker1.setSkills(jobSeeker.getSkills());
        jobSeeker1.setResume(jobSeeker.getResume());
        jobSeeker1.setAddress(jobSeeker.getAddress());
        jobSeeker1.setHighestEducation(jobSeeker.getHighestEducation());

        JobSeeker jobSeeker2=jobSeekerRepository.save(jobSeeker1);
        ChatAndFeedbackDto chatAndFeedbackDto = new ChatAndFeedbackDto();
        chatAndFeedbackDto.setId(jobSeeker1.getId());
        chatAndFeedbackDto.setEmail(jobSeeker1.getEmail());
        template.convertSendAndReceive(Constants.JOBSEEKER_EXCHANGE,Constants.CHAT_ROUTING_KEY,chatAndFeedbackDto);
        template.convertSendAndReceive(Constants.JOBSEEKER_EXCHANGE,Constants.FEEDBACK_ROUTING_KEY,chatAndFeedbackDto);
        log.info("updated JobSeeker by id ");
        return jobSeeker2;
    }

    /*
     * @Description: this method is used to delete JobSeeker
     * @Param: It takes id as parameter
     * @returns : void
     * @throws:IdNotFoundException
     * @created by: Sakshi
     * @created date: 21 nov 2022
     *
     */
    @Override
    public void deleteJobSeeker(long id) throws IdNotFoundException {
        Optional<JobSeeker> optionalJobSeeker =jobSeekerRepository.findById(id);
        if(optionalJobSeeker.isEmpty())
            throw new IdNotFoundException("id not found for delete");
        JobSeeker jobSeeker=optionalJobSeeker.get();
        jobSeekerRepository.delete(jobSeeker);
        log.info("delete JobSeeker by id");
    }


    /*
     * @Description: this method is used to get JobSeeker by id
     * @Param: It takes id as parameter
     * @returns : It returns JobSeeker object
     * @throws:IdNotFoundException
     * @created by: Sakshi
     * @created date: 21 nov 2022
     *
     */
    @Override
    public JobSeeker getJobSeekerById(Long id) throws IdNotFoundException {
        Optional<JobSeeker> jobSeekerId= jobSeekerRepository.findById(id);
        if(jobSeekerId.isEmpty())
            throw new IdNotFoundException("Id not present");
        log.info("retrieve JobSeeker by id");
        return jobSeekerId.get();
    }

    /*
     * @Description: this method is used to get all JobSeeker
     * @Param: void
     * @returns : It returns JobSeeker object
     * @throws:null
     * @created by: Sakshi
     * @created date: 21 nov 2022
     *
     */
    @Override
    public List<JobSeeker> getAllJobSeeker() {
        List<JobSeeker> jobSeekerList=jobSeekerRepository.findAll();
        log.info("retrieve all JobSeekers");
        return jobSeekerList;
    }

    @Override
    public String applyJobs(Long id,JobSearchDto jobData) {
        String message=null;
        Optional<JobSeeker> optionalJobSeeker= jobSeekerRepository.findById(id);
        if(optionalJobSeeker.isEmpty()) {
            message = "JobSeeker is not exits";
            log.info("JobSeeker is not exits");
            return message;
        }
            JobSeeker jobSeeker = optionalJobSeeker.get();
        if(jobSeeker.getResume()==null){
            message = "Please update your resume before applying.";
            log.info("Please update your resume before applying.");
            return message;
        }
            Set<JobSearchDto> jobList=null;
            if(jobSeeker.getJobs()!=null){
                jobList = jobSeeker.getJobs();
            }
            else {
                jobList = new HashSet<>();
            }
            if(!jobList.contains(jobData)) {
                jobList.add(jobData);
                jobSeeker.setJobs(jobList);
                jobSeekerRepository.save(jobSeeker);
                sendEmail(jobSeeker,jobData);
                message="JobSeeker applied successfully";
                log.info("JobSeeker applied successfully");
                return message;
            }
        else if(jobList.contains(jobData)){
            message="JobSeeker already applied to this job";
                log.info("JobSeeker already applied to this job");
                return message;
            }
        return message;
    }

    private  void sendEmail(JobSeeker jobSeeker,JobSearchDto jobSearchDto){

        EmailDto emailDto = new EmailDto();
        emailDto.setJobSeekerMail(jobSeeker.getEmail());
        emailDto.setJobSeekerName(jobSeeker.getName());
        emailDto.setFileData(jobSeeker.getResume());
        emailDto.setJobSearchDto(jobSearchDto);
        template.convertAndSend(Constants.JOBSEEKER_EXCHANGE,Constants.EMAIL_ROUTING_KEY,emailDto);
    }

    @Override
    public Set<JobSearchDto> appliedJobs(Long id) throws IdNotFoundException {
        Optional<JobSeeker> optionalJobSeeker= jobSeekerRepository.findById(id);
        if(optionalJobSeeker.isEmpty())
            throw new IdNotFoundException("Id not present");
        JobSeeker jobSeeker = optionalJobSeeker.get();
        return jobSeeker.getJobs();
    }

}
