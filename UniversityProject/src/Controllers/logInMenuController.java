package Controllers;

import java.sql.SQLException;

import DAO.AccountsDAOImplementation;
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
    void li_loggingIn(ActionEvent event) throws SQLException {
    	
    	//==============================================================TESTING
    	if (li_userText.getText().equals("1") && li_passText.getText().equals("1")) {
    		closeLogInWindow();
        	fxmlScreenLoader fcl=new fxmlScreenLoader();
        	fcl.loadScreen("../Interfaces/adminMenu.fxml");
        }
    	else if (li_userText.getText().equals("2") && li_passText.getText().equals("2")) {
    		closeLogInWindow();
        	fxmlScreenLoader fcl=new fxmlScreenLoader();
        	fcl.loadScreen("../Interfaces/operatorMenu.fxml");
        }
    	else if(li_userText.getText().equals("3") && li_passText.getText().equals("3")) {
    		closeLogInWindow();
        	fxmlScreenLoader fcl=new fxmlScreenLoader();
        	fcl.loadScreen("../Interfaces/readerMenu.fxml");
    	}
    	//==============================================================TESTING
    	
    	if (li_userText.getText().isEmpty()) {showAlert(1); return;}
        if (li_passText.getText().isEmpty()) {showAlert(2);	return;}
        
        AccountsDAOImplementation accdao = new AccountsDAOImplementation();
        
        if(accdao.ValidateLogin(li_userText.getText(), li_passText.getText())) {
        	closeLogInWindow();
        	fxmlScreenLoader fcl=new fxmlScreenLoader();
        	fcl.loadScreen("../Interfaces/adminMenu.fxml");
	    }
        else {/*showAlert(3); return;*/}
    }    	
    
    @FXML
    void li_backToStartManu(ActionEvent event) {
    	closeLogInWindow();
    	fxmlScreenLoader fcl=new fxmlScreenLoader();
    	fcl.loadScreen("../Interfaces/startMenu.fxml");
    }
    
    private void showAlert(int i) {
    	Alert alert = new Alert(AlertType.ERROR);
    	switch(i) {
    	case 1: alert.setContentText("Username can not be empty"); break;
    	case 2: alert.setContentText("Password can not be empty"); break;
    	case 3: alert.setContentText("Unable to login"); break;
    	}
    	alert.setTitle("Warning!");
    	alert.setHeaderText(null);
		alert.setGraphic(null);
		alert.showAndWait();
    }
    
    private void closeLogInWindow() {
    	((Stage)li_userText.getScene().getWindow()).close();
	}

}