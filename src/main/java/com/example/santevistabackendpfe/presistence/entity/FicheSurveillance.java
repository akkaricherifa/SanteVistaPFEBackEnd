package com.example.santevistabackendpfe.presistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "FicheSurveillances")
public class FicheSurveillance {
    @Id
    private String id;

}
