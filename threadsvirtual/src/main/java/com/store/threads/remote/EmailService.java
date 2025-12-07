package com.store.threads.remote;

public class EmailService {

    public String sendEmail(String email) {
        System.out.println("Email sent to " +  email);
        return email;
    }
}
