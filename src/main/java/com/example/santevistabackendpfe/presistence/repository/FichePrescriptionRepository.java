package com.example.santevistabackendpfe.presistence.repository;

import com.example.santevistabackendpfe.presistence.entity.FichePerscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FichePrescriptionRepository extends MongoRepository<FichePerscription, String> {
}
