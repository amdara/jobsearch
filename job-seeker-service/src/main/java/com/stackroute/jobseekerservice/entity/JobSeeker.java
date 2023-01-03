package com.stackroute.jobseekerservice.entity;


import com.stackroute.jobseekerservice.dto.AppliedJobDto;
import com.stackroute.jobseekerservice.dto.JobSearchDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "jobseeker")
public class JobSeeker {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private Long id;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private String workExperience;
    private String currentLocation;
    private String preferredLocation;
    private String highestEducation;
    private List<String> skills;
    private String address;
    private Set<JobSearchDto> jobs;
    private byte[] resume;
    private List<AppliedJobDto> appliedJobDtoList;

    public JobSeeker(Long id, String name, String email, String phoneNumber, String workExperience, String currentLocation, String preferredLocation, String highestEducation, List<String> skills, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.workExperience = workExperience;
        this.currentLocation = currentLocation;
        this.preferredLocation = preferredLocation;
        this.highestEducation = highestEducation;
        this.skills = skills;
        this.address = address;
    }
}
