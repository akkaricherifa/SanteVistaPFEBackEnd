package com.example.santevistabackendpfe.presistence.repository;

import com.example.santevistabackendpfe.presistence.entity.Patient;
import com.example.santevistabackendpfe.presistence.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends MongoRepository<Patient, String> {
    Optional<Patient> findById(String id);
//    Optional<Patient> findByName(String patientname);

    List<Patient> findByDateAdded(LocalDate date);




}
