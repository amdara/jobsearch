package com.stackroute.emailservice.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmailValidationServiceTest {

    @Autowired
    EmailValidationService emailValidationService;

    @Test
    void validEmailTest() {
        String email = "abc@gmail.com";
        assertEquals(true, emailValidationService.isValidEmailAddress(email));
    }

    @Test
    void invalidEmailTest() {
        String email = "abc..gmail.com";
        assertEquals(false, emailValidationService.isValidEmailAddress(email));
    }
}
