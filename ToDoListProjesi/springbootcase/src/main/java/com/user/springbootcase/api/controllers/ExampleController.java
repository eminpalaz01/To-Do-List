package com.user.springbootcase.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleController {
  
    @GetMapping("/hello")
    public String hello() {
        return "Hello, API is running!";
    }
}
