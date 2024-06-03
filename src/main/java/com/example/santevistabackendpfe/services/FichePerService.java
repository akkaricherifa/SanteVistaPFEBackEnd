package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.presistence.entity.FichePerscription;
import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.repository.FichePrescriptionRepository;
import com.example.santevistabackendpfe.presistence.repository.FicheSurveillanceRepository;
import com.example.santevistabackendpfe.services.Interfaces.IFichePrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FichePerService implements IFichePrescriptionService {
    @Autowired
    FichePrescriptionRepository fpr;

    @Override
    public FichePerscription addFichePer(FichePerscription f) {
        return fpr.save(f);
    }
}
