package BusinessLogic;

import java.sql.SQLException;
import java.util.List;

import DAO.AccountsDAOImplementation;
import DAO.BooksDAOImplementation;
import Model.Accounts;
import Model.Books;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class OperatorFunctions {
	
	//private AccountsDAOImplementation adi=new AccountsDAOImplementation();
    //private BooksDAOImplementation bdi=new BooksDAOImplementation();
	
	//=============NOTIFICATIONS
	public void notifyOperator() {
		
	}
	
	//=============ADD READER REQUEST
	public void registerRequest(Accounts acc) {
		
	}
    
    //========FOR ACCOUNTS
    public void setAccountTableData(TableView<Accounts> table) {
    	//=============FOR TESTING===========
    	Accounts as=new Accounts();
    	as.setID(1);
		as.setFirstName("Daniel");
		as.setLastName("Kostov");
		as.setUCN("9923");
		as.setPhoneNumber("0877");
		as.setEmail("daniel@abv.bg");
		as.setAddress("varna");
		
		table.getItems().add(as);
		//=============FOR TESTING===========
    	
//    	try {
//			List<Accounts> la=ad.SelectAll();
//			
//			for (Accounts a : la) {
//				Accounts as=new Accounts();
//				as.setID((int)a.getID());
//				as.setFirstName(a.getFirstName());
//				as.setLastName(a.getLastName());
//				as.setUCN(a.getUCN());
//				as.setPhoneNumber(a.getPhoneNumber());
//				as.setEmail(a.getEmail());
//				as.setAddress(a.getAddress());
//				
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
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
    	//=============FOR TESTING===========
    	Books b=new Books();
    	b.setID(1);
		b.setTitle("Game of Thrones");
		b.setAuthor("Robin");
		b.setGenre("Comedy");
		b.setCondition(2);
		b.setbAvailable(true);
		
		table.getItems().add(b);
		//=============FOR TESTING===========
		
//    	try {
//			List<Accounts> la=ad.SelectAll();
//			
//			for (Accounts a : la) {
//				Accounts as=new Accounts();
//				as.setID((int)a.getID());
//				as.setFirstName(a.getFirstName());
//				as.setLastName(a.getLastName());
//				as.setUCN(a.getUCN());
//				as.setPhoneNumber(a.getPhoneNumber());
//				as.setEmail(a.getEmail());
//				as.setAddress(a.getAddress());
//				
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
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
    	
    	Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
            	while(true) {
            		try {
    	            	if(tb.getSelectionModel().getSelectedItem() != null) {
    	            		Accounts acc=new Accounts();
    	            		acc=(Accounts) tb.getSelectionModel().getSelectedItem();
    	            		
    	            		for (Node n : vb.getChildren()) {
    	            			if(n instanceof TextField) {
    	            				try {
    									TextField tf=(TextField)n;
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
    								catch(Exception e) {
    									break;
    								}
    	            			}
								
							}
    	            		
    	            		break;
    	            	}
    	            	else {
    	            		Thread.sleep(2000);
    	            	}
    	            } catch (InterruptedException ex) {
    	                ex.printStackTrace();
    	            }
            	}				
                return null;
            }
        };
        
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
            	// what to do if cycle is succeeded
//            	Alert alert = new Alert(AlertType.INFORMATION, "alert");
//				alert.setTitle("success");
//				alert.setHeaderText(null);
//				alert.setGraphic(null);
//				alert.showAndWait();
            };
        });
            
        new Thread(sleeper).start();
    }
    
  //========GET SELECTED BOOK TABLE
    public void getSelectedRowBooks(TableView<Books> tb, VBox vb) {
    	
    	Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
            	while(true) {
            		try {
    	            	if(tb.getSelectionModel().getSelectedItem() != null) {
    	            		Books boo=new Books();
    	            		boo=(Books) tb.getSelectionModel().getSelectedItem();
    	            		
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
								if(n instanceof ListView) {
									try {
										ListView<String> li=(ListView<String>)n;
										if(boo.getCondition()==0) {
											li.scrollTo(0);
									        li.getSelectionModel().select(0);
										}
										if(boo.getCondition()==1) {
											li.scrollTo(1);
									        li.getSelectionModel().select(1);
										}
										if(boo.getCondition()==2) {
											li.scrollTo(2);
									        li.getSelectionModel().select(2);
										}
										if(boo.getCondition()==3) {
											li.scrollTo(3);
									        li.getSelectionModel().select(3);
										}
										if(boo.getCondition()==4) {
											li.scrollTo(4);
									        li.getSelectionModel().select(4);
										}
										if(boo.getCondition()==5) {
											li.scrollTo(5);
									        li.getSelectionModel().select(5);
										}
										if(boo.getCondition()==6) {
											li.scrollTo(6);
									        li.getSelectionModel().select(6);
										}
										if(boo.getCondition()==7) {
											li.scrollTo(7);
									        li.getSelectionModel().select(7);
										}
										if(boo.getCondition()==8) {
											li.scrollTo(8);
									        li.getSelectionModel().select(8);
										}
									}
    								catch(Exception e) {
    									break;
    								}
								}
								if(n instanceof HBox) {
									try {
										HBox hb=(HBox)n;
										boolean flag=boo.isbAvailable();
										for (int i = 0; i < hb.getChildren().size(); i++) {											
											RadioButton rb=(RadioButton) hb.getChildren().get(i);
											if(flag==true) {
												if(rb.getText().equals("Available")) {
													rb.setSelected(true);
												}
											}
											else {
												if(rb.getText().equals("Unavailable")) {
													rb.setSelected(true);
												}
											}
										}
									}
									catch(Exception e) {
										break;
									}
								}
							}
    	            		
    	            		break;
    	            	}
    	            	else {
    	            		Thread.sleep(2000);
    	            	}
    	            } catch (InterruptedException ex) {
    	                ex.printStackTrace();
    	            }
            	}				
                return null;
            }
        };
        
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
            	// what to do if cycle is succeeded
