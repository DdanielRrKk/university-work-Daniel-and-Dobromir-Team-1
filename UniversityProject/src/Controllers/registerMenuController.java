package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    void reg_registering(ActionEvent event) {
    	
    }
    
    @FXML
    void reg_backToStartManu(ActionEvent event) {
    	try {
    		closeRegisterWindow();
    		
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/startMenu.fxml"));
    	    Parent root = (Parent) fxmlLoader.load();
    	    Stage stage = new Stage();
    	    stage.setTitle("Start Menu");
    	    stage.setResizable(false);
    	    stage.setScene(new Scene(root));
    	    stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    void closeRegisterWindow() {
    	((Stage)reg_backBtn.getScene().getWindow()).close();
    }

}
