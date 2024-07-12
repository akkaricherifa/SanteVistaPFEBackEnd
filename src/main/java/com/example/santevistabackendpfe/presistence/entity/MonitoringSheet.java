package com.example.santevistabackendpfe.presistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "monitoring_sheets")
@Getter
@Setter
public class MonitoringSheet {
    @Id
    private String id;
    private String patientId;
    private LocalDateTime timestamp;
    private Map<String, Object> values;
}
