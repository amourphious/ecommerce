package com.example.ecommerce.services;

import com.example.ecommerce.dto.PasswordDTO;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.ConfirmationTokenExpiredException;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class PasswordService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    public boolean generatePassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("user with email " + email + " not found");
        } else {
            user.setResetToken(UUID.randomUUID().toString());
            user.setExpiryDate(2);
            userRepository.save(user);
            emailService.resetPasswordMail(user.getEmail(), user.getResetToken());
            return true;
        }
    }

    public boolean confirmPassword(PasswordDTO passwordDTO){
        if(!passwordDTO.getPassword().equals(passwordDTO.getConfirmPassword())){
            return true;
        }
        return false;
    }

    public Boolean resetPassword(PasswordDTO passwordDTO) {
        User user = userRepository.findByResetToken(passwordDTO.getResetToken());
        if (user == null) {
            throw new ConfirmationTokenExpiredException("the token given is not valid");
        }

        Calendar cal = Calendar.getInstance();
        if ((user.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            System.out.println("token expired");
            user.setResetToken(UUID.randomUUID().toString());
            user.setExpiryDate(2);
            userRepository.save(user);
            emailService.resetPasswordMail(user.getEmail(), user.getResetToken());
            return false;
        } else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
            user.setResetToken(null);
            userRepository.save(user);
            return true;
        }

    }
}