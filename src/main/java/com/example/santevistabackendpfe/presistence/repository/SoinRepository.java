package com.example.santevistabackendpfe.presistence.repository;


import com.example.santevistabackendpfe.presistence.entity.Soin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SoinRepository extends MongoRepository<Soin, String> {
    Optional<Soin> findById(String id);
}
