package com.example.santevistabackendpfe.presistence.repository;

import com.example.santevistabackendpfe.presistence.entity.InfirmierKine;
import com.example.santevistabackendpfe.presistence.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InfirmierKineRepository extends MongoRepository<InfirmierKine, String> {
    Optional<InfirmierKine> findByUsername(String username);

}
