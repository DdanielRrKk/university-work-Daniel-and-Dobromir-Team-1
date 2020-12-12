package Controllers;

import Interfaces.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class startMenuController {
	
	@FXML
    private Button st_toRegisterBtn;

    @FXML
    private Button st_toLoginBtn;
    
    @FXML
    void st_toLogIn(ActionEvent event) {
    	Main.getInstance().setScene("/Interfaces/logInMenu.fxml");
    }

    @FXML
    void st_toRegister(ActionEvent event) {
    	Main.getInstance().setScene("/Interfaces/registerMenu.fxml");
    }   
}
