package com.stackroute.jobsearchservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.jobsearchservice.model.JobSearch;

@Repository
public interface JobSearchRepository extends MongoRepository<JobSearch, Long> {

	List<JobSearch> findByVacancy(Integer vacancy);
	List<JobSearch> findByCompanyType(String type);
	List<JobSearch> findByJobLocation(String location);
	List<JobSearch> findByCompanyName(String name);
}
