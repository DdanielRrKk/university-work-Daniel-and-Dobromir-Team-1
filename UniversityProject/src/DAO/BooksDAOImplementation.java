package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Books;

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
	public int Insert(Books oBook) throws SQLException 
	{
		String strQuery = "INSERT INTO BOOKS (TITLE, AUTHOR, GENRE, CONDITION_ID, AVAILABLE) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(strQuery);
		preparedStatement.setString(1, oBook.getTitle());
		preparedStatement.setString(2, oBook.getAuthor());
		preparedStatement.setString(3, oBook.getGenre());
		preparedStatement.setInt(4, oBook.getCondition());
		preparedStatement.setBoolean(5, oBook.isAvailable());
		return preparedStatement.executeUpdate();
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
		String strQuery = "UPDATE BOOKS SET TITLE = ?, SET AUTHOR = ?, SET GENRE = ?, SET CONDITION_ID = ?, SET AVAILABLE = ? WHERE ID = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(strQuery);
		preparedStatement.setString(1, oBook.getTitle());
		preparedStatement.setString(2, oBook.getAuthor());
		preparedStatement.setString(3, oBook.getGenre());
		preparedStatement.setInt(4, oBook.getCondition());
		preparedStatement.setBoolean(5, oBook.isAvailable());
		preparedStatement.setInt(6, ID);
		preparedStatement.executeUpdate();
	}
}
