package com.example.santevistabackendpfe.controllers;

import com.example.santevistabackendpfe.presistence.entity.*;
import com.example.santevistabackendpfe.presistence.repository.UserRepository;
import com.example.santevistabackendpfe.services.FicheService;
import com.example.santevistabackendpfe.services.Interfaces.IFicheService;
import com.example.santevistabackendpfe.services.Interfaces.IPatientService;
import com.example.santevistabackendpfe.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/Medecins")
@PreAuthorize("hasAuthority('MEDECINJEUNE')")
public class MedecinJeuneController {
    private final UserRepository userRepository;
    @Autowired
    IPatientService ips;
    @Autowired
    IFicheService ifs;
    @Autowired
    private PatientService patientService;
    @Autowired
    private FicheService ficheService;


    //*********************************** get listes de personnels de santé ************************************
    @GetMapping("/MedecinsSeniorss")
    public ResponseEntity<List<UserEntity>> users() {
        List<UserEntity> users = userRepository.findAll();
        users = users.stream().filter(MedecinSenior.class::isInstance).toList();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/MedecinsJeuness")
    public ResponseEntity<List<UserEntity>> users4() {
        List<UserEntity> users4 = userRepository.findAll();
        users4 = users4.stream().filter(MedecinJeune.class::isInstance).toList();
        return ResponseEntity.ok(users4);
    }

    @GetMapping("/InfirmierKiness")
    public ResponseEntity<List<UserEntity>> users1() {
        List<UserEntity> users1 = userRepository.findAll();
        users1 = users1.stream().filter(InfirmierKine.class::isInstance).toList();
        return ResponseEntity.ok(users1);
    }

    @GetMapping("/InfirmierSurveillantss")
    public ResponseEntity<List<UserEntity>> users2() {
        List<UserEntity> users2 = userRepository.findAll();
        users2 = users2.stream().filter(InfirmierSurveillant.class::isInstance).toList();
        return ResponseEntity.ok(users2);
    }

    @GetMapping("/InfirmierSoignantss")
    public ResponseEntity<List<UserEntity>> users3() {
        List<UserEntity> users3 = userRepository.findAll();
        users3 = users3.stream().filter(InfirmierSoignant.class::isInstance).toList();
        return ResponseEntity.ok(users3);
    }

    //**********************/*****************les méthodes des Patients***************************************
    @GetMapping("/allP")
    public List<Patient> getall(){
        return ips.getAllPatients();
    }
    @GetMapping("/patient/{id}")

    public Patient getparid(@PathVariable String id){

        return ips.getPatientById(id);
    }
    @PutMapping("/modifierPa/{id}")
    public ResponseEntity<?> modifier(@RequestBody Patient p, @PathVariable String id) {
        try {
            Patient updatedPatient = patientService.updatePatient(p, id);
            return ResponseEntity.ok(updatedPatient);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body("Erreur : Patient non trouvé");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur de modification");
        }
    }

    //    **************************************méthodes des fiches *************************************//
    @GetMapping("/GetAllFichess")
    public List<FicheSurveillance> getallFiches() {
        return ifs.getAllFicheSurveillances();
    }


    @PutMapping("/ModifierUneFiche/{id}")
    public ResponseEntity<?> modifierFiche(@RequestBody FicheSurveillance f, @PathVariable String id) {
        try {
            FicheSurveillance updatedFiche = ficheService.updateFicheSurveillance(f, id);
            return ResponseEntity.ok(updatedFiche);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body("Erreur : Patient non trouvé");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("erreur de modification");
        }
    }
    @GetMapping("/getFiche1/{id}")

    public FicheSurveillance getFicheparid(@PathVariable String id) {

        return ifs.getFicheSurveillanceById(id);
    }
    @DeleteMapping("/deleteFiche1/{id}")
    public String deleteFiche(@PathVariable String id) {
        ifs.deleteFicheSurveillanceById(id);
        return "la fiche est supprimée avec succes";

    }
}
