package com.example.santevistabackendpfe.services;

import com.example.santevistabackendpfe.presistence.entity.FicheSurveillance;
import com.example.santevistabackendpfe.presistence.repository.FicheSurveillanceRepository;
import com.example.santevistabackendpfe.presistence.repository.PatientRepository;
import com.example.santevistabackendpfe.services.Interfaces.IFicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class FicheService implements IFicheService {

    @Autowired
    FicheSurveillanceRepository fr;
    @Autowired
    PatientRepository patientRepository;
    @Override
    public List<FicheSurveillance> getAllFicheSurveillances() {
        return fr.findAll();
    }

    @Override
    public FicheSurveillance addFicheFiche(FicheSurveillance f) {
        return fr.save(f);
    }


    @Override
    public FicheSurveillance getFicheSurveillanceById(String id) {
        return fr.findById(id).get();
    }

    @Override
    public FicheSurveillance updateFicheSurveillance(FicheSurveillance f, String id) {
        Optional<FicheSurveillance> optionalFicheSurveillance = fr.findById(id);
        if (optionalFicheSurveillance.isPresent()) {
            FicheSurveillance existingFicheSurveillance = optionalFicheSurveillance.get();
            existingFicheSurveillance.setGcs(f.getGcs());
            existingFicheSurveillance.setRass(f.getRass());
            existingFicheSurveillance.setBurst_suppression(f.getBurst_suppression());
            existingFicheSurveillance.setCam_icu(f.getCam_icu());
            existingFicheSurveillance.setAt_delirium(f.getAt_delirium());
            existingFicheSurveillance.setMinimal_consciousness_state(f.getMinimal_consciousness_state());
            existingFicheSurveillance.setVegetative_state(f.getVegetative_state());
            existingFicheSurveillance.setBrain_death(f.getBrain_death());
            existingFicheSurveillance.setAcf_grade(f.getAcf_grade());
            existingFicheSurveillance.setArf_grade(f.getArf_grade());
            existingFicheSurveillance.setKdigo(f.getKdigo());
            existingFicheSurveillance.setWeight(f.getWeight());
            existingFicheSurveillance.setFibrobronchoscopy(f.getFibrobronchoscopy());
            existingFicheSurveillance.setTracheostomy(f.getTracheostomy());
            existingFicheSurveillance.setEchography(f.getEchography());
            existingFicheSurveillance.setHemodialysis(f.getHemodialysis());
            existingFicheSurveillance.setHemodiafiltration(f.getHemodiafiltration());
            existingFicheSurveillance.setPlasma_exchange(f.getPlasma_exchange());
            existingFicheSurveillance.setCatheter_dressing(f.getCatheter_dressing());
            existingFicheSurveillance.setTracheostomy_care(f.getTracheostomy_care());
            existingFicheSurveillance.setTracheal_aspiration_microbio(f.getTracheal_aspiration_microbio());
            existingFicheSurveillance.setBlood_culture(f.getBlood_culture());
            existingFicheSurveillance.setOropharyngeal_care(f.getOropharyngeal_care());
            existingFicheSurveillance.setSuctioning(f.getSuctioning());
            existingFicheSurveillance.setPositioning(f.getPositioning());
            existingFicheSurveillance.setPressure_ulcer_risk_score(f.getPressure_ulcer_risk_score());
            existingFicheSurveillance.setPressure_ulcer_grade(f.getPressure_ulcer_grade());
            existingFicheSurveillance.setEarly_mobilization(f.getEarly_mobilization());
            existingFicheSurveillance.setPhysiotherapy_motor(f.getPhysiotherapy_motor());
            existingFicheSurveillance.setPhysiotherapy_respiratory(f.getPhysiotherapy_respiratory());
            existingFicheSurveillance.setExit_for_fresh_air(f.getExit_for_fresh_air());
            existingFicheSurveillance.setFamily_visit(f.getFamily_visit());
            existingFicheSurveillance.setTemperature(f.getTemperature());
            existingFicheSurveillance.setHr(f.getHr());
            existingFicheSurveillance.setSbp(f.getSbp());
            existingFicheSurveillance.setDbp(f.getDbp());
            existingFicheSurveillance.setMap(f.getMap());
            existingFicheSurveillance.setScv02(f.getScv02());
            existingFicheSurveillance.setLactates(f.getLactates());
            existingFicheSurveillance.setMarbling_score(f.getMarbling_score());
            existingFicheSurveillance.setApp_13pourcent(f.getApp_13pourcent());
            existingFicheSurveillance.setIvc_collapse_18pourcent(f.getIvc_collapse_18pourcent());
            existingFicheSurveillance.setPlr_co(f.getPlr_co());
            existingFicheSurveillance.setApco2_gap(f.getApco2_gap());
            existingFicheSurveillance.setO2_l_min(f.getO2_l_min());
            existingFicheSurveillance.setHfnc_flow_l_min(f.getHfnc_flow_l_min());
            existingFicheSurveillance.setFio2(f.getFio2());
            existingFicheSurveillance.setSpo2(f.getSpo2());
            existingFicheSurveillance.setEtco2(f.getEtco2());
            existingFicheSurveillance.setOri_o2_reserve_index(f.getOri_o2_reserve_index());
            existingFicheSurveillance.setSpo2_fio2(f.getSpo2_fio2());
            existingFicheSurveillance.setNippv(f.getNippv());
            existingFicheSurveillance.setAuto_peep_cmh2o(f.getAuto_peep_cmh2o());
            existingFicheSurveillance.setPs_cmh2o(f.getPs_cmh2o());
            existingFicheSurveillance.setPeep_cmh2o(f.getPeep_cmh2o());
            existingFicheSurveillance.setLeaks_pourcent_or_l_min(f.getLeaks_pourcent_or_l_min());
            existingFicheSurveillance.setSlope_sec(f.getSlope_sec());
            existingFicheSurveillance.setExp_tigger(f.getExp_tigger());
            existingFicheSurveillance.setVt_ml(f.getVt_ml());
            existingFicheSurveillance.setVt_ml_kgibw(f.getVt_ml_kgibw());
            existingFicheSurveillance.setRr_c_min(f.getRr_c_min());
            existingFicheSurveillance.setTi1_sec(f.getTi1_sec());
            existingFicheSurveillance.setFlow_curve_shape(f.getFlow_curve_shape());
            existingFicheSurveillance.setImv_vc(f.getImv_vc());
            existingFicheSurveillance.setBest_peep(f.getBest_peep());
            existingFicheSurveillance.setIbw(f.getIbw());
            existingFicheSurveillance.setPaco2(f.getPaco2());
            existingFicheSurveillance.setVt2_ml(f.getVt2_ml());
            existingFicheSurveillance.setVt2_ml_kgibw(f.getVt2_ml_kgibw());
            existingFicheSurveillance.setRr(f.getRr());
            existingFicheSurveillance.setFlow_l_min(f.getFlow_l_min());
            existingFicheSurveillance.setTi2_sec(f.getTi2_sec());
            existingFicheSurveillance.setI_e(f.getI_e());
            existingFicheSurveillance.setPeep2_cmh2o(f.getPeep2_cmh2o());
            existingFicheSurveillance.setPeep_total(f.getPeep_total());
            existingFicheSurveillance.setRr_patient(f.getRr_patient());
            existingFicheSurveillance.setVe(f.getVe());
            existingFicheSurveillance.setP_peak(f.getP_peak());
            existingFicheSurveillance.setP_plat(f.getP_plat());
            existingFicheSurveillance.setDp(f.getDp());
            existingFicheSurveillance.setC(f.getC());
            existingFicheSurveillance.setR(f.getR());
            existingFicheSurveillance.setVr(f.getVr());
            existingFicheSurveillance.setPao2_fio2(f.getPao2_fio2());
            existingFicheSurveillance.setWeaning_attempt(f.getWeaning_attempt());
            existingFicheSurveillance.setTraining_for_home_ventilation(f.getTraining_for_home_ventilation());
            existingFicheSurveillance.setUrinary_output(f.getUrinary_output());
            existingFicheSurveillance.setDextro(f.getDextro());
            existingFicheSurveillance.setInsulin(f.getInsulin());
            existingFicheSurveillance.setIntraabdominal_pressure(f.getIntraabdominal_pressure());
            existingFicheSurveillance.setChest_tube(f.getChest_tube());
            existingFicheSurveillance.setNasogastric_tube(f.getNasogastric_tube());
            existingFicheSurveillance.setDigestive_tubes(f.getDigestive_tubes());
            existingFicheSurveillance.setCutaneous_water_losses(f.getCutaneous_water_losses());
            existingFicheSurveillance.setVentilation_water_losses(f.getVentilation_water_losses());
            existingFicheSurveillance.setFluid_losses(f.getFluid_losses());
            existingFicheSurveillance.setEnteral_feeding(f.getEnteral_feeding());
            existingFicheSurveillance.setParenteral_feeding(f.getParenteral_feeding());
            existingFicheSurveillance.setGlucose(f.getGlucose());
            existingFicheSurveillance.setSaline(f.getSaline());
            existingFicheSurveillance.setNa_glucose(f.getNa_glucose());
            existingFicheSurveillance.setNa_saline(f.getNa_saline());
            existingFicheSurveillance.setNa_medications(f.getNa_medications());
            existingFicheSurveillance.setNa_total_intake(f.getNa_total_intake());
            existingFicheSurveillance.setK(f.getK());
            existingFicheSurveillance.setMg(f.getMg());
            existingFicheSurveillance.setCa(f.getCa());
            existingFicheSurveillance.setP(f.getP());
            existingFicheSurveillance.setFluid_challenge(f.getFluid_challenge());
            existingFicheSurveillance.setFluid_intake(f.getFluid_intake());
            existingFicheSurveillance.setFluid_balance(f.getFluid_balance());
            existingFicheSurveillance.setNorepinephrin(f.getNorepinephrin());
            existingFicheSurveillance.setRemifentanyl(f.getRemifentanyl());
            existingFicheSurveillance.setMidazolam(f.getMidazolam());
            existingFicheSurveillance.setPropofol(f.getPropofol());
            existingFicheSurveillance.setCisatracurium(f.getCisatracurium());
            existingFicheSurveillance.setMedications(f.getMedications());

            return fr.save(existingFicheSurveillance);
        } else {
            throw new RuntimeException("Patient not found with id " + id);
        }
    }

    @Override
    public FicheSurveillance fillFicheSurveillance(FicheSurveillance fiche) {
        fiche.setFillTime(LocalDateTime.now());
        fiche.setValidated(false);
        return fr.save(fiche);
    }


    @Override
    public FicheSurveillance validateFicheSurveillance(String id) {
        Optional<FicheSurveillance> optionalFiche = fr.findById(id);
        if (optionalFiche.isPresent()) {
            FicheSurveillance fiche = optionalFiche.get();
            fiche.setValidated(true);
            return fr.save(fiche);
        } else {
            throw new RuntimeException("FicheSurveillance not found");
        }
    }



    @Override
    public void deleteFicheSurveillanceById(String id) {
        fr.deleteById(id);

    }

}
