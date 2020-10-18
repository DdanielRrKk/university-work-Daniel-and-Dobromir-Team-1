package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class registerMenuController {

    @FXML
    private TextField reg_fNameText;

    @FXML
    private TextField reg_lNameText;

    @FXML
    private TextField reg_phoneText;

    @FXML
    private TextField reg_addressText;

    @FXML
    private Button reg_registerBtn;

    @FXML
    private TextField reg_emailText;
    
    @FXML
    private Button reg_backBtn;
    
    @FXML
    private TextField reg_user;

    @FXML
    private PasswordField reg_pass;

    @FXML
    void reg_registering(ActionEvent event) {
    	
    }
    
    @FXML
    void reg_backToStartManu(ActionEvent event) {
    	closeRegisterWindow();
    	fxmlScreenLoader fcl=new fxmlScreenLoader();
    	fcl.loadScreen("../Interfaces/startMenu.fxml");
    }
    
    private void closeRegisterWindow() {
    	((Stage)reg_backBtn.getScene().getWindow()).close();
    }

}
