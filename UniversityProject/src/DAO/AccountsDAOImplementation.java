package DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			oAccountsList.add(oAccount);
		}
	
		return oAccountsList;
	}

	@Override
	public int Insert(Accounts oAccount) throws SQLException 
	{
		String strQuery = "INSERT INTO ACCOUNTS (FIRSTNAME, LASTNAME, UCN, PHONENUMBER, EMAIL, ADDRESS, USERNAME, PASSWORD, ROLE_ID, RATING_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(strQuery);
		preparedStatement.setString(1, oAccount.getFirstName());
		preparedStatement.setString(2, oAccount.getLastName());
		preparedStatement.setString(3, oAccount.getUCN());
		preparedStatement.setString(4, oAccount.getPhoneNumber());
		preparedStatement.setString(5, oAccount.getEmail());
		preparedStatement.setString(6, oAccount.getAddress());
		preparedStatement.setString(7, oAccount.getUsername());
		preparedStatement.setString(8, oAccount.getPassword());
		preparedStatement.setLong(9, oAccount.getRoleID());
		preparedStatement.setLong(10, oAccount.getRatingID());
		return preparedStatement.executeUpdate();
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
		String strQuery = "UPDATE ACCOUNTS SET FIRSTNAME = ?, SET LASTNAME = ?, SET UCN = ?, SET PHONENUMBER = ?, SET EMAIL = ?, SET ADDRESS = ?, SET USERNAME = ?, SET PASSWORD = ?, SET ROLE_ID, SET RATING_ID = ? WHERE ID = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(strQuery);
		preparedStatement.setString(1, oAccount.getFirstName());
		preparedStatement.setString(2, oAccount.getLastName());
		preparedStatement.setString(3, oAccount.getUCN());
		preparedStatement.setString(4, oAccount.getPhoneNumber());
		preparedStatement.setString(5, oAccount.getEmail());
		preparedStatement.setString(6, oAccount.getAddress());
		preparedStatement.setString(7, oAccount.getUsername());
		preparedStatement.setString(8, oAccount.getPassword());
		preparedStatement.setInt(9, oAccount.getRoleID());
		preparedStatement.setInt(10, oAccount.getRatingID());
		preparedStatement.setInt(11, ID);
		preparedStatement.executeUpdate();
	}

	@Override
	public boolean ValidateLogin(String username, String password) throws SQLException
	{
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE username = ? and password = ?");
	    preparedStatement.setString(1, username);
	    preparedStatement.setString(2, password);
	     
	    ResultSet resultSet = preparedStatement.executeQuery();
         
	    if (resultSet.next()) 
	    {
            return true;
        }
	
	    return false;
	}

}
