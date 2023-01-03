package com.stackroute.authenticationservice.dto;

import lombok.*;

@Getter
@Setter
@Data
public class JwtResponse {

	private String accessToken;
	private String tokenType = "Bearer";


	public JwtResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
}
