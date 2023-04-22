package com.example.medicalunit.medicalunit.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.medicalunit.medicalunit.Patient;
import com.example.medicalunit.medicalunit.Pharmacist;
import com.example.medicalunit.medicalunit.Physician;
import com.example.medicalunit.medicalunit.dtos.PatientMedicalRecordDTO;
import com.example.medicalunit.medicalunit.services.MedicalDataService;

import java.util.List;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/medicalRecords")
    public ResponseEntity<List<PatientMedicalRecordDTO>> getAllMedicalRecords(
            @RequestParam(value = "physicianId", required = false) String physicianId,
            @RequestParam(value = "pharmacistId", required = false) String pharmacistId,
            @RequestParam(value = "patientId", required = false) String patientId) {

        if (physicianId != null) {
            List<PatientMedicalRecordDTO> patientMedicalReport = MedicalDataService
                    .getAllPatientsMedicalRecordsByPhysicianId(physicianId);
            return new ResponseEntity<>(patientMedicalReport, HttpStatus.OK);
        }

        if (pharmacistId != null) {
            List<PatientMedicalRecordDTO> patientMedicalReport = MedicalDataService
                    .getAllPatientsMedicalRecordsByPharmacistId(pharmacistId);
            return new ResponseEntity<>(patientMedicalReport, HttpStatus.OK);
        }

        if (patientId != null) {
            List<PatientMedicalRecordDTO> patientMedicalReport = MedicalDataService
                    .getAllPatientsMedicalRecordsByPatientId(patientId);
            return new ResponseEntity<>(patientMedicalReport, HttpStatus.OK);
        }

        List<PatientMedicalRecordDTO> patientMedicalRecords = MedicalDataService.getAllPatientsMedicalRecords();

        return new ResponseEntity<>(patientMedicalRecords, HttpStatus.OK);
    }

    @GetMapping("/physicians")
    public ResponseEntity<List<Physician>> getAllPhysicians() {
        List<Physician> physicians = MedicalDataService.getAllPhysicians();

        return new ResponseEntity<>(physicians, HttpStatus.OK);
    }

    @GetMapping("/pharmacists")
    public ResponseEntity<List<Pharmacist>> getAllPharmacists() {
        List<Pharmacist> physicians = MedicalDataService.getAllPharmacists();

        return new ResponseEntity<>(physicians, HttpStatus.OK);
    }

    @PostMapping("/medicalRecords/assignDoctor")
    public ResponseEntity<String> grantAccessToDoctor(
            @RequestBody PatientMedicalRecordDTO patientMedicalRecord) {

        Patient patient = MedicalDataService.getPatientById(patientMedicalRecord.getPatientId());
        Physician physician = MedicalDataService.getPhysicianById(patientMedicalRecord.getPhysicianId());

        if (patient == null)
            return new ResponseEntity<>("Invalid patient id", HttpStatus.NOT_FOUND);
        if (physician == null)
            return new ResponseEntity<>("Invalid physician id", HttpStatus.NOT_FOUND);

        // find record
        PatientMedicalRecordDTO foundPatientMedicalRecord = MedicalDataService
                .getPatientMedicalRecordById(patientMedicalRecord.getId());
        if (foundPatientMedicalRecord == null)
            return new ResponseEntity<>("Invalid medical record id", HttpStatus.OK);

        // Grant access
        foundPatientMedicalRecord.setPhysicianId(physician.getId());

        MedicalDataService.updatePatientMedicalRecordById(foundPatientMedicalRecord);

        return new ResponseEntity<>("Granted access successfully", HttpStatus.OK);
    }

    @PostMapping("/medicalRecords/assignPharmacist")
    public ResponseEntity<String> grantAccessToPharmacy(
            @RequestBody PatientMedicalRecordDTO patientMedicalRecord) {

        Patient patient = MedicalDataService.getPatientById(patientMedicalRecord.getPatientId());
        Pharmacist pharmacist = MedicalDataService.getPharmacistById(patientMedicalRecord.getPharmacistId());

        if (patient == null)
            return new ResponseEntity<>("Invalid patient id", HttpStatus.NOT_FOUND);
        if (pharmacist == null)
            return new ResponseEntity<>("Invalid pharmacist id", HttpStatus.NOT_FOUND);

        // find record
        PatientMedicalRecordDTO foundPatientMedicalRecord = MedicalDataService
                .getPatientMedicalRecordById(patientMedicalRecord.getId());
        if (foundPatientMedicalRecord == null)
            return new ResponseEntity<>("Invalid medical record id", HttpStatus.OK);

        // Grant access
        foundPatientMedicalRecord.setPharmacistId(pharmacist.getId());

        MedicalDataService.updatePatientMedicalRecordById(foundPatientMedicalRecord);

        return new ResponseEntity<>("Granted access successfully", HttpStatus.OK);
    }

    @PostMapping("/addConsultation")
    public ResponseEntity<String> provideConsultation(
            @RequestBody PatientMedicalRecordDTO patientMedicalRecord) {

        if (patientMedicalRecord.getConsultation() == null)
            return new ResponseEntity<>("Provide consultation details", HttpStatus.BAD_REQUEST);

        Physician physician = MedicalDataService.getPhysicianById(patientMedicalRecord.getPhysicianId());

        if (physician == null)
            return new ResponseEntity<>("Invalid physician id", HttpStatus.BAD_REQUEST);

        // find record
        PatientMedicalRecordDTO foundPatientMedicalRecord = MedicalDataService
                .getPatientMedicalRecordById(patientMedicalRecord.getId());

        if (foundPatientMedicalRecord == null)
            return new ResponseEntity<>("Invalid medical record id", HttpStatus.BAD_REQUEST);

        if (!physician.getId().equals(foundPatientMedicalRecord.getPhysicianId()))
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);

        // Grant access
        foundPatientMedicalRecord.setConsultation(patientMedicalRecord.getConsultation());

        MedicalDataService.updatePatientMedicalRecordById(foundPatientMedicalRecord);

        return new ResponseEntity<>("Added consultation successfully", HttpStatus.OK);
    }

    @PostMapping("/addMedicine")
    public ResponseEntity<String> addMedicine(
            @RequestBody PatientMedicalRecordDTO patientMedicalRecord) {

        if (patientMedicalRecord.getMedicines() == null)
            return new ResponseEntity<>("Provide medicine", HttpStatus.BAD_REQUEST);

        Pharmacist pharmacist = MedicalDataService.getPharmacistById(patientMedicalRecord.getPharmacistId());

        if (pharmacist == null)
            return new ResponseEntity<>("Invalid pharmacist id", HttpStatus.BAD_REQUEST);

        // find record
        PatientMedicalRecordDTO foundPatientMedicalRecord = MedicalDataService
                .getPatientMedicalRecordById(patientMedicalRecord.getId());

        if (foundPatientMedicalRecord == null)
            return new ResponseEntity<>("Invalid medical record id", HttpStatus.BAD_REQUEST);

        if (!pharmacist.getId().equals(foundPatientMedicalRecord.getPharmacistId()))
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);

        // add medicine
        foundPatientMedicalRecord.setMedicines(patientMedicalRecord.getMedicines());

        MedicalDataService.updatePatientMedicalRecordById(foundPatientMedicalRecord);

        return new ResponseEntity<>("Added medicine successfully", HttpStatus.OK);
    }

    @PostMapping("/populateMedicalRecords")
    public ResponseEntity<String> populateMedicalRecords() {

        MedicalDataService.populatePatients();
        MedicalDataService.populatePharmacist();
        MedicalDataService.populatePhysicians();
        MedicalDataService.populatePatientMedicalRecords();

        return new ResponseEntity<>("Populated medical records, patients, pharmacists, and physicians", HttpStatus.OK);
    }

}