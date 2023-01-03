package com.stackroute.authenticationservice.Exception;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleNotFoundException extends RuntimeException
{
    private String message;


}
