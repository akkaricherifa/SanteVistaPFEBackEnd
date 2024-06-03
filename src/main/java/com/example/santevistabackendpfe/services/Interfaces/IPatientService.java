package com.example.santevistabackendpfe.services.Interfaces;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPatientService {
    public List<Patient> getAllPatients();
    public Patient addPatient(Patient p);
    public Patient getPatientById(String id);
    public Patient updatePatient(Patient p, String id);
    void deletePatientById(String id);

//    FicheSurveillance getFicheByPatientName(String patientname);

    public FicheSurveillance addFicheSurveillance(FicheSurveillance ficheSurveillanceDetails, String patientId);

}
