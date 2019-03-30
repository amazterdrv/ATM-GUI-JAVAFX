/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmfx;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//This class controls or interacts with the GUI developed in fxml file
public class MitchellATMController implements Initializable {
    
    // We get buttons from the GUI in variables
    @FXML
    private Button button0;
            
    @FXML
    private Button button1;
    
    @FXML
    private Button button2;
            
    @FXML
    private Button button3;

    @FXML
    private Button button4;
            
    @FXML
    private Button button5;
    
    @FXML
    private Button button6;
            
    @FXML
    private Button button7;         
            
    @FXML
    private Button button8;
            
    @FXML
    private Button button9;
    
    @FXML
    private Button buttonDeposit;
    
    @FXML
    private Button buttonBalance;
    
    
    @FXML
    private Button buttonWithdraw;
    
    @FXML
    private Button buttonCancel;
            
    @FXML
    private Button buttonEnter;     
    
    @FXML
    private TextField resultArea; //This is text field where whatever user types in appears 
    
    @FXML
    private TextArea infoArea;  //This is the text area in which system outputs are visible
    
    private int flag = 1; //flag variable is used to represent the state of the atm 
    //flag = 0 means that currently no transaction has been selected after the user has entered a correct account number
    //flag = 1 means that the user has not even entered a correct account number
    //flag = 2 means that the deposit button has been clicked so the user should enter amount and press enter button
    //flag = 3 means that the withdraw button has been clicked so the user should enter amount and press enter button
    private Account currentAccount = null; //This is the account in use initially set to null
                
    
    @FXML
    private void handleButtonAction(ActionEvent event) //This function handles button events
    {
        //From buttons0 to buttons9 all when pressed append the number pressed to the end of the already typed number
        if(event.getSource() == button0)
        {
            resultArea.appendText("0");
        }
        else if(event.getSource() == button1)
        {
            resultArea.appendText("1");
        }
        else if(event.getSource() == button2)
        {
            resultArea.appendText("2");
        }
        else if(event.getSource() == button3)
        {
            resultArea.appendText("3");
        }
        else if(event.getSource() == button4)
        {
            resultArea.appendText("4");
        }
        else if(event.getSource() == button5)
        {
            resultArea.appendText("5");
        }
        else if(event.getSource() == button6)
        {
            resultArea.appendText("6");
        }
        else if(event.getSource() == button7)
        {
            resultArea.appendText("7");
        }
        else if(event.getSource() == button8)
        {
            resultArea.appendText("8");
        }
        else if(event.getSource() == button9)
        {
            resultArea.appendText("9");
        }
        else if(event.getSource() == buttonEnter) //This condition is triggered when enter button is pressed
        {
            String entered = resultArea.getText(); //Enterd text is obtained
            if(entered == null | entered == "")
            {
                infoArea.setText("Value Entered not valid");
            }
            else if(flag == 0) //If flag is 0 no tracsaction is selected
            {
                infoArea.setText("Select a valid next transaction-->");
            }
            else if(flag == 1) //As flag is 1 no valid account number has been enetered
            {
                boolean match = false; 
                int account_no = Integer.parseInt(entered);
                for(Account account: AtmFx.accounts) //Account numbers in the text file are compared to the number entered 
                {
                    if(account_no == account.getId())
                    {
                        currentAccount = account;
                        match = true;
                    }
                }
                resultArea.clear(); 
                if(match == false) //If no match found wrong number is entered
                {
                    infoArea.setText("Wrong Account number! Try again !");
                }
                else //Successfully matched
                {
                   infoArea.setText("Welcome account number : "+currentAccount.getId()+"\n"+"Select your next transaction -->");
                   flag = 0;
                }
            }
            else if(flag == 2) //Flag = 2 means that deposit has benn selected
            {
                double money = Double.parseDouble(entered);
                currentAccount.deposit(money); //Deposit function is called of currentAccount of Account class
                infoArea.setText("Your current balance is: "+currentAccount.getBalance()+"\n"+"Select your next transaction -->");
                resultArea.clear(); //After transaction is completed area is cleared
                flag = 0;
            }
            else if(flag == 3)
            {
                double money = Double.parseDouble(entered);
                
                if(currentAccount.getBalance()<money) //If Withdrawal amount is more than balance then error is shown 
                {
                    infoArea.setText("Insuffiecient Balance! Your current balance is: "+currentAccount.getBalance()+"\n"+"Select your next transaction -->");
                    resultArea.clear();
                }
                else //Withdrawal amount is less than balance
                {
                    currentAccount.withdraw(money);  //Mooney is withdrawn
                    infoArea.setText("Your current balance is: "+currentAccount.getBalance()+"\n"+"Select your next transaction -->");
                    resultArea.clear();
                }
                flag = 0;
            }
        }
        else if(event.getSource() == buttonBalance) //Balance button is selected so balance is shown
        {
            if(currentAccount != null)
            {
                infoArea.setText("Your current balance is: "+currentAccount.getBalance()+"\n"+"Select your next transaction -->");
            }
            else
            {
                infoArea.setText("Enter a valid account number before checking balance!");
            }
        }
        else if(event.getSource() == buttonDeposit) //Deposit button is selected so flag changes to 2 
        {
            if(currentAccount != null)
            {
                infoArea.setText("Enter amount to deposit: ");
                flag = 2;
            }
            else
            {
                infoArea.setText("Enter a valid account number before depositing money!");
            }
        }
        else if(event.getSource() == buttonWithdraw) //Withdraw button is chosed to flag turns to 3
        {
            if(currentAccount != null)
            {
                infoArea.setText("Enter amount to withdraw: ");
                flag = 3;
            }
            else
            {
                infoArea.setText("Enter a valid account number before withdrawing money!");
            }
        }
        else if(event.getSource() == buttonCancel) //Current transaction is canceled so flag is set to 0
        {
            if(currentAccount != null)
            {
                infoArea.setText("Welcome account number : "+currentAccount.getId()+"\n"+"Select your next transaction -->");
                flag = 0;
                resultArea.clear();
            }
            else
            {
                resultArea.clear();
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
