package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Books;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class BooksDAOImplementation implements BooksDAO 
{
	private static Connection connection = DatabaseConnection.getInstance().getConnection();
	
	@Override
	public Books SelectWhereID(int ID) throws SQLException 
	{
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOKS WHERE ID = ?");
		preparedStatement.setInt(1, ID);
		
		Books oBook = new Books();
		
		ResultSet rs = preparedStatement.executeQuery();
		boolean check = false;
		
		while(rs.next())
		{
			check = true;
			oBook.setID(rs.getInt("ID"));
			oBook.setUpdateCounter(rs.getInt("UPDATE_COUNTER"));
			oBook.setTitle(rs.getString("TITLE"));
			oBook.setAuthor(rs.getString("AUTHOR"));
			oBook.setGenre(rs.getString("GENRE"));
			oBook.setCondition(rs.getInt("CONDITION_ID"));
			oBook.setAvailable(rs.getBoolean("AVAILABLE"));
		}
		
		if(check == true)
			return oBook;
		else
			return null;
	}

	@Override
	public List<Books> SelectAll() throws SQLException 
	{
		List<Books> oBooksList = new ArrayList<Books>();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOKS");
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next())
		{
			Books oBook = new Books();
			oBook.setID(rs.getInt("ID"));
			oBook.setUpdateCounter(rs.getInt("UPDATE_COUNTER"));
			oBook.setTitle(rs.getString("TITLE"));
			oBook.setAuthor(rs.getString("AUTHOR"));
			oBook.setGenre(rs.getString("GENRE"));
			oBook.setCondition(rs.getInt("CONDITION_ID"));
			oBook.setAvailable(rs.getBoolean("AVAILABLE"));
			oBooksList.add(oBook);
		}
	
		return oBooksList;
	}

	@Override
	public boolean Insert(Books oBook) throws SQLException 
	{
		String strQuery = "INSERT INTO BOOKS (UPDATE_COUNTER, TITLE, AUTHOR, GENRE, CONDITION_ID, AVAILABLE) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(strQuery, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, oBook.getUpdateCounter());
		preparedStatement.setString(2, oBook.getTitle());
		preparedStatement.setString(3, oBook.getAuthor());
		preparedStatement.setString(4, oBook.getGenre());
		preparedStatement.setInt(5, oBook.getCondition());
		preparedStatement.setBoolean(6, oBook.isAvailable());
		
		if (preparedStatement.executeUpdate() == 0)
		{
			return false;
		}
		
		ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) 
        {
        	oBook.setID(rs.getInt(1));
        }
        
		return true;
	}

	@Override
	public int DeleteWhereID(int ID) throws SQLException 
	{
		PreparedStatement preparedStatement  = connection.prepareStatement("DELETE FROM BOOKS WHERE ID = ?");
	    preparedStatement.setInt(1, ID);
	    return preparedStatement.executeUpdate();
	}

	@Override
	public void UpdateWhereID(int ID, Books oBook) throws SQLException 
	{
		connection.setAutoCommit(false);
		String strQuery = "SELECT UPDATE_COUNTER, TITLE, AUTHOR, GENRE, CONDITION_ID, AVAILABLE FROM BOOKS WITH (UPDLOCK) WHERE ID = " + ID;	
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = statement.executeQuery(strQuery);
		
		if(rs.next())
		{			
			if(oBook.getUpdateCounter() != rs.getInt("UPDATE_COUNTER"))
			{
				connection.rollback();
				connection.setAutoCommit(true);
				Alert alert = new Alert(AlertType.CONFIRMATION, "An update occured while you were working. Please restart the application.", ButtonType.OK);
		    	alert.setHeaderText(null);
		    	alert.showAndWait();
				return;
			}
			
			oBook.setUpdateCounter(rs.getInt("UPDATE_COUNTER")+1);
			rs.updateInt("UPDATE_COUNTER", oBook.getUpdateCounter());
			rs.updateString("TITLE", oBook.getTitle());
			rs.updateString("AUTHOR", oBook.getAuthor());
			rs.updateString("GENRE", oBook.getGenre());
			rs.updateInt("CONDITION_ID", oBook.getCondition());
			rs.updateBoolean("AVAILABLE", oBook.isAvailable());
			rs.updateRow();		
			
		}
		connection.commit();
		connection.setAutoCommit(true);
	}
}
