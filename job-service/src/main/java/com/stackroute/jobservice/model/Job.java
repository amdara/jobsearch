package com.stackroute.jobservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "jobPost")
public class Job {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private Long jobId;
    private Long recruiterId;
    private String jobCategory;
    private String jobType;
    private String jobLocation;
    private String jobSkills;
    private String experienced;
    private String salary;
    private String companyName;
    private String companyType;
    private String description;
    private Date closeDate;
    private Date postedDate=new Date(System.currentTimeMillis());
    private String noOfVacancies;
    private String recruiterMail;
    private String recruiterName;
//    private List<JobDto> jobDtoList;
}
