package com.example.medicalunit.medicalunit;

public class Patient {
    private String name;
    private Physician physician;
    private Pharmacist pharmacist;

    // Constructor
    public Patient(String name, Physician physician, Pharmacist pharmacist) {
        this.name = name;
        this.physician = physician;
        this.pharmacist = pharmacist;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Physician getPhysician() {
        return physician;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }
}
