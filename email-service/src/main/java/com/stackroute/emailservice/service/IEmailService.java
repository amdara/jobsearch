package com.stackroute.emailservice.service;

import com.stackroute.emailservice.model.Email;

public interface IEmailService {
    String sendEmail(Email emailRecruiter, Email emailJobseeker);

    String sendSignupMail(String email, String username);

}
