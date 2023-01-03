package com.stackroute.jobseekerservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    private String jobSeekerMail;
    private String jobSeekerName;
    private JobSearchDto jobSearchDto;
    private byte[] fileData;

}
