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
        Scanner scanner = new Scanner(System.in);
        
        
        while (true) {
            System.out.println("\n--- Zoo Management System ---");
            System.out.println("1. Import data");
            System.out.println("2. Find animals by Species");
            System.out.println("3. Find animals by Habitat");
            System.out.println("4. Find animals by Type");
            System.out.println("5. Find animals by Name");
            System.out.println("6. Quit");
            System.out.println("Choose an option by typing the number of the action you want to run");
            
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch(java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.nextLine();
                continue; //Restart the loop
            }
            
            
            switch (choice) {
                //user has to import data first
                case 1: readAndValidateData();
                break;
                case 2: 
                    if (!isAnimalsListEmpty()) {
                        findAnimalsBySpecies(scanner);
                    }
                break;
                case 3: 
                    if (!isAnimalsListEmpty()) {
                        findAnimalsByHabitat(scanner);
                    }
                    
                break;
                case 4: 
                    if (!isAnimalsListEmpty()) {
                        findAnimalsByType(scanner);
                    }
                    
                break;
                case 5: 
                    if (!isAnimalsListEmpty()) {
                        findAnimalsByName(scanner);
                    }
                    
                break;
                case 6: {
                    System.out.println("Exiting system. Goodbye!");
                    return;
                }
                default: System.out.println("Invalid choice. Try again");
            }
        }
}
    private static void readAndValidateData() {
            try (BufferedReader reader = new BufferedReader(new FileReader("animals.txt"))) {
                //Declared before the loop to ensure the storage of the result of each readLine() call
                String line;
                //null means that the end of the file has been reached
                while ((line = reader.readLine()) != null) {
                    String[] speciesAndName = line.split(",");
                    String habitat = reader.readLine();
                    String[] dobAndWeight = reader.readLine().split(",");
                    String specificData = reader.readLine();
                    
                    if (isValid(speciesAndName, habitat, dobAndWeight, specificData)) {
                        String species = speciesAndName[0];
                        String type = speciesAndName[1];
                        String name = speciesAndName[2];
                        String dob = dobAndWeight[0];
                        double weight = Double.parseDouble(dobAndWeight[1]);
                        
                        switch (species.toLowerCase()) {
                            case "mammal": animals.add(new Mammal(species, type, name, habitat, dob, weight, specificData));
                            System.out.println(species + " data read and validated successfully.");
                            break;
                            case "bird": animals.add(new Bird(species, type, name, habitat, dob, weight, Double.parseDouble(specificData)));
                            System.out.println(species + " data read and validated successfully.");
                            break;
                            case "reptile": animals.add(new Reptile(species, type, name, habitat, dob, weight, specificData));
                            System.out.println(species + " data read and validated successfully.");
                            break;
                            case "fish": animals.add(new Fish(species, type, name, habitat, dob, weight, specificData));
                            System.out.println(species + " data read and validated successfully.");
                            break;
                            default: System.out.println("Unknown species: " + species);
                        }
                        
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        
    }
    private static boolean isValid(String[] speciesAndName, String habitat, String[] dobAndWeight, String specificData) {
        
            //Species
            if (speciesAndName.length != 3 || !speciesAndName[0].matches("[a-zA-Z]+") || speciesAndName[0].isEmpty()) {
                System.out.println("Invalid" + speciesAndName[0] + "species. The species must be text only");
                return false;
            }
            
            //Type
            if (speciesAndName.length != 3 || !speciesAndName[1].matches("[a-zA-Z]+") || speciesAndName[1].isEmpty()) {
                System.out.println("Invalid" + speciesAndName[1] + "type. The name must be text only");
                return false;
            }
            
            //Name
            if (speciesAndName.length != 3 || !speciesAndName[2].matches("[a-zA-Z0-9]+") || speciesAndName[2].isEmpty()) {
                System.out.println("Invalid" + speciesAndName[2] + "name. The name must be text and/or numbers");
                return false;
            }
            
            //Habitat
            List<String> validHabitats = List.of("forest", "desert", "ocean", "freshwater");
            if (!validHabitats.contains(habitat.toLowerCase()) || habitat.isEmpty()) {
                System.out.println("Invalid habitat: " + habitat + ". Habitat available: forest, desert, ocean, freshwater");
                return false;
            }
            
            //Date of Birth 
            if (!dobAndWeight[0].matches("\\d{4}/\\d{2}/\\d{2}") || dobAndWeight.length != 2) {
                System.out.println("Invalid date of birth: " + dobAndWeight[0] + ". The Date of Birth and Weight must be in the form yyyy/mm/dd,135.0");
                return false;
            }
            
            //Weight
            try {
                double weight = Double.parseDouble(dobAndWeight[1]);
                if (weight <= 0) {
                    System.out.println("Invalid weight: " + dobAndWeight[1] + ". The weight must be a positive decimal number");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid weight: " + dobAndWeight[1]);
                return false;
            }
            
            //Specific Data
            switch(speciesAndName[0]) {
                case "mammal": if(!specificData.matches("[a-zA-Z]+") || specificData.isEmpty()) {
                        System.out.println("Invalid " + specificData + " fur type. Field can't be empty and must be text only");
                        return false;
                };
                break;
                case "bird": 
                    double wingSpan = Double.parseDouble(specificData);
                    if(wingSpan <= 0) {
                        System.out.println("Invalid wing span. Field must contain a positive decimal number");
                        return false;
                };
                break;
                case "reptile": if(!specificData.matches("yes|no")) {
                        System.out.println("Invalid " + speciesAndName[0] + " value. Field must contain a yes or no only");
                        return false;
                };
                break;
                case "fish": if(!specificData.matches("[a-zA-Z]+") || specificData.isEmpty()) {
                        System.out.println("Invalid" + specificData + "water type. Field can't be empty and must be text only");
                        return false;
                };
                break;
            }
            
            return true;
        }
    private static void findAnimalsBySpecies(Scanner scanner) {
        System.out.print("Enter species (Mammal, Bird, Reptile, Fish): ");
        String species = scanner.nextLine();
        animals.stream()
                .filter(animal -> animal.getSpecies().equalsIgnoreCase(species))
                .forEach(Animal::displayInfo);
    }
    private static void findAnimalsByHabitat(Scanner scanner) {
        System.out.print("Enter habitat: ");
        String habitat = scanner.nextLine();
        animals.stream()
                .filter(animal -> animal.getHabitat().equalsIgnoreCase(habitat))
                .forEach(Animal::displayInfo);
    }
    private static void findAnimalsByType(Scanner scanner) {
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        animals.stream()
                .filter(animal -> animal.getType().equalsIgnoreCase(type))
                .forEach(Animal::displayInfo);
    }
    private static void findAnimalsByName(Scanner scanner) {
        System.out.println("Enter Name: ");
        String name = scanner.nextLine();
        animals.stream()
                .filter(animal -> animal.getName().equalsIgnoreCase(name))
                .forEach(Animal::displayInfo);
    }
    
    private static boolean isAnimalsListEmpty() {
    if (animals.isEmpty()) {
        System.out.println("Please import data first.");
        return true;
    }
    return false;
}
}
