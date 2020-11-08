package BusinessLogic;

import java.sql.SQLException;
import java.util.List;

import DAO.AccountsDAOImplementation;
import Model.Accounts;

public class AccountsData 
{
	private AccountsDAOImplementation m_oAccountsDaoImpl;
	
	public AccountsData()
	{
		m_oAccountsDaoImpl = new AccountsDAOImplementation();
	}
	
	public Accounts SelectWhereID(int ID) throws SQLException
	{
		return m_oAccountsDaoImpl.SelectWhereID(ID);
	}
	
	public List<Accounts> SelectAll() throws SQLException
	{
		return m_oAccountsDaoImpl.SelectAll();
	}
	
	public List<Accounts> SelectAllOperators() throws SQLException
	{
		return m_oAccountsDaoImpl.SelectAllOperators();
	}
	
	public int Insert(Accounts oAccount) throws SQLException
	{
		return m_oAccountsDaoImpl.Insert(oAccount);
	}
	
	public int DeleteWhereID(int ID) throws SQLException
	{
		return m_oAccountsDaoImpl.DeleteWhereID(ID);
	}
	
	public void UpdateWhereID(int ID, Accounts oAccount) throws SQLException
	{
		m_oAccountsDaoImpl.UpdateWhereID(ID, oAccount);
	}
	
	public int ValidateLogin(String username, String password) throws SQLException
	{
		return m_oAccountsDaoImpl.ValidateLogin(username, password);
	}
}
