package com.stackroute.jobservice.dao;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecruiterDto {
    private long id;
    @NotBlank(message = "Company name is mandatory")
    private String companyName;
    private String companyType;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter valid email Id")
    private String email;
    @NotBlank(message = "FirsName is mandatory")
    private String firstName;
    private String lastName;
    private String designation;


}
