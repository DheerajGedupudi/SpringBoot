package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController
{
    @GetMapping("/")
    public String showHomePage()
    {
        System.out.println("Home Page Loaded");
        return "home";
    }
}
