/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animalmanagementsystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author francescosciabbarrasi
 */
public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/zoo";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    String add_sql = "INSERT INTO zoo_animals (species, type, name, habitat, dob, weight, specific_data) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    // Method to insert animals into the database
    public void insertAnimals(List<Animal> animals) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(add_sql)) {
            
            // Loop through the list of animals
            for (Animal animal : animals) {
                pstmt.setString(1, animal.getSpecies());
                pstmt.setString(2, animal.getType());
                pstmt.setString(3, animal.getName());
                pstmt.setString(4, animal.getHabitat());
                pstmt.setString(5, null);
                pstmt.setString(6, null);
                pstmt.setString(7, null);                
//                // Convert java.util.Date to java.sql.Date for SQL
//                if (animal.getDob() != null) {
//                    pstmt.setDate(5, new java.sql.Date(animal.getDob().getTime()));
//                } else {
//                    pstmt.setNull(5, java.sql.Types.DATE);
//                }
//                
//                pstmt.setDouble(6, animal.getWeight());
//                pstmt.setString(7, animal.getSpecificData());

                // Execute the insert
                pstmt.executeUpdate();
            }
            System.out.println("Animals inserted successfully!");
        
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }   
    }
    
    public List<List<String>> retrieveAnimals(String detail, String input) {
        List<List<String>> retrieved_animals = new ArrayList<>();
        String retrieve_sql = "SELECT * FROM zoo_animals WHERE " + detail + " = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(retrieve_sql)) {
                    // Set the input value as a parameter
                    pstmt.setString(1, input);
                    
                    try (ResultSet rs = pstmt.executeQuery()) {
                        while (rs.next()) {
                            
                            List<String> row = new ArrayList<>();
                                row.add(rs.getString("species"));
                                row.add(rs.getString("type"));
                                row.add(rs.getString("name"));
                                row.add(rs.getString("habitat"));
                                retrieved_animals.add(row);   
                        }
                        
                        for (List<String> row : retrieved_animals) {
                            System.out.println(row);
                        }
                        System.out.println("Retrieved " + retrieved_animals.size() + " animals matching " + detail + " = " + input);
                    }
        
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();

        }
        return retrieved_animals;
    } 
}
