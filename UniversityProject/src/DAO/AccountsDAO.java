package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Accounts;

public interface AccountsDAO 
{
	public Accounts SelectWhereID(int ID) throws SQLException;
	public List<Accounts> SelectAll() throws SQLException;
	public int Insert(Accounts oAccount) throws SQLException;
	public int DeleteWhereID(int ID) throws SQLException;
	public void UpdateWhereID(int ID, Accounts oAccount) throws SQLException;
	public boolean ValidateLogin(String username, String password) throws SQLException;
}
