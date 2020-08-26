package com.blejson.webapp.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Blejson on 26.08.2020
 */
@Controller
public class ChatController {
    @GetMapping("/c")
    public String getChatPage(){
        return "views/chat";
    }
}
