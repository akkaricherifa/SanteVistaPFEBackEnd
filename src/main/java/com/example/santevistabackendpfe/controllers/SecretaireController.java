package com.example.santevistabackendpfe.controllers;

import com.example.santevistabackendpfe.presistence.entity.MedecinSenior;
import com.example.santevistabackendpfe.presistence.entity.Patient;
import com.example.santevistabackendpfe.presistence.entity.UserEntity;
import com.example.santevistabackendpfe.presistence.repository.PatientRepository;
import com.example.santevistabackendpfe.presistence.repository.UserRepository;
import com.example.santevistabackendpfe.services.Interfaces.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/Secretaire")
@PreAuthorize("hasAuthority('SECRETAIRE')")
public class SecretaireController {

    private final PatientRepository patientRepository;
    @Autowired
    IPatientService ips;


    @PostMapping("/addPatient")
    public String add(@RequestBody Patient p){
        try {
            ips.addPatient(p);
            return p.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "erreur ajout";
        }
    }

    @GetMapping("/all")
    public List<Patient> getall(){
        return ips.getAllPatients();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        ips.deletePatientById(id);
        return "le patient est supprimé avec succes";

    }

    @PutMapping("/modifier/{id}")
    public String modifier(@RequestBody Patient i, @PathVariable String id){
        try {
            ips.updatePatient(i);
            return i.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "erreur Modifier";
        }
    }
    @GetMapping("/{id}")

    public Patient getparid(@PathVariable String id){

        return ips.getPatientById(id);
    }
}
