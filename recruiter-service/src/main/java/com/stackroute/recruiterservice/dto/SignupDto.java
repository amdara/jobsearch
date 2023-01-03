package com.stackroute.recruiterservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignupDto {

    private long id;
    private String email;
    private String username;
    private String password;
    private String role;

}
	
    
    
    

