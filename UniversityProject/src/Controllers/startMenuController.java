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
    	closeStartMenuWindow();
    	fxmlScreenLoader fcl=new fxmlScreenLoader();
    	fcl.loadScreen("../Interfaces/logInMenu.fxml");
    }

    @FXML
    void st_toRegister(ActionEvent event) {
    	closeStartMenuWindow();
    	fxmlScreenLoader fcl=new fxmlScreenLoader();
    	fcl.loadScreen("../Interfaces/registerMenu.fxml");
    }
    
    //function for closing the startMenu window
    @FXML
    private void closeStartMenuWindow() {
    	//by getting the window from the button, we close it
    	//so we can open the new window without them being stacked
    	((Stage)st_toRegisterBtn.getScene().getWindow()).close();
    }
    
}
