package com.example.backend.controllers;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AppController {

//    onliy login and register can access without token
    @GetMapping("/login")
    public String testing()
    {
        return "Hi i am a Bot, Hello ATuL";
    }

}