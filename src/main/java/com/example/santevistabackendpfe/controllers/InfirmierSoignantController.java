package com.example.santevistabackendpfe.controllers;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.entity.Soin;
import com.example.santevistabackendpfe.presistence.repository.FicheSurveillanceRepository;
import com.example.santevistabackendpfe.presistence.repository.PatientRepository;
import com.example.santevistabackendpfe.services.FicheService;
import com.example.santevistabackendpfe.services.Interfaces.IFichePrescriptionService;
import com.example.santevistabackendpfe.services.Interfaces.IFicheService;
import com.example.santevistabackendpfe.services.Interfaces.IPatientService;
import com.example.santevistabackendpfe.services.Interfaces.ISoinService;
import com.example.santevistabackendpfe.services.PatientService;
import com.example.santevistabackendpfe.services.SoinService;
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
@RequestMapping("/private/InfirmierSoignant")
@PreAuthorize("hasAuthority('INFIRMIERSOIGNANT')")

public class InfirmierSoignantController {
    private final PatientRepository patientRepository;
    private final FicheSurveillanceRepository ficheSurveillanceRepository;
    @Autowired
    IPatientService ips;
    @Autowired
    IFicheService ifs;
    @Autowired
    ISoinService iss;
    @Autowired
    IFichePrescriptionService ifps;
    @Autowired
    private PatientService patientService;
    @Autowired
    FicheService ficheService;
    @Autowired
    SoinService soinService;


    //*********************** methode de la fiche *************************************
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

    //******************************* methode de soin ***************************************
    @GetMapping("/GetAllSoins")
    public List<Soin> getallSoins() {
        return iss.getAllSoins();
    }

    @GetMapping("/getSoin/{id}")

    public Soin getSoinparid(@PathVariable String id) {

        return iss.getSoinById(id);
    }


    @PostMapping("/{patientId}/addSoin")
    public ResponseEntity<Soin> addSoin(
            @PathVariable String patientId,
            @RequestBody Soin soin
    ) {
        try {
            // Récupérer le nom de l'utilisateur connecté
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String filledByName = authentication.getName();
            // Ajouter le nom de user au soin
            soin.setFilledByName(filledByName);
            Soin createdSoin = patientService.addSoin(soin, patientId);
            return ResponseEntity.ok(createdSoin);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
        @PutMapping("/modifSoin/{id}")
        public ResponseEntity<?> modifierSoin(@RequestBody Soin s, @PathVariable String id) {
            try {
                Soin updatedSoin = soinService.updateSoin(s, id);
                return ResponseEntity.ok(updatedSoin);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("erreur de modification");
            }
        }
    @DeleteMapping("/deleteSoin/{id}")
    public String deleteSoin(@PathVariable String id) {
        iss.deleteSoinById(id);
        return "le soin est supprimé avec succes";

    }
    }


