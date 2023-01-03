package com.stackroute.emailservice.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Email {
    private String jobSeekerName;
    private String recruiterName;
    private String jobName;
    private Job job;
    private String msgBody;
    private String subject;
    private String file;
    private String emailTo;
}
