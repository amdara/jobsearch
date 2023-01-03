package com.stackroute.emailservice.util;

public class Constants {
    public static final String SUCCESS_MESSAGE = "Mail Sent Successfully!";
    public static final String FAILURE_MESSAGE = "Error while Sending Mail!";
    public static final String INVALID_EMAIL = "Email is invalid!";
    public static final String RECRUITER_SUBJECT = "New Applicant";
    public static final String JOBSEEKER_SUBJECT = "Applied Successfully";
    public static final String SIGNUP_SUBJECT = "Welcome!";
    public static final String INVALID_FILE = "Please upload valid file";
    public static final String SIGNUP_QUEUE = "signupQueue";
    public static final String SIGNUP_ROUTING_KEY = "signupRoutingKey";
    public static final String APPLY_JOB_QUEUE = "applyJobQueue";
    public static final String APPLY_JOB_ROUTING_KEY = "applyJobRoutingKey";
    public static final String EXCHANGE = "exchange";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
}
