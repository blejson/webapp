package com.blejson.webapp.controllers;

import com.blejson.webapp.domain.Conversation;
import com.blejson.webapp.domain.PostMessage;
import com.blejson.webapp.domain.User;
import com.blejson.webapp.repositories.ConversationRepository;
import com.blejson.webapp.repositories.PostMessageRepository;
import com.blejson.webapp.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * Created by Blejson on 20.08.2020
 */
@Controller
public class UserPageController {
    private final PostMessageRepository postMessageRepository;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    public UserPageController(PostMessageRepository postMessageRepository, UserRepository userRepository, ConversationRepository conversationRepository) {
        this.postMessageRepository = postMessageRepository;
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }

    public User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof MyUserDetails) {
            username = ((MyUserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<User> user = userRepository.findByUserName(username);
        return user.stream().findFirst().orElse(null);
    }

    @GetMapping("/user/{userName}")
    public String getUserPage(@PathVariable("userName") String userName, Model model){
        Optional<User> user = userRepository.findByUserName(userName);
        User user1 = user.stream().findFirst().orElse(null);
        User currentUser = getCurrentUser();
        if(user.isPresent()){
            Optional<Conversation> conversationOptional = conversationRepository.findByUsers(user1, currentUser);
            Long id = 0L;
            if(conversationOptional.isPresent()) {
                Conversation conversation = conversationOptional.stream().findFirst().orElse(null);
                id = conversation.getId();
            }
            String role = "";
            List<User> friends = user1.getFriends();
            if(friends.contains(getCurrentUser())) role = "friend";
            else if(getCurrentUser().equals(user1)) role = "you";
            else role = "stranger";
            model.addAttribute("posts", postMessageRepository.findByAuthor(user1)); //finds all posts of user
            model.addAttribute("user", user1);
            model.addAttribute("role", role);
            model.addAttribute("id", id);
            return "/views/user";
        }
        model.addAttribute("posts", postMessageRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("postMessage", new PostMessage());
        return "redirect:/home";    //if parameter is invalid the user is redirected to homepage
    }

    @PostMapping("/user/{userName}")    //works for now, to upgrade later
    public String addUser(@PathVariable("userName") String userName, Model model){
        Optional<User> user = userRepository.findByUserName(userName);
        User user1 = user.stream().findFirst().orElse(null);
        User user2 = getCurrentUser();
        user1.addFriend(user2);
        user2.addFriend(user1);
        userRepository.save(user1);
        userRepository.save(user2);

        Conversation conversation = new Conversation(user1, user2); //TODO: check if doesn't exist
        conversationRepository.save(conversation);

        String role = "";
        List<User> friends = user1.getFriends();
        if(friends.contains(getCurrentUser())) role = "friend";
        else if(getCurrentUser().equals(user1)) role = "you";
        else role = "stranger";
        model.addAttribute("posts", postMessageRepository.findByAuthor(user1)); //finds all posts of user
        model.addAttribute("user", user1);
        model.addAttribute("role", role);
        model.addAttribute("id", conversation.getId());
        return "/views/user";
    }
}
