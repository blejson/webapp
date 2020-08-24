package com.blejson.webapp.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by Blejson on 24.08.2020
 */
@Controller
public class ChatMessageControler {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String get(ChatMessage chatMessage){
        return "";
    }
}
