package Controllers;

import java.sql.SQLException;

import DAO.AccountsDAOImplementation;
import Interfaces.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class logInMenuController {

    @FXML
    private TextField li_userText;

    @FXML
    private PasswordField li_passText;
    
    @FXML
    private Button li_logInBtn;
    
    @FXML
    private Button li_BackBtn;

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
        	Main.getInstance().setScene("../Interfaces/adminMenu.fxml");
	    }
        else if(accdao.ValidateLogin(username, password) == 2)
        {
        	Main.getInstance().setScene("../Interfaces/operatorMenu.fxml");
        }
        else if(accdao.ValidateLogin(username, password) == 3) 
        {
        	Main.getInstance().setScene("../Interfaces/readerMenu.fxml");
        }
    }    	
    
    @FXML
    void li_backToStartManu(ActionEvent event) 
    {
    	closeLogInWindow();
    	fxmlScreenLoader fcl = new fxmlScreenLoader();
    	fcl.loadScreen("../Interfaces/startMenu.fxml");
    }
    
    private void infoBox(String infoMessage, String headerText, String title) 
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    private void closeLogInWindow() 
    {
    	((Stage)li_userText.getScene().getWindow()).close();
	}

}