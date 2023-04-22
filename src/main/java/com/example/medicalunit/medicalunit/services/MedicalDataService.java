package com.example.medicalunit.medicalunit.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.medicalunit.medicalunit.Patient;
import com.example.medicalunit.medicalunit.Pharmacist;
import com.example.medicalunit.medicalunit.Physician;
import com.example.medicalunit.medicalunit.dtos.PatientMedicalRecordDTO;

public class MedicalDataService {
    private static final Map<String, PatientMedicalRecordDTO> patientsMedicalRecords = new LinkedHashMap<>();
    private static final Map<String, Patient> patients = new LinkedHashMap<>();
    private static final Map<String, Physician> physicians = new LinkedHashMap<>();
    private static final Map<String, Pharmacist> pharmacists = new LinkedHashMap<>();

    // pre-populate patients with id, name, age, gender
    public static void populatePatients() {

        Patient p1 = new Patient("1", "John", "18", "MALE");
        Patient p2 = new Patient("2", "Jane", "19", "FEMALE");

        patients.put(p1.getId(), p1);
        patients.put(p2.getId(), p2);
    }

    // pre-populate physicians with id, name, age, gender
    public static void populatePhysicians() {
        Physician p1 = new Physician("10", "William", "45", "MALE");
        Physician p2 = new Physician("20", "Beth", "40", "FEMALE");

        physicians.put(p1.getId(), p1);
        physicians.put(p2.getId(), p2);
    }

    // pre-populate pharmacist with id, name, age, gender
    public static void populatePharmacist() {
        Pharmacist p1 = new Pharmacist("100", "Jack", "30", "MALE");
        Pharmacist p2 = new Pharmacist("200", "Ellen", "31", "FEMALE");

        pharmacists.put(p1.getId(), p1);
        pharmacists.put(p2.getId(), p2);
    }

    // pre-populate patients with id, name, age, gender
    public static void populatePatientMedicalRecords() {

        PatientMedicalRecordDTO p1 = new PatientMedicalRecordDTO("57282g2bbh", "1", "Cough and fever");
        PatientMedicalRecordDTO p2 = new PatientMedicalRecordDTO("672g2by272", "2", "Typhoid");

        patientsMedicalRecords.put(p1.getId(), p1);
        patientsMedicalRecords.put(p2.getId(), p2);
    }

    // assign doctor and pharmacy
    public static void assignDoctorAndPharmacy(PatientMedicalRecordDTO patientMedicalRecord) {
        patientsMedicalRecords.put(patientMedicalRecord.getPatientId(), patientMedicalRecord);
    }

    public static List<PatientMedicalRecordDTO> getPatientMedicalRecordByPhysicianId(String physicianId) {
        List<PatientMedicalRecordDTO> foundPatientsMedicalRecords = new ArrayList<>();

        for (PatientMedicalRecordDTO patientMedicalRecord : patientsMedicalRecords.values()) {

            if (patientMedicalRecord.getPhysicianId().equals(physicianId))
                foundPatientsMedicalRecords.add(patientMedicalRecord);
        }

        return foundPatientsMedicalRecords;
    }

    public static List<Physician> getAllPhysicians() {
        return new ArrayList<>(physicians.values());
    }

    public static List<Pharmacist> getAllPharmacists() {
        return new ArrayList<>(pharmacists.values());
    }

    public static List<PatientMedicalRecordDTO> getAllPatientsMedicalRecords() {
        return new ArrayList<>(patientsMedicalRecords.values());
    }

    public static List<PatientMedicalRecordDTO> getAllPatientsMedicalRecordsByPhysicianId(String physicianId) {
        List<PatientMedicalRecordDTO> foundPatientsMedicalRecords = new ArrayList<>();

        for (PatientMedicalRecordDTO patientMedicalRecord : patientsMedicalRecords.values()) {

            if (patientMedicalRecord.getPhysicianId() != null
                    && patientMedicalRecord.getPhysicianId().equals(physicianId))
                foundPatientsMedicalRecords.add(patientMedicalRecord);
        }

        return foundPatientsMedicalRecords;
    }

    public static List<PatientMedicalRecordDTO> getAllPatientsMedicalRecordsByPharmacistId(String pharmacistId) {
        List<PatientMedicalRecordDTO> foundPatientsMedicalRecords = new ArrayList<>();

        for (PatientMedicalRecordDTO patientMedicalRecord : patientsMedicalRecords.values()) {

            if (patientMedicalRecord.getPharmacistId() != null
                    && patientMedicalRecord.getPharmacistId().equals(pharmacistId))
                foundPatientsMedicalRecords.add(patientMedicalRecord);
        }

        return foundPatientsMedicalRecords;
    }

    public static List<PatientMedicalRecordDTO> getAllPatientsMedicalRecordsByPatientId(String patientId) {
        List<PatientMedicalRecordDTO> foundPatientsMedicalRecords = new ArrayList<>();

        for (PatientMedicalRecordDTO patientMedicalRecord : patientsMedicalRecords.values()) {

            if (patientMedicalRecord.getPatientId() != null
                    && patientMedicalRecord.getPatientId().equals(patientId))
                foundPatientsMedicalRecords.add(patientMedicalRecord);
        }

        return foundPatientsMedicalRecords;
    }

    public static PatientMedicalRecordDTO getPatientMedicalRecordById(String medicalRecordId) {
        return patientsMedicalRecords.get(medicalRecordId);
    }

    public static PatientMedicalRecordDTO updatePatientMedicalRecordById(
            PatientMedicalRecordDTO patientMedicalRecordDTO) {
        return patientsMedicalRecords.put(patientMedicalRecordDTO.getId(), patientMedicalRecordDTO);
    }

    // get all patients
    public static Map<String, Patient> getPatients() {
        return patients;
    }

    // get patient by id
    public static Patient getPatientById(String patientId) {
        return patients.get(patientId);
    }

    // get all physicians
    public static Map<String, Physician> getPhysicians() {
        return physicians;
    }

    // get physician by id
    public static Physician getPhysicianById(String physicianId) {
        return physicians.get(physicianId);
    }

    // get pharmacist by id
    public static Pharmacist getPharmacistById(String pharmacistId) {
        return pharmacists.get(pharmacistId);
    }

    // get all pharmacists
    public static Map<String, Pharmacist> getPharmacists() {
        return pharmacists;
    }
}
