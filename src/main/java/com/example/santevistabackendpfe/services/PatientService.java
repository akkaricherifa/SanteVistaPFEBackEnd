package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.entity.Patient;
import com.example.santevistabackendpfe.presistence.entity.UserEntity;
import com.example.santevistabackendpfe.presistence.repository.FicheSurveillanceRepository;
import com.example.santevistabackendpfe.presistence.repository.PatientRepository;
import com.example.santevistabackendpfe.presistence.repository.UserRepository;
import com.example.santevistabackendpfe.services.Interfaces.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService implements IPatientService {
    @Autowired
    PatientRepository pr;
    @Autowired
    FicheSurveillanceRepository fr;
    @Autowired
    UserRepository ur;

    @Override
    public List<Patient> getAllPatients() {
        return pr.findAll();
    }

    @Override
    public Patient addPatient(Patient p) {
        return pr.save(p);
    }

    @Override
    public Patient getPatientById(String id) {
        return pr.findById(id).get();
    }


    @Override
    public Patient updatePatient(Patient p, String id) {
        Optional<Patient> optionalPatient = pr.findById(id);
        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();
            existingPatient.setNom(p.getNom());
            existingPatient.setPrenom(p.getPrenom());
            existingPatient.setAdresseVille(p.getAdresseVille());
            existingPatient.setSexe(p.getSexe());
            existingPatient.setNumTel(p.getNumTel());
            existingPatient.setAge(p.getAge());
            existingPatient.setMaladie(p.getMaladie());
            return pr.save(existingPatient);
        } else {
            throw new RuntimeException("Patient not found with id " + id);
        }
    }
        @Override
        public void deletePatientById (String id){
            pr.deleteById(id);

        }


    public FicheSurveillance addFicheSurveillance(FicheSurveillance ficheSurveillanceDetails, String patientId) {

        Patient patient = pr.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        ficheSurveillanceDetails.setPatient(patient);
        ficheSurveillanceDetails.setFillTime(LocalDateTime.now());
        FicheSurveillance savedFicheSurveillance = fr.save(ficheSurveillanceDetails);

        pr.save(patient);

        return savedFicheSurveillance;
    }

    }

