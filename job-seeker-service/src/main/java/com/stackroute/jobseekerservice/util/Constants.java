package com.stackroute.jobseekerservice.util;

public class Constants {

    /* exchange */
    public static final String JOBSEEKER_EXCHANGE="jobseeker_exchange";


    /* email queue and key */
    public static final String EMAIL_QUEUE="applyJobQueue";
    public static final String EMAIL_ROUTING_KEY="applyJobRoutingKey";

    /* job search queue and key */
    public static final String QUEUE= "job_search_queue";
    public static final String JOB_QUEUE= "job_producer_queue";
    public static final String ROUTING_KEY = "job_producing_routing_key";

    /* chat queue and key */
    public static final String CHAT_QUEUE= "chat_queue";
    public static final String CHAT_ROUTING_KEY="chat_routing_key";

    /* feedback queue and key */
    public static final String FEEDBACK_QUEUE= "feedback_queue";
    public static final String FEEDBACK_ROUTING_KEY="feedback_routing_key";

    public static final String CONSUMER_AUTHENTICATION_JOBSEEKER_QUEUE="authentication_jobseeker_message_queue";

}
