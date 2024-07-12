package com.example.santevistabackendpfe.controllers;

import com.example.santevistabackendpfe.presistence.entity.*;
import com.example.santevistabackendpfe.presistence.repository.UserRepository;
import com.example.santevistabackendpfe.services.FicheService;
import com.example.santevistabackendpfe.services.Interfaces.IFicheService;
import com.example.santevistabackendpfe.services.Interfaces.IPatientService;
import com.example.santevistabackendpfe.services.PatientService;
import com.example.santevistabackendpfe.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/Medecins")
@PreAuthorize("hasAuthority('MEDECINSENIOR')")
public class MedecinSeniorController {
    private final UserRepository userRepository;
    @Autowired
    IPatientService ips;
    @Autowired
    IFicheService ifs;
    @Autowired
    private PatientService patientService;
    @Autowired
    private FicheService ficheService;
    @Autowired
    private UserService userService;

//*********************************** get listes de personnels de santé ************************************


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String id) {
        Optional<UserEntity> medecin = userService.getUser(id);
        if (medecin.isPresent()) {
            return ResponseEntity.ok(medecin.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/MedecinsSeniors")
    public ResponseEntity<List<UserEntity>> users() {
        List<UserEntity> users = userRepository.findAll();
        users = users.stream().filter(MedecinSenior.class::isInstance).toList();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/MedecinsJeunes")
    public ResponseEntity<List<UserEntity>> users4() {
        List<UserEntity> users4 = userRepository.findAll();
        users4 = users4.stream().filter(MedecinJeune.class::isInstance).toList();
        return ResponseEntity.ok(users4);
    }


    @GetMapping("/InfirmierKines")
    public ResponseEntity<List<UserEntity>> users1() {
        List<UserEntity> users1 = userRepository.findAll();
        users1 = users1.stream().filter(InfirmierKine.class::isInstance).toList();
        return ResponseEntity.ok(users1);
    }

    @GetMapping("/InfirmierSurveillants")
    public ResponseEntity<List<UserEntity>> users2() {
        List<UserEntity> users2 = userRepository.findAll();
        users2 = users2.stream().filter(InfirmierSurveillant.class::isInstance).toList();
        return ResponseEntity.ok(users2);
    }

    @GetMapping("/InfirmierSoignants")
    public ResponseEntity<List<UserEntity>> users3() {
        List<UserEntity> users3 = userRepository.findAll();
        users3 = users3.stream().filter(InfirmierSoignant.class::isInstance).toList();
        return ResponseEntity.ok(users3);
    }
//**********************/*****************les méthodes des Patients***************************************
    @GetMapping("/all")
    public List<Patient> getall(){
        return ips.getAllPatients();
    }
    @GetMapping("/{id}")

    public Patient getparid(@PathVariable String id){

        return ips.getPatientById(id);
    }
    @PutMapping("/modifier/{id}")
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
    @GetMapping("/GetAllFiches")
    public List<FicheSurveillance> getallFiches() {
        return ifs.getAllFicheSurveillances();
    }

    @PutMapping("/ModifFiche/{id}")
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
    @GetMapping("/getFiche/{id}")

    public FicheSurveillance getFicheparid(@PathVariable String id) {

        return ifs.getFicheSurveillanceById(id);
    }


    @DeleteMapping("/deleteFiche/{id}")
    public String deleteFiche(@PathVariable String id) {
        ifs.deleteFicheSurveillanceById(id);
        return "la fiche est supprimée avec succes";

    }


    @PostMapping("/{id}/validate")
    public ResponseEntity<FicheSurveillance> validateFicheSurveillance(@PathVariable String id) {
        FicheSurveillance validatedFiche = ficheService.validateFicheSurveillance(id);
        return ResponseEntity.ok(validatedFiche);
    }


    @PostMapping("/remplir")
    public ResponseEntity<FicheSurveillance> fillFicheSurveillance(@RequestBody FicheSurveillance fiche) {
        FicheSurveillance savedFiche = ficheService.fillFicheSurveillance(fiche);
        return ResponseEntity.ok(savedFiche);
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
