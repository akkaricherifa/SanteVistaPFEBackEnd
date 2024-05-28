package com.example.santevistabackendpfe.model.request;

import com.example.santevistabackendpfe.exception.ApplicationException;

public record LoginRequest(String email, String password){
    public LoginRequest {
        if (email == null || email.isBlank()) {
            throw new ApplicationException("Email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new ApplicationException("Password cannot be null or empty");
        }
    }
}
