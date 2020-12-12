package Controllers;

import java.sql.SQLException;

import BusinessLogic.OperatorFunctions;
import DAO.AccountsDAOImplementation;
import Interfaces.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class logInMenuController {

    @FXML
    private TextField li_userText;

    @FXML
    private PasswordField li_passText;
    
    @FXML
    private Button li_logInBtn;
    
    @FXML
    private Button li_BackBtn;
    
    OperatorFunctions of=new OperatorFunctions();

    @FXML
    void li_loggingIn(ActionEvent event) throws SQLException 
    {   	
    	String username = li_userText.getText();
    	String password = li_passText.getText();
    	
    	if(username.isEmpty())
    	{
    		infoBox("Please enter a username.", null, "Login");
    		return;
    	}
    	
    	if(password.isEmpty())
    	{
    		infoBox("Please enter a password.", null, "Login");
    		return;
    	}
        
        AccountsDAOImplementation accdao = new AccountsDAOImplementation();
        
        if(accdao.ValidateLogin(username, password) == 0) 
        {   	
        	infoBox("Invalid login.", null, "Login");
    		return;
	    }
        if(accdao.ValidateLogin(username, password) == 1) 
        {   	
        	Main.getInstance().setID(accdao.GetUserID(username, password));
        	Main.getInstance().setScene("/Interfaces/adminMenu.fxml");       	
	    }
        else if(accdao.ValidateLogin(username, password) == 2)
        {
        	of.startNotifyThread();        	
        	Main.getInstance().setID(accdao.GetUserID(username, password));
        	Main.getInstance().setScene("/Interfaces/operatorMenu.fxml"); 
        }
        else if(accdao.ValidateLogin(username, password) == 3) 
        {
        	Main.getInstance().setID(accdao.GetUserID(username, password));
        	Main.getInstance().setScene("/Interfaces/readerMenu.fxml");         	
        }
    }    	
    
    @FXML
    void li_backToStartManu(ActionEvent event) 
    {
    	Main.getInstance().setScene("/Interfaces/startMenu.fxml");
    }
    
    private void infoBox(String infoMessage, String headerText, String title) 
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}