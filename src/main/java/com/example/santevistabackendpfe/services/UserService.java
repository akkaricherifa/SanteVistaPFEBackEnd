package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.presistence.entity.MedecinSenior;
import com.example.santevistabackendpfe.presistence.entity.UserEntity;
import com.example.santevistabackendpfe.presistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<UserEntity> getUser(String id) {
        return userRepository.findById(id);
    }

}
