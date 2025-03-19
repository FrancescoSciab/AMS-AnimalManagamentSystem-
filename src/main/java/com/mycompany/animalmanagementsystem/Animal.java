package com.mycompany.animalmanagementsystem;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author francescosciabbarrasi
 */
abstract class Animal {
    protected String species;
    protected String type;
    protected String name;
    protected String habitat;
    protected String dob; // Date of Birth in yyyy/MM/dd format
    private LocalDate formattedDob;
    protected double weight;
    

    public Animal(String species, String type, String name, String habitat, String dob, double weight) {
        this.species = species;
        this.type = type;
        this.name = name;
        this.habitat = habitat;
        this.dob = dob;
        this.weight = weight;
    }
    
    public java.sql.Date getFormattedDob() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.formattedDob = LocalDate.parse(this.dob, formatter);
        return java.sql.Date.valueOf(formattedDob);
    }

    public String getDob() {
        return dob;
    }

    public double getWeight() {
        return weight;
    }

    public abstract void displayInfo();

    public String getType() {
        return type;
    }

    public String getHabitat() {
        return habitat;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }
}

class Mammal extends Animal {
    private String furType;

    public Mammal(String species, String type, String name, String habitat, String dob, double weight, String furType) {
        super(species, type, name, habitat, dob, weight);
        this.furType = furType;
    }

    @Override
    public void displayInfo() {
        System.out.println("Mammal [Species=" + species + ", Type=" + type + ", Name=" + name + ", Habitat=" + habitat + 
                ", DoB=" + dob + ", Weight=" + weight + "kg, Fur Type=" + furType + "]");
    }
}

class Bird extends Animal {
    private double wingSpan;

    public Bird(String species, String type, String name, String habitat, String dob, double weight, double wingSpan) {
        super(species, type, name, habitat, dob, weight);
        this.wingSpan = wingSpan;
    }

    @Override
    public void displayInfo() {
        System.out.println("Bird [Species=" + species + ", Type=" + type + ", Name=" + name + ", Habitat=" + habitat + 
                ", DoB=" + dob + ", Weight=" + weight + "kg, Wing Span=" + wingSpan + "cm]");
    }
}

class Reptile extends Animal {
    private String isVenomous;

    public Reptile(String species, String type, String name, String habitat, String dob, double weight, String isVenomous) {
        super(species, type, name, habitat, dob, weight);
        this.isVenomous = isVenomous;
    }

    @Override
    public void displayInfo() {
        System.out.println("Reptile [Species=" + species + ", Type=" + type + ", Name=" + name + ", Habitat=" + habitat + 
                ", DoB=" + dob + ", Weight=" + weight + "kg, Venomous=" + isVenomous + "]");
    }
}

class Fish extends Animal {
    private String waterType;

    public Fish(String species, String type, String name, String habitat, String dob, double weight, String waterType) {
        super(species, type, name, habitat, dob, weight);
        this.waterType = waterType;
    }

    @Override
    public void displayInfo() {
        System.out.println("Fish [Species=" + species + ", Type=" + type + ", Name=" + name + ", Habitat=" + habitat + 
                ", DoB=" + dob + ", Weight=" + weight + "kg, Water Type=" + waterType + "]");
    }
}

