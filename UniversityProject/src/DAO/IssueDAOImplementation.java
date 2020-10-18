package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Accounts;
import Model.Issue;

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
			oIssue.setAccountID(rs.getInt("ACCOUNT_ID"));
			oIssue.setBookID(rs.getInt("BOOK_ID"));
			oIssue.setIssueDate(rs.getDate("ISSUE_DATE"));
			oIssue.setReturnDate(rs.getDate("RETURN_DATE"));
			oIssue.setReturnedDate(rs.getDate("RETURNED_DATE"));
			oIssue.setReturnedCondition(rs.getInt("RETURNED_CONDITION"));
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
			oIssue.setAccountID(rs.getInt("ACCOUNT_ID"));
			oIssue.setBookID(rs.getInt("BOOK_ID"));
			oIssue.setIssueDate(rs.getDate("ISSUE_DATE"));
			oIssue.setReturnDate(rs.getDate("RETURN_DATE"));
			oIssue.setReturnedDate(rs.getDate("RETURNED_DATE"));
			oIssue.setReturnedCondition(rs.getInt("RETURNED_CONDITION"));
			oIssueList.add(oIssue);
		}
	
		return oIssueList;
	}

	@Override
	public int Insert(Issue oIssue) throws SQLException 
	{
		String strQuery = "INSERT INTO ISSUE (BOOK_ID, ACCOUNT_ID, ISSUE_DATE, RETURN_DATE, RETURNED_DATE, RETURNED_CONDITION) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(strQuery);
		preparedStatement.setInt(1, oIssue.getBookID());
		preparedStatement.setInt(2, oIssue.getAccountID());
		preparedStatement.setDate(3, oIssue.getIssueDate());
		preparedStatement.setDate(4, oIssue.getReturnDate());
		preparedStatement.setDate(5, oIssue.getReturnedDate());
		preparedStatement.setInt(6, oIssue.getReturnedCondition());
		return preparedStatement.executeUpdate();
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
		String strQuery = "UPDATE ISSUE SET BOOK_ID = ?, SET ACCOUNT_ID = ?, SET ISSUE_DATE = ?, SET RETURN_DATE = ?, SET RETURNED_DATE = ?, SET CONDITION_ID = ? WHERE ID = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(strQuery);
		preparedStatement.setInt(1, oIssue.getBookID());
		preparedStatement.setInt(2, oIssue.getAccountID());
		preparedStatement.setDate(3, oIssue.getIssueDate());
		preparedStatement.setDate(4, oIssue.getReturnDate());
		preparedStatement.setDate(5, oIssue.getReturnedDate());
		preparedStatement.setInt(6, oIssue.getReturnedCondition());
		preparedStatement.executeUpdate();
	}

}
