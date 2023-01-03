package com.stackroute.jobsearchservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.stackroute.jobsearchservice.dto.AppliedJobDto;
import com.stackroute.jobsearchservice.exceptions.ValueEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.jobsearchservice.dto.JobSearchDto;
import com.stackroute.jobsearchservice.exceptions.IdNotFoundException;
import com.stackroute.jobsearchservice.model.JobSearch;
import com.stackroute.jobsearchservice.repository.JobSearchRepository;
import com.stackroute.jobsearchservice.util.Constants;

/*
 * Business Implementations
 * @createdBy : Meena Nagari
 * @CreatedDate : 11/19/2022
 */
@Service
@Slf4j
public class JobSearchServiceImpl implements JobSearchService{

	@Autowired
	JobSearchRepository jobSearchRepository;

	@Autowired
	RabbitTemplate template;

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * @Description : This method is used to retrieve all jobs from DB
	 * @Param : It takes no parameter
	 * @returns : It returns List of all jobs
	 * @Throws : It throws ValueEmptyException if no data present in DB
	 * @Created by : Meena Nagari
	 * @Created Date : Nov 19,2022
	 */
	@Override
	public List<JobSearchDto> viewAllJobs() {
	List<JobSearch> jobList=jobSearchRepository.findAll();
	if(jobList.isEmpty()){
		throw new ValueEmptyException(Constants.DATA_RESPONSE); }
	log.info("Retrieving all jobs");
	return jobSearchRepository.findAll().stream().map((jobSearch) -> convertJobToDto(jobSearch)).collect(Collectors.toList());
	}

	/*
	 * @Description : This method is used to get jobs based on vacancy
	 * @Param : It takes vacancy as parameter
	 * @returns : It returns list of jobs based on the passed vacancy
	 * @Throws : It throws ValueEmptyException if no jobs found for passed vacancy from DB
	 * @Created by : Meena Nagari
	 * @Created Date : Nov 19,2022
	 */
	@Override
	public List<JobSearchDto> filterByVacancy(Integer vacancy) {
		List<JobSearch> jobVacancy=jobSearchRepository.findByVacancy(vacancy);
		if (jobVacancy.isEmpty()){
			throw new ValueEmptyException(Constants.DATA_REQUEST+" vacancy "+vacancy); }
		log.info("Retrieve Job by vacancy");
		return jobVacancy.stream().map((job) -> convertJobToDto(job)).collect(Collectors.toList());
	}

	/*
	 * @Description : This method is used to get jobs based on location
	 * @Param : It takes location as parameter
	 * @returns : It returns list of jobs based on the passed location
	 * @Throws : It throws ValueEmptyException if no jobs found for passed location from DB
	 * @Created by : Meena Nagari
	 * @Created Date : Nov 19,2022
	 */
	@Override
	public List<JobSearchDto> filterByLocation(String location) {
		List<JobSearch> jobs = jobSearchRepository.findByJobLocation(location);
		if(jobs.isEmpty()){
			throw new ValueEmptyException(Constants.DATA_REQUEST+" location "+location); }
		log.info("Retrieve Job by location");
		return jobs.stream().map((job) -> convertJobToDto(job)).collect(Collectors.toList());
	}

	/*
	 * @Description : This method is used to get jobs based on company name
	 * @Param : It takes company name as parameter
	 * @returns : It returns list of jobs based on the passed company name
	 * @Throws : It throws ValueEmptyException if no jobs found for passed company name from DB
	 * @Created by : Meena Nagari
	 * @Created Date : Nov 19,2022
	 */
	@Override
	public List<JobSearchDto> filterByCompanyName(String companyName) {
		List<JobSearch> companyNames = jobSearchRepository.findByCompanyName(companyName);
		if(companyNames.isEmpty()){
			throw new ValueEmptyException(Constants.DATA_REQUEST+" company "+companyName); }
		log.info("Retrieve Job by company name");
		return companyNames.stream().map((job) -> convertJobToDto(job)).collect(Collectors.toList());
	}

	/*
	 * @Description : This method is used to invoke apply job
	 * @Param : It takes seekerId and jobId as parameters
	 * @returns : It returns string whether job is applied or no seekedId exists message
	 * @Created by : Meena Nagari
	 * @Created Date : Nov 19,2022
	 */
	public String invokeApplyJob(Long seekerId,Long id) {
		Optional<JobSearch> jobSearch=jobSearchRepository.findById(id);
		if(jobSearch.isEmpty()){
			throw new IdNotFoundException(Constants.IdNotFound+id);
		}
		JobSearchDto jobDto=convertJobToDto(jobSearch.get());
		AppliedJobDto appliedJobDto=new AppliedJobDto();
		appliedJobDto.setJobSearchDto(jobDto);
		appliedJobDto.setSeekerId(seekerId);
		log.info("printing................."+appliedJobDto);
		Object message=template.convertSendAndReceive(Constants.EXCHANGE,Constants.ROUTING_KEY,appliedJobDto);
		return message.toString();
	}

	/*
	 * @Description : This method is used to convert Dto to Entity.
	 * @Param : It takes JobDto as parameter
	 * @returns : It returns Job Entity
	 * @Created by : Meena Nagari
	 * @Created Date : Nov 19,2022
	 */
	public JobSearch convertDtoToJob(JobSearchDto jobDto) {
		JobSearch job = modelMapper.map(jobDto, JobSearch.class);
		return job;
	}

	/*
	 * @Description : This method is used to convert Entity to Dto
	 * @Param : It takes Job as parameter
	 * @returns : It returns Dto
	 * @Created by : Meena Nagari
	 * @Created Date : Nov 19,2022
	 */
	public JobSearchDto convertJobToDto(JobSearch job) {
		JobSearchDto jobDto = modelMapper.map(job, JobSearchDto.class);
		return jobDto;
	}
}
