package BusinessLogic;

import java.sql.SQLException;
import java.util.List;
import DAO.AccountsDAOImplementation;
import DAO.BooksDAOImplementation;
import Model.Accounts;
import Model.Books;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OperatorFunctions {
	
	private AccountsDAOImplementation adi=new AccountsDAOImplementation();
    private BooksDAOImplementation bdi=new BooksDAOImplementation();
	 
    private static boolean registerFlag;
    private Task<Void> notify;
    
	//=============ADD READER REQUEST
	public void registerRequest(Accounts acc) {
		registerFlag=true;
		
		//....
	}
	
	//=============NOTIFICATIONS
	public void notifyOperator(Label lbl) {
			
		notify=new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				while(true) {
					if(registerFlag==true) {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								int numb=Integer.parseInt(lbl.getText());
								numb++;
								lbl.setText(String.valueOf(numb));
							}
							
						});
						return null;
					}
					else {
						Thread.sleep(2000);
					}
				}
			}
				
		};
			
		notify.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent arg0) {
				registerFlag=false;
			}
				
		});
			
		Thread th=new Thread(notify);
		th.setDaemon(true);
		th.start();
	}
    
    //========FOR ACCOUNTS
    public void setAccountTableData(TableView<Accounts> table) {
    	try {
    		List<Accounts> la=adi.SelectAll();
    		
    		for (Accounts a : la) {
    			table.getItems().add(a);
    		}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}		
    }
    
    public TableView<Accounts> makeAccountsTable() {
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
        setAccountTableData(table);
        
        return table;
    }
    
    //=========FOR BOOKS 
    
    public void setBookTableData(TableView<Books> table) {
    	
    	try {
			List<Books> lb=bdi.SelectAll();
			
			for (Books b : lb) {
				table.getItems().add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public TableView<Books> makeBookTable() {    	
    	TableView<Books> table = new TableView<>();
    	table.setEditable(false);
    	
    	TableColumn<Books, Integer> idCol = new TableColumn<>("ID");
    	idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
    	idCol.setMaxWidth(30);
    	
        TableColumn<Books, String> titleCol = new TableColumn<>("Title");
    	titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        TableColumn<Books, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        TableColumn<Books, String> genreCol = new TableColumn<>("Genre");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        
        TableColumn<Books, Integer> conditionCol = new TableColumn<>("Condition");
        conditionCol.setCellValueFactory(new PropertyValueFactory<>("condition"));
        
        TableColumn<Books, Boolean> availableCol = new TableColumn<>("Available");
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));
        
        table.getColumns().addAll(idCol, titleCol, authorCol, genreCol, conditionCol, availableCol);
        setBookTableData(table);
        
    	return table;
    }
    
    //========GET SELECTED ACCOUNTS TABLE
    public void getSelectedRowAccounts(TableView<Accounts> tb, VBox vb) {
    	
    	tb.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Accounts acc=(Accounts) tb.getSelectionModel().getSelectedItem();
            		
           		for (Node n : vb.getChildren()) {
           			if(n instanceof TextField) {
						TextField tf=(TextField)n;
						tf.clear();
								
						if(tf.getPromptText().equals("First Name")) {
							tf.setText(acc.getFirstName());
						}
						if(tf.getPromptText().equals("Last Name")) {
							tf.setText(acc.getLastName());
						}
						if(tf.getPromptText().equals("UCN")) {
							tf.setText(acc.getUCN());
						}
						if(tf.getPromptText().equals("Phone Number")) {
							tf.setText(acc.getPhoneNumber());
						}
						if(tf.getPromptText().equals("Email")) {
							tf.setText(acc.getEmail());
						}
						if(tf.getPromptText().equals("Address")) {
							tf.setText(acc.getAddress());
						}
           			}
				}
			}
    		
    	});
    }
    
  //========GET SELECTED BOOK TABLE
    public void getSelectedRowBooks(TableView<Books> tb, VBox vb, ListView<String> li) {
    	
    	tb.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Books boo=(Books) tb.getSelectionModel().getSelectedItem();
           		
           		switch(boo.getCondition()) {
    	           	case 0:
    	           		li.scrollTo(0);
					    li.getSelectionModel().select(0);
    	           		break;
    	           			
    	           	case 1:
    	           		li.scrollTo(1);
					    li.getSelectionModel().select(1);
    	           		break;
    	           			
    	           	case 2:
    	           		li.scrollTo(2);
					    li.getSelectionModel().select(2);
    	           		break;
    	           			
    	           	case 3:
    	           		li.scrollTo(3);
					    li.getSelectionModel().select(3);
    	           		break;
    	           			
    	           	case 4:
    	           		li.scrollTo(4);
					    li.getSelectionModel().select(4);
    	           		break;
    	           			
    	           	case 5:
    	           		li.scrollTo(5);
					    li.getSelectionModel().select(5);
    	           		break;
    	           			
    	           	case 6:
    	           		li.scrollTo(6);
						li.getSelectionModel().select(6);
						break;
    	           			
    	           	case 7:
    	           		li.scrollTo(7);
					    li.getSelectionModel().select(7);
					    break;
    	           			
    	     		case 8:
    	       			li.scrollTo(8);
					    li.getSelectionModel().select(8);
    	       			break;
    	   		}
               
           		for (Node n : vb.getChildren()) {
           			if(n instanceof TextField) {
           				try {
							TextField tf=(TextField)n;
							if(tf.getPromptText().equals("Title")) {
								tf.setText(boo.getTitle());
							}
							if(tf.getPromptText().equals("Author")) {
								tf.setText(boo.getAuthor());
							}
							if(tf.getPromptText().equals("Genre")) {
								tf.setText(boo.getGenre());
							}
						}
						catch(Exception e) {
							break;
						}
           			}
					if(n instanceof HBox) {
						HBox hb=(HBox)n;
						boolean flag=boo.getAvailable();
						for (int i = 0; i < hb.getChildren().size(); i++) {											
							RadioButton rb=(RadioButton) hb.getChildren().get(i);
							
							if(rb.getText().equals("Available") && flag==true) {
								rb.setSelected(true);
							}
							if(rb.getText().equals("Unavailable") && flag==false) {
								rb.setSelected(true);
							}
						}
					}
				
				}
			}
    		
    	});
    }
    
    //=================ACCOUNT TO DB
    public void AccountToDB(Accounts acc, int choice) {
    	
    	switch(choice) {
		case 1: 
			try {
				adi.Insert(acc);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case 2:
			try {
				List<Accounts> li=adi.SelectAll();
				for (Accounts a : li) {
					if(acc.getUCN().equals(a.getUCN())) {
						adi.UpdateWhereID(a.getID(), acc);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
    }
    
    //=================BOOK TO DB
    public void BookToDB(Books boo, int choice) {
    	
    	switch(choice) {
		case 1: 
			try {
				bdi.Insert(boo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case 2:
			try {
				List<Books> li=bdi.SelectAll();
				for (Books b : li) {
					if(boo.getTitle().equals(b.getTitle())) {
						bdi.UpdateWhereID(b.getID(), boo);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		
    }
    
    //==============REMOVE ACCOUNT
    public void removeAccount(TableView<Accounts> table) {
    	Accounts acc=new Accounts();
    	acc=(Accounts) table.getSelectionModel().getSelectedItem();
    	
    	try {
    		List<Accounts> li=adi.SelectAll();
    		for (int i = 0; i < li.size(); i++) {
    			if(acc.getUCN().equals(li.get(i).getUCN())) {
    				adi.DeleteWhereID((int)li.get(i).getID());
    			}
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    //==============REMOVE BOOK
    public void removeBook(TableView<Books> table) {
    	Books boo=new Books();
		boo=(Books) table.getSelectionModel().getSelectedItem();
		
		try {
			List<Books> li=bdi.SelectAll();
			for (int i = 0; i < li.size(); i++) {
				if(boo.getTitle().equals(li.get(i).getTitle())) {
					bdi.DeleteWhereID(li.get(i).getID());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
}
