package com.stackroute.jobsearchservice.model;

import java.util.Date;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "Jobs")
public class JobSearch {

	@MongoId
	@Indexed(unique = true)
	private Long jobId;
	@Indexed(unique = true)
	private Long recruiterId;
	private String jobCategory;
	private String jobType;
	@NotNull(message = "Job location should not be null")
	private String jobLocation;
	@NotNull(message = "Job skills are required")
	private String jobSkills;
	@NotNull(message = "Provide a valid experience")
	private String experience;
	private String salary;
	@NotNull(message = "Company Name should not be null")
	private String companyName;
	private String companyType;
	@NotBlank
	private String description;
	private Date closeDate;
	private Date postedDate=new Date(System.currentTimeMillis());
	private Integer vacancy;
	@Indexed(unique = true)
	@Email(message = "Invalid email address")
	private String recruiterMailId;
	private String recruiterName;

}
