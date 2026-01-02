package com.smtpbenchmark.services;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public class LoginService {
    

    @Value("${spring.mail.host}")
    private String hostName;

    @Value("${spring.mail.port}")
    private String hostPort;

    public void login(String username, String password) throws MessagingException{
       JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(hostName);
        mailSender.setPort(Integer.valueOf(hostPort));
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.connectiontimeout", "5000"); // Timeout di 5 secondi
        mailSender.testConnection();
    }
}
