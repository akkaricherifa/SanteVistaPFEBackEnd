package com.example.santevistabackendpfe.presistence.repository;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.entity.MonitoringSheet;
import com.example.santevistabackendpfe.presistence.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import java.util.List;
public interface FicheSurveillanceRepository extends MongoRepository<FicheSurveillance, String> {
    Optional<FicheSurveillance> findById(String id);
    List<FicheSurveillance> findByPatient(Patient patient);
    List<FicheSurveillance> findByPatientAndFillTimeBetween(Patient patient, LocalDateTime start, LocalDateTime end);

}
