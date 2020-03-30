package com.example.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    SimpleMailMessage simpleMailMessage;

    public void emailToSeller(String toEmail){
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("Seller Account Created");
        simpleMailMessage.setText("account is inactive pls wait while we verify details");
        javaMailSender.send(simpleMailMessage);
    }

    public void emailToCustomer(String toEmail, String activationToken, String path){
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText("go to the link-->" + path+"\n"+
                " or use the activation token->"+
                activationToken);
        javaMailSender.send(simpleMailMessage);
    }

}
