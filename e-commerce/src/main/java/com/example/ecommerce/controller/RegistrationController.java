package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @GetMapping(path = "/testing")
    public String test(){
        return "hello this is testing, testing 123";
    }
}
