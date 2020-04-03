package com.example.ecommerce.controller;

import com.example.ecommerce.dto.PasswordDTO;
import com.example.ecommerce.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordController {

    @Autowired
    PasswordService passwordService;

    @PostMapping(path = "/generate-password")
    public ResponseEntity<String> generatePassword(@RequestParam String email){
        if(passwordService.generatePassword(email)){
            return new ResponseEntity("email has been sent to reset the password", HttpStatus.OK);
        }
        return  new ResponseEntity("not able to generate password",HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordDTO passwordDTO){
        if(passwordService.confirmPassword(passwordDTO)){
            return new ResponseEntity<>("the entered password do not match confirm password",HttpStatus.NOT_ACCEPTABLE);
        }
        if(passwordService.resetPassword(passwordDTO)){
            return new ResponseEntity<>("password has been update",HttpStatus.OK);
        }
        return new ResponseEntity<>("token expired new token sent via mail",HttpStatus.BAD_REQUEST);
    }
}
