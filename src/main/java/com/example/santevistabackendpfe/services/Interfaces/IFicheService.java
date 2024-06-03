package com.example.santevistabackendpfe.services.Interfaces;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;

import java.util.List;

public interface IFicheService {
    public List<FicheSurveillance> getAllFicheSurveillances();
    public FicheSurveillance addFicheFiche(FicheSurveillance f);
    public FicheSurveillance getFicheSurveillanceById(String id);
    public FicheSurveillance updateFicheSurveillance(FicheSurveillance f, String id);

    public FicheSurveillance validateFicheSurveillance (FicheSurveillance f, String id);
    void deleteFicheSurveillanceById(String id);

}
