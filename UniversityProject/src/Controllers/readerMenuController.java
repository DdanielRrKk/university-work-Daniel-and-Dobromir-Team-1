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
import Interfaces.Main;
import Model.Accounts;
import Model.Books;
import Model.Issue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class readerMenuController implements Initializable {

	@FXML private BorderPane rd_border;

	@FXML private Button rd_books;
	
	@FXML private Button rd_Information;

	@FXML private Button rd_borrowBook;

	@FXML private Button rd_logOutBtn;
    
    @FXML private TableView<Books> booksTable;
    private TableView<Issue> issueTable;
    
    @FXML private TableColumn<Books, Integer> idColumn;
    @FXML private TableColumn<Books, String> titleColumn;
    @FXML private TableColumn<Books, String> authorColumn;
    @FXML private TableColumn<Books, String> genreColumn;
    @FXML private TableColumn<Books, Integer> conditionColumn;
    @FXML private TableColumn<Books, Boolean> availableColumn;
    
    @FXML private ContextMenu contextBooksMenuTable;
    private ContextMenu contextIssueMenuTable;

    @FXML private MenuItem menuItemBorrowBook;
    private MenuItem menuItemIssueBook;

    public readerMenuController()
    {
    	issueTable = new TableView<Issue>();
    	contextIssueMenuTable = new ContextMenu();
    	menuItemIssueBook = new MenuItem("Return book");
    	contextIssueMenuTable.getItems().add(menuItemIssueBook);
    	menuItemIssueBook.setOnAction(event -> returnBook(event));
    	issueTable.setContextMenu(contextIssueMenuTable);
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) 
    {    	
    	setupBooksTable();
    	fillBooksTable();    	
    	setupIssueTable();   
    	fillIssueTable(Main.getInstance().getID());   	
	}
    
    private void setupIssueTable()
    {
    	TableColumn<Issue, Integer> idColumn = new TableColumn<>("ID");
    	TableColumn<Issue, Integer> bookColumn = new TableColumn<>("BOOK_ID");
    	TableColumn<Issue, Integer> accountColumn = new TableColumn<>("ACCOUNT_ID");
    	TableColumn<Issue, Date> issueColumn = new TableColumn<>("ISSUE_DATE");
    	TableColumn<Issue, Date> returnColumn = new TableColumn<>("RETURN_DATE");
    	TableColumn<Issue, Date> returnedColumn = new TableColumn<>("RETURNED_DATE");
    	TableColumn<Issue, Integer> returnedConditionColumn = new TableColumn<>("RETURNED_CONDITION_ID");
    	TableColumn<Issue, Boolean> approvedColumn = new TableColumn<>("APPROVED");
    	
    	idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
    	bookColumn.setCellValueFactory(new PropertyValueFactory<>("BookID"));
    	accountColumn.setCellValueFactory(new PropertyValueFactory<>("AccountID"));
    	issueColumn.setCellValueFactory(new PropertyValueFactory<>("IssueDate"));
    	returnColumn.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
    	returnedColumn.setCellValueFactory(new PropertyValueFactory<>("ReturnedDate"));
    	returnedConditionColumn.setCellValueFactory(new PropertyValueFactory<>("ReturnedCondition"));
    	approvedColumn.setCellValueFactory(new PropertyValueFactory<>("Approved"));
    	
    	issueTable.getColumns().addAll(idColumn, bookColumn, accountColumn, issueColumn, returnColumn, returnedColumn, returnedConditionColumn, approvedColumn);
    }
    
    private void fillIssueTable(int ID)
    {
    	List<Issue> list = new ArrayList<Issue>();
    	IssueData oIssueData = new IssueData();
    	try 
    	{
			list = oIssueData.SelectAllWhereAccountID(ID);
		} catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	for(Issue oIssue : list)
    	{
    		issueTable.getItems().add(oIssue);
    	}
    }
    
    private void setupBooksTable()
    {
    	idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
    	titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    	authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
    	genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
    	conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
    	availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
    }
    
    private void fillBooksTable()
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
    	if(oBook == null)
    	{
    		return;
    	}
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
    
    void returnBook(ActionEvent event)
    {
    	Issue oIssue = issueTable.getSelectionModel().getSelectedItem();
    	if(oIssue == null)
    	{
    		return;
    	}
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Return the book?", ButtonType.YES, ButtonType.NO);
    	alert.setHeaderText(null);
    	alert.showAndWait();  	
    	
    	if (alert.getResult() == ButtonType.YES) 
    	{
    		java.sql.Date currentDate = new Date(Calendar.getInstance().getTime().getTime()); 
    		oIssue.setReturnedDate(currentDate);
    		IssueData oIssueData = new IssueData();
    		
    		try 
    		{
				oIssueData.UpdateWhereID(oIssue.getID(), oIssue);
			} catch (SQLException e) 
    		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		infoBox("Book returned", null, "Issues");
    	}
    }
    
    @FXML
    void onInformation(ActionEvent event) 
    {
    	infoBox("All book borrowing is for 30 days.\nIf you want more than that you have to return it and re-borrow it. Make sure you return your book on time!", null, "Information");
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
    void onBorrowedBooks(ActionEvent event) 
    {
    	rd_border.setCenter(null);
    	rd_border.setCenter(issueTable);
    }
    
    @FXML
    void onBooks(ActionEvent event) 
    {
    	rd_border.setCenter(null);
    	rd_border.setCenter(booksTable);
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
