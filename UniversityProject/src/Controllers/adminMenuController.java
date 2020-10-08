package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class adminMenuController {

    @FXML
    private Button adm_addBtn;

    @FXML
    private Button adm_removeBtn;

    @FXML
    private Button adm_logOutBtn;
    
    @FXML
    void adm_LoggingOut(ActionEvent event) {
    	try {
    		closeAdminMenuWindow();
    		
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
    
    public void closeAdminMenuWindow() {
    	((Stage)adm_logOutBtn.getScene().getWindow()).close();
    }

}