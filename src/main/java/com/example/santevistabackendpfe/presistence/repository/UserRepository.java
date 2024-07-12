package com.example.santevistabackendpfe.presistence.repository;

import com.example.santevistabackendpfe.presistence.entity.InfirmierKine;

import com.example.santevistabackendpfe.presistence.entity.MedecinSenior;
import com.example.santevistabackendpfe.presistence.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
//    Optional<UserEntity> findByUsername(String email);
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(String id);


}
