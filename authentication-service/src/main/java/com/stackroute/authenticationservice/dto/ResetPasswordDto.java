package com.stackroute.authenticationservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResetPasswordDto
{
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    //private int otp;


}
