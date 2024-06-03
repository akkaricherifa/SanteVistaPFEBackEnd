package com.example.santevistabackendpfe.presistence.repository;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

import java.util.List;
public interface FicheSurveillanceRepository extends MongoRepository<FicheSurveillance, String> {
    Optional<FicheSurveillance> findById(String id);

//    FicheSurveillance findByPatientname(String patientname);
}
