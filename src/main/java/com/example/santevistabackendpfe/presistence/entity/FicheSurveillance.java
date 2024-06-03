package com.example.santevistabackendpfe.presistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Getter
@Setter
@Document(collection = "FicheSurveillances")
public class FicheSurveillance {
    @Id
    private String id;
    private String gcs;
    private String rass;
    private String burst_suppression;
    private String cam_icu;
    private String at_delirium;
    private String minimal_consciousness_state;
    private String vegetative_state;
    private String brain_death;
    private String acf_grade;
    private String arf_grade;
    private String kdigo;
    private String weight;

    @DBRef
    private Patient patient;



}
