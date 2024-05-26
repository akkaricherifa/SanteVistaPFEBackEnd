package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.presistence.entity.Patient;
import com.example.santevistabackendpfe.presistence.repository.PatientRepository;
import com.example.santevistabackendpfe.services.Interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService {
    @Autowired
    PatientRepository pr;
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
    public Patient updatePatient(Patient p) {
        return pr.save(p);
    }

    @Override
    public void deletePatientById(String id) {
        pr.deleteById(id);

    }
}
