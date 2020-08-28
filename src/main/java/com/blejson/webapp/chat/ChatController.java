package com.blejson.webapp.chat;

import com.blejson.webapp.controllers.MyUserDetails;
import com.blejson.webapp.domain.User;
import com.blejson.webapp.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

/**
 * Created by Blejson on 26.08.2020
 */
@Controller
public class ChatController {
    private final UserRepository userRepository;

    public ChatController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/c")
    public String getChatPage(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof MyUserDetails) {
            username = ((MyUserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("user",username);
        return "views/chat";
    }
}
