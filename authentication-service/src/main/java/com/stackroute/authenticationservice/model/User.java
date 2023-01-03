package com.stackroute.authenticationservice.model;


import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;


    @Column(name = "username", unique = true, nullable = false, length = 20)
    private String username;


    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 64)
    private ERole role;

}
	
    
    
    

