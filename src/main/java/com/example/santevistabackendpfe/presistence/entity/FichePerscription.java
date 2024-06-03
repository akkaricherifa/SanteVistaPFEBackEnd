package com.example.santevistabackendpfe.presistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "FichePerscription")
public class FichePerscription {
    @Id
    private String id;
    private String gcs;
    private String rass;
}
