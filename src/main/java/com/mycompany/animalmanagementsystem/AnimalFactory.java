package com.mycompany.animalmanagementsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author francescosciabbarrasi
 */
public class AnimalFactory {
    public static Animal createAnimal(String species, String type, String name, String habitat, String dob, double weight, String specificData) {
        return switch (species.toLowerCase()) {
            case "mammal" -> new Mammal(species, type, name, habitat, dob, weight, specificData);
            
            case "bird" -> new Bird(species, type, name, habitat, dob, weight, Double.parseDouble(specificData));
            
            case "reptile" -> new Reptile(species, type, name, habitat, dob, weight, specificData);
            
            case "fish" -> new Fish(species, type, name, habitat, dob, weight, specificData);
            
            default -> throw new IllegalArgumentException("Unknown species: " + species);
        };
    }
}
