package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import BusinessLogic.AccountsData;
import Interfaces.Main;
import Model.Accounts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class adminMenuController implements Initializable
{
	@FXML private BorderPane adm_border;
    
    @FXML private Button adm_showOperators;

    @FXML private Button adm_logOutBtn;
    
    @FXML private TableView<Accounts> operatorsTable;
    
    @FXML private TableColumn<Accounts, Integer> idColumn;
    @FXML private TableColumn<Accounts, String> firstNameColumn;
    @FXML private TableColumn<Accounts, String> lastNameColumn;
    @FXML private TableColumn<Accounts, String> ucnColumn;
    @FXML private TableColumn<Accounts, String> phoneNumberColumn;
    @FXML private TableColumn<Accounts, String> emailColumn;
    @FXML private TableColumn<Accounts, String> addressColumn;
    
    @FXML private ContextMenu contextMenuTable;
    
    @FXML private MenuItem menuItemAddOperator;

    @FXML private MenuItem menuItemRemoveOperator;
    
    private TextFieldLimited username = new TextFieldLimited(32); 
    private PasswordField password = new PasswordField();
    private TextFieldLimited firstName = new TextFieldLimited(32);
    private TextFieldLimited lastName = new TextFieldLimited(32);
    private TextFieldLimited ucn = new TextFieldLimited(16);
    private TextFieldLimited phoneNumber = new TextFieldLimited(16);
    private TextFieldLimited email = new TextFieldLimited(32);
    private TextFieldLimited address = new TextFieldLimited(512);
    private Button btnRegister = new Button();
    
    @FXML
    void addOperator(ActionEvent event) 
    {
        VBox vb=new VBox();
        vb.getChildren().add(username);
        vb.getChildren().add(password);
        vb.getChildren().add(firstName);
        vb.getChildren().add(lastName);
        vb.getChildren().add(ucn);
        vb.getChildren().add(phoneNumber);
        vb.getChildren().add(email);
        vb.getChildren().add(address);        
        vb.getChildren().add(btnRegister);
    	vb.setAlignment(Pos.CENTER);
    	vb.setSpacing(15);
    	adm_border.setCenter(vb);
    }
    
    void EmptyTextFields()
    {
    	username.setText(null);
    	password.setText(null);
    	firstName.setText(null);
    	lastName.setText(null);
    	ucn.setText(null);
    	phoneNumber.setText(null);
    	email.setText(null);
    	address.setText(null);
    }

    @FXML
    void removeOperator(ActionEvent event) 
    {
    	Accounts oAccount = operatorsTable.getSelectionModel().getSelectedItem();
    	if(oAccount == null)
    	{
    		return;
    	}
    	AccountsData oAccountData = new AccountsData();
    	try 
    	{
			oAccountData.DeleteWhereID(oAccount.getID());
		} catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	operatorsTable.getItems().remove(oAccount);
    }
    
    @FXML
    void adm_showOperators(ActionEvent event) {
    	adm_border.setCenter(null);
    	
    	adm_border.setCenter(operatorsTable);
    }
    
    @FXML
    void adm_LoggingOut(ActionEvent event) 
    {
    	Main.getInstance().setScene("../Interfaces/logInMenu.fxml");
    }
    
    private void closeAdminMenuWindow() 
    {
    	((Stage)adm_logOutBtn.getScene().getWindow()).close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		//setting placeholder text for text and password fields
		username.setPromptText("Username");
        password.setPromptText("Password");
        firstName.setPromptText("First Name");        
        lastName.setPromptText("Last Name");        
        ucn.setPromptText("UCN");        
        phoneNumber.setPromptText("Phone Number");        
        email.setPromptText("Email");       
        address.setPromptText("Address");
        btnRegister.setText("Register");
        btnRegister.setOnAction( e -> onRegister(e));
        
        setupTable();
        fillTable();             	
	}
	
	private void onRegister(ActionEvent e)
	{
		if(!validateData())
    	{
    		return;
    	}
		
    	Accounts oAccount = new Accounts();
    	oAccount.setUsername(username.getText());
    	oAccount.setPassword(password.getText());
    	oAccount.setFirstName(firstName.getText());
    	oAccount.setLastName(lastName.getText());
    	oAccount.setUCN(ucn.getText());
    	oAccount.setPhoneNumber(phoneNumber.getText());
    	oAccount.setEmail(email.getText());
    	oAccount.setAddress(address.getText());
    	oAccount.setRoleID(2);
    	oAccount.setRatingID(5);
    	
    	AccountsData oAccountData = new AccountsData();
    	try 
    	{
			oAccountData.Insert(oAccount);
			operatorsTable.getItems().add(oAccount);
		} catch (SQLException e1) 
    	{
			e1.printStackTrace();
		}
    	EmptyTextFields();
    	adm_border.setCenter(null);
    	adm_border.setCenter(operatorsTable);
	}
	
	private void setupTable()
	{
    	idColumn.setCellValueFactory(new PropertyValueFactory<>("ID")); 	
    	firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));    	
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));        
        ucnColumn.setCellValueFactory(new PropertyValueFactory<>("UCN"));        
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));      
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));        
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));              
	}
	
	private void fillTable()
	{
		List<Accounts> list = new ArrayList<Accounts>();
    	AccountsData oAccountData = new AccountsData();
    	try 
    	{
			list = oAccountData.SelectAllOperators();
		} catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	for(Accounts oAccount : list)
    	{
    		operatorsTable.getItems().add(oAccount);
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
	
	private boolean validateData()
	{
		boolean validData = true;
		StringBuilder infoMessage = new StringBuilder();
		
		if(username.getText().isEmpty() || password.getText().isEmpty() || firstName.getText().isEmpty() || lastName.getText().isEmpty() ||  ucn.getText().isEmpty() || phoneNumber.getText().isEmpty()
				|| email.getText().isEmpty() || address.getText().isEmpty())
		{
			infoMessage.append("Please fill all fields.\n");
			validData = false;
		}	
		
		validData = checkForSpecialCharacters(infoMessage);
		
		if(validData == false)
		{
			infoBox(infoMessage.toString(), null, "Register");
			return false;
		}
						
		return true;
	}
	
	private boolean checkForSpecialCharacters(StringBuilder infoMessage)
	{
		boolean validData = true;		
		DataValidator oDataValidator = new DataValidator();
		
		if(!oDataValidator.validateUserName(username.getText()))
		{
			infoMessage.append("Username field contains invalid characters.\n");
			validData = false;
		}
		
		if(!oDataValidator.validateName(firstName.getText()))
		{
			infoMessage.append("First Name field contains invalid characters.\n");
			validData = false;
		}
		
		if(!oDataValidator.validateName(lastName.getText()))
		{
			infoMessage.append("Last Name field contains invalid characters.\n");
			validData = false;
		}
		
		if(!oDataValidator.validateUCN(ucn.getText()))
		{
			infoMessage.append("UCN field contains invalid characters.\n");
			validData = false;
		}
		
		if(!oDataValidator.validatePhoneNumber(phoneNumber.getText()))
		{
			infoMessage.append("Phone Number field contains invalid characters.\n");
			validData = false;
		}
		
		if(!oDataValidator.validateEmail(email.getText()))
		{
			infoMessage.append("Email field contains invalid characters.\n");
			validData = false;
		}
		
		if(!oDataValidator.validateAddress(address.getText()))
		{
			infoMessage.append("Address field contains invalid characters.\n");
			validData = false;
		}
		
		if(validData == false)
		{
			return false;
		}
		
		return true;
	}
	

}
