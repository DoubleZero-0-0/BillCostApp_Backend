package com.example.backend.service.impl;

import com.example.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String from;


    @Override
    public void sendVerificationEmail(String to, Integer verificationToken) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(String.valueOf(new InternetAddress(from,"BillCostApp")));
            message.setTo(to);
            message.setSubject("Account Verifications");
            message.setText("Your verification code is: " + verificationToken);

            javaMailSender.send(message);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
