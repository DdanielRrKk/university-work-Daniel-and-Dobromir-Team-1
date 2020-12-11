package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Issue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class IssueDAOImplementation implements IssueDAO
{
	private static Connection connection = DatabaseConnection.getInstance().getConnection();
	
	@Override
	public Issue SelectWhereID(int ID) throws SQLException 
	{
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ISSUE WHERE ID = ?");
		preparedStatement.setLong(1, ID);
		
		Issue oIssue = new Issue();
		
		ResultSet rs = preparedStatement.executeQuery();
		boolean check = false;
		
		while(rs.next())
		{
			check = true;
			oIssue.setID(rs.getInt("ID"));
			oIssue.setUpdateCounter(rs.getInt("UPDATE_COUNTER"));
			oIssue.setAccountID(rs.getInt("ACCOUNT_ID"));
			oIssue.setBookID(rs.getInt("BOOK_ID"));
			oIssue.setIssueDate(rs.getDate("ISSUE_DATE"));
			oIssue.setReturnDate(rs.getDate("RETURN_DATE"));
			oIssue.setReturnedDate(rs.getDate("RETURNED_DATE"));
			oIssue.setReturnedCondition(rs.getInt("RETURNED_CONDITION_ID"));
			oIssue.setApproved(rs.getBoolean("APPROVED"));
		}
		
		if(check == true)
			return oIssue;
		else
			return null;
	}

	@Override
	public List<Issue> SelectAll() throws SQLException 
	{
		List<Issue> oIssueList = new ArrayList<Issue>();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ISSUE");
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next())
		{
			Issue oIssue = new Issue();
			oIssue.setID(rs.getInt("ID"));
			oIssue.setUpdateCounter(rs.getInt("UPDATE_COUNTER"));
			oIssue.setAccountID(rs.getInt("ACCOUNT_ID"));
			oIssue.setBookID(rs.getInt("BOOK_ID"));
			oIssue.setIssueDate(rs.getDate("ISSUE_DATE"));
			oIssue.setReturnDate(rs.getDate("RETURN_DATE"));
			oIssue.setReturnedDate(rs.getDate("RETURNED_DATE"));
			oIssue.setReturnedCondition(rs.getInt("RETURNED_CONDITION_ID"));
			oIssue.setApproved(rs.getBoolean("APPROVED"));
			oIssueList.add(oIssue);
		}
	
		return oIssueList;
	}

	@Override
	public boolean Insert(Issue oIssue) throws SQLException 
	{
		String strQuery = "INSERT INTO ISSUE (UPDATE_COUNTER, BOOK_ID, ACCOUNT_ID, ISSUE_DATE, RETURN_DATE, RETURNED_DATE, RETURNED_CONDITION_ID, APPROVED) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(strQuery, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, oIssue.getUpdateCounter());
		preparedStatement.setInt(2, oIssue.getBookID());
		preparedStatement.setInt(3, oIssue.getAccountID());
		preparedStatement.setDate(4, oIssue.getIssueDate());
		preparedStatement.setDate(5, oIssue.getReturnDate());
		preparedStatement.setDate(6, oIssue.getReturnedDate());
		preparedStatement.setInt(7, oIssue.getReturnedCondition());
		preparedStatement.setBoolean(8, oIssue.isApproved());
		
		if(preparedStatement.executeUpdate() == 0)
		{
			return false;
		}
		
		ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) 
        {
        	oIssue.setID(rs.getInt(1));
        }
        
		return true;
	}

	@Override
	public int DeleteWhereID(int ID) throws SQLException 
	{
		PreparedStatement preparedStatement  = connection.prepareStatement("DELETE FROM ISSUE WHERE ID = ?");
	    preparedStatement.setInt(1, ID);
	    return preparedStatement.executeUpdate();
	}

	@Override
	public void UpdateWhereID(int ID, Issue oIssue) throws SQLException 
	{
		connection.setAutoCommit(false);
		String strQuery = "SELECT UPDATE_COUNTER, BOOK_ID, ACCOUNT_ID, ISSUE_DATE, RETURN_DATE, RETURNED_DATE, RETURNED_CONDITION_ID, APPROVED FROM ISSUE WITH (UPDLOCK) WHERE ID = " + ID;
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = statement.executeQuery(strQuery);
		
		if(rs.next())
		{
			if(oIssue.getUpdateCounter() != rs.getInt("UPDATE_COUNTER"))
			{
				connection.rollback();
				connection.setAutoCommit(true);
				Alert alert = new Alert(AlertType.CONFIRMATION, "An update occured while you were working. Please restart the application.", ButtonType.OK);
		    	alert.setHeaderText(null);
		    	alert.showAndWait();
				return;
			}
			
			oIssue.setUpdateCounter(rs.getInt("UPDATE_COUNTER")+1);
			rs.updateInt("UPDATE_COUNTER", oIssue.getUpdateCounter());
			rs.updateInt("BOOK_ID", oIssue.getBookID());
			rs.updateInt("ACCOUNT_ID", oIssue.getAccountID());
			rs.updateDate("RETURN_DATE", oIssue.getReturnDate());
			rs.updateDate("RETURNED_DATE", oIssue.getReturnedDate());
			rs.updateInt("RETURNED_CONDITION_ID", oIssue.getReturnedCondition());
			rs.updateBoolean("APPROVED", oIssue.isApproved());
			rs.updateRow();
		}
		
		connection.commit();
		connection.setAutoCommit(true);
	}
	
	@Override
	public List<Issue> SelectAllWhereAccountID(int ID) throws SQLException
	{
		List<Issue> oIssueList = new ArrayList<Issue>();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ISSUE WHERE ACCOUNT_ID = ?");
		preparedStatement.setInt(1, ID);
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next())
		{
			Issue oIssue = new Issue();
			oIssue.setID(rs.getInt("ID"));
			oIssue.setUpdateCounter(rs.getInt("UPDATE_COUNTER"));
			oIssue.setAccountID(rs.getInt("ACCOUNT_ID"));
			oIssue.setBookID(rs.getInt("BOOK_ID"));
			oIssue.setIssueDate(rs.getDate("ISSUE_DATE"));
			oIssue.setReturnDate(rs.getDate("RETURN_DATE"));
			oIssue.setReturnedDate(rs.getDate("RETURNED_DATE"));
			oIssue.setReturnedCondition(rs.getInt("RETURNED_CONDITION_ID"));
			oIssue.setApproved(rs.getBoolean("APPROVED"));
			oIssueList.add(oIssue);
		}
	
		return oIssueList;
	}

}
