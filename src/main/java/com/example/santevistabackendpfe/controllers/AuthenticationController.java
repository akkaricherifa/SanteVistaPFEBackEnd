package com.example.santevistabackendpfe.controllers;

import com.example.santevistabackendpfe.model.RegisterRequest;
import com.example.santevistabackendpfe.model.request.LoginRequest;
import com.example.santevistabackendpfe.model.response.LoginResponse;
import com.example.santevistabackendpfe.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/authentication-resources")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
//        return ResponseEntity.ok(authenticationService.login(loginRequest));
//    }
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    LoginResponse loginResponse =authenticationService.login(loginRequest);
    Map<String,Object> response=new HashMap<>();
    response.put("data",loginResponse);
    return ResponseEntity.ok(response);
}

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest registerRequest) {
        authenticationService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
