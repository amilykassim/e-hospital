package com.example.medicalunit.medicalunit;

public class Physician implements Comparable<Physician> {
    private String name;

    // Constructor
    public Physician(String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Physician other) {
        return this.name.compareTo(other.name);
    }
}
