package com.example.santevistabackendpfe.presistence.repository;

import com.example.santevistabackendpfe.presistence.entity.MedecinSenior;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MedecinSeniorRepository extends MongoRepository<MedecinSenior, String> {
    Optional<MedecinSenior> findByUsername(String username);
}
