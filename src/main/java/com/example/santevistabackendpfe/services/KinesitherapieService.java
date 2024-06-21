package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.presistence.entity.Kinesitherapie;
import com.example.santevistabackendpfe.presistence.entity.Soin;
import com.example.santevistabackendpfe.presistence.repository.KinesitherapieRepository;
import com.example.santevistabackendpfe.presistence.repository.PatientRepository;
import com.example.santevistabackendpfe.services.Interfaces.IKinesitherapieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KinesitherapieService implements IKinesitherapieService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    KinesitherapieRepository kr;
    @Override
    public List<Kinesitherapie> getAllSeanceskinesitherapie() {
        return kr.findAll();
    }

    @Override
    public Kinesitherapie addSeanceKinesitherapie(Kinesitherapie k) {
        return kr.save(k);
    }

    @Override
    public Kinesitherapie getSeanceKinesitherapieById(String id) {
        return kr.findById(id).get();
    }

    @Override
    public Kinesitherapie updateSeanceKinesitherapie(Kinesitherapie k, String id) {
        Optional<Kinesitherapie> optionalKinesitherapie = kr.findById(id);
        if (optionalKinesitherapie.isPresent()) {
            Kinesitherapie existingKinesitherapie = optionalKinesitherapie.get();
            existingKinesitherapie.setSeance_type(k.getSeance_type());
            existingKinesitherapie.setDescription(k.getDescription());
            return kr.save(existingKinesitherapie);
        } else {
            throw new RuntimeException("séance de kiné not found with id " + id);
        }
    }

    @Override
    public void deleteSeanceKinesitherapieById(String id) {
        kr.deleteById(id);
    }
}
