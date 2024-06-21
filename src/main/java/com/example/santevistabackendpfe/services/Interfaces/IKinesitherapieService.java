package com.example.santevistabackendpfe.services.Interfaces;

import com.example.santevistabackendpfe.presistence.entity.Kinesitherapie;


import java.util.List;

public interface IKinesitherapieService {
    public List<Kinesitherapie> getAllSeanceskinesitherapie();
    public Kinesitherapie addSeanceKinesitherapie (Kinesitherapie k);
    public Kinesitherapie getSeanceKinesitherapieById(String id);
    public Kinesitherapie updateSeanceKinesitherapie(Kinesitherapie k, String id);

    void deleteSeanceKinesitherapieById(String id);
}
