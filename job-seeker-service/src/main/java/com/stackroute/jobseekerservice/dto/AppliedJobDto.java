package com.stackroute.jobseekerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppliedJobDto {

    private Long seekerId;
    JobSearchDto jobSearchDto;

}
