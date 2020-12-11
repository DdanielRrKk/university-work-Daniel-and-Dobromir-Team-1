package BusinessLogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import BusinessLogic.OperatorFunctions.HBoxCell;
import DAO.AccountsDAOImplementation;
import DAO.BooksDAOImplementation;
import DAO.IssueDAOImplementation;
import Model.Accounts;
import Model.Books;
import Model.Issue;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class OperatorFunctions {
	
	private static AccountsDAOImplementation adi=new AccountsDAOImplementation();
    private static BooksDAOImplementation bdi=new BooksDAOImplementation();
    private static IssueDAOImplementation idi=new IssueDAOImplementation();
	 
    private static boolean registerFlag;
    private static boolean issueFlag;
    private static boolean returnedFlag;
    
    private static boolean stopFlag=false;
    
    private static ObservableList<HBoxCell> ol;
    private static ListView<HBoxCell> lv;
    
    public void setNotifyList(ListView<HBoxCell> listView) {
    	lv=listView;
    }
    
  //=============THREAD
    public void startNotifyThread() {
    	
    	stopFlag=false;
    	
    	Task<Void> notify=new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				while(true) {
					if(stopFlag==true) {
						return null;
					}
					if(registerFlag==true) {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								TrayNotification tray = new TrayNotification("New Notification", "A new Reader is waiting to be approved", NotificationType.INFORMATION);
						        tray.setAnimationType(AnimationType.POPUP);
						        tray.showAndWait();
							}
								
						});
						
						registerFlag=false;
					}
					else if(issueFlag==true) {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								TrayNotification tray = new TrayNotification("New Notification", "A Reader wants to borrow a book", NotificationType.INFORMATION);
						        tray.setAnimationType(AnimationType.POPUP);
						        tray.showAndWait();
							}
								
						});
						
						issueFlag=false;
					}
					else if(returnedFlag==true) {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								TrayNotification tray = new TrayNotification("New Notification", "A Reader just returned a book", NotificationType.INFORMATION);
						        tray.setAnimationType(AnimationType.POPUP);
						        tray.showAndWait();
							}
								
						});
						
						returnedFlag=false;
					}
					else {
						Thread.sleep(2000);
					}
					
				}
			}
				
		};
			
		Thread th=new Thread(notify);
		th.setDaemon(true);
		th.start();
    }
    
    //=============STOP THREAD
    public void stopThread() {
    	stopFlag=true;
    }
    
	//=============ADD READER REQUEST
	public void registerRequest(Accounts acc) {
		registerFlag=true;
		
		try {
			adi.Insert(acc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//=============ADD ISSUE REQUEST
	public void issueRequest() {
		issueFlag=true;
	}
		
	//=============RETURNED BOOK
	public void returnedBook() {
		returnedFlag=true;
	}
	
	//=============CLASS FOR THE LIST DATA
	public static class HBoxCell extends HBox {
        Label lbl = new Label();
        Button btnApr = new Button();
        Button btnRej = new Button();

        HBoxCell(String type, int id) {
             super();
             
             if(type.equals("Issue")) {
            	 try {
             		List<Issue> li=idi.SelectAll();
     	    			    		
      	    		for(Issue i : li) {
      	    			if(i.getID()==id) {
      	    				Accounts acc=adi.SelectWhereID(i.getAccountID());
      	    				Books boo=bdi.SelectWhereID(i.getBookID());
      	    				
      	    				this.lbl.setText("Reader "+acc.getUsername()+" want to borrow '"+boo.getTitle()+"' book");
      	    			}
      	    		}                	 
                 } catch (SQLException e) {
                	 e.printStackTrace();
                 }
            	 
            	btnApr.setText("Approve");
   	            btnRej.setText("Reject");
   	    		
   	    		btnApr.setOnAction(makeApproveIssueAction(id));
   	    		btnRej.setOnAction(makeRejectIssueAction(id));
   	    		
   	    		this.lbl.setMaxWidth(Double.MAX_VALUE);
   	            HBox.setHgrow(this.lbl, Priority.ALWAYS);

   	            this.getChildren().addAll(this.lbl, btnApr, btnRej);
            	 
             }
             else if(type.equals("Return")) {
            	 try {
              		List<Issue> li=idi.SelectAll();
      	    		
       	    		for(Issue i : li) {
       	    			if(i.getID()==id) {
       	    				Accounts acc=adi.SelectWhereID(i.getAccountID());
       	    				Books boo=bdi.SelectWhereID(i.getBookID());
       	    				
       	    				this.lbl.setText("Reader "+acc.getUsername()+" want to return '"+boo.getTitle()+"' book");
       	    			}
       	    		}
       	    		
       	    		btnApr.setOnAction(makeApproveIssueAction(id));
                 	 
                 } catch (SQLException e) {
                 	 e.printStackTrace();
                 }
            	 
            	 btnApr.setText("Evaluate Book");
            	
            	 btnApr.setOnAction(makeEvaluateAction(id));
            	 
            	 this.lbl.setMaxWidth(Double.MAX_VALUE);
                 HBox.setHgrow(this.lbl, Priority.ALWAYS);

                 this.getChildren().addAll(this.lbl, btnApr);
            	 
              }
              else {
            	 try {
            		List<Accounts> la=adi.SelectAll();
            	 
            	 	for (Accounts a : la) {
            	 		if(a.getID()==id) {
      	    				this.lbl.setText("Reader "+a.getUsername()+" is waiting to be approved");
  	    				}
  	    			}
            	 } catch (SQLException e) {
                 	 e.printStackTrace();
                 }
            	 
            	 btnApr.setText("Approve");
   	           	 btnRej.setText("Reject");
            	
            	 btnApr.setOnAction(makeApproveAccountsAction(id));
            	 btnRej.setOnAction(makeRejectAccountsAction(id));
            	 
            	 this.lbl.setMaxWidth(Double.MAX_VALUE);
                 HBox.setHgrow(this.lbl, Priority.ALWAYS);

                 this.getChildren().addAll(this.lbl, btnApr, btnRej);
             }
             
        }
	}
	
	
	//====ACCOUNTS EVENTS
	private static EventHandler<ActionEvent> makeApproveAccountsAction(int id) {
		EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) { 
            	try {
     	    		List<Accounts> la=adi.SelectAll();
     	    		
     	    		for (Accounts a : la) {
     	    			if(a.getID()==id) {
     	    				a.setApproved(true);
     	    				
     	    				Accounts acc=a;
     	    				
         	    			adi.UpdateWhereID(acc.getID(), acc);
         	    			
         	    			setNotifyListData();
         	    			break;
     	    			}
     	    		}
     	    	}
     			catch(SQLException ex) {
     				ex.printStackTrace();
     			}
            } 
        };
        
        return ev;
	}
	
	private static EventHandler<ActionEvent> makeRejectAccountsAction(int id) {
		EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) { 
            	try {
     	    		List<Accounts> la=adi.SelectAll();
     	    		
     	    		for (Accounts a : la) {
     	    			if(a.getID()==id) {
     	    				a.setApproved(false);
     	    				
     	    				Accounts acc=a;
         	    			
         	    			adi.DeleteWhereID(acc.getID());
         	    			
         	    			setNotifyListData();
         	    			break;
     	    			}
     	    		}
     	    	}
     			catch(SQLException ex) {
     				ex.printStackTrace();
     			}
            } 
        };
        
        return ev;
	}
	
	//=========ISSUES EVENTS
	private static EventHandler<ActionEvent> makeApproveIssueAction(int id) {
		EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) { 
            	try {
            		List<Issue> li=idi.SelectAll();
     	    		   	    		
     	    		for(Issue i : li) {
     	    			if(i.getID()==id) {
     	    				i.setApproved(true);
     	    				Issue iss=i;
         	    			idi.UpdateWhereID(iss.getID(), iss);
         	    			
         	    			Books boo=bdi.SelectWhereID(i.getBookID());
         	    			boo.setAvailable(false);
         	    			bdi.UpdateWhereID(i.getBookID(), boo);
         	    			
         	    			setNotifyListData();
         	    			break;
     	    			}
     	    		}
     	    		
     	    	}
     			catch(SQLException ex) {
     				ex.printStackTrace();
     			}
            } 
        };
        
        return ev;
	}
	
	private static EventHandler<ActionEvent> makeRejectIssueAction(int id) {
		EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) { 
            	try {
     	    		List<Issue> li=idi.SelectAll();
     	    		
     	    		for(Issue i : li) {
     	    			if(i.getID()==id) {
     	    				i.setApproved(true);
     	    				
     	    				Issue iss=i;
         	    			
         	    			idi.DeleteWhereID(iss.getID());
         	    			
         	    			setNotifyListData();
         	    			break;
     	    			}
     	    		}
     	    	}
     			catch(SQLException ex) {
     				ex.printStackTrace();
     			}
            } 
        };
        
        return ev;
	}
	
	//=========EVALUATE BOOK
		private static EventHandler<ActionEvent> makeEvaluateAction(int id) {
			EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() { 
	            public void handle(ActionEvent e) { 
	            	try {
	            		List<Issue> li=idi.SelectAll();
	     	    		
	     	    		for(Issue i : li) {
	     	    			Issue iss=i;
	     	    			
	     	    			if(iss.getID()==id) {
	     	    				List<String> choices = new ArrayList<>();
	     	    				choices.add("As new");
	     	    				choices.add("Fine");
	     	    				choices.add("Very good");
	     	    				choices.add("Good");
	     	    				choices.add("Fair");
	     	    				choices.add("Worn");
	     	    				choices.add("Poor");
	     	    				choices.add("Very poor");
	     	    				choices.add("For scrapping");

	     	    				ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
	     	    				dialog.setTitle("Evaluate book condition");
	     	    				dialog.setContentText("Choose condition:");
	     	    				
	     	    				Optional<String> result = dialog.showAndWait();
	     	    				if (result.isPresent()){
	     	    					
	     	    				    for(String s : choices) {
	     	    				    	if(s.equals(result.get())) {
	     	    				    		iss.setReturnedCondition(choices.indexOf(s)+1);
	     	    				    		
	     	    				    		Books boo=bdi.SelectWhereID(iss.getBookID());
	     	    				    		boo.setAvailable(true);
	     	    				    		bdi.UpdateWhereID(boo.getID(), boo);
	     	    				    		
	     	    				    		if((choices.indexOf(s)!=boo.getCondition()) && (iss.getReturnedDate().after(iss.getReturnDate()))) {
	     	    				    			Accounts acc=adi.SelectWhereID(iss.getAccountID());
	     	    								
	     	    								if(acc.getRatingID()>=2) {
	     	    									acc.setRatingID(acc.getRatingID()-1);
	     	    								}
	     	    								
	     	    								adi.UpdateWhereID(iss.getAccountID(), acc);
	     	    				    		}
	     	    				    		else if((choices.indexOf(s)!=boo.getCondition()) || (iss.getReturnedDate().after(iss.getReturnDate()))){
	     	    				    			
	     	    				    		}
	     	    				    		else {
	     	    				    			Accounts acc=adi.SelectWhereID(iss.getAccountID());
	     	    								
	     	    								if(acc.getRatingID()<=4) {
	     	    									acc.setRatingID(acc.getRatingID()+1);
	     	    								}
	     	    								
	     	    								adi.UpdateWhereID(iss.getAccountID(), acc);
	     	    				    		}
	     	    				    			     	    				    		
	     	    				    		break;
	     	    				    	}
	     	    				    }
	     	    				   
	     	    				   idi.UpdateWhereID(iss.getID(), iss);
	     	    				   break;
	     	    				}
	     	    				
	     	    			}
	     	    		}
	     	    		
	     	    		
	     	    		setNotifyListData();
	     	    	}
	     			catch(SQLException ex) {
	     				ex.printStackTrace();
	     			}
	            } 
	        };
	        
	        return ev;
		}
	
	//=========MAKE NOTIFICATIONS IN LIST
	public ListView<HBoxCell> makeNotifications() {
		try {
			List<HBoxCell> list = new ArrayList<>();
			List<Accounts> la=adi.SelectAll();
			List<Issue> li=idi.SelectAll();
			
			for (Accounts a : la) {
    			if(a.isApproved() == false) {
    				list.add(new HBoxCell("Reader", a.getID()));
    			}
    		}
			
			for (Issue i : li) {
    			if(i.isApproved() == false) {
    				list.add(new HBoxCell("Issue", i.getID()));
    			}
    			
    			if(i.getReturnedCondition()==10 && i.getReturnedDate() != null) {
    				list.add(new HBoxCell("Return", i.getID()));
    			}
    		}
	        
	        ol = FXCollections.observableList(list);
	        
	        lv = new ListView<HBoxCell>();
	        lv.setItems(ol);
	        
	        return lv;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void setNotifyListData() {
		try {
			List<HBoxCell> list = new ArrayList<>();
			List<Accounts> la=adi.SelectAll();
			List<Issue> li=idi.SelectAll();
			
			for (Accounts a : la) {
				if(a.isApproved() == false) {
					list.add(new HBoxCell("Reader", a.getID()));
				}
			}
			
			for (Issue i : li) {
				if(i.isApproved() == false) {
					list.add(new HBoxCell("Issue", i.getID()));
				}
				
				if(i.getReturnedCondition()==10 && i.getReturnedDate() != null) {
					list.add(new HBoxCell("Return", i.getID()));
				}
			}
			
			ol = FXCollections.observableList(list);
	        
	        lv.getItems().clear();
	        lv.setItems(ol);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
    
    //========FOR ACCOUNTS
		
    public void setAccountTableData(TableView<Accounts> table) {
    	try {
    		List<Accounts> la=adi.SelectAll();
    		
    		for (Accounts a : la) {
    			if(a.getRoleID()==3) {
    				table.getItems().add(a);
    			}
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
    public void getSelectedRowAccounts(TableView<Accounts> tb, VBox vb, ListView<String> li) {
    	
    	tb.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Accounts acc=(Accounts) tb.getSelectionModel().getSelectedItem();
            	
				li.scrollTo(acc.getRatingID()-1);
			    li.getSelectionModel().select(acc.getRatingID()-1);
				
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
           		
				li.scrollTo(boo.getCondition());
			    li.getSelectionModel().select(boo.getCondition());
               
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
						boolean flag=boo.isAvailable();
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
				adi.UpdateWhereID(acc.getID(), acc);
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
				bdi.UpdateWhereID(boo.getID(), boo);
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
    		List<Issue> li=idi.SelectAll();
    		boolean flag=false;
    		
    		for(Issue i : li) {
    			Accounts a=adi.SelectWhereID(i.getAccountID());
				if(a.getUsername().equals(acc.getUsername())) {
					flag=true;
					break;
				}
			}
    		
    		if(flag==false) {
    			List<Accounts> la=adi.SelectAll();
    			
    			for(Accounts a : la) {
    				if(a.getUsername().equals(acc.getUsername())) {
    					adi.DeleteWhereID(a.getID());
    					break;
    				}
    			}
    		}
    		else {
    			Alert alert = new Alert(AlertType.INFORMATION, "This reader still has books.", ButtonType.OK);
        		alert.setTitle("Warning");
        		alert.setHeaderText(null);
        		alert.setGraphic(null);
        		alert.showAndWait();
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    //==============REMOVE BOOK
    public void removeBook(TableView<Books> table) {
    	Books boo=new Books();
		boo=(Books) table.getSelectionModel().getSelectedItem();
		
		if(boo.isAvailable()==true) {
			try {
				List<Books> lb=bdi.SelectAll();
				
				for(Books b : lb) {
					if(b.getTitle().equals(boo.getTitle())) {
						bdi.DeleteWhereID(b.getID());
						break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION, "This book is not available.", ButtonType.OK);
    		alert.setTitle("Warning");
    		alert.setHeaderText(null);
    		alert.setGraphic(null);
    		alert.showAndWait();
		}
    }
    
    public void removeThisBook(Books boo) {
		try {
			List<Books> lb=bdi.SelectAll();
			
			for(Books b : lb) {
				if(b.getTitle().equals(boo.getTitle())) {
					bdi.DeleteWhereID(b.getID());
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
}