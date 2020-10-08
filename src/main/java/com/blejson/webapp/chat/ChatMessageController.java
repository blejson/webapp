package com.blejson.webapp.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by Blejson on 24.08.2020
 */
@Controller
public class ChatMessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void sendMessage(ChatMessage chatMessage){
        simpMessagingTemplate.convertAndSend("/topic/messages", chatMessage);
    }
}