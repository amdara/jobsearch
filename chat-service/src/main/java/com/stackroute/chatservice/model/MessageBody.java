package com.stackroute.chatservice.model;

import java.io.Serializable;
import java.time.LocalDateTime;


public class MessageBody implements Serializable  {
	
	private static final long serialVersionUID = 2L;

	
	private String senderMail;
	private String msgBody;
    private LocalDateTime createdAt=LocalDateTime.now();
    
    
    
	public MessageBody() {
		super();
	}
	
	
	public MessageBody(String senderMail, String mssgBody, LocalDateTime createdAt) {
		super();
		this.senderMail = senderMail;
		this.msgBody = mssgBody;
		this.createdAt = createdAt;
	}


	public String getSenderMail() {
		return senderMail;
	}
	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}
	public String getMessageBody() {
		return msgBody;
	}
	public void setMessageBody(String messageBody) {
		this.msgBody = messageBody;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
    
    

	

}
