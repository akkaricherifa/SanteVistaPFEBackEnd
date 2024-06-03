package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.repository.FicheSurveillanceRepository;
import com.example.santevistabackendpfe.presistence.repository.PatientRepository;
import com.example.santevistabackendpfe.services.Interfaces.IFicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FicheService implements IFicheService {

    @Autowired
    FicheSurveillanceRepository fr;
    @Autowired
    PatientRepository patientRepository;
    @Override
    public List<FicheSurveillance> getAllFicheSurveillances() {
        return fr.findAll();
    }

    @Override
    public FicheSurveillance addFicheFiche(FicheSurveillance f) {
        return fr.save(f);
    }


    @Override
    public FicheSurveillance getFicheSurveillanceById(String id) {
        return fr.findById(id).get();
    }

    @Override
    public FicheSurveillance updateFicheSurveillance(FicheSurveillance f, String id) {
        Optional<FicheSurveillance> optionalFicheSurveillance = fr.findById(id);
        if (optionalFicheSurveillance.isPresent()) {
            FicheSurveillance existingFicheSurveillance = optionalFicheSurveillance.get();
            existingFicheSurveillance.setGcs(f.getGcs());
            existingFicheSurveillance.setRass(f.getRass());
            existingFicheSurveillance.setBurst_suppression(f.getBurst_suppression());
            existingFicheSurveillance.setCam_icu(f.getCam_icu());
            existingFicheSurveillance.setAt_delirium(f.getAt_delirium());
            existingFicheSurveillance.setMinimal_consciousness_state(f.getMinimal_consciousness_state());
            existingFicheSurveillance.setVegetative_state(f.getVegetative_state());
            existingFicheSurveillance.setBrain_death(f.getBrain_death());
            existingFicheSurveillance.setAcf_grade(f.getAcf_grade());
            existingFicheSurveillance.setArf_grade(f.getArf_grade());
            existingFicheSurveillance.setKdigo(f.getKdigo());
            existingFicheSurveillance.setWeight(f.getWeight());
            return fr.save(existingFicheSurveillance);
        } else {
            throw new RuntimeException("Patient not found with id " + id);
        }
    }



    @Override
    public FicheSurveillance validateFicheSurveillance(FicheSurveillance f, String id) {
        return null;
    }

    @Override
    public void deleteFicheSurveillanceById(String id) {
        fr.deleteById(id);

    }

}
