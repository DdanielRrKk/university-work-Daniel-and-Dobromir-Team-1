package Controllers;

import BusinessLogic.OperatorFunctions;
import Model.Accounts;
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
    private TextField reg_ucn;

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
    
    OperatorFunctions of=new OperatorFunctions();

    @FXML
    void reg_registering(ActionEvent event) {
    	Accounts acc=new Accounts();
    	acc.setUsername(reg_user.getText());
    	acc.setPassword(reg_pass.getText());
    	acc.setFirstName(reg_fNameText.getText());
    	acc.setLastName(reg_lNameText.getText());
    	acc.setPhoneNumber(reg_phoneText.getText());
    	acc.setEmail(reg_emailText.getText());
    	acc.setUCN(reg_ucn.getText());
    	acc.setAddress(reg_addressText.getText());
    	acc.setRoleID(3);
    	acc.setRatingID(1);
    	
    	of.registerRequest(acc);
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
