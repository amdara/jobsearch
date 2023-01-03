package com.stackroute.authenticationservice.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Data
public class SignUpDto {

	private String username;
	private String email;
	private String password;
	private String role;


	public SignUpDto() {

	}
}
