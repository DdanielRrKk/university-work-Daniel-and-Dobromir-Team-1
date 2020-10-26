package Controllers;

import java.util.Optional;

import Model.Accounts;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
        
        VBox vb=new VBox();
        addTextFieldToVBox(vb, "Username");
        addTextFieldToVBox(vb, "Password");
        addTextFieldToVBox(vb, "First Name");
        addTextFieldToVBox(vb, "Last Name");
        addTextFieldToVBox(vb, "Phone Number");
        addTextFieldToVBox(vb, "Email");
        addTextFieldToVBox(vb, "Address");
        vb.getChildren().add(btnReg);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	adm_border.setCenter(vb);
    }

    @FXML
    void adm_remove(ActionEvent event) {
    	adm_border.setRight(null);
    	
    	TableView<Accounts> table = new TableView<>();
    	table.setEditable(false);
    	
    	TableColumn<Accounts, Integer> idCol = new TableColumn<>("ID");
    	idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
    	idCol.setMaxWidth(30);
    	
    	TableColumn<Accounts, String> fNameCol = new TableColumn<>("First Name");
    	fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	
        TableColumn<Accounts, String> lNameCol = new TableColumn<>("Last Name");
        lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        
        TableColumn<Accounts, String> ucnCol = new TableColumn<>("UCN");
        ucnCol.setCellValueFactory(new PropertyValueFactory<>("UCN"));
        
        TableColumn<Accounts, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        
        TableColumn<Accounts, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        TableColumn<Accounts, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        table.getColumns().addAll(idCol, fNameCol, lNameCol, ucnCol,  phoneCol, emailCol, addressCol);
    	
        ObservableList<Accounts> ola=table.getItems();
        FilteredList<Accounts> flPerson = new FilteredList(ola, p -> true);
        table.setItems(flPerson);
        
        TextField tf = new TextField();
        tf.setPromptText("Search UCN");
        tf.setOnKeyReleased(keyEvent -> {
         	flPerson.setPredicate(p -> p.getUCN().contains(tf.getText().trim()));            
        });
        
        Button btn2=new Button();
    	btn2.setText("Remove");
    	EventHandler<ActionEvent> ev2 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to remove this operator?");
        		alert.setTitle("Alert");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		
        		Optional<ButtonType> result = alert.showAndWait();
        		if (result.get() == ButtonType.OK){
        		    // remove from db
        		} else {
        		    // nothig
        		}
            } 
        };
        btn2.setOnAction(ev2);
        
    	VBox vb=new VBox();
    	vb.getChildren().add(new Label("Remove Operator"));
    	vb.getChildren().add(tf);
    	vb.getChildren().add(table);
    	vb.getChildren().add(btn2);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	adm_border.setCenter(vb);
    }
    
    @FXML
    void adm_showOperators(ActionEvent event) {
    	adm_border.setRight(null);
    	
    	TableView<Accounts> table = new TableView<>();
    	table.setEditable(false);
    	
    	TableColumn<Accounts, Integer> idCol = new TableColumn<>("ID");
    	idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
    	idCol.setMaxWidth(30);
    	
    	TableColumn<Accounts, String> fNameCol = new TableColumn<>("First Name");
    	fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	
        TableColumn<Accounts, String> lNameCol = new TableColumn<>("Last Name");
        lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        
        TableColumn<Accounts, String> ucnCol = new TableColumn<>("UCN");
        ucnCol.setCellValueFactory(new PropertyValueFactory<>("UCN"));
        
        TableColumn<Accounts, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        
        TableColumn<Accounts, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        TableColumn<Accounts, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        table.getColumns().addAll(idCol, fNameCol, lNameCol, ucnCol,  phoneCol, emailCol, addressCol);
    	
    	
    	VBox vb=new VBox();
    	vb.getChildren().add(new Label("All Operators"));
    	vb.getChildren().add(table);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
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
    
    //=======FOR VBOX
    
    private void addTextFieldToVBox(VBox vb, String txt) {
    	
    	TextField tf=new TextField();
    	tf.setPromptText(txt);
    	vb.getChildren().add(tf);
    }

}
