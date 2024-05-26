package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.presistence.entity.UserEntity;
import com.example.santevistabackendpfe.presistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ApplicationUserDetailsService  implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        if(optionalUserEntity.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        UserEntity userEntity = optionalUserEntity.get();
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.singletonList(userEntity.getRole()));
    }
}
