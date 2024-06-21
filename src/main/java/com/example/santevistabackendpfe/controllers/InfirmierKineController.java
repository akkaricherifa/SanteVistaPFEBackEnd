package com.example.santevistabackendpfe.controllers;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.entity.Kinesitherapie;
import com.example.santevistabackendpfe.presistence.entity.Soin;
import com.example.santevistabackendpfe.presistence.repository.FicheSurveillanceRepository;
import com.example.santevistabackendpfe.presistence.repository.PatientRepository;
import com.example.santevistabackendpfe.services.FicheService;
import com.example.santevistabackendpfe.services.Interfaces.IFichePrescriptionService;
import com.example.santevistabackendpfe.services.Interfaces.IFicheService;
import com.example.santevistabackendpfe.services.Interfaces.IKinesitherapieService;
import com.example.santevistabackendpfe.services.Interfaces.IPatientService;
import com.example.santevistabackendpfe.services.KinesitherapieService;
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
@RequestMapping("/private/InfirmierKine")
@PreAuthorize("hasAuthority('INFIRMIERKINE')")
public class InfirmierKineController {

    private final PatientRepository patientRepository;
    private final FicheSurveillanceRepository ficheSurveillanceRepository;
    @Autowired
    IPatientService ips;
    @Autowired
    IFicheService ifs;
    @Autowired
    IFichePrescriptionService ifps;
    @Autowired
    IKinesitherapieService iks;
    @Autowired
    private PatientService patientService;
    @Autowired
    FicheService ficheService;
    @Autowired
    KinesitherapieService kinesitherapieService;

//*********************** methode de la fiche *************************************
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

    @GetMapping("/GetAllFiches")
    public List<FicheSurveillance> getallFiches() {
        return ifs.getAllFicheSurveillances();
    }

    @GetMapping("/getFiche/{id}")

    public FicheSurveillance getFicheparid(@PathVariable String id) {

        return ifs.getFicheSurveillanceById(id);
    }

    //**************************methode seance de kiné *****************************************
    @GetMapping("/GetAllSeances")
    public List<Kinesitherapie> getallSeances() {
        return iks.getAllSeanceskinesitherapie();
    }

    @GetMapping("/getSeancekine/{id}")

    public Kinesitherapie getSeanceparid(@PathVariable String id) {

        return iks.getSeanceKinesitherapieById(id);
    }


    @PostMapping("/{patientId}/addSeanceKine")
    public ResponseEntity<Kinesitherapie> addSeanceKine(
            @PathVariable String patientId,
            @RequestBody Kinesitherapie kinesitherapie
    ) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String filledByName = authentication.getName();
            kinesitherapie.setFilledByName(filledByName);
            Kinesitherapie createdkinesitherapie = patientService.addSeanceKinesitherapie(kinesitherapie, patientId);
            return ResponseEntity.ok(createdkinesitherapie);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/deleteSeance/{id}")
    public String deleteSeancekine(@PathVariable String id) {
        iks.deleteSeanceKinesitherapieById(id);
        return "la seance est supprimée avec succes";

    }
    @PutMapping("/modifSeancekine/{id}")
    public ResponseEntity<?> modifierSeance(@RequestBody Kinesitherapie k, @PathVariable String id) {
        try {
            Kinesitherapie updatedKinesitherapie = kinesitherapieService.updateSeanceKinesitherapie(k, id);
            return ResponseEntity.ok(updatedKinesitherapie);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("erreur de modification");
        }
    }
}
