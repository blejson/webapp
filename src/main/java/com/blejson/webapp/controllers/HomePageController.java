package com.blejson.webapp.controllers;

import com.blejson.webapp.domain.PostMessage;
import com.blejson.webapp.domain.User;
import com.blejson.webapp.repositories.PostMessageRepository;
import com.blejson.webapp.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by Blejson on 12.08.2020
 */
@Controller
public class HomePageController {
    private final PostMessageRepository postMessageRepository;
    private final UserRepository userRepository;

    public HomePageController(PostMessageRepository postMessageRepository, UserRepository userRepository) {
        this.postMessageRepository = postMessageRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{userName}")
    public String getUserPage(@PathVariable("userName") String userName, Model model){
        Optional<User> user = userRepository.findByUserName(userName);
        User user1 = user.stream().findFirst().orElse(null);
        if(user.isPresent()){
            model.addAttribute("posts", postMessageRepository.findByAuthor(user1)); //finds all posts of user
            model.addAttribute("user", user1);
            return "/views/user";
        }
        model.addAttribute("posts", postMessageRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("postMessage", new PostMessage());
        return "redirect:/home";    //if parameter is invalid the user is redirected to homepage
    }

    @GetMapping(value= {"/home", "","/"})
    public String getHomePage(Model model){

        model.addAttribute("posts", postMessageRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("postMessage", new PostMessage());
        return "/home";
    }

    @PostMapping(value = {"/home","","/"})
    public String addPostMessage(PostMessage postMessage, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof MyUserDetails) {
            username = ((MyUserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<User> user = userRepository.findByUserName(username);
        user.ifPresent(postMessage::setAuthor);
        postMessageRepository.save(postMessage);
        model.addAttribute("posts", postMessageRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("postMessage", new PostMessage());
        return "/home";
    }
}
