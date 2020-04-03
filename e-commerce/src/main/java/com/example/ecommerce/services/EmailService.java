package com.example.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class EmailService {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    SimpleMailMessage simpleMailMessage;

    @Async
    public void emailToSeller(String toEmail){
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("Seller Account Created");
        simpleMailMessage.setText("account is inactive pls wait while we verify details");
        javaMailSender.send(simpleMailMessage);
    }

    @Async
    public void emailToCustomer(String toEmail, String activationToken, URI location){
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText(location +"\n"+ " or use the activation token->"+
                activationToken);
        javaMailSender.send(simpleMailMessage);
    }

    @Async
    public void statusMail(String toEmail,String status){
        simpleMailMessage.setFrom("admin-eCommerce@gmail.com");
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("Account status mail");
        simpleMailMessage.setText(status);
        javaMailSender.send(simpleMailMessage);
    }

    @Async
    public void resetPasswordMail(String toEmail,String token){
        simpleMailMessage.setFrom("admin-eCommerce@gmail.com");
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("password regeneration link");
        simpleMailMessage.setText("use the token to genrate ne password\n"+
                token);
        javaMailSender.send(simpleMailMessage);
    }

}
