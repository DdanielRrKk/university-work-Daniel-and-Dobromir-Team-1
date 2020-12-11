package Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import BusinessLogic.OperatorFunctions;
import BusinessLogic.OperatorFunctions.HBoxCell;
import DAO.AccountsDAOImplementation;
import DAO.BooksDAOImplementation;
import Interfaces.Main;
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
import javafx.util.Pair;

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
    
    private AccountsDAOImplementation adi = new AccountsDAOImplementation();
    private BooksDAOImplementation bdi=new BooksDAOImplementation();

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
    	btnReg.setText("Add");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	if(title.getText().isEmpty() || author.getText().isEmpty() || genre.getText().isEmpty() || list.getSelectionModel().isEmpty()==true || (rb1.isSelected()==false && rb2.isSelected()==false))
            	{
            		Alert alert = new Alert(AlertType.INFORMATION, "One or more boxes are empty.", ButtonType.OK);
            		alert.setTitle("Warning");
            		alert.setHeaderText(null);
            		alert.setGraphic(null);
            		alert.showAndWait();
            		return;
            	}
            	if(checkExistingBook(title.getText())==true) {
            		Alert alert = new Alert(AlertType.INFORMATION, "This book is already in the library.", ButtonType.OK);
            		alert.setTitle("Warning");
            		alert.setHeaderText(null);
            		alert.setGraphic(null);
            		alert.showAndWait();
            		return;
            	}
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly created an Book", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		of.BookToDB(new Books(0, title.getText(), author.getText(), genre.getText(), list.getSelectionModel().getSelectedIndex()+1, rb1.isSelected()), 1);
        		
        		title.clear();
        		author.clear();
        		genre.clear();
        		rb1.setSelected(false);
        		rb2.setSelected(false);
        		list.getSelectionModel().clearSelection();
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
            	Pair<String,Boolean> p=new Pair<>("",false);
            	
            	if(uName.getText().isEmpty() || pass.getText().isEmpty() || fName.getText().isEmpty() || lName.getText().isEmpty() || ucn.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty() || address.getText().isEmpty()) {
            		Alert alert = new Alert(AlertType.INFORMATION, "One or more boxes are empty.", ButtonType.OK);
            		alert.setTitle("Warning");
            		alert.setHeaderText(null);
            		alert.setGraphic(null);
            		alert.showAndWait();
            		return;
            	}
            	
            	if(checkEmail(email.getText())==true) {
            		Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
    				alert.setContentText("Invalid Email");
    	    		alert.setTitle("Warning");
    	    		alert.setHeaderText(null);
    	    		alert.setGraphic(null);
    	    		alert.showAndWait();
    	    		return;
    			}
            	
            	p=checkSymbols(fName.getText(), lName.getText(), address.getText());
    	    	if(p.getValue()==true) {
    	    		Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
    	    		alert.setContentText(p.getKey()+" shouldnt contain symbols");
    	    		alert.setTitle("Warning");
    	    		alert.setHeaderText(null);
    	    		alert.setGraphic(null);
    	    		alert.showAndWait();
    	    		return;
    	    	}
    	    	
    	    	p=checkLetters(phone.getText(), ucn.getText());
    	    	if(p.getValue()==true) {
    	    		Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
    	    		alert.setContentText(p.getKey()+" shouldnt contain letters");
    	    		alert.setTitle("Warning");
    	    		alert.setHeaderText(null);
    	    		alert.setGraphic(null);
    	    		alert.showAndWait();
    	    		return;
    	    	}
    	    	
    	    	p=checkSize(uName.getText(), pass.getText(), phone.getText(), ucn.getText());
    	    	if(p.getValue()==true) {
    	    		Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
    	    		if(p.getKey().equals("Username") || p.getKey().equals("Username")) {  	    			
    	    			alert.setContentText(p.getKey()+" must be at least 10 characters long");	    			
    	    		}
    	    		else {
    	    			alert.setContentText(p.getKey()+" must be 10 characters long");
    	    		}
    	    		alert.setTitle("Warning");
    	    		alert.setHeaderText(null);
    	    		alert.setGraphic(null);
    	    		alert.showAndWait();
    	    		return;
    	    	}
            	
            	p=checkExistingAccount(uName.getText(), phone.getText(), email.getText(), ucn.getText());
    	    	if(p.getValue()==true) {
    	    		Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
    	    		alert.setContentText(p.getKey()+" is already taken");
    	    		alert.setTitle("Warning");
    	    		alert.setHeaderText(null);
    	    		alert.setGraphic(null);
    	    		alert.showAndWait();
    	    		return;
    	    	}
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly created an Reader account", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		of.AccountToDB(new Accounts(0, fName.getText(), lName.getText(), ucn.getText(), phone.getText(), email.getText(), address.getText(), uName.getText(), pass.getText(), 3, 1, true), 1);
        		
        		fName.clear();
        		lName.clear();
        		ucn.clear();
        		phone.clear();
        		email.clear();
        		address.clear();
        		uName.clear();
        		pass.clear();
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
            	if(table.getSelectionModel().isEmpty()==true) {
            		Alert alert = new Alert(AlertType.INFORMATION, "You can edit only selected books from the table.", ButtonType.OK);
            		alert.setTitle("Warning");
            		alert.setHeaderText(null);
            		alert.setGraphic(null);
            		alert.showAndWait();
            		return;
            	}
            	if(title.getText().isEmpty() || author.getText().isEmpty() || genre.getText().isEmpty() || list.getSelectionModel().isEmpty()==true || (rb1.isSelected()==false && rb2.isSelected()==false))
            	{
            		Alert alert = new Alert(AlertType.INFORMATION, "One or more boxes are empty.", ButtonType.OK);
            		alert.setTitle("Warning");
            		alert.setHeaderText(null);
            		alert.setGraphic(null);
            		alert.showAndWait();
            		return;
            	}
            	
            	Books boo=table.getSelectionModel().getSelectedItem();
            	
            	if(boo.isAvailable()==false) {
            		Alert alert = new Alert(AlertType.INFORMATION, "This book is not available.", ButtonType.OK);
            		alert.setTitle("Warning");
            		alert.setHeaderText(null);
            		alert.setGraphic(null);
            		alert.showAndWait();
            		return;
            	}
            	
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly edited book", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		boo.setTitle(title.getText());
        		boo.setAuthor(author.getText());
        		boo.setGenre(genre.getText());
        		boo.setCondition(list.getSelectionModel().getSelectedIndex()+1);
        		boo.setAvailable(rb1.isSelected());
        		
        		if(list.getSelectionModel().getSelectedIndex()+1 == 9) {
        			Alert alert2 = new Alert(AlertType.CONFIRMATION, "Do you want to send this book for scraping?");
            		alert2.setTitle("Alert");
            		alert2.setHeaderText(null);
            		alert2.setGraphic(null);
            		
            		final Optional<ButtonType> result = alert2.showAndWait();
            		if (result.get() == ButtonType.OK){
            			of.removeThisBook(boo);
            		}
        		}
        		else {
        			of.BookToDB(boo, 2);
        		}
        		
        		table.getItems().clear();
        		of.setBookTableData(table);
        		
        		title.clear();
        		author.clear();
        		genre.clear();
        		rb1.setSelected(false);
        		rb2.setSelected(false);
        		list.getSelectionModel().clearSelection();
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
    	
    	TableView<Accounts> table = of.makeAccountsTable();
    	table.setMaxWidth(420);
    	
    	VBox vb=new VBox();
    	
    	TextField fname = new TextField();fname.setPromptText("First Name");
    	TextField lname = new TextField();lname.setPromptText("Last Name");
    	TextField ucn = new TextField();ucn.setPromptText("UCN");
    	TextField phone = new TextField();phone.setPromptText("Phone Number");
    	TextField email = new TextField();email.setPromptText("Email");
    	TextField address = new TextField();address.setPromptText("Address");
    	
    	vb.getChildren().add(fname);
        vb.getChildren().add(lname);
        vb.getChildren().add(ucn);
        vb.getChildren().add(phone);
        vb.getChildren().add(email);
        vb.getChildren().add(address);
    	
    	ListView<String> lv=new ListView();
    	ObservableList<String> l=FXCollections.observableArrayList ("1","2","3","4","5");
        lv.setItems(l);
    	lv.setOrientation(Orientation.HORIZONTAL);
    	lv.setMaxHeight(30);
    	lv.setMaxWidth(124);
    	
    	vb.getChildren().add(new Label("Rating"));
    	vb.getChildren().add(lv);
    	
    	Button btn1=new Button();
    	btn1.setText("Save");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	if(table.getSelectionModel().isEmpty()==true) {
            		Alert alert = new Alert(AlertType.INFORMATION, "You can edit only selected readers from the table.", ButtonType.OK);
            		alert.setTitle("Warning");
            		alert.setHeaderText(null);
            		alert.setGraphic(null);
            		alert.showAndWait();
            		return;
            	}
            	if(fname.getText().isEmpty() || lname.getText().isEmpty() || ucn.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty() || address.getText().isEmpty()) {
            		Alert alert = new Alert(AlertType.INFORMATION, "One or more boxes are empty.", ButtonType.OK);
            		alert.setTitle("Warning");
            		alert.setHeaderText(null);
            		alert.setGraphic(null);
            		alert.showAndWait();
            		return;
            	}
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly edited reader", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		Accounts acc=table.getSelectionModel().getSelectedItem();
        		acc.setFirstName(fname.getText());
        		acc.setLastName(lname.getText());
        		acc.setUCN(ucn.getText());
        		acc.setPhoneNumber(phone.getText());
        		acc.setEmail(email.getText());
        		acc.setAddress(address.getText());
        		acc.setRatingID(lv.getSelectionModel().getSelectedIndex()+1);
        		
        		of.AccountToDB(acc, 2);
        		
        		table.getItems().clear();
        		of.setAccountTableData(table);
        		
        		fname.clear();
        		lname.clear();
        		ucn.clear();
        		phone.clear();
        		email.clear();
        		address.clear();
        		lv.getSelectionModel().clearSelection();
            }
    	};
        btn1.setOnAction(ev);
        vb.getChildren().add(btn1);
    	
    	//===========================VBOX 2
    	VBox vb2=new VBox();
    	
    	vb2.getChildren().add(new Label("Edit Reader"));
    	vb2.getChildren().add(table);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb2.setAlignment(Pos.CENTER);
    	
    	vb.setSpacing(5);
    	vb2.setSpacing(5);
    	
    	opr_border.setCenter(vb);
    	opr_border.setRight(vb2);
    	
    	//====fill the textFields
    	of.getSelectedRowAccounts(table, vb, lv);
    }
    
    //=======================================REMOVE BOOK
    @FXML
    void removeBook(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	TableView<Books> table = of.makeBookTable();
    	
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
        			
        			table.getItems().clear();
        			of.setBookTableData(table);
        		}
            } 
        };
        btn2.setOnAction(ev2);
        
        VBox vb=new VBox();
        vb.getChildren().add(new Label("Remove Book"));
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
        			
        			table.getItems().clear();
        			of.setAccountTableData(table);
        		}
            } 
        };
        btn2.setOnAction(ev2);
        
        VBox vb=new VBox();
        vb.getChildren().add(new Label("Remove Reader"));
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
    	vb.getChildren().add(new Label("Total Books: "+table.getItems().size()));
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
    	vb.getChildren().add(new Label("Total Readers: "+table.getItems().size()));
    	vb.getChildren().add(table);
    	
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
    	opr_border.setCenter(vb);
    }
    
    //=======================================NOTIFICATIONS
    @FXML
    void notifications(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	ListView<HBoxCell> lv=of.makeNotifications();
    	of.setNotifyList(lv);
    	
    	VBox vb=new VBox();
    	vb.getChildren().add(new Label("Notifications"));
    	vb.getChildren().add(lv);
        
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(5);
    	
        opr_border.setCenter(vb);
    }
    
    //=======================================LOGOUT
    @FXML
    void opr_LoggingOut(ActionEvent event) {
    	of.stopThread();
    	Main.getInstance().setScene("../Interfaces/logInMenu.fxml");   	
    }
    
    //=======FOR VBOX
    
    private void addTextFieldToVBox(VBox vb, String txt) {
    	
    	TextField tf=new TextField();
    	tf.setPromptText(txt);
    	vb.getChildren().add(tf);
    }
    
    //============CHECKS
    private boolean checkExistingBook(String title) {
    	List<Books> li=new ArrayList<Books>();
		try {
			li = bdi.SelectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	for (Books b : li) {
			if(b.getTitle().equals(title)) {
				return true;
			}
		}
    	
    	return false;
    }
    
    private Pair<String,Boolean> checkExistingAccount(String uName, String phone, String email, String ucn) {
    	Pair<String,Boolean> p = new Pair<>("",false);
    	
    	List<Accounts> li=new ArrayList<Accounts>();
		try {
			li = adi.SelectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	for (Accounts a : li) {
			if(a.getUsername().equals(uName)) { p=new Pair<>("Username",true); }
			if(a.getPhoneNumber().equals(phone)) { p=new Pair<>("Phone Number",true); }
			if(a.getEmail().equals(email)) { p=new Pair<>("Email",true); }
			if(a.getUCN().equals(ucn)) { p=new Pair<>("UCN",true); }
		}
    	
    	return p;
    }
    
    private boolean checkEmail(String email) {
    	boolean flag=false;
    	
    	Pattern validMail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    	Matcher m = validMail.matcher(email);
    	flag=!m.find();
    	
    	return flag;
    }
    
    private Pair<String,Boolean> checkSymbols(String fName, String lName, String address) {
    	Pair<String,Boolean> p = new Pair<>("",false);
    	
    	if(fName.matches("^[a-zA-Z]*$") == false) { p=new Pair<>("First Name",true); }
    	if(lName.matches("^[a-zA-Z]*$") == false) { p=new Pair<>("Last Name",true); }
    	if(address.matches("^[a-zA-Z0-9]*$") == false) { p=new Pair<>("Address",true); }
    	
    	return p;
    }
    
    private Pair<String,Boolean> checkLetters(String phone, String ucn) {
    	Pair<String,Boolean> p = new Pair<>("",false);
    	
    	if(phone.matches("^[a-zA-Z]*$") == true) { p=new Pair<>("Phone Number",true); }
    	if(ucn.matches("^[a-zA-Z]*$") == true) { p=new Pair<>("UCN",true); }
    	
    	return p;
    }
    
    private Pair<String,Boolean> checkSize(String uName, String pass, String phone, String ucn) {
    	Pair<String,Boolean> p = new Pair<>("",false);
    	
    	if(uName.length()<10) { p=new Pair<>("Username",true); }
    	if(pass.length()<10) { p=new Pair<>("Password",true); }
    	if(phone.length() != 10) { p=new Pair<>("Phone Number",true); }
    	if(ucn.length() != 10) { p=new Pair<>("UCN",true); }
    	
    	return p;
    }
    
}
