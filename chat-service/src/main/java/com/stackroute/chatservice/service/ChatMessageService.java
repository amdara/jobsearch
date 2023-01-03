package com.stackroute.chatservice.service;

import com.stackroute.chatservice.model.ChatMessage;
import com.stackroute.chatservice.model.MessageBody;

import java.util.List;

public interface ChatMessageService {
    Object addRoom(ChatMessage chatMessage);
     List<ChatMessage> getMessageByJobId(long jobId);
     Object updateMessage(MessageBody body, long jobId);
}
