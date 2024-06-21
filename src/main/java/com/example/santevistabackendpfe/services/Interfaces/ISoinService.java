package com.example.santevistabackendpfe.services.Interfaces;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.entity.Soin;

import java.util.List;

public interface ISoinService {
    public List<Soin> getAllSoins();
    public Soin addSoin (Soin s);
    public Soin getSoinById(String id);
    public Soin updateSoin(Soin s, String id);

    void deleteSoinById(String id);


}
