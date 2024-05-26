package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.presistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

}
