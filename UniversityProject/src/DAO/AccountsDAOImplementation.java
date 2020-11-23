package DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Accounts;

public class AccountsDAOImplementation implements AccountsDAO 
{
	private static Connection connection = DatabaseConnection.getInstance().getConnection();
	
	@Override
	public Accounts SelectWhereID(int ID) throws SQLException
	{
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE ID = ?");
		preparedStatement.setInt(1, ID);
		
		Accounts oAccount = new Accounts();
		
		ResultSet rs = preparedStatement.executeQuery();
		boolean check = false;
		
		while(rs.next())
		{
			check = true;
			oAccount.setID(rs.getInt("ID"));
			oAccount.setUpdateCounter(rs.getInt("UPDATE_COUNTER"));
			oAccount.setFirstName(rs.getString("FIRSTNAME"));
			oAccount.setLastName(rs.getString("LASTNAME"));
			oAccount.setUCN(rs.getString("UCN"));
			oAccount.setPhoneNumber(rs.getString("PHONENUMBER"));
			oAccount.setEmail(rs.getString("EMAIL"));
			oAccount.setAddress(rs.getString("ADDRESS"));
			oAccount.setUsername(rs.getString("USERNAME"));
			oAccount.setPassword(rs.getString("PASSWORD"));
			oAccount.setRoleID(rs.getInt("ROLE_ID"));
			oAccount.setRatingID(rs.getInt("RATING_ID"));
			oAccount.setApproved(rs.getBoolean("APPROVED"));
		}
		
		if(check == true)
			return oAccount;
		else
			return null;
	}

	@Override
	public List<Accounts> SelectAll() throws SQLException 
	{
		List<Accounts> oAccountsList = new ArrayList<Accounts>();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNTS");
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next())
		{
			Accounts oAccount = new Accounts();
			oAccount.setID(rs.getInt("ID"));
			oAccount.setUpdateCounter(rs.getInt("UPDATE_COUNTER"));
			oAccount.setFirstName(rs.getString("FIRSTNAME"));
			oAccount.setLastName(rs.getString("LASTNAME"));
			oAccount.setUCN(rs.getString("UCN"));
			oAccount.setPhoneNumber(rs.getString("PHONENUMBER"));
			oAccount.setEmail(rs.getString("EMAIL"));
			oAccount.setAddress(rs.getString("ADDRESS"));
			oAccount.setUsername(rs.getString("USERNAME"));
			oAccount.setPassword(rs.getString("PASSWORD"));
			oAccount.setRoleID(rs.getInt("ROLE_ID"));
			oAccount.setRatingID(rs.getInt("RATING_ID"));
			oAccount.setApproved(rs.getBoolean("APPROVED"));
			oAccountsList.add(oAccount);
		}
	
