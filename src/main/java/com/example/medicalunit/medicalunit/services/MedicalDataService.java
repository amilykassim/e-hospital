package com.example.medicalunit.medicalunit.services;

import java.util.LinkedHashMap;
import java.util.Map;

import com.example.medicalunit.medicalunit.Patient;

public class MedicalDataService {
    private static final Map<String, Patient> patients = new LinkedHashMap<>();

    public static void addPatient(Patient patient) {
        patients.put(patient.getName(), patient);
    }

    public static Patient getPatient(String name) {
        return patients.get(name);
    }
}
