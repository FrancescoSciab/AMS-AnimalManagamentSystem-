/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.animalmanagementsystem;
import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner; 
//import java.io.IOException;
public class AnimalManagementSystem {

    public static void main(String[] args) {
        try {
        File fileEntered = new File("animals.txt");
        Scanner myReader = new Scanner(fileEntered);
        
        while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred reading your file");
          e.printStackTrace();
        }
    }
}
