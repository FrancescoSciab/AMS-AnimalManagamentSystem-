/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.animalmanagementsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;  
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner; 
import java.util.List;
import java.io.IOException;
import java.util.*;

public class AnimalManagementSystem {
    private static List<Animal> animals = new ArrayList<>();

    public static void main(String[] args) {
            readAndValidateData();
//        
}
    private static void readAndValidateData() {
            try (BufferedReader reader = new BufferedReader(new FileReader("animals.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] speciesAndName = line.split(",");
                    String habitat = reader.readLine();
                    String[] dobAndWeight = reader.readLine().split(",");
                    String specificData = reader.readLine();
                    
                    if (isValid(speciesAndName, habitat, dobAndWeight, specificData)) {
                        String species = speciesAndName[0];
                        String name = speciesAndName[1];
                        String dob = dobAndWeight[0];
                        double weight = Double.parseDouble(dobAndWeight[1]);
                        
                        switch (species.toLowerCase()) {
                            case "mammal": animals.add(new Mammal(species, name, habitat, dob, weight, specificData));
                            break;
                            case "bird": animals.add(new Bird(species, name, habitat, dob, weight, Double.parseDouble(specificData)));
                            break;
                            case "reptile": animals.add(new Reptile(species, name, habitat, dob, weight, Boolean.parseBoolean(specificData)));
                            break;
                            case "fish": animals.add(new Fish(species, name, habitat, dob, weight, specificData));
                            break;
                            default: System.out.println("Unknown species type: " + species);
                    }
//                        
                    }
                }
                System.out.println("Data read and validated successfully.");
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        
    }
    private static boolean isValid(String[] speciesAndName, String habitat, String[] dobAndWeight, String specificData) {
            if (speciesAndName.length != 2 || !speciesAndName[0].matches("[a-zA-Z]+") || speciesAndName[1].isEmpty()) {
                System.out.println("Invalid species or name.");
                return false;
            }
            
            List<String> validHabitats = List.of("forest", "desert", "ocean", "freshwater");
            if (!validHabitats.contains(habitat.toLowerCase())) {
                System.out.println("Invalid habitat: " + habitat);
                return false;
            }
            
            if (!dobAndWeight[0].matches("\\d{4}/\\d{2}/\\d{2}")) {
                System.out.println("Invalid date of birth: " + dobAndWeight[0]);
                return false;
            }
            
            try {
                double weight = Double.parseDouble(dobAndWeight[1]);
                if (weight <= 0) {
                    System.out.println("Invalid weight: " + dobAndWeight[1]);
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid weight: " + dobAndWeight[1]);
                return false;
            }
            
            return true;
        }
}
