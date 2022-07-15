package com.practice.rest;

import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/test")
public class RestController
{
    @GetMapping("/hello")
    public String sayHello()
    {
        return "Hello World";
    }
}
