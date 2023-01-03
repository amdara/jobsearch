package com.stackroute.jobseekerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private Long id;

    private String email;

    private String username;

    private String password;

    private String role;
}