		return oAccountsList;
	}
	
	@Override
	public List<Accounts> SelectAllOperators() throws SQLException 
	{
		List<Accounts> oAccountsList = new ArrayList<Accounts>();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE ROLE_ID = 2");
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next())
		{
			Accounts oAccount = new Accounts();
			oAccount.setID(rs.getInt("ID"));
			oAccount.setUpdateCounter(rs.getInt("UPDATE_COUNTER"));
			oAccount.setFirstName(rs.getString("FIRSTNAME"));
			oAccount.setLastName(rs.getString("LASTNAME"));
			oAccount.setUCN(rs.getString("UCN"));
			oAccount.setPhoneNumber(rs.getString("PHONENUMBER"));
			oAccount.setEmail(rs.getString("EMAIL"));
			oAccount.setAddress(rs.getString("ADDRESS"));
			oAccount.setUsername(rs.getString("USERNAME"));
			oAccount.setPassword(rs.getString("PASSWORD"));
			oAccount.setRoleID(rs.getInt("ROLE_ID"));
			oAccount.setRatingID(rs.getInt("RATING_ID"));
			oAccount.setApproved(rs.getBoolean("APPROVED"));
			oAccountsList.add(oAccount);
		}
	
		return oAccountsList;
	}


	@Override
	public boolean Insert(Accounts oAccount) throws SQLException 
	{
		String strQuery = "INSERT INTO ACCOUNTS (UPDATE_COUNTER, FIRSTNAME, LASTNAME, UCN, PHONENUMBER, EMAIL, ADDRESS, USERNAME, PASSWORD, ROLE_ID, RATING_ID, APPROVED) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(strQuery, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, oAccount.getUpdateCounter());
		preparedStatement.setString(2, oAccount.getFirstName());
		preparedStatement.setString(3, oAccount.getLastName());
		preparedStatement.setString(4, oAccount.getUCN());
		preparedStatement.setString(5, oAccount.getPhoneNumber());
		preparedStatement.setString(6, oAccount.getEmail());
		preparedStatement.setString(7, oAccount.getAddress());
		preparedStatement.setString(8, oAccount.getUsername());
		preparedStatement.setString(9, oAccount.getPassword());
		preparedStatement.setLong(10, oAccount.getRoleID());
		preparedStatement.setLong(11, oAccount.getRatingID());
		preparedStatement.setBoolean(12, oAccount.isApproved());
		
		if(preparedStatement.executeUpdate() == 0)
		{
			return false;
		}
		
		ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) 
        {
        	oAccount.setID(rs.getInt(1));
        }
		
		return true;
	}

	@Override
	public int DeleteWhereID(int ID) throws SQLException
	{	
		PreparedStatement preparedStatement  = connection.prepareStatement("DELETE FROM ACCOUNTS WHERE ID = ?");
	    preparedStatement.setInt(1, ID);
	    return preparedStatement.executeUpdate();
	}


	@Override
	public void UpdateWhereID(int ID, Accounts oAccount) throws SQLException
	{
		connection.setAutoCommit(false);
		String strQuery = "SELECT UPDATE_COUNTER, FIRSTNAME, LASTNAME, UCN, PHONENUMBER, EMAIL, ADDRESS, USERNAME, PASSWORD, ROLE_ID, RATING_ID, APPROVED FROM ACCOUNTS WITH (UPDLOCK) WHERE ID = " + ID;
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = statement.executeQuery(strQuery);
		
		if(rs.next())
		{
			if(oAccount.getUpdateCounter() != rs.getInt("UPDATE_COUNTER"))
			{
				connection.rollback();
				connection.setAutoCommit(true);
				return;
			}
			
			oAccount.setUpdateCounter(rs.getInt("UPDATE_COUNTER")+1);
			rs.updateInt("UPDATE_COUNTER", oAccount.getUpdateCounter());
			rs.updateString("FIRSTNAME", oAccount.getFirstName());
			rs.updateString("LASTNAME", oAccount.getLastName());
			rs.updateString("UCN", oAccount.getUCN());
			rs.updateString("PHONENUMBER", oAccount.getPhoneNumber());
			rs.updateString("EMAIL", oAccount.getEmail());
			rs.updateString("ADDRESS", oAccount.getAddress());
			rs.updateString("USERNAME", oAccount.getUsername());
			rs.updateString("PASSWORD", oAccount.getPassword());
			rs.updateInt("ROLE_ID", oAccount.getRoleID());
			rs.updateInt("RATING_ID", oAccount.getRatingID());
			rs.updateBoolean("APPROVED", oAccount.isApproved());
			rs.updateRow();
		}
		
		connection.commit();
		connection.setAutoCommit(true);
	}

	@Override
	public int ValidateLogin(String username, String password) throws SQLException
	{
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE username = ? and password = ? and approved = 'true'");
	    preparedStatement.setString(1, username);
	    preparedStatement.setString(2, password);
	     
	    ResultSet resultSet = preparedStatement.executeQuery();
         
	    if (resultSet.next()) 
	    {
            int ID = resultSet.getInt("ROLE_ID");
            return ID;
        }
	
	    return 0;
	}
	
	@Override 
	public int GetUserID(String username, String password) throws SQLException
	{
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE username = ? and password = ?");
	    preparedStatement.setString(1, username);
	    preparedStatement.setString(2, password);
	     
	    ResultSet resultSet = preparedStatement.executeQuery();
         
	    if (resultSet.next()) 
	    {
            int ID = resultSet.getInt("ID");
            return ID;
        }
	
	    return 0;
	}
}
