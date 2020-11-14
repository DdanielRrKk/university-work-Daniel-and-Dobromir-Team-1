package Controllers;

import java.util.Optional;

import BusinessLogic.OperatorFunctions;
import Model.Accounts;
import Model.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class operatorMenuController {
	
	@FXML
    private BorderPane opr_border;
	
	@FXML
    private Button opr_addReaderBtn;

    @FXML
    private Button opr_addBookBtn;

    @FXML
    private Button opr_editReaderBtn;

    @FXML
    private Button opr_editBookBtn;

    @FXML
    private Button opr_removeReaderBtn;

    @FXML
    private Button opr_removeBookBtn;

    @FXML
    private Button opr_showAllReadersBtn;

    @FXML
    private Button opr_showAllBooksBtn;

    @FXML
    private Button opr_searchReaderBtn;

    @FXML
    private Button opr_searchBookBtn;

    @FXML
    private Button opr_notificationsBtn;

    @FXML
    private Button opr_logOutBtn;
    
    private OperatorFunctions of=new OperatorFunctions();

    //================================================ADD BOOK
    @FXML
    void addBook(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	ObservableList<String> listRows = FXCollections.<String>observableArrayList("As new", "Fine", "Very good", "Good", "Fair", "Worn", "Poor", "Very poor", "For scrapping");
    	ListView<String> list = new ListView<>(listRows);
        list.setOrientation(Orientation.VERTICAL);
        list.setMaxHeight(100);
    	
    	ToggleGroup group=new ToggleGroup();
    	RadioButton rb1 = new RadioButton("Available");
    	rb1.setToggleGroup(group);
    	RadioButton rb2 = new RadioButton("Unavailabe");
    	rb2.setToggleGroup(group);
    	
		HBox hb5=new HBox(rb1,rb2);
		hb5.setSpacing(5);
		hb5.setAlignment(Pos.CENTER);
		
		VBox vb=new VBox();
		vb.getChildren().add(new Label("Add Book"));
    	
		TextField title = new TextField();title.setPromptText("Title");
        TextField author = new TextField();author.setPromptText("Author");
        TextField genre = new TextField();genre.setPromptText("Genre");
        
        vb.getChildren().add(title);
        vb.getChildren().add(author);
        vb.getChildren().add(genre);
    	
    	Button btnReg=new Button();
    	btnReg.setText("Create");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly created an Book", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		of.BookToDB(new Books(title.getText(), author.getText(), genre.getText(), list.getSelectionModel().getSelectedIndex(), rb1.isSelected()), 1);
        		
            } 
        };
        btnReg.setOnAction(ev);
        
    	vb.getChildren().add(list);
    	vb.getChildren().add(hb5);
        vb.getChildren().add(btnReg);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	opr_border.setCenter(vb);
    }

    //================================================ADD READER
    @FXML
    void addReader(ActionEvent event) {
    	opr_border.setRight(null);

    	VBox vb=new VBox();
    	vb.getChildren().add(new Label("Add Reader"));
    	TextField uName = new TextField();uName.setPromptText("Username");
        TextField pass = new TextField();pass.setPromptText("Password");
        TextField fName = new TextField();fName.setPromptText("First Name");
        TextField lName = new TextField();lName.setPromptText("Last Name");
        TextField ucn = new TextField();ucn.setPromptText("UCN");
        TextField phone = new TextField();phone.setPromptText("Phone Number");
        TextField email = new TextField();email.setPromptText("Email");
        TextField address = new TextField();address.setPromptText("Address");
        
        vb.getChildren().add(uName);
        vb.getChildren().add(pass);
        vb.getChildren().add(fName);
        vb.getChildren().add(lName);
        vb.getChildren().add(ucn);
        vb.getChildren().add(phone);
        vb.getChildren().add(email);
        vb.getChildren().add(address);
    	
    	Button btnReg=new Button();
    	btnReg.setText("Register");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly created an Reader account", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		of.AccountToDB(new Accounts(fName.getText(), lName.getText(), ucn.getText(), phone.getText(), email.getText(), address.getText(), uName.getText(), pass.getText(), 3, 1, false), 1);
            } 
        };
        btnReg.setOnAction(ev);
        vb.getChildren().add(btnReg);
            	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	opr_border.setCenter(vb);
    }
    
    //=======================================EDIT BOOK
    @FXML
    void editBook(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	TableView<Books> table = of.makeBookTable();
    	table.setMaxWidth(420);
        
    	ObservableList<String> listRows = FXCollections.<String>observableArrayList("As new", "Fine", "Very good", "Good", "Fair", "Worn", "Poor", "Very poor", "For scrapping");
    	ListView<String> list = new ListView<>(listRows);
        list.setOrientation(Orientation.VERTICAL);
        list.setMaxHeight(100);
    	
    	ToggleGroup group=new ToggleGroup();
    	RadioButton rb1 = new RadioButton("Available");
    	rb1.setToggleGroup(group);
    	RadioButton rb2 = new RadioButton("Unavailable");
    	rb2.setToggleGroup(group);
		HBox hb5=new HBox(rb1,rb2);
		hb5.setAlignment(Pos.CENTER);
		
		VBox vb=new VBox();
        
		TextField title = new TextField();title.setPromptText("Title");
        TextField author = new TextField();author.setPromptText("Author");
        TextField genre = new TextField();genre.setPromptText("Genre");
        
        vb.getChildren().add(title);
        vb.getChildren().add(author);
        vb.getChildren().add(genre);
        
    	vb.getChildren().add(list);
    	vb.getChildren().add(hb5);
    	
    	Button btn1=new Button();
    	btn1.setText("Save");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly edited book", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		System.out.println(list.getSelectionModel().getSelectedIndex());
        		System.out.println(rb1.isSelected());
        		
        		of.BookToDB(new Books(title.getText(), author.getText(), genre.getText(), list.getSelectionModel().getSelectedIndex(), rb1.isSelected()), 2);
        		
            }
        };
        btn1.setOnAction(ev);
        
        vb.getChildren().add(btn1);
        
        // right border
    	VBox vb2=new VBox();

    	vb2.getChildren().add(new Label("Edit Book"));
        vb2.getChildren().add(table);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb2.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	opr_border.setCenter(vb);
    	opr_border.setRight(vb2);
    	
    	   		
    	of.getSelectedRowBooks(table, vb, list);
    	
    }

    //=======================================EDIT READER
    @FXML
    void editReader(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	VBox vb=new VBox();
    	
    	addTextFieldToVBox(vb, "First Name");
    	addTextFieldToVBox(vb, "Last Name");
    	addTextFieldToVBox(vb, "UCN");
    	addTextFieldToVBox(vb, "Phone Number");
    	addTextFieldToVBox(vb, "Email");
    	addTextFieldToVBox(vb, "Address");
    	
    	vb.getChildren().add(new Label("Readers Books"));
    	
    	// book titles 
    	ObservableList<String> listRows2 = FXCollections.<String>observableArrayList();
    	ListView<String> list2 = new ListView<>(listRows2);
    	list2.setOrientation(Orientation.VERTICAL);
    	list2.setMaxHeight(50);
    	vb.getChildren().add(list2);
    	
    	Button btn1=new Button();
    	btn1.setText("Save");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly edited reader", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		Accounts acc=new Accounts();
        		
        		for (Node n : vb.getChildren()) {
        			if(n instanceof TextField) {
        				try {
        					TextField tf=(TextField)n;
        					if(tf.getPromptText().equals("Username")) {
        						acc.setUsername(tf.getText());
        					}
        					if(tf.getPromptText().equals("Password")) {
        						acc.setPassword(tf.getText());
        					}
        					if(tf.getPromptText().equals("First Name")) {
        						acc.setFirstName(tf.getText());
        					}
        					if(tf.getPromptText().equals("Last Name")) {
        						acc.setLastName(tf.getText());
        					}
        					if(tf.getPromptText().equals("UCN")) {
        						acc.setUCN(tf.getText());
        					}
        					if(tf.getPromptText().equals("Phone Number")) {
        						acc.setPhoneNumber(tf.getText());
        					}
        					if(tf.getPromptText().equals("Email")) {
        						acc.setEmail(tf.getText());
        					}
        					if(tf.getPromptText().equals("Address")) {
        						acc.setAddress(tf.getText());
        					}
        				}
        				catch(Exception ex) {
        					break;
        				}
        			}
        			acc.setRatingID(1);
        			acc.setRoleID(3);
            	}
        		
        		of.AccountToDB(acc, 2);
            }
    	};
        btn1.setOnAction(ev);
        vb.getChildren().add(btn1);
    	
    	//===========================VBOX 2
    	VBox vb2=new VBox();
    	
    	vb2.getChildren().add(new Label("Edit Reader"));
        
    	TableView<Accounts> table = of.makeAccountsTable();
    	table.setMaxWidth(420);
    	vb2.getChildren().add(table);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb2.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	opr_border.setCenter(vb);
    	opr_border.setRight(vb2);
    	
    	//====fill the textFields
    	of.getSelectedRowAccounts(table, vb);
    }
    
    //=======================================REMOVE BOOK
    @FXML
    void removeBook(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	TableView<Books> table = of.makeBookTable();
        
        ObservableList<Books> ola=table.getItems();
        FilteredList<Books> flPerson = new FilteredList(ola, p -> true);
        table.setItems(flPerson);
        
        TextField tf = new TextField();
        tf.setPromptText("Search Title");
        tf.setOnKeyReleased(keyEvent -> {
         	flPerson.setPredicate(p -> p.getTitle().toLowerCase().contains(tf.getText().toLowerCase().trim()));            
        });
    	
        Button btn2=new Button();
    	btn2.setText("Remove");
    	EventHandler<ActionEvent> ev2 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to delete this book?");
        		alert.setTitle("Alert");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		
        		final Optional<ButtonType> result = alert.showAndWait();
        		if (result.get() == ButtonType.OK){
        			of.removeBook(table);
        		}
            } 
        };
        btn2.setOnAction(ev2);
        
        VBox vb=new VBox();
        vb.getChildren().add(new Label("Remove Book"));
        vb.getChildren().add(tf);
        vb.getChildren().add(table);
        vb.getChildren().add(btn2);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	opr_border.setCenter(vb);
    }

    //=======================================REMOVE READER
    @FXML
    void removeReader(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	TableView<Accounts> table = of.makeAccountsTable();
        
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
            	Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to remove this reader?");
        		alert.setTitle("Alert");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);

        		Optional<ButtonType> result = alert.showAndWait();
        		if (result.get() == ButtonType.OK){
        			of.removeAccount(table);
        		}
            } 
        };
        btn2.setOnAction(ev2);
        
        VBox vb=new VBox();
        vb.getChildren().add(new Label("Remove Reader"));
    	vb.getChildren().add(tf);        
        vb.getChildren().add(table);
        vb.getChildren().add(btn2);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);

    	opr_border.setCenter(vb);
    }

    //=======================================SEARCH BOOK
    @FXML
    void searchBook(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	TableView<Books> table = of.makeBookTable();
        
        ObservableList<Books> ola=table.getItems();
        FilteredList<Books> flPerson = new FilteredList(ola, p -> true);
        table.setItems(flPerson);
        
        TextField tf = new TextField();
        tf.setPromptText("Search Title");
        tf.setOnKeyReleased(keyEvent -> {
         	flPerson.setPredicate(p -> p.getTitle().toLowerCase().contains(tf.getText().toLowerCase().trim()));            
        });
        
        VBox vb=new VBox();        
        vb.getChildren().add(new Label("Search Book"));
        vb.getChildren().add(tf);        
        vb.getChildren().add(table);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	opr_border.setCenter(vb);
    }
    
    //=======================================SEARCH READER
    @FXML
    void searchReader(ActionEvent event) {
    	opr_border.setRight(null);

    	TableView<Accounts> table = of.makeAccountsTable();
        
        ObservableList<Accounts> ola=table.getItems();
        FilteredList<Accounts> flPerson = new FilteredList(ola, p -> true);
        table.setItems(flPerson);
        
        TextField tf = new TextField();
        tf.setPromptText("Search UCN");
        tf.setOnKeyReleased(keyEvent -> {
         	flPerson.setPredicate(p -> p.getUCN().contains(tf.getText().trim()));            
        });
        
    	VBox vb=new VBox();        
    	vb.getChildren().add(new Label("Search Reader"));
        vb.getChildren().add(tf);        
        vb.getChildren().add(table);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);

    	opr_border.setCenter(vb);
    }

    //=======================================SHOW ALL BOOKS
    @FXML
    void showAllBooks(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	TableView<Books> table = of.makeBookTable();
    	
    	VBox vb=new VBox();
    	vb.getChildren().add(new Label("All Books"));
    	vb.getChildren().add(table);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);

    	opr_border.setCenter(vb);
    }

    //=======================================SHOW ALL READERS
    @FXML
    void showAllReaders(ActionEvent event) {
    	opr_border.setRight(null);

    	TableView<Accounts> table = of.makeAccountsTable();
    	
    	VBox vb=new VBox();
    	vb.getChildren().add(new Label("All Readers"));
    	vb.getChildren().add(table);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	opr_border.setCenter(vb);
    }
    
    //=======================================NOTIFICATIONS
    @FXML
    void notifications(ActionEvent event) {
    	
    }
    
    //=======================================LOGOUT
    @FXML
    void opr_LoggingOut(ActionEvent event) {
    	of.stopThread();
    	closeOperatorMenuWindow();
    	fxmlScreenLoader fcl=new fxmlScreenLoader();
    	fcl.loadScreen("../Interfaces/logInMenu.fxml");
    }

    @FXML
    private void closeOperatorMenuWindow() {
    	((Stage)opr_addReaderBtn.getScene().getWindow()).close();
    }
    
    //=======FOR VBOX
    
    private void addTextFieldToVBox(VBox vb, String txt) {
    	
    	TextField tf=new TextField();
    	tf.setPromptText(txt);
    	vb.getChildren().add(tf);
    }
    
}
