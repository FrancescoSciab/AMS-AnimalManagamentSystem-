/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.animalmanagementsystem;
import java.util.Scanner; 
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author francescosciabbarrasi
 */
public interface AnimalFilterStrategy {
    boolean filter(Animal animal);
}

class ZooManagement {
    private final Scanner scanner;
    private final List<Animal> animals;
    
    public ZooManagement(Scanner scanner, List<Animal> animals) {
        this.scanner = scanner;
        this.animals = animals;
    }
    
    private void findAnimals(String prompt, AnimalFilterStrategy strategy) {
        
        
        List<Animal> filteredAnimals = animals.stream()
                .filter(strategy::filter)
                .collect(Collectors.toList());

        if (filteredAnimals.isEmpty()) {
            System.out.println("No animals found with ");
        } else {
            filteredAnimals.forEach(Animal::displayInfo);
        }
    }
    
    public void findAnimalsbySpecies() {
        System.out.print("Enter species: ");
        String input = scanner.nextLine();
        findAnimals("Species: ", animal -> animal.getSpecies().equalsIgnoreCase(input));
    }
    public void findAnimalsByHabitat() {
        System.out.print("Enter habitat: ");
        String input = scanner.nextLine();
        findAnimals("Habitat: ", animal -> animal.getHabitat().equalsIgnoreCase(input));
    }
    
    public void findAnimalsByType() {
        System.out.print("Enter type: ");
        String input = scanner.nextLine();
        findAnimals("Type: ", animal -> animal.getType().equalsIgnoreCase(input));
    }
    
    public void findAnimalsByName() {
        System.out.print("Enter name: ");
        String input = scanner.nextLine();
        findAnimals("Name: ", animal -> animal.getName().equalsIgnoreCase(input));
    }
}