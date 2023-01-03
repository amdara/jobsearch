package com.stackroute.recruiterservice.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="recruiters")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Recruiter {
    @Id
    private long id;
    private String companyName;
    private String companyType;
    private String email;
    private String street;
    private String city;
    private String state;
    private String country;
    private String landmark;
    private String headquarters;
    private String firstName;
    private String lastName;
    private String designation;
    private String contactNumber;
}
