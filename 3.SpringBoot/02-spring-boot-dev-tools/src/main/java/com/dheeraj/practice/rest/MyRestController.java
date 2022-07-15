package com.dheeraj.practice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MyRestController
{
    @GetMapping("/")
    public String hello()
    {
        return "Hello World! the time on server is "+ LocalDateTime.now();
    }

    @GetMapping("/workout")
    public String getMyWorkout()
    {
        return "Run 10km";
    }

    @GetMapping("/fortune")
    public String getFortune()
    {
        return "Eat Bananas";
    }

}
