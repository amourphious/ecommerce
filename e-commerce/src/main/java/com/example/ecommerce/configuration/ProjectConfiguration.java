package com.example.ecommerce.configuration;

import org.apache.logging.log4j.message.SimpleMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class ProjectConfiguration {

    @Autowired
    EmailConfig emailConfig;

    @Bean
    public JavaMailSenderImpl getJavaMailSender(){
        JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
        javaMailSender.setHost(emailConfig.getHost());
        javaMailSender.setPort(emailConfig.getPort());
        javaMailSender.setUsername(emailConfig.getUsername());
        javaMailSender.setPassword(emailConfig.getPassword());
        return javaMailSender;
    }

    @Bean
    public SimpleMailMessage getMailMessage(){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("e-commerce@gmail.com");
        simpleMailMessage.setSubject("account activation link");
        return simpleMailMessage;
    }

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper;
    }

}