//            	Alert alert = new Alert(AlertType.INFORMATION, "alert");
//				alert.setTitle("success");
//				alert.setHeaderText(null);
//				alert.setGraphic(null);
//				alert.showAndWait();
            };
        });
            
        new Thread(sleeper).start();
    }
    
//    //=================ACCOUNT TO DB
//    public void AccountToDB(VBox vb, int choice) {
//    	Accounts acc=new Accounts();
//		
//		for (Node n : vb.getChildren()) {
//			if(n instanceof TextField) {
//				try {
//					TextField tf=(TextField)n;
//					if(tf.getPromptText().equals("Username")) {
//						acc.setUsername(tf.getText());
//					}
//					if(tf.getPromptText().equals("Password")) {
//						acc.setPassword(tf.getText());
//					}
//					if(tf.getPromptText().equals("First Name")) {
//						acc.setFirstName(tf.getText());
//					}
//					if(tf.getPromptText().equals("Last Name")) {
//						acc.setLastName(tf.getText());
//					}
//					if(tf.getPromptText().equals("UCN")) {
//						acc.setUCN(tf.getText());
//					}
//					if(tf.getPromptText().equals("Phone Number")) {
//						acc.setPhoneNumber(tf.getText());
//					}
//					if(tf.getPromptText().equals("Email")) {
//						acc.setEmail(tf.getText());
//					}
//					if(tf.getPromptText().equals("Address")) {
//						acc.setAddress(tf.getText());
//					}
//				}
//				catch(Exception e) {
//					break;
//				}
//			}
//			acc.setRatingID(1);
//			acc.setRoleID(3);
//			
//			switch(choice) {
//			case 1: 
//				try {
//					adi.Insert(acc);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				break;
//				
//			case 2:
//				try {
//					List<Accounts> li=adi.SelectAll();
//					for (int i = 0; i < li.size(); i++) {
//						if(acc.getUCN().equals(li.get(i).getUCN())) {
//							adi.UpdateWhereID((int)li.get(i).getID(), acc);
//						}
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				break;
//			}
//		}
//    }
//    
//    //=================BOOK TO DB
//    public void BookToDB(VBox vb, int choice) {
//    	Books boo=new Books();
//    	
//    	for (Node n : vb.getChildren()) {
//			if(n instanceof TextField) {
//				try {
//					TextField tf=(TextField)n;
//					if(tf.getPromptText().equals("Title")) {
//						boo.setTitle(tf.getText());
//					}
//					if(tf.getPromptText().equals("Author")) {
//						boo.setAuthor(tf.getText());
//					}
//					if(tf.getPromptText().equals("Genre")) {
//						boo.setGenre(tf.getText());
//					}
//				}
//				catch(Exception e) {
//					break;
//				}
//			}
//			if(n instanceof ListView) {
//				try {
//					ListView<String> li=(ListView<String>)n;
//					boo.setCondition(li.getSelectionModel().getSelectedIndex());
//				}
//				catch(Exception e) {
//					break;
//				}
//			}
//			if(n instanceof HBox) {
//				try {
//					HBox hb=(HBox)n;
//					for (int i = 0; i < hb.getChildren().size(); i++) {											
//						RadioButton rb=(RadioButton) hb.getChildren().get(i);
//						if(rb.getText().equals("Available")) {
//							boo.setbAvailable(true);
//						}
//						if(rb.getText().equals("Unavailable")) {
//							boo.setbAvailable(false);
//						}
//					}
//				}
//				catch(Exception e) {
//					break;
//				}
//			}
//			
//			switch(choice) {
//			case 1: 
//				try {
//					bdi.Insert(boo);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				break;
//				
//			case 2:
//				try {
//					List<Books> li=bdi.SelectAll();
//					for (int i = 0; i < li.size(); i++) {
//						if(boo.getTitle().equals(li.get(i).getTitle())) {
//							bdi.UpdateWhereID(li.get(i).getID(), boo);
//						}
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				break;
//			}
//		}
//    }
//    
//    //==============REMOVE ACCOUNT
//    public void removeAccount(TableView<Accounts> table) {
//    	Accounts acc=new Accounts();
//    	acc=(Accounts) table.getSelectionModel().getSelectedItem();
//    	
//    	try {
//    		List<Accounts> li=adi.SelectAll();
//    		for (int i = 0; i < li.size(); i++) {
//    			if(acc.getUCN().equals(li.get(i).getUCN())) {
//    				adi.DeleteWhereID((int)li.get(i).getID());
//    			}
//    		}
//    	} catch (SQLException e) {
//    		e.printStackTrace();
//    	}
//    }
//    
//    //==============REMOVE BOOK
//    public void removeBook(TableView<Books> table) {
//    	Books boo=new Books();
//		boo=(Books) table.getSelectionModel().getSelectedItem();
//		
//		try {
//			List<Books> li=bdi.SelectAll();
//			for (int i = 0; i < li.size(); i++) {
//				if(boo.getTitle().equals(li.get(i).getTitle())) {
//					bdi.DeleteWhereID(li.get(i).getID());
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//    }
//    
}