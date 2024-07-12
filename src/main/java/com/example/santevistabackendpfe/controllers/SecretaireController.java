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
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
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

    @PostMapping("/addPatientsFromCsv")
    public String addPatientsFromCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Le fichier est vide";
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            // Skip the header line if your CSV has a header
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                // Ignore empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");
                // Trim whitespace from each data element
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }

                if (data.length != 9) { // Assurez-vous que le nombre de colonnes est correct
                    return "Le fichier CSV ne contient pas le nombre correct de colonnes";
                }

                Patient patient = new Patient();
                // L'ID sera généré automatiquement par MongoDB
                patient.setNom(data[0]);
                patient.setPrenom(data[1]);
                patient.setAdresseVille(data[2]);
                patient.setAge(data[3]);
                patient.setSexe(data[4]);

                try {
                    patient.setNumTel(Integer.parseInt(data[5]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return "Erreur de format de numéro de téléphone";
                }

                patient.setMaladie(data[6]);

                try {
                    patient.setDateAdded(LocalDate.parse(data[7]));
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                    return "Erreur de format pour dateAdded";
                }

                try {
                    patient.setDateDeDece(LocalDate.parse(data[8]));
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                    return "Erreur de format pour dateDeDece";
                }

                patientService.addPatient(patient);
            }
            return "Patients ajoutés avec succès";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'ajout des patients";
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
            @RequestBody FicheSurveillance ficheSurveillanceDetails) {
        try {
            // Récupérer le nom de l'utilisateur connecté
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String filledByName = authentication.getName();

            // Récupérer le patient à partir de l'ID
            Optional<Patient> optionalPatient = patientRepository.findById(patientId);
            if (!optionalPatient.isPresent()) {
                throw new RuntimeException("Patient not found with id: " + patientId);
            }

            Patient patient = optionalPatient.get();

            // Assigner le patient et le nom de remplissage à la fiche de surveillance
            ficheSurveillanceDetails.setPatient(patient);
            ficheSurveillanceDetails.setFilledByName(filledByName);
            // Assigner la date et l'heure actuelle à time
            ficheSurveillanceDetails.setTime(LocalTime.now()); // Exemple: Utilisation de l'heure actuelle
            // Appeler le service pour ajouter la fiche de surveillance
            FicheSurveillance createdFicheSurveillance = ficheService.createFicheSurveillance(ficheSurveillanceDetails);
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

    @GetMapping("/{patientId}/fiches")
    public ResponseEntity<List<FicheSurveillance>> getFichesByPatientId(@PathVariable String patientId) {
        try {
            List<FicheSurveillance> fiches = ficheService.getFichesByPatientId(patientId);
            return ResponseEntity.ok(fiches);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{patientId}/fiches/{date}")
    public ResponseEntity<List<FicheSurveillance>> getFichesByPatientIdAndDate(@PathVariable String patientId, @PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<FicheSurveillance> fiches = ficheService.getFichesByPatientIdAndDate(patientId, localDate);
            return ResponseEntity.ok(fiches);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
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






