package Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import BusinessLogic.AccountsData;
import BusinessLogic.OperatorFunctions;
import Interfaces.Main;
import Model.Accounts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Pair;

public class registerMenuController {

    @FXML
    private TextField reg_fNameText;

    @FXML
    private TextField reg_lNameText;

    @FXML
    private TextField reg_phoneText;
    
    @FXML
    private TextField reg_ucn;

    @FXML
    private TextField reg_addressText;

    @FXML
    private Button reg_registerBtn;

    @FXML
    private TextField reg_emailText;
    
    @FXML
    private Button reg_backBtn;
    
    @FXML
    private TextField reg_user;

    @FXML
    private PasswordField reg_pass;
    
    OperatorFunctions of=new OperatorFunctions();
    AccountsData ad=new AccountsData();

    @FXML
    void reg_registering(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	Pair<String,Boolean> p=new Pair<>("",false);
    	
    	boolean errFlag=false;
    	
		if(this.checkEmptyBoxes()==true) {
			alert.setContentText("One or more boxes are empty.");
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setGraphic(null);
			alert.showAndWait();
			errFlag=true;
		}
		else {
			
			if(checkEmail()==true) {
				alert.setContentText("Invalid Email");
	    		alert.setTitle("Warning");
	    		alert.setHeaderText(null);
	    		alert.setGraphic(null);
	    		alert.showAndWait();
	    		errFlag=true;
			}
			
			p=this.checkSymbols();
	    	if(p.getValue()==true) {
	    		alert.setContentText(p.getKey()+" shouldnt contain symbols");
	    		alert.setTitle("Warning");
	    		alert.setHeaderText(null);
	    		alert.setGraphic(null);
	    		alert.showAndWait();
	    		errFlag=true;
	    	}
	    	
	    	p=this.checkLetters();
	    	if(p.getValue()==true) {
	    		alert.setContentText(p.getKey()+" shouldnt contain letters");
	    		alert.setTitle("Warning");
	    		alert.setHeaderText(null);
	    		alert.setGraphic(null);
	    		alert.showAndWait();
	    		errFlag=true;
	    	}

	    	p=this.checkExisting();
	    	if(p.getValue()==true) {
	    		alert.setContentText(p.getKey()+" is already taken");
	    		alert.setTitle("Warning");
	    		alert.setHeaderText(null);
	    		alert.setGraphic(null);
	    		alert.showAndWait();
	    		errFlag=true;
	    	}
	    	
	    	p=this.checkSize();
	    	if(p.getValue()==true) {
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
	    		errFlag=true;
	    	}
			
		}		
    	
    	if(errFlag==false) {
    		Accounts acc=new Accounts();
    		acc.setUsername(reg_user.getText());
        	acc.setPassword(reg_pass.getText());
        	acc.setFirstName(reg_fNameText.getText());
        	acc.setLastName(reg_lNameText.getText());
        	acc.setPhoneNumber(reg_phoneText.getText());
        	acc.setEmail(reg_emailText.getText());
        	acc.setUCN(reg_ucn.getText());
        	acc.setAddress(reg_addressText.getText());
        	acc.setRoleID(3);
        	acc.setRatingID(1);
        	acc.setApproved(false);
        	
        	of.registerRequest(acc);
        	
        	alert.setContentText("Registration was successful");
    		alert.setTitle("Success");
    		alert.setHeaderText(null);
    		alert.setGraphic(null);
    		alert.showAndWait();
    	}
    	else {
    		alert.setContentText("Registration failed");
    		alert.setTitle("Warning");
    		alert.setHeaderText(null);
    		alert.setGraphic(null);
    		alert.showAndWait();
    	}
    }
    
    private boolean checkEmptyBoxes() {
    	boolean flag=false;
    	
    	if(reg_user.getText().isEmpty()) { flag= true; }
    	if(reg_pass.getText().isEmpty()) { flag= true; }
    	if(reg_fNameText.getText().isEmpty()) { flag= true; }
    	if(reg_lNameText.getText().isEmpty()) { flag= true; }
    	if(reg_phoneText.getText().isEmpty()) { flag= true; }
    	if(reg_emailText.getText().isEmpty()) { flag= true; }
    	if(reg_ucn.getText().isEmpty()) { flag= true; }
    	if(reg_addressText.getText().isEmpty()) { flag= true; }
    	
    	return flag;
    }
    
    private boolean checkEmail() {
    	boolean flag=false;
    	
    	Pattern validMail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    	Matcher m = validMail.matcher(reg_emailText.getText());
    	flag=!m.find();
    	
    	return flag;
    }
    
    private Pair<String,Boolean> checkSymbols() {
    	Pair<String,Boolean> p = new Pair<>("",false);
    	
    	if(reg_fNameText.getText().matches("^[a-zA-Z]*$") == false) { p=new Pair<>("First Name",true); }
    	if(reg_lNameText.getText().matches("^[a-zA-Z]*$") == false) { p=new Pair<>("Last Name",true); }
    	if(reg_addressText.getText().matches("^[a-zA-Z0-9.,-]*$") == false) { p=new Pair<>("Address",true); }
    	
    	return p;
    }
    
    private Pair<String,Boolean> checkLetters() {
    	Pair<String,Boolean> p = new Pair<>("",false);
    	
    	if(reg_phoneText.getText().matches("^[a-zA-Z]*$") == true) { p=new Pair<>("Phone Number",true); }
    	if(reg_ucn.getText().matches("^[a-zA-Z]*$") == true) { p=new Pair<>("UCN",true); }
    	
    	return p;
    }
    
    private Pair<String,Boolean> checkExisting() {
    	Pair<String,Boolean> p = new Pair<>("",false);
    	
    	List<Accounts> li=new ArrayList<Accounts>();
		try {
			li = ad.SelectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	for (Accounts a : li) {
			if(a.getUsername().equals(reg_user.getText())) { p=new Pair<>("Username",true); }
			if(a.getPhoneNumber().equals(reg_phoneText.getText())) { p=new Pair<>("Phone Number",true); }
			if(a.getEmail().equals(reg_emailText.getText())) { p=new Pair<>("Email",true); }
			if(a.getUCN().equals(reg_ucn.getText())) { p=new Pair<>("UCN",true); }
		}
    	
    	return p;
    }
    
    private Pair<String,Boolean> checkSize() {
    	Pair<String,Boolean> p = new Pair<>("",false);
    	
    	if(reg_user.getText().length()<10) { p=new Pair<>("Username",true); }
    	if(reg_pass.getText().length()<10) { p=new Pair<>("Password",true); }
    	if(reg_phoneText.getText().length()<10 || reg_phoneText.getText().length()>10) { p=new Pair<>("Phone Number",true); }
    	if(reg_ucn.getText().length()<10 || reg_ucn.getText().length()>10) { p=new Pair<>("UCN",true); }
    	
    	return p;
    }
    
    @FXML
    void reg_backToStartManu(ActionEvent event) {
    	Main.getInstance().setScene("../Interfaces/startMenu.fxml");
    }
}
