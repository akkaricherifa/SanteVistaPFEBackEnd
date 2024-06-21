package com.example.santevistabackendpfe.presistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "Soins")
public class Soin {
    @Id
    private String id;
    private String type;
    private String description;
    private LocalDateTime fillTime;
    private String filledByName;

    @DBRef
    private Patient patient;
}
