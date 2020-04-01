package com.example.ecommerce.services;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.UUID;

@Service
public class PasswordService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    public boolean generatePassword(String email){
        User user=userRepository.findByEmail(email);
        if (user==null){
            throw new NotFoundException("user with email "+email+" not found");
        }
        else{
            user.setResetToken(UUID.randomUUID().toString());
            user.setExpiryDate(2);
            emailService.resetPasswordMail(user.getEmail(),user.getResetToken());
            return true;
        }
    }
}
