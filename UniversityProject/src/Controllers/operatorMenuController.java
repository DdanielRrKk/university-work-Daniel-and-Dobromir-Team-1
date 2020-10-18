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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
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

    //================================================ADD BOOK
    @FXML
    void addBook(ActionEvent event) {
    	opr_border.setRight(null);
    	VBox vb=new VBox();
    	
    	Label l1=new Label("Add Book");
    	vb.getChildren().add(l1);
    	vb.setMargin(l1, new Insets(5,10,5,10));
    	
    	TextField tf1=new TextField();
    	tf1.setPromptText("Title");
    	vb.getChildren().add(tf1);
    	vb.setMargin(tf1, new Insets(5,10,5,10));
    	
    	TextField tf2=new TextField();
    	tf2.setPromptText("Author");
    	vb.getChildren().add(tf2);
    	vb.setMargin(tf2, new Insets(5,10,5,10));
    	
    	TextField tf3=new TextField();
    	tf3.setPromptText("Genre");
    	vb.getChildren().add(tf3);
    	vb.setMargin(tf3, new Insets(5,10,5,10));
    	
    	ObservableList<String> listRows = FXCollections.<String>observableArrayList("As new", "Fine", "Very good", "Good", "Fair", "Worn", "Poor", "Very poor", "For scrapping");
    	ListView<String> list = new ListView<>(listRows);
        list.setOrientation(Orientation.VERTICAL);
        list.setMaxHeight(100);
        vb.getChildren().add(list);
    	vb.setMargin(list, new Insets(5,10,5,10));
    	
    	ToggleGroup group=new ToggleGroup();
    	RadioButton rb1 = new RadioButton("Available");
    	rb1.setToggleGroup(group);
    	RadioButton rb2 = new RadioButton("Unavailabe");
    	rb2.setToggleGroup(group);
		HBox hb5=new HBox(rb1,rb2);
		hb5.setMargin(rb1, new Insets(0,5,0,5));
		hb5.setMargin(rb2, new Insets(0,5,0,5));
		hb5.setAlignment(Pos.CENTER);
    	vb.getChildren().add(hb5);
    	vb.setMargin(hb5, new Insets(5,10,5,10));
    	
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
        		
        		// add book in db function
            } 
        };
        btnReg.setOnAction(ev);
        vb.getChildren().add(btnReg);
        vb.setMargin(btnReg, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	
    	opr_border.setCenter(vb);
    }

    //================================================ADD READER
    @FXML
    void addReader(ActionEvent event) {
    	opr_border.setRight(null);
    	VBox vb=new VBox();
    	
    	Label l1=new Label("Add Reader");
    	vb.getChildren().add(l1);
    	vb.setMargin(l1, new Insets(5,10,5,10));
    	
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
    	
    	TextField tf8=new TextField();
    	tf8.setPromptText("UCN");
    	vb.getChildren().add(tf8);
    	vb.setMargin(tf8, new Insets(5,10,5,10));
    	
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
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly created an Reader account", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		// register account in db function
            } 
        };
        btnReg.setOnAction(ev);
        vb.getChildren().add(btnReg);
        vb.setMargin(btnReg, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	
    	opr_border.setCenter(vb);
    }
    
    //=======================================EDIT BOOK
    @FXML
    void editBook(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	// book id
        ObservableList<String> listRows = FXCollections.<String>observableArrayList();
    	ListView<String> list = new ListView<>(listRows);
        list.setOrientation(Orientation.VERTICAL);
        list.setMaxWidth(30);
        list.setMinWidth(30);
        
        VBox vb=new VBox();
        TextField tf1=new TextField();
    	tf1.setPromptText("Title");
    	vb.getChildren().add(tf1);
    	vb.setMargin(tf1, new Insets(5,10,5,10));
    	
    	TextField tf2=new TextField();
    	tf2.setPromptText("Author");
    	vb.getChildren().add(tf2);
    	vb.setMargin(tf2, new Insets(5,10,5,10));
    	
    	TextField tf3=new TextField();
    	tf3.setPromptText("Genre");
    	vb.getChildren().add(tf3);
    	vb.setMargin(tf3, new Insets(5,10,5,10));
    	
    	ObservableList<String> listRows2 = FXCollections.<String>observableArrayList("As new", "Fine", "Very good", "Good", "Fair", "Worn", "Poor", "Very poor", "For scrapping");
    	ListView<String> list2 = new ListView<>(listRows2);
        list2.setOrientation(Orientation.VERTICAL);
        list2.setMaxHeight(100);
        vb.getChildren().add(list2);
    	vb.setMargin(list2, new Insets(5,10,5,10));
    	
    	ToggleGroup group=new ToggleGroup();
    	RadioButton rb1 = new RadioButton("Available");
    	rb1.setToggleGroup(group);
    	RadioButton rb2 = new RadioButton("Unavailabe");
    	rb2.setToggleGroup(group);
		HBox hb5=new HBox(rb1,rb2);
		hb5.setMargin(rb1, new Insets(0,5,0,5));
		hb5.setMargin(rb2, new Insets(0,5,0,5));
		hb5.setAlignment(Pos.CENTER);
    	vb.getChildren().add(hb5);
    	vb.setMargin(hb5, new Insets(5,10,5,10));
    	
    	Button btn1=new Button();
    	btn1.setText("Save");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly found book", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		// save changes function
            }
        };
        btn1.setOnAction(ev);
        vb.getChildren().add(btn1);
        vb.setMargin(btn1, new Insets(5,10,5,10));
        
        // right border
    	VBox vb2=new VBox();
    	Label l1=new Label("Edit Book");
    	vb2.getChildren().add(l1);
    	vb2.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
    	TableColumn idCol = new TableColumn("ID");
    	idCol.setMaxWidth(30);
        TableColumn titleCol = new TableColumn("Title");
        TableColumn authorCol = new TableColumn("Author");
        TableColumn genreCol = new TableColumn("Genre");
        TableColumn conditionCol = new TableColumn("Condition");
        TableColumn availableCol = new TableColumn("Available");
        table.getColumns().addAll(idCol, titleCol, authorCol, genreCol, conditionCol, availableCol);
        table.setMaxWidth(420);
        
        vb2.getChildren().add(table);
    	vb2.setMargin(table, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	vb2.setAlignment(Pos.CENTER);
    	
    	HBox hb=new HBox(list, vb);
    	opr_border.setCenter(hb);
    	opr_border.setRight(vb2);
    }

    //=======================================EDIT READER
    @FXML
    void editReader(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	// reader id
        ObservableList<String> listRows = FXCollections.<String>observableArrayList();
    	ListView<String> list = new ListView<>(listRows);
        list.setOrientation(Orientation.VERTICAL);
        list.setMaxWidth(30);
        list.setMinWidth(30);
    	
    	VBox vb=new VBox();
    	TextField tf3=new TextField();
    	tf3.setPromptText("First Name");
    	vb.getChildren().add(tf3);
    	vb.setMargin(tf3, new Insets(5,10,5,10));
    	
    	TextField tf4=new TextField();
    	tf4.setPromptText("Last Name");
    	vb.getChildren().add(tf4);
    	vb.setMargin(tf4, new Insets(5,10,5,10));
    	
    	TextField tf8=new TextField();
    	tf8.setPromptText("UCN");
    	vb.getChildren().add(tf8);
    	vb.setMargin(tf8, new Insets(5,10,5,10));
    	
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
    	
    	//=== in hbox
    	Label le1=new Label("Readers Books");
    	vb.getChildren().add(le1);
    	vb.setMargin(le1, new Insets(5,10,5,10));
    	
    	// book titles ==== in hbox
    	ObservableList<String> listRows2 = FXCollections.<String>observableArrayList();
    	ListView<String> list2 = new ListView<>(listRows2);
    	list2.setOrientation(Orientation.VERTICAL);
    	list2.setMaxHeight(50);
    	vb.getChildren().add(list2);
    	vb.setMargin(list2, new Insets(5,10,5,10));
    	
    	Button btn1=new Button();
    	btn1.setText("Save");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "You have successfuly found reader", ButtonType.OK);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		// save function
            }
    	};
        btn1.setOnAction(ev);
        vb.getChildren().add(btn1);
        vb.setMargin(btn1, new Insets(5,10,5,10));
        
    	//=======================================VBOX 2
    	VBox vb2=new VBox();
    	Label l1=new Label("Edit Reader");
    	vb2.getChildren().add(l1);
    	vb2.setMargin(l1, new Insets(5,10,5,10));
        
    	TableView table = new TableView();
    	table.setEditable(false);
    	TableColumn idCol = new TableColumn("ID");
    	idCol.setMaxWidth(30);
    	TableColumn fNameCol = new TableColumn("First Name");
        TableColumn lNameCol = new TableColumn("Last Name");
        TableColumn ucnCol = new TableColumn("UCN");
        TableColumn phoneCol = new TableColumn("Phone");
        TableColumn emailCol = new TableColumn("Email");
        TableColumn addressCol = new TableColumn("Address");
        table.getColumns().addAll(idCol, fNameCol, lNameCol, ucnCol,  phoneCol, emailCol, addressCol);
        table.setMaxWidth(420);
    	vb2.getChildren().add(table);
    	vb2.setMargin(table, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	vb2.setAlignment(Pos.CENTER);
    	
    	HBox hb1=new HBox(list, vb);
    	opr_border.setCenter(hb1);
    	opr_border.setRight(vb2);
    }
    
    //=======================================REMOVE BOOK
    @FXML
    void removeBook(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	// ============ vbox 2
    	VBox vb2=new VBox();
    	Label l1=new Label("Remove Book");
    	vb2.getChildren().add(l1);
    	vb2.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
    	TableColumn idCol = new TableColumn("ID");
    	idCol.setMaxWidth(30);
        TableColumn titleCol = new TableColumn("Title");
        TableColumn authorCol = new TableColumn("Author");
        TableColumn genreCol = new TableColumn("Genre");
        TableColumn conditionCol = new TableColumn("Condition");
        TableColumn availableCol = new TableColumn("Available");
        table.getColumns().addAll(idCol, titleCol, authorCol, genreCol, conditionCol, availableCol);
    	vb2.getChildren().add(table);
    	vb2.setMargin(table, new Insets(5,10,5,10));
    	
    	// ============== vbox 1
        VBox vb=new VBox();
    	TextField tf1=new TextField();
    	tf1.setPromptText("Book Title");
    	vb.getChildren().add(tf1);
    	vb.setMargin(tf1, new Insets(5,10,5,10));
    	
    	Button btn1=new Button();
    	btn1.setText("Search");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "Book successfuly found");
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		Label l1=new Label("Title");
            	vb.getChildren().add(l1);
            	vb.setMargin(l1, new Insets(5,10,5,10));
            	
            	Label l2=new Label("Author");
            	vb.getChildren().add(l2);
            	vb.setMargin(l2, new Insets(5,10,5,10));
            	
            	Label l3=new Label("Genre");
            	vb.getChildren().add(l3);
            	vb.setMargin(l3, new Insets(5,10,5,10));
            	
            	Label l4=new Label("Condition");
            	vb.getChildren().add(l4);
            	vb.setMargin(l4, new Insets(5,10,5,10));
            	
            	Label l5=new Label("Available");
            	vb.getChildren().add(l5);
            	vb.setMargin(l5, new Insets(5,10,5,10));
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
            	Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to delete this book?");
        		alert.setTitle("Alert");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		// remove book from db function
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
    	
    	opr_border.setRight(vb2);
    	opr_border.setCenter(vb3);
    }

    //=======================================REMOVE READER
    @FXML
    void removeReader(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	// ============ vbox 2
    	VBox vb2=new VBox();
    	Label l1=new Label("Remove Reader");
    	vb2.getChildren().add(l1);
    	vb2.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
    	TableColumn idCol = new TableColumn("ID");
    	idCol.setMaxWidth(30);
    	TableColumn fNameCol = new TableColumn("First Name");
        TableColumn lNameCol = new TableColumn("Last Name");
        TableColumn ucnCol = new TableColumn("UCN");
        TableColumn phoneCol = new TableColumn("Phone");
        TableColumn emailCol = new TableColumn("Email");
        TableColumn addressCol = new TableColumn("Address");
        table.getColumns().addAll(idCol, fNameCol, lNameCol, ucnCol,  phoneCol, emailCol, addressCol);
        table.setMaxWidth(420);
    	vb2.getChildren().add(table);
    	vb2.setMargin(table, new Insets(5,10,5,10));
    	
    	// ============== vbox 1
        VBox vb=new VBox();
    	TextField tf1=new TextField();
    	tf1.setPromptText("Reader UCN");
    	vb.getChildren().add(tf1);
    	vb.setMargin(tf1, new Insets(5,10,5,10));
    	
    	Button btn1=new Button();
    	btn1.setText("Search");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "Reader successfuly found");
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		Label l1=new Label("First Name");
            	vb.getChildren().add(l1);
            	vb.setMargin(l1, new Insets(5,10,5,10));
            	
            	Label l2=new Label("Last Name");
            	vb.getChildren().add(l2);
            	vb.setMargin(l2, new Insets(5,10,5,10));
            	
            	Label l3=new Label("UCN");
            	vb.getChildren().add(l3);
            	vb.setMargin(l3, new Insets(5,10,5,10));
            	
            	Label l4=new Label("Phone Number");
            	vb.getChildren().add(l4);
            	vb.setMargin(l4, new Insets(5,10,5,10));
            	
            	Label l5=new Label("Email");
            	vb.getChildren().add(l5);
            	vb.setMargin(l5, new Insets(5,10,5,10));
            	
            	Label l6=new Label("Address");
            	vb.getChildren().add(l6);
            	vb.setMargin(l6, new Insets(5,10,5,10));
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
            	Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to remove this reader?");
        		alert.setTitle("Alert");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		// remove reader from db function
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
    	
    	opr_border.setRight(vb2);
    	opr_border.setCenter(vb3);
    }

    //=======================================SEARCH BOOK
    @FXML
    void searchBook(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	// ============ vbox 2
    	VBox vb2=new VBox();
    	Label l1=new Label("Search Book");
    	vb2.getChildren().add(l1);
    	vb2.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
    	TableColumn idCol = new TableColumn("ID");
    	idCol.setMaxWidth(30);
        TableColumn titleCol = new TableColumn("Title");
        TableColumn authorCol = new TableColumn("Author");
        TableColumn genreCol = new TableColumn("Genre");
        TableColumn conditionCol = new TableColumn("Condition");
        TableColumn availableCol = new TableColumn("Available");
        table.getColumns().addAll(idCol, titleCol, authorCol, genreCol, conditionCol, availableCol);
    	table.setMaxWidth(420);
        vb2.getChildren().add(table);
    	vb2.setMargin(table, new Insets(5,10,5,10));
    	
    	// ============== vbox 1
        VBox vb=new VBox();
    	TextField tf1=new TextField();
    	tf1.setPromptText("Book Title");
    	vb.getChildren().add(tf1);
    	vb.setMargin(tf1, new Insets(5,10,5,10));
    	
    	Button btn1=new Button();
    	btn1.setText("Search");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "Book successfuly found");
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		Label l1=new Label("Title");
            	vb.getChildren().add(l1);
            	vb.setMargin(l1, new Insets(5,10,5,10));
            	
            	Label l2=new Label("Author");
            	vb.getChildren().add(l2);
            	vb.setMargin(l2, new Insets(5,10,5,10));
            	
            	Label l3=new Label("Genre");
            	vb.getChildren().add(l3);
            	vb.setMargin(l3, new Insets(5,10,5,10));
            	
            	Label l4=new Label("Condition");
            	vb.getChildren().add(l4);
            	vb.setMargin(l4, new Insets(5,10,5,10));
            	
            	Label l5=new Label("Available");
            	vb.getChildren().add(l5);
            	vb.setMargin(l5, new Insets(5,10,5,10));
            } 
        };
        btn1.setOnAction(ev);
        vb.getChildren().add(btn1);
        vb.setMargin(btn1, new Insets(5,10,5,10));
        
    	vb.setAlignment(Pos.CENTER);
    	vb2.setAlignment(Pos.CENTER);
    	
    	opr_border.setRight(vb2);
    	opr_border.setCenter(vb);
    }
    
    //=======================================SEARCH READER
    @FXML
    void searchReader(ActionEvent event) {
    	opr_border.setRight(null);
    	
    	// ============ vbox 2
    	VBox vb2=new VBox();
    	Label l1=new Label("Search Reader");
    	vb2.getChildren().add(l1);
    	vb2.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
    	TableColumn idCol = new TableColumn("ID");
    	idCol.setMaxWidth(30);
    	TableColumn fNameCol = new TableColumn("First Name");
        TableColumn lNameCol = new TableColumn("Last Name");
        TableColumn ucnCol = new TableColumn("UCN");
        TableColumn phoneCol = new TableColumn("Phone");
        TableColumn emailCol = new TableColumn("Email");
        TableColumn addressCol = new TableColumn("Address");
        table.getColumns().addAll(idCol, fNameCol, lNameCol, ucnCol,  phoneCol, emailCol, addressCol);
        table.setMaxWidth(420);
        vb2.getChildren().add(table);
    	vb2.setMargin(table, new Insets(5,10,5,10));
    	
    	// ============== vbox 1
        VBox vb=new VBox();
    	TextField tf1=new TextField();
    	tf1.setPromptText("Reader UCN");
    	vb.getChildren().add(tf1);
    	vb.setMargin(tf1, new Insets(5,10,5,10));
    	
    	Button btn1=new Button();
    	btn1.setText("Search");
    	EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.INFORMATION, "Reader successfuly found");
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
        		
        		Label l1=new Label("First Name");
            	vb.getChildren().add(l1);
            	vb.setMargin(l1, new Insets(5,10,5,10));
            	
            	Label l2=new Label("Last Name");
            	vb.getChildren().add(l2);
            	vb.setMargin(l2, new Insets(5,10,5,10));
            	
            	Label l3=new Label("UCN");
            	vb.getChildren().add(l3);
            	vb.setMargin(l3, new Insets(5,10,5,10));
            	
            	Label l4=new Label("Phone Number");
            	vb.getChildren().add(l4);
            	vb.setMargin(l4, new Insets(5,10,5,10));
            	
            	Label l5=new Label("Email");
            	vb.getChildren().add(l5);
            	vb.setMargin(l5, new Insets(5,10,5,10));
            	
            	Label l6=new Label("Address");
            	vb.getChildren().add(l6);
            	vb.setMargin(l6, new Insets(5,10,5,10));
            } 
        };
        btn1.setOnAction(ev);
        vb.getChildren().add(btn1);
        vb.setMargin(btn1, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	vb2.setAlignment(Pos.CENTER);
    	
    	opr_border.setRight(vb2);
    	opr_border.setCenter(vb);
    }

    //=======================================SHOW ALL BOOKS
    @FXML
    void showAllBooks(ActionEvent event) {
    	opr_border.setRight(null);
    	VBox vb=new VBox();
    	
    	Label l1=new Label("All Books");
    	vb.getChildren().add(l1);
    	vb.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
    	TableColumn idCol = new TableColumn("ID");
    	idCol.setMaxWidth(30);
        TableColumn titleCol = new TableColumn("Title");
        TableColumn authorCol = new TableColumn("Author");
        TableColumn genreCol = new TableColumn("Genre");
        TableColumn conditionCol = new TableColumn("Condition");
        TableColumn availableCol = new TableColumn("Available");
        table.getColumns().addAll(titleCol, authorCol, genreCol, conditionCol, availableCol);
        
        vb.getChildren().add(table);
    	vb.setMargin(table, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	
    	opr_border.setCenter(vb);
    }

    //=======================================SHOW ALL READERS
    @FXML
    void showAllReaders(ActionEvent event) {
    	opr_border.setRight(null);
    	VBox vb=new VBox();
    	
    	Label l1=new Label("All Readers");
    	vb.getChildren().add(l1);
    	vb.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
    	TableColumn idCol = new TableColumn("ID");
    	idCol.setMaxWidth(30);
    	TableColumn fNameCol = new TableColumn("First Name");
        TableColumn lNameCol = new TableColumn("Last Name");
        TableColumn ucnCol = new TableColumn("UCN");
        TableColumn phoneCol = new TableColumn("Phone");
        TableColumn emailCol = new TableColumn("Email");
        TableColumn addressCol = new TableColumn("Address");
        table.getColumns().addAll(idCol, fNameCol, lNameCol, ucnCol,  phoneCol, emailCol, addressCol);
        vb.getChildren().add(table);
    	vb.setMargin(table, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	
    	opr_border.setCenter(vb);
    }
    
    //=======================================NOTIFICATIONS
    @FXML
    void notifications(ActionEvent event) {
    	opr_border.setRight(null);
    	VBox vb=new VBox();
    	
    	Label l1=new Label("Notifications");
    	vb.getChildren().add(l1);
    	vb.setMargin(l1, new Insets(5,10,5,10));
    	
    	TableView table = new TableView();
    	table.setEditable(false);
        TableColumn dateCol = new TableColumn("Date");
        TableColumn uchCol = new TableColumn("UCH");
        TableColumn fNameCol = new TableColumn("First Name");
        TableColumn lNameCol = new TableColumn("Last Name");
        TableColumn titleCol = new TableColumn("Title");
        table.getColumns().addAll(dateCol, uchCol, fNameCol, lNameCol, titleCol);
        vb.getChildren().add(table);
    	vb.setMargin(table, new Insets(5,10,5,10));
    	
    	vb.setAlignment(Pos.CENTER);
    	
    	opr_border.setCenter(vb);
    }
    
    //=======================================LOGOUT
    @FXML
    void opr_LoggingOut(ActionEvent event) {
    	closeOperatorMenuWindow();
    	fxmlScreenLoader fcl=new fxmlScreenLoader();
    	fcl.loadScreen("../Interfaces/logInMenu.fxml");
    }

    @FXML
    private void closeOperatorMenuWindow() {
    	((Stage)opr_addReaderBtn.getScene().getWindow()).close();
    }
}
