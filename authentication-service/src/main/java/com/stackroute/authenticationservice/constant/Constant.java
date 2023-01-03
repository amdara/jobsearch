package com.stackroute.authenticationservice.constant;

public class Constant {

   /* public  final static String SUCCESS_ADD_VALUE="Successfullt added..!!";
    public final static String EMPTY_DATA_RESPONSE="Empty data in the table";
    public final static String EMPTY_DATA_REQUEST="Empty paramater value..Please enter the value";
    public final static String NOT_FOUND_ID="Id is not found in the table..Please enter valid one..!!";
    public final static String SUCCESS_UPDATE_VALUE="Successfully updated..!!";
    public final static String SUCCESS_DELETE_VALUE="Successfully deleted..!!";

    //HTTP CODE
    public final static int SUCCESS_CODE=200;
    public final static int CLIENT_ERROR_CODE=400;*/



    //Signup Email
    public static final String PRODUCER_AUTHENTICATION_SIGNUPEMAIL_QUEUE="signupQueue";
    public static final String PRODUCER_AUTHENTICATION_SIGNUPEMAIL_EXCHANGE="signupExchange";
    public static final String PRODUCER_AUTHENTICATION_SIGNUPEMAIL_ROUNTING_KEY="signupRoutingKey";

    //Recruiter
    public static final String PRODUCER_AUTHENTICATION_RECRUITER_QUEUE="authentication_recruiter_message_queue";
    public static final String PRODUCER_AUTHENTICATION_RECRUITER_EXCHANGE="authentication_recruiter_message_exchange";
    public static final String PRODUCER_AUTHENTICATION_RECRUITER_ROUTING_KEY="authentication_recruiter_message_routing_key";

    //Job_seeker
    public static final String PRODUCER_AUTHENTICATION_JOBSEEKER_QUEUE="authentication_jobseeker_message_queue";
    public static final String PRODUCER_AUTHENTICATION_JOBSEEKER_EXCHANGE="authentication_jobseeker_message_exchange";
    public static final String PRODUCER_AUTHENTICATION_JOBSEEKER_ROUTING_KEY="authentication_jobseeker_message_routing_key";

}
