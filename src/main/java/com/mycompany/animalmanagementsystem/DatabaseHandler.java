/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animalmanagementsystem;

import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author francescosciabbarrasi
 */
public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/zoo";
    private static final String USER = "root";
    private static final String PASSWORD = " ";
    
    String sql = "INSERT INTO zoo_animals (species, type, name, habitat, dob, weight, specific_data) VALUES (?, ?, ?, ?, ?, ?, ?)";

    // Method to insert animals into the database
    public void insertAnimals(List<Animal> animals) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static final Logger LOG = Logger.getLogger(DatabaseHandler.class.getName());

    
    
}
