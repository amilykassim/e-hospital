package com.example.medicalunit.medicalunit;

public class Pharmacist implements Comparable<Pharmacist> {
    private String name;
    private int age;

    // Constructor
    public Pharmacist(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Pharmacist other) {
        return Integer.compare(this.age, other.age);
    }
}
