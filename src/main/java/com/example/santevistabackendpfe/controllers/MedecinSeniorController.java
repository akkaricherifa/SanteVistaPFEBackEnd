package com.example.santevistabackendpfe.controllers;

import com.example.santevistabackendpfe.presistence.entity.*;
import com.example.santevistabackendpfe.presistence.repository.InfirmierKineRepository;
import com.example.santevistabackendpfe.presistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/Medecins")
@PreAuthorize("hasAuthority('MEDECINSENIOR')")
public class MedecinSeniorController {
    private final UserRepository userRepository;



    @GetMapping("/MedecinsSeniors")
    public ResponseEntity<List<UserEntity>> users() {
        List<UserEntity> users = userRepository.findAll();
        users = users.stream().filter(MedecinSenior.class::isInstance).toList();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/MedecinsJeunes")
    public ResponseEntity<List<UserEntity>> users4() {
        List<UserEntity> users4 = userRepository.findAll();
        users4 = users4.stream().filter(MedecinJeune.class::isInstance).toList();
        return ResponseEntity.ok(users4);
    }


    @GetMapping("/InfirmierKines")
    public ResponseEntity<List<UserEntity>> users1() {
        List<UserEntity> users1 = userRepository.findAll();
        users1 = users1.stream().filter(InfirmierKine.class::isInstance).toList();
        return ResponseEntity.ok(users1);
    }

    @GetMapping("/InfirmierSurveillants")
    public ResponseEntity<List<UserEntity>> users2() {
        List<UserEntity> users2 = userRepository.findAll();
        users2 = users2.stream().filter(InfirmierSurveillant.class::isInstance).toList();
        return ResponseEntity.ok(users2);
    }

    @GetMapping("/InfirmierSoignants")
    public ResponseEntity<List<UserEntity>> users3() {
        List<UserEntity> users3 = userRepository.findAll();
        users3 = users3.stream().filter(InfirmierSoignant.class::isInstance).toList();
        return ResponseEntity.ok(users3);
    }

}
