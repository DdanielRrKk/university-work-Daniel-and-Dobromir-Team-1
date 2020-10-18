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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class adminMenuController {
	
	@FXML
    private BorderPane adm_border;

    @FXML
    private Button adm_addBtn;

    @FXML
    private Button adm_removeBtn;
    
    @FXML
    private Button adm_showOperators;

    @FXML
    private Button adm_logOutBtn;
    
    @FXML
    void adm_add(ActionEvent event) { 	
    	adm_border.setRight(null);
    	VBox vb=new VBox();
    	
    	TextField tf1=new TextField();
    	tf1.setPromptText("Username");
    	vb.getChildren().add(tf1);
    	vb.setMargin(tf1, new Insets(5,10,5,10));
    	
    	TextField tf2=new TextField();
    	tf2.setPromptText("Password");
    	vb.getChildren().add(tf2);
    	vb.setMargin(tf2, new Insets(5,10,5,10));
    	
    	TextField tf3=new TextField();
    	tf3.setPromptText("First Name");
    	vb.getChildren().add(tf3);
    	vb.setMargin(tf3, new Insets(5,10,5,10));
    	
    	TextField tf4=new TextField();
    	tf4.setPromptText("Last Name");
    	vb.getChildren().add(tf4);
    	vb.setMargin(tf4, new Insets(5,10,5,10));
    	
    	TextField tf5=new TextField();
    	tf5.setPromptText("Phone Number");
    	vb.getChildren().add(tf5);
    	vb.setMargin(tf5, new Insets(5,10,5,10));
    	
    	TextField tf6=new TextField();
    	tf6.setPromptText("Email");
    	vb.getChildren().add(tf6);
    	vb.setMargin(tf6, new Insets(5,10,5,10));
    	
    	TextField tf7=new TextField();
    	tf7.setPromptText("Address");
    	vb.getChildren().add(tf7);
    	vb.setMargin(tf7, new Insets(5,10,5,10));
    	
    	Button btnReg=new Button();
    	btnReg.setText("Register");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly created an Operator account", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		// register operator in db
            } 
        };
        btnReg.setOnAction(ev);
        vb.getChildren().add(btnReg);
        vb.setMargin(btnReg, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	
    	adm_border.setCenter(vb);
    }

    @FXML
    void adm_remove(ActionEvent event) {
    	adm_border.setRight(null);
    	
    	// ============ vbox 2
    	VBox vb2=new VBox();
    	Label l1=new Label("Remove Operator");
    	vb2.getChildren().add(l1);
    	vb2.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
    	TableColumn idCol = new TableColumn("ID");
    	idCol.setMaxWidth(30);
    	TableColumn uNameCol = new TableColumn("Username");
    	TableColumn fNameCol = new TableColumn("First Name");
        TableColumn lNameCol = new TableColumn("Last Name");
        table.getColumns().addAll(idCol,uNameCol, fNameCol, lNameCol);
        table.setMaxWidth(370);
    	vb2.getChildren().add(table);
    	vb2.setMargin(table, new Insets(5,10,5,10));
    	
    	// ============== vbox 1
        VBox vb=new VBox();
    	TextField tf1=new TextField();
    	tf1.setPromptText("Operator Username");
    	vb.getChildren().add(tf1);
    	vb.setMargin(tf1, new Insets(5,10,5,10));
    	
    	Button btn1=new Button();
    	btn1.setText("Search");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "Operator successfuly found");
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		Label l3=new Label("Username");
            	vb.getChildren().add(l3);
            	vb.setMargin(l3, new Insets(5,10,5,10));
        		
        		Label l1=new Label("First Name");
            	vb.getChildren().add(l1);
            	vb.setMargin(l1, new Insets(5,10,5,10));
            	
            	Label l2=new Label("Last Name");
            	vb.getChildren().add(l2);
            	vb.setMargin(l2, new Insets(5,10,5,10));
            } 
        };
        btn1.setOnAction(ev);
        vb.getChildren().add(btn1);
        vb.setMargin(btn1, new Insets(5,10,5,10));
        
        Button btn2=new Button();
    	btn2.setText("Remove");
    	EventHandler<ActionEvent> ev2 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to remove this operator?");
        		alert.setTitle("Alert");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		// remove operator from db function
            } 
        };
        btn2.setOnAction(ev2);
        
        //========== vbox 3
        VBox vb3=new VBox();
        vb3.getChildren().add(vb);
        vb3.setMargin(vb, new Insets(5,10,5,10));
        vb3.getChildren().add(btn2);
        vb3.setMargin(btn2, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	vb2.setAlignment(Pos.CENTER);
    	vb3.setAlignment(Pos.CENTER);
    	
    	adm_border.setRight(vb2);
    	adm_border.setCenter(vb3);
    }
    
    @FXML
    void adm_showOperators(ActionEvent event) {
    	adm_border.setRight(null);
    	VBox vb=new VBox();
    	
    	Label l1=new Label("All Operators");
    	vb.getChildren().add(l1);
    	vb.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
    	TableColumn idCol = new TableColumn("ID");
    	idCol.setMaxWidth(30);
    	TableColumn uNameCol = new TableColumn("Username");
    	TableColumn fNameCol = new TableColumn("First Name");
        TableColumn lNameCol = new TableColumn("Last Name");
        table.getColumns().addAll(idCol,uNameCol, fNameCol, lNameCol);
        vb.getChildren().add(table);
    	vb.setMargin(table, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	
    	adm_border.setCenter(vb);
    }
    
    @FXML
    void adm_LoggingOut(ActionEvent event) {
    	closeAdminMenuWindow();
    	fxmlScreenLoader fcl=new fxmlScreenLoader();
    	fcl.loadScreen("../Interfaces/logInMenu.fxml");
    }
    
    private void closeAdminMenuWindow() {
    	((Stage)adm_logOutBtn.getScene().getWindow()).close();
    }

}