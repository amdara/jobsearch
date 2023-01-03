package com.stackroute.chatservice.model;



import java.io.Serializable;
import java.util.List;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="ChatMessages")
@Getter
@Setter
public class ChatMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Id
	private long jobId;
	private String jobseekerEmail;
	private String recruiterEmail;
	
	private List<MessageBody> iMessage;
	

	
	
	
	
	

}
