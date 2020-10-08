package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class logInMenuController {

    @FXML
    private TextField li_userText;

    @FXML
    private TextField li_passText;
    
    @FXML
    private Button li_logInBtn;
    
    @FXML
    private Button li_BackBtn;

    @FXML
    void li_loggingIn(ActionEvent event) {
    	
    	//String username=li_userText.getText();
    	//String password=li_passText.getText();
    	
    	try {
        	closeLogInWindow();
        		
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/adminMenu.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Admin Menu");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }    	
    
    @FXML
    void li_backToStartManu(ActionEvent event) {
    	try {
    		closeLogInWindow();
    		
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
    
    public void closeLogInWindow() {
    	((Stage)li_userText.getScene().getWindow()).close();
	}

}