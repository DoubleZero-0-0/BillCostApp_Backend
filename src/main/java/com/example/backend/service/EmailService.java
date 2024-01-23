package com.example.backend.service;

public interface EmailService {

    void sendVerificationEmail(String to, Integer verificationToken);
}
