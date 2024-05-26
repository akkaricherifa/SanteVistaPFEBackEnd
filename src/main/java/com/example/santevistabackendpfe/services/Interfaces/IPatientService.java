package com.example.santevistabackendpfe.services.Interfaces;

import com.example.santevistabackendpfe.presistence.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPatientService {
    public List<Patient> getAllPatients();
    public Patient addPatient(Patient p);
    public Patient getPatientById(String id);
    public Patient updatePatient(Patient p);
    void deletePatientById(String id);
}
