package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class startMenuController {
	
	@FXML
    private Button st_toRegisterBtn;

    @FXML
    private Button st_toLoginBtn;
    
    @FXML
    void st_toLogIn(ActionEvent event) {    	
    	try {
    		closeStartMenuWindow();
    		
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/logInMenu.fxml"));
    	    Parent root = (Parent) fxmlLoader.load();
    	    Stage stage = new Stage();
    	    stage.setTitle("LogIn Menu");
    	    stage.setResizable(false);
    	    stage.setScene(new Scene(root));
    	    stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void st_toRegister(ActionEvent event) {
    	try {
    		closeStartMenuWindow();
    		
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Interfaces/registerMenu.fxml"));
    		Parent root = (Parent) fxmlLoader.load();
    	    Stage stage = new Stage();
    	    stage.setTitle("Register Menu");
    	    stage.setResizable(false);
    	    stage.setScene(new Scene(root));  
    	    stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    //function for closing the startMenu window
    @FXML
    public void closeStartMenuWindow() {
    	//by getting the window from the button, we close it
    	//so we can open the new window without them being stacked
    	((Stage)st_toRegisterBtn.getScene().getWindow()).close();
    }
    
}
