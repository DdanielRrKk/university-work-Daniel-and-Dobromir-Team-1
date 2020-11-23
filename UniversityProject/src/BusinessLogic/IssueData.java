package BusinessLogic;

import java.sql.SQLException;
import java.util.List;

import DAO.IssueDAOImplementation;
import Model.Issue;

public class IssueData 
{
	private IssueDAOImplementation m_oIssueDaoImpl;
	
	public IssueData()
	{
		m_oIssueDaoImpl = new IssueDAOImplementation();
	}
	
	public Issue SelectWhereID(int ID) throws SQLException
	{
		return m_oIssueDaoImpl.SelectWhereID(ID);
	}
	
	public List<Issue> SelectAll() throws SQLException
	{
		return m_oIssueDaoImpl.SelectAll();
	}
	
	public boolean Insert(Issue oIssue) throws SQLException
	{
		return m_oIssueDaoImpl.Insert(oIssue);
	}
	
	public int DeleteWhereID(int ID) throws SQLException
	{
		return m_oIssueDaoImpl.DeleteWhereID(ID);
	}
	
	public void UpdateWhereID(int ID, Issue oIssue) throws SQLException
	{
		m_oIssueDaoImpl.UpdateWhereID(ID, oIssue);
	}
	
	public List<Issue> SelectAllWhereAccountID(int ID) throws SQLException
	{
		return m_oIssueDaoImpl.SelectAllWhereAccountID(ID);
	}
}
