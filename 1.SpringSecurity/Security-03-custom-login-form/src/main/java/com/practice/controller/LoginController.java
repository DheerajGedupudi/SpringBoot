package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController
{
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage()
    {
        System.out.println("Custom Login page loaded");
        return "plain-login";
    }
}
