package com.blejson.webapp.controllers;

import com.blejson.webapp.domain.User;
import com.blejson.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Blejson on 11.08.2020
 */
@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyUserDetailsService userService;

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user",new User());
        return "views/registerForm";
    }
    @PostMapping("/register")
    public String registerUser(@Validated User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "views/registerForm";
        }
        if(userService.isUserPresent(user.getUserName())){
            model.addAttribute("exists",true);
            return "views/registerForm";
        }
        user.setRole("USER");
        user.setActive(true);
        userRepository.save(user);
        return "views/success";
    }
}
