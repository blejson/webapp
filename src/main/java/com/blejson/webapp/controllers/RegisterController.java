package com.blejson.webapp.controllers;

import com.blejson.webapp.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Blejson on 11.08.2020
 */
@Controller
public class RegisterController {
    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user",new User());
        return "views/registerForm";
    }
}
