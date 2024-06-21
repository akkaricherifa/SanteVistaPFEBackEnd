package com.example.santevistabackendpfe.controllers;
import com.example.santevistabackendpfe.presistence.entity.FichePerscription;
import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.entity.Patient;
import com.example.santevistabackendpfe.presistence.entity.UserEntity;
import com.example.santevistabackendpfe.presistence.repository.FicheSurveillanceRepository;
import com.example.santevistabackendpfe.presistence.repository.PatientRepository;
import com.example.santevistabackendpfe.services.FicheService;
import com.example.santevistabackendpfe.services.Interfaces.IFichePrescriptionService;
import com.example.santevistabackendpfe.services.Interfaces.IFicheService;
import com.example.santevistabackendpfe.services.Interfaces.IPatientService;
import com.example.santevistabackendpfe.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/Secretaire")
@PreAuthorize("hasAuthority('SECRETAIRE')")
public class SecretaireController {

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

    @Autowired FicheService ficheService;

    //***************************** methodes des patients *************************************************
    @PostMapping("/addPatient")
    public String add(@RequestBody Patient p) {
        try {
            ips.addPatient(p);
            return p.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "erreur ajout";
        }
    }

    @GetMapping("/all")
    public List<Patient> getall() {
        return ips.getAllPatients();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        ips.deletePatientById(id);
        return "le patient est supprimé avec succes";

    }

    @GetMapping("/{id}")

    public Patient getparid(@PathVariable String id) {

        return ips.getPatientById(id);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<?> modifier(@RequestBody Patient p, @PathVariable String id) {
        try {
            Patient updatedPatient = patientService.updatePatient(p, id);
            return ok(updatedPatient);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body("Erreur : Patient non trouvé");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur de modification");
        }
    }

    //    ******************************** méthode de fiche ********************************************
    @PostMapping("/{patientId}/addFicheSurveillance")
    public ResponseEntity<FicheSurveillance> addFicheSurveillance(
            @PathVariable String patientId,
            @RequestBody FicheSurveillance ficheSurveillanceDetails
           ) {
        try {
            // Récupérer le nom de l'utilisateur connecté
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            //UserEntity userDetails = (UserEntity) authentication.getPrincipal();
            //String filledByName = userDetails.getUsername();
            String filledByName = authentication.getName();

            // Ajouter le nom du médecin à la fiche de surveillance
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


    @PostMapping("/addFichePer")
    public ResponseEntity<FichePerscription> addFichePer(@RequestBody FichePerscription f) {
        FichePerscription fiche=ifps.addFichePer(f);
        return ResponseEntity.ok(fiche);

    }


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


    //******************************** liste d'admission chaque jour ****************
    @GetMapping("/addedOn/{date}")
    public ResponseEntity<List<Patient>> getPatientsAddedOn(@PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<Patient> patients = patientService.getPatientsAddedOn(localDate);
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    //********************************* liste des patients morts ********************************
    @GetMapping("/deceased")
    public ResponseEntity<List<Patient>> getAllDeceasedPatients() {
        try {
            List<Patient> patients = patientService.getAllDeceasedPatients();
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
    }






