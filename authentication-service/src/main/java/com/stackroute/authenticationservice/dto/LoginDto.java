package com.stackroute.authenticationservice.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {

	private String usernameOrEmail;
	private String password;
	private String role;



}
