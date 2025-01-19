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
    protected String name;
    protected String habitat;
    protected String dob; // Date of Birth in yyyy/MM/dd format
    protected double weight;

    public Animal(String species, String name, String habitat, String dob, double weight) {
        this.species = species;
        this.name = name;
        this.habitat = habitat;
        this.dob = dob;
        this.weight = weight;
    }

    public abstract void displayInfo();

    public String getType() {
        return this.getClass().getSimpleName();
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
