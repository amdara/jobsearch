package com.stackroute.emailservice.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Job {
    private String jobCategory;
    private String jobType;
    private String jobLocation;
    private String jobSkills;
    private String experience;
    private String salary;
    private String companyName;
    private String companyType;
    private String description;
    private String recruiterMailId;
    private String recruiterName;
}
