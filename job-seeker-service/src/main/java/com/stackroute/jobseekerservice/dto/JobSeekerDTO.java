package com.stackroute.jobseekerservice.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JobSeekerDTO {

    @NotEmpty
    private Long id;
    @NotEmpty(message = "name should not be empty")
    private String name;
//    @Email(message = "Provide some valid email ")
//    private String email;
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\d{10}$",message = "Invalid mobile number entered")
    private String phoneNumber;
    @NotEmpty(message = "Provide some valid experience")
    private String workExperience;
    private String currentLocation;
    @NotEmpty(message = "preferredLocation should not be empty")
    private String preferredLocation;
    private String highestEducation;
    private List<String> skills;
    private String address;
    //private byte[] resume;

}

