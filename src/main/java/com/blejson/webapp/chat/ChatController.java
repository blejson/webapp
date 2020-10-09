package com.blejson.webapp.chat;

import com.blejson.webapp.controllers.MyUserDetails;
import com.blejson.webapp.domain.Conversation;
import com.blejson.webapp.domain.User;
import com.blejson.webapp.repositories.ConversationRepository;
import com.blejson.webapp.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Created by Blejson on 26.08.2020
 */
@Controller
public class ChatController {
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;

    public ChatController(UserRepository userRepository, ConversationRepository conversationRepository){
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }
    @GetMapping("/m/{id}")
    public String getChatPage(@PathVariable("id") String id, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof MyUserDetails) {
            username = ((MyUserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<User> user = userRepository.findByUserName(username);
        User currentUser = user.stream().findFirst().orElse(null);
        Optional<Conversation> conversation = conversationRepository.findByUserAndId(currentUser, Long.parseLong(id));
        if(conversation.isPresent()){
            model.addAttribute("user",username);
            model.addAttribute("id",id);
            return "views/chat";
        }
        return "redirect:/home";
    }
}
