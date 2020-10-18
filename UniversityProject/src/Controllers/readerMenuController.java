package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.HBox;
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
    private Button rd_changeReturnDateBtn;

    @FXML
    private Button rd_logOutBtn;


    @FXML
    void rd_requestBook(ActionEvent event) {
    	rd_border.setRight(null);
    	VBox vb=new VBox();
    	
    	Label l1=new Label("All Books");
    	vb.getChildren().add(l1);
    	vb.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
        TableColumn titleCol = new TableColumn("Title");
        TableColumn authorCol = new TableColumn("Author");
        TableColumn genreCol = new TableColumn("Genre");
        TableColumn conditionCol = new TableColumn("Condition");
        TableColumn availableCol = new TableColumn("Available");
        table.getColumns().addAll(titleCol, authorCol, genreCol, conditionCol, availableCol);
        
        vb.getChildren().add(table);
    	vb.setMargin(table, new Insets(5,10,5,10));
    	
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
        vb.getChildren().add(btn1);
        vb.setMargin(btn1, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	
    	rd_border.setCenter(vb);
    }

    @FXML
    void rd_requestReturnBook(ActionEvent event) {
    	rd_border.setRight(null);
    	VBox vb=new VBox();
    	
    	// book titles
    	ObservableList<String> listRows = FXCollections.<String>observableArrayList();
    	ListView<String> list = new ListView<>(listRows);
        list.setOrientation(Orientation.VERTICAL);
        list.setMaxHeight(100);
        vb.getChildren().add(list);
    	vb.setMargin(list, new Insets(5,10,5,10));
    	
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
        vb.getChildren().add(btn1);
        vb.setMargin(btn1, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	rd_border.setCenter(vb);
    }
    
    @FXML
    void rd_requestChangeReturnDate(ActionEvent event) {
    	rd_border.setRight(null);
    	
    	// ============ vbox 1
    	VBox vb=new VBox();
    	
    	Label l1=new Label("Change Return Date");
    	vb.getChildren().add(l1);
    	vb.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
        TableColumn titleCol = new TableColumn("Title");
        TableColumn recCol = new TableColumn("Received Date");
        recCol.setMinWidth(100);
        TableColumn retCol = new TableColumn("Return Date");
        table.getColumns().addAll(titleCol, recCol, retCol);
        vb.getChildren().add(table);
        vb.setMargin(table, new Insets(5,10,5,10));
    	
        // ================= vbox 2
        VBox vb2=new VBox();
        
        Label le1=new Label("Current Return Date:");
    	vb2.getChildren().add(le1);
    	vb2.setMargin(le1, new Insets(5,10,5,10));
    	
    	Label le2=new Label("Return Date");
    	vb2.getChildren().add(le2);
    	vb2.setMargin(le2, new Insets(5,10,5,10));
    	
    	Label le3=new Label("New Return Date:");
    	vb2.getChildren().add(le3);
    	vb2.setMargin(le3, new Insets(5,10,5,10));
    	
    	DatePicker d = new DatePicker();
    	d.setPromptText("New Return Date");
    	vb2.getChildren().add(d);
    	vb2.setMargin(d, new Insets(5,10,5,10));
        
        Button btn1=new Button();
    	btn1.setText("Request");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly requested to change return date", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		// send request function
            } 
        };
        btn1.setOnAction(ev);
        vb2.getChildren().add(btn1);
        vb2.setMargin(btn1, new Insets(5,10,5,10));
        
        vb.setAlignment(Pos.CENTER);
        vb2.setAlignment(Pos.CENTER);
        
        rd_border.setRight(vb);
        rd_border.setCenter(vb2);
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
