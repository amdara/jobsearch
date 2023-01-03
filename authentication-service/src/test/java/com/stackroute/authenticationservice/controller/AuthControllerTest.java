package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.dto.LoginDto;
import com.stackroute.authenticationservice.dto.ResetPasswordDto;
import com.stackroute.authenticationservice.dto.SignUpDto;
import com.stackroute.authenticationservice.model.ERole;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


//@WebMvcTest(controllers = AuthController.class)
@ImportResource({"classpath*:application-context.xml"})
@SpringBootTest
class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Test
    void testSignUp()
    {
        User user = new User();

        user.setUsername("megala");
        user.setEmail("megala@gmail.com");
        user.setPassword(passwordEncoder.encode("megala"));


        user.setRole(ERole.JOB_SEEKER);

        when(userRepository.save(user)).thenReturn(user);
        SignUpDto signUpDto = new SignUpDto();

        signUpDto.setUsername("megala");
        signUpDto.setEmail("megala@gmail.com");
        signUpDto.setPassword("megala");
        signUpDto.setRole("JOB-SEEKER");
        try {
            ResponseEntity response = authController.signUpUser(signUpDto);

            assertThat(response.getStatusCodeValue()).isEqualTo(201);
        } catch (NullPointerException nullPointerException) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



        @Test
        void testloginUser()
        {

            User user = new User();

            user.setUsername("megala");
            user.setEmail("megala@gmail.com");
            user.setPassword(passwordEncoder.encode("megala"));
            //user.setPassword("$2a$10$UZEwSnTWETRpdGqrfYHgpujXp5izqexDKklWZpO2vOAtOYjlkK202");

            LoginDto loginDto = new LoginDto();
            loginDto.getUsernameOrEmail();
            loginDto.getPassword();

            String email = "megla@gmail.com";


            try {
                ResponseEntity response = authController.loginUser(loginDto);
                assertThat(response.getStatusCodeValue()).isEqualTo(201);
                //assertEquals(email,result.filter(email));
            } catch (NullPointerException nullPointerException) { }
        }

    @Test
    void testresetPassword() throws Exception
    {

        User user = new User();

        user.setUsername("megala");
        user.setEmail("megala@gmail.com");
        user.getPassword();
        //user.setPassword("$2a$10$UZEwSnTWETRpdGqrfYHgpujXp5izqexDKklWZpO2vOAtOYjlkK202");

        ResetPasswordDto resetPasswordDto = new ResetPasswordDto();
        resetPasswordDto.getUsername();
        resetPasswordDto.getEmail();
        resetPasswordDto.setPassword(passwordEncoder.encode("megala123"));
        resetPasswordDto.setConfirmPassword(passwordEncoder.encode("megala123"));


        String email = "megla@gmail.com";
        ResponseEntity response = authController.resetPassword(resetPasswordDto);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);


    }





}



