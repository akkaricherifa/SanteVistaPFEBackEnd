package com.example.santevistabackendpfe.controllers;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.repository.FicheSurveillanceRepository;
import com.example.santevistabackendpfe.presistence.repository.PatientRepository;
import com.example.santevistabackendpfe.services.FicheService;
import com.example.santevistabackendpfe.services.Interfaces.IFichePrescriptionService;
import com.example.santevistabackendpfe.services.Interfaces.IFicheService;
import com.example.santevistabackendpfe.services.Interfaces.IPatientService;
import com.example.santevistabackendpfe.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/InfirmierSurveillant")
@PreAuthorize("hasAuthority('INFIRMIERSURVEILLANT')")

public class InfirmierSurveillantController {
    private final PatientRepository patientRepository;
    private final FicheSurveillanceRepository ficheSurveillanceRepository;
    @Autowired
    IPatientService ips;
    @Autowired
    IFicheService ifs;
    @Autowired
    IFichePrescriptionService ifps;
    @Autowired
    private PatientService patientService;
    @Autowired
    FicheService ficheService;


    //************************************* méthode de la fiche *************************************
    @GetMapping("/GetAllFiches")
    public List<FicheSurveillance> getallFiches() {
        return ifs.getAllFicheSurveillances();
    }

    @GetMapping("/getFiche/{id}")

    public FicheSurveillance getFicheparid(@PathVariable String id) {

        return ifs.getFicheSurveillanceById(id);
    }

    @PostMapping("/{patientId}/addFicheSurveillance")
    public ResponseEntity<FicheSurveillance> addFicheSurveillance(
            @PathVariable String patientId,
            @RequestBody FicheSurveillance ficheSurveillanceDetails
    ) {
        try {
            // Récupérer le nom de l'utilisateur connecté
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String filledByName = authentication.getName();

            // Ajouter le nom de user à la fiche de surveillance
            ficheSurveillanceDetails.setFilledByName(filledByName);
            FicheSurveillance createdFicheSurveillance = patientService.addFicheSurveillance(ficheSurveillanceDetails, patientId);
            return ResponseEntity.ok(createdFicheSurveillance);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
