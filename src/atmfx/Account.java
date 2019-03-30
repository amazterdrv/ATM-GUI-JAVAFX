/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmfx;

public class Account 
{ 
    private int id; //Account number or id 
    private double balance; //Account balance
    private static double annualInterestRate = 0; 
    private final java.util.Date dateCreated; //Date of account opening

    public Account() 
    {
        dateCreated = new java.util.Date();
    } 
    
    public Account(int newId, double newBalance)  //Constructor which takes in account number and balance as parameters
    { 
        id = newId; 
        balance = newBalance;
        dateCreated = new java.util.Date();
    }

    public int getId() //Getter function which returns account id
    { 
        return id;
    } 
    
    public double getBalance() //Getter function to obtain account balance
    { 
        return balance;
    }

    public static double getAnnualInterestRate() 
    { 
        return annualInterestRate;

    }

    public void setId(int newId) //Setter function to set account id
    { 
        id = newId;
    }

    public void setBalance(double newBalance)   //Setter function to set account balance
    { 
        balance = newBalance;
    }

    public static void setAnnualInterestRate(double newAnnualInterestRate) 
    {

        annualInterestRate = newAnnualInterestRate;
    } 
    
    public double getMonthlyInterest() 
    {   
        return balance * (annualInterestRate / 1200);
    }
    
    public java.util.Date getDateCreated() //This function returns the date of account creation
    { 
        return dateCreated;
    }

    public void withdraw(double amount) //This function is used to withdraw amount and reduce the balance
    { 
        balance -= amount;
    }

    public void deposit(double amount)  //This function is used to deposit money and increase the balance
    { 
        balance += amount;
    }
}