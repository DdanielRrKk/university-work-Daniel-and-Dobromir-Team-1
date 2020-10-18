package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class fxmlScreenLoader {
	public void loadScreen(String path) {
		try {
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Library System");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	}
}
