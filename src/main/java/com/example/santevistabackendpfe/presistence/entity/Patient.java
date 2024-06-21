package com.example.santevistabackendpfe.presistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "Patients")
public class Patient {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String adresseVille;
    private String age;
    private String sexe;
    private int numTel;
    private String maladie;

    private LocalDate dateAdded;
    private LocalDate dateDeDece;





}
