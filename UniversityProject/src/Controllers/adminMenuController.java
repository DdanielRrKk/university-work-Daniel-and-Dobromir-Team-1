package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import BusinessLogic.AccountsData;
import Interfaces.Main;
import Model.Accounts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


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
    	username.setText("");
    	password.setText("");
    	firstName.setText("");
    	lastName.setText("");
    	ucn.setText("");
    	phoneNumber.setText("");
    	email.setText("");
    	address.setText("");
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
    	Main.getInstance().setScene("/Interfaces/logInMenu.fxml");
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
    	oAccount.setApproved(true);
    	
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
		validData = checkMinSize(infoMessage);
		
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
	
	private boolean checkMinSize(StringBuilder infoMessage)
	{
		boolean validData = true;		
		DataValidator oDataValidator = new DataValidator();
		
		if(!oDataValidator.validateMinSize(username.getText(), 3))
		{
			infoMessage.append("Username field must be at least 3 characters long.\n");
			validData = false;
		}
		
		if(!oDataValidator.validateMinSize(firstName.getText(), 1))
		{
			infoMessage.append("First Name field must be at least 1 characters long.\n");
			validData = false;
		}
		
		if(!oDataValidator.validateMinSize(lastName.getText(), 1))
		{
			infoMessage.append("Last Name field must be at least 1 characters long.\n");
			validData = false;
		}
		
		if(!oDataValidator.validateMinSize(ucn.getText(), 10))
		{
			infoMessage.append("UCN field must be at least 10 characters long.\n");
			validData = false;
		}
		
		if(!oDataValidator.validateMinSize(phoneNumber.getText(), 10))
		{
			infoMessage.append("Phone Number field must be at least 10 characters long.\n");
			validData = false;
		}
		
		if(!oDataValidator.validateMinSize(email.getText(), 5))
		{
			infoMessage.append("Email field must be at least 5 characters long.\n");
			validData = false;
		}
		
		if(!oDataValidator.validateMinSize(address.getText(), 20))
		{
			infoMessage.append("Address field must be at least 20 characters long.\n");
			validData = false;
		}
		
		if(validData == false)
		{
			return false;
		}
		
		return true;
	}
	
}
