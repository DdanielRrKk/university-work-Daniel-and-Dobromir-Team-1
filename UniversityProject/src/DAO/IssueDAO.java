package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Issue;

public interface IssueDAO 
{
	public Issue SelectWhereID(int ID) throws SQLException;
	public List<Issue> SelectAll() throws SQLException;
	public int Insert(Issue oIssue) throws SQLException;
	public int DeleteWhereID(int ID) throws SQLException;
	public void UpdateWhereID(int ID, Issue oIssue) throws SQLException;
}
