//package com.example.santevistabackendpfe.bootstrap;
//
//import com.example.santevistabackendpfe.model.internal.RoleEnumeration;
//import com.example.santevistabackendpfe.presistence.entity.MedecinJeune;
//import com.example.santevistabackendpfe.presistence.entity.MedecinSenior;
//import com.example.santevistabackendpfe.presistence.entity.Secretaire;
//import com.example.santevistabackendpfe.presistence.entity.UserEntity;
//import com.example.santevistabackendpfe.presistence.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class UserBootstrap  implements CommandLineRunner {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    @Override
//    public void run(String... args)  {
//        userRepository.deleteAll();
//        UserEntity user = new UserEntity();
//        user.setEmail("testme@yopmail.com");
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("admin123"));
//        user.setMobile("1234567890");
//        user.setGender("Male");
//        user.setAddress("Tunis");
//        user.setRole(RoleEnumeration.UserEntity);
//        userRepository.save(user);
//
//        MedecinSenior medecinSenior = new MedecinSenior();
//        medecinSenior.setEmail("");
//        medecinSenior.setUsername("");
//        medecinSenior.setPassword(passwordEncoder.encode(""));
//        medecinSenior.setMobile("");
//        medecinSenior.setGender("");
//        medecinSenior.setAddress("");
//        medecinSenior.setRole(RoleEnumeration.MEDECINSENIOR);
//        userRepository.save(medecinSenior);
//
//
//        Secretaire secretaire = new Secretaire();
//        secretaire.setEmail("student@yopmail.com");
//        secretaire.setUsername("karima");
//        secretaire.setPassword(passwordEncoder.encode("secretaire123"));
//        secretaire.setMobile("1234567890");
//        secretaire.setGender("Male");
//        secretaire.setAddress("Tunis");
//        secretaire.setSecretaireCardNumber("123456");
//        secretaire.setRole(RoleEnumeration.SECRETAIRE);
//        userRepository.save(secretaire);
//
//
//        MedecinJeune medecinJeune = new MedecinJeune();
//        medecinJeune.setEmail("student@yopmail.com");
//        medecinJeune.setUsername("karima");
//        medecinJeune.setPassword(passwordEncoder.encode("secretaire123"));
//        medecinJeune.setMobile("1234567890");
//        medecinJeune.setGender("Male");
//        medecinJeune.setAddress("Tunis");
//        medecinJeune.setMedecinJeuneCardNumber("123456");
//        medecinJeune.setRole(RoleEnumeration.MEDECINJEUNE);
//        userRepository.save(medecinJeune);
//    }
//
//
//
//    }
//
