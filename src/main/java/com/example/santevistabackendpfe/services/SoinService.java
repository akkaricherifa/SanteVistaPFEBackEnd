package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.entity.Patient;
import com.example.santevistabackendpfe.presistence.entity.Soin;
import com.example.santevistabackendpfe.presistence.repository.FicheSurveillanceRepository;
import com.example.santevistabackendpfe.presistence.repository.PatientRepository;
import com.example.santevistabackendpfe.presistence.repository.SoinRepository;
import com.example.santevistabackendpfe.services.Interfaces.ISoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoinService implements ISoinService {
    @Autowired
    SoinRepository sr;
    @Autowired
    PatientRepository patientRepository;
    @Override
    public List<Soin> getAllSoins() {
        return sr.findAll();
    }

    @Override
    public Soin addSoin(Soin s) {
        return sr.save(s);
    }

    @Override
    public Soin getSoinById(String id) {
        return sr.findById(id).get();
    }


    @Override
    public Soin  updateSoin(Soin s, String id) {
        Optional<Soin> optionalSoin = sr.findById(id);
        if (optionalSoin.isPresent()) {
            Soin existingSoin = optionalSoin.get();
            existingSoin.setType(s.getType());
            existingSoin.setDescription(s.getDescription());
            return sr.save(existingSoin);
        } else {
            throw new RuntimeException("Soin not found with id " + id);
        }
    }

    @Override
    public void deleteSoinById(String id) {
        sr.deleteById(id);

    }


}
