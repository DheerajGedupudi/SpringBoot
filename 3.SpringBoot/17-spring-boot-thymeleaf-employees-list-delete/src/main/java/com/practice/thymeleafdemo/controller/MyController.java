package com.practice.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController
{
    @GetMapping("/hello")
    public String sayHello(Model myModel)
    {
        myModel.addAttribute("theDate", new java.util.Date());

        return "helloworld";
    }
}
