package Controllers;

import BusinessLogic.OperatorFunctions;
import Model.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class readerMenuController {

    @FXML
    private BorderPane rd_border;

    @FXML
    private Button rd_getBookBtn;

    @FXML
    private Button rd_returnBookBtn;

    @FXML
    private Button rd_logOutBtn;

    private OperatorFunctions of=new OperatorFunctions();

    @FXML
    void rd_requestBook(ActionEvent event) {
    	rd_border.setRight(null);
    	
    	TableView<Books> table = of.makeBookTable();
    	
    	ObservableList<Books> ola=table.getItems();
        FilteredList<Books> flPerson = new FilteredList(ola, p -> true);
        table.setItems(flPerson);
        
        TextField tf = new TextField();
        tf.setPromptText("Search Title");
        tf.setOnKeyReleased(keyEvent -> {
         	flPerson.setPredicate(p -> p.getTitle().toLowerCase().contains(tf.getText().toLowerCase().trim()));            
        });
        
    	Button btn1=new Button();
    	btn1.setText("Request");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly requested book", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		// send request function
            } 
        };
        btn1.setOnAction(ev);
        
        VBox vb=new VBox();
        vb.getChildren().add(new Label("All Books"));
        vb.getChildren().add(tf);
        vb.getChildren().add(table);
        vb.getChildren().add(btn1);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	rd_border.setCenter(vb);
    }

    @FXML
    void rd_requestReturnBook(ActionEvent event) {
    	rd_border.setRight(null);
    	
    	// book titles
    	ObservableList<String> listRows = FXCollections.<String>observableArrayList();
    	ListView<String> list = new ListView<>(listRows);
        list.setOrientation(Orientation.VERTICAL);
        list.setMaxHeight(150);
    	
    	Button btn1=new Button();
    	btn1.setText("Return");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly returned book", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		// return book function
            } 
        };
        btn1.setOnAction(ev);
        
        VBox vb=new VBox();
        vb.getChildren().add(new Label("Return Book"));
        vb.getChildren().add(list);
        vb.getChildren().add(btn1);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	rd_border.setCenter(vb);
    }

    @FXML
    void rd_LoggingOut(ActionEvent event) {
    	closeReaderMenuWindow();
    	fxmlScreenLoader fcl=new fxmlScreenLoader();
    	fcl.loadScreen("../Interfaces/logInMenu.fxml");
    }
    
    private void closeReaderMenuWindow() {
    	((Stage)rd_logOutBtn.getScene().getWindow()).close();
	}
    
}
