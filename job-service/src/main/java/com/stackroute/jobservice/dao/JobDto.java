package com.stackroute.jobservice.dao;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobDto {

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
    private Date postedDate=new Date(System.currentTimeMillis());
    @NotEmpty(message = "Number of vacancies should be not empty")
    private String noOfVacancies;
    @Email(message = "Provide some valid email ")
    private String recruiterMail;
    private String recruiterName;

}
