/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmfx;

import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner;
import java.io.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtmFx extends Application {
    
    public static ArrayList<Account> accounts = new ArrayList<>(); //Arraylist of Account class onjects
        
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MitchellATM.fxml"));
        
        Scene scene = new Scene(root); //Scene is initialized
        
        stage.setScene(scene);
        stage.show(); //Stage is shown
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileReader inputFile = null;  //Initially inputFile is set to null
        try {
            inputFile = new FileReader("AccountDataIn.txt"); //File is initialized which contains accounts woth their ids and opening balances
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AtmFx.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Input file is wrapped around try catch so as to detect exceptions
        Scanner parser = new Scanner(inputFile); 
        while (parser.hasNextLine())   //Input file is parsed line by line
        {
            String line = parser.nextLine();  //A single line is obtained as a string
            String[] acc = line.split(" "); //First 2 accounts are separated by a space in between
            for (String elm: acc) //We look through each individual account
            {
                String[] att = elm.split(",");
                System.out.println(att[0]); //It should contain 'C'
                System.out.println(att[1]); //It contains account number
                System.out.println(att[2]); //It contains account balance
                Account account = new Account(Integer.parseInt(att[1]),Double.parseDouble(att[2])); //We initialize a single account object
                accounts.add(account); //We add this account instance to the arrayList of accounts
            }
	}
        launch(args);
    }
    
}
