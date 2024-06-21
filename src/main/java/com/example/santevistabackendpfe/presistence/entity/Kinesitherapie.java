package com.example.santevistabackendpfe.presistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@Document(collection = "SeancesKinesithérapie")
public class Kinesitherapie {
        @Id
        private String id;
        private String Seance_type;
        private String description;
        private LocalDateTime fillTime;
        private String filledByName;

        @DBRef
        private Patient patient;
}
