package com.stackroute.recruiterservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecruiterDto {
    private long id;
    @NotBlank(message = "Company name is mandatory")
    private String companyName;
    private String companyType;
    private String street;
    private String city;
    private String state;
    private String country;
    private String landmark;
    private String headquarters;
    @NotBlank(message = "FirsName is mandatory")
    private String firstName;
    private String lastName;
    private String designation;
    @NotBlank(message = "Phonenumber is mandatory")
    @Pattern(regexp = "^\\d{10}$",message = "Invalid mobile number entered")
    private String contactNumber;
}
