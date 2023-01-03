package com.stackroute.jobseekerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobSearchDto {

	private Long jobId;
	private Long recruiterId;
	private String jobCategory;
	private String jobType;
	private String jobLocation;
	private String jobSkills;
	private String experience;
	private String salary;
	private String companyName;
	private String companyType;
	private String description;
	private String closeDate;
	private String postedDate;
	private Integer vacancy;
	private String recruiterMailId;
	private String recruiterName;

}
