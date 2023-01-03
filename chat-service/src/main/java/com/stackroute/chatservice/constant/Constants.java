package com.stackroute.chatservice.constant;

public class Constants {
	
	
	private Constants() {
	    throw new IllegalStateException("Constants class");
	}
	public static final String CHAT_DOES_NOT_EXIST = "chat.does.not.exist";
	public static final String CHAT_SAVED_SUCCESSFULLY = "chat.saved.successfully";
	public static final String SUCCESS_CODE = "success.code";
	public static final String CHAT_FETCHED_SUCCESSFULLY = "chat.fetched.successfully";
	public static final String DEFAULT_EXCEPTION_CODE = "default.exception.code";

	public static final String PRODUCER_RECRUITER_CHAT_QUEUE="recruiter_to_chat_message_queue";

	public static final String PRODUCER_RECRUITER_CHAT_ROUNTING_KEY="recruiter_to_chat_message_rounting_key";

	public static final String EXCHANGE="topic_exchange";
}

