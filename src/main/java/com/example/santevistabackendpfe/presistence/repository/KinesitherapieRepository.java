package com.example.santevistabackendpfe.presistence.repository;

import com.example.santevistabackendpfe.presistence.entity.Kinesitherapie;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface KinesitherapieRepository extends MongoRepository<Kinesitherapie, String> {
    Optional<Kinesitherapie> findById(String id);

}
