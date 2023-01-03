package com.stackroute.emailservice.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDto {
    private String jobSeekerMail;
    private String jobSeekerName;
    private JobSearchDto jobSearchDto;
    private byte[] fileData;
}
