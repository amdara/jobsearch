package com.stackroute.recruiterservice.constant;

public class Constant {

    public  final static String SUCCESS_ADD_VALUE="Successfullt added..!!";
    public final static String EMPTY_DATA_RESPONSE="Empty data in the table";
    public final static String EMPTY_DATA_REQUEST="Empty paramater value..Please enter the value";
    public final static String NOT_FOUND_ID="Id is not found in the table..Please enter valid one..!!";
    public final static String SUCCESS_UPDATE_VALUE="Successfully updated..!!";
    public final static String SUCCESS_DELETE_VALUE="Successfully deleted..!!";
   //HTTP CODE
    public final static int SUCCESS_CODE=200;
    public final static int CLIENT_ERROR_CODE=400;

    //RabbitMQ producer message to job service
    public static final String PRODUCER_RECRUITER_TO_JOB_QUEUE="recruiter_message_queue";
    public static final String PRODUCER_RECRUITER_TO_JOB_ROUNTING_KEY="recruiter_message_rounting_key";

    //RabbitMQ's consumer message from authentication service
    public static final String CONSUMER_AUTHENTICATION_RECRUITER_QUEUE="authentication_recruiter_message_queue";

    //RabbitMQ producer message to chat service
    public static final String PRODUCER_RECRUITER_CHAT_QUEUE="recruiter_to_chat_message_queue";
    public static final String PRODUCER_RECRUITER_CHAT_ROUNTING_KEY="recruiter_to_chat_message_rounting_key";

    //RabbitMQ producer message to feedback service
    public static final String PRODUCER_RECRUITER_FEEDBACK_QUEUE="recruiter_to_feedback_message_queue";
    public static final String PRODUCER_RECRUITER_FEEDBACK_ROUNTING_KEY="recruiter_to_feedback_message_rounting_key";
    //RabbitMQ exchange
    public static final String EXCHANGE="topic_exchange";

}
