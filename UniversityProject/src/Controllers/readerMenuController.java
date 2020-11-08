package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import BusinessLogic.AccountsData;
import BusinessLogic.BooksData;
import BusinessLogic.IssueData;
import BusinessLogic.OperatorFunctions;
import Interfaces.Main;
import Model.Accounts;
import Model.Books;
import Model.Issue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class readerMenuController implements Initializable {

	@FXML private BorderPane rd_border;

	@FXML private Button rd_books;

	@FXML private Button rd_borrowBook;

	@FXML private Button rd_logOutBtn;
    
    @FXML private TableView<Books> booksTable;
    
    @FXML private TableColumn<Books, Integer> idColumn;
    @FXML private TableColumn<Books, String> titleColumn;
    @FXML private TableColumn<Books, String> authorColumn;
    @FXML private TableColumn<Books, String> genreColumn;
    @FXML private TableColumn<Books, Integer> conditionColumn;
    @FXML private TableColumn<Books, Boolean> availableColumn;
    
    @FXML private ContextMenu contextMenuTable;

    @FXML private MenuItem menuItemBorrowBook;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) 
    {
    	setupTable();
    	fillTable();
	}
    
    private void setupTable()
    {
    	idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
    	titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    	authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
    	genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
    	conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
    	availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
    }
    
    private void fillTable()
    {
    	List<Books> list = new ArrayList<Books>();
    	BooksData oBookData = new BooksData();
    	try 
    	{
			list = oBookData.SelectAll();
		} catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	for(Books oBook : list)
    	{
    		booksTable.getItems().add(oBook);
    	}
    }
    
    @FXML
    void borrowBook(ActionEvent event) 
    {
    	IssueData oIssueData = new IssueData();
    	Issue oIssue = new Issue();
    	Books oBook = booksTable.getSelectionModel().getSelectedItem();
    	if(oBook.isAvailable() == false)
    	{
    		infoBox("This book is not currently available.", null, "Books");
    		return;
    	}
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Borrow the book: " + oBook.getTitle() + " ?", ButtonType.YES, ButtonType.NO);
    	alert.setHeaderText(null);
    	alert.showAndWait();

    	if (alert.getResult() == ButtonType.YES) 
    	{
    		java.sql.Date currentDate = new Date(Calendar.getInstance().getTime().getTime()); 
    		Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.DATE, 30);
    		java.sql.Date returnDate = new java.sql.Date(c.getTimeInMillis());
    		oIssue.setBookID(oBook.getID());
    		oIssue.setAccountID(3);
    		oIssue.setIssueDate(currentDate);
    		oIssue.setReturnDate(returnDate);
    		oIssue.setReturnedCondition(3);
    		oIssue.setApproved(false);       		
    	    try 
    	    {
				oIssueData.Insert(oIssue);
			} catch (SQLException e) 
    	    {

				e.printStackTrace();
			}
    	}
    }
    
    private void infoBox(String infoMessage, String headerText, String title) 
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    

    @FXML
    void rd_LoggingOut(ActionEvent event) 
    {
    	closeReaderMenuWindow();
    	fxmlScreenLoader fcl=new fxmlScreenLoader();
    	fcl.loadScreen("../Interfaces/logInMenu.fxml");
    }
    
    private void closeReaderMenuWindow() 
    {
    	((Stage)rd_logOutBtn.getScene().getWindow()).close();
	}
  
}
