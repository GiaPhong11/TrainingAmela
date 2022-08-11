package com.example.validation.controller;

import com.example.validation.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class InfoController {

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("Controller");
        model.addAttribute("user", new User());
        return "Index";
    }



    @PostMapping("/info")
    public String showInfo(@Valid @ModelAttribute("user") User user,
                           BindingResult result) {
        new User().validate(user,result);
        System.out.println(user);
        try {
            if (result.hasErrors()) {
                System.out.println("False");
                return "Index";
            }
            return "Info";
        }catch (Exception e) {
            System.out.println("Loi :" + e.getMessage());
            return "Index";
        }
    }
}

