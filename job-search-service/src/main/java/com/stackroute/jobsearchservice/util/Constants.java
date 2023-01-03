package com.stackroute.jobsearchservice.util;

public class Constants {
	
	public static final String IdNotFound="Job not found for Id: ";
	public  final static String ADD_SUCCESS="Added Successfully...!!";
	public final static String DATA_RESPONSE="No data in the table";
	public final static String DATA_REQUEST="No jobs found for";
	public final static String UPDATE_VALUE="Updated Successfully...!!";
	public final static String DELETE_VALUE="Deleted Successfully..!!";
	public final static String NOT_FOUND="No Job found to apply";



	//HTTP CODE
	public final static int SUCCESS_CODE=200;
	public final static int CLIENT_ERROR_CODE=400;

	public static final String QUEUE= "job_search_queue";
	public static final String EXCHANGE= "job_search_exchange";
	public static final String ROUTING_KEY = "job_search_routing_key";

	public static final String JOBSERVICE_QUEUE_1="job_message_queue";
	public static final String ROUTING_KEY_1="job_message_routing_key";

	public static final String JOB_QUEUE= "job_producer_queue";


}
