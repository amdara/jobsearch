package com.stackroute.emailservice.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Signup {
    private long id;
    private String email;
    private String username;
    private String password;
    private String role;
}
