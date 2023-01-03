package com.stackroute.jobsearchservice.dto;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobDto {
    @MongoId
    @Indexed(unique = true)
    private Long jobId;
    private Long recruiterId;
    private String jobCategory;
    private String jobType;
    @NotEmpty(message = "Job location should not be empty")
    private String jobLocation;
    @NotNull(message = "Job skills are mandatory")
    private String jobSkills;
    @NotEmpty(message = "Provide some valid experience")
    private String experienced;
    private String salary;
    private String companyName;
    private String companyType;
    @NotBlank
    private String description;
    private Date closeDate;
    private Date postedBy;
    @NotEmpty(message = "Number of vacancies should be not empty")
    private String noOfVacancies;
    @Email(message = "Provide some valid email ")
    private String recruiterMail;
    private String recruiterName;

}
