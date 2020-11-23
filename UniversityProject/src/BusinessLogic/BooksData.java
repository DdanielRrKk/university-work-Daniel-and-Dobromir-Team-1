package BusinessLogic;

import java.sql.SQLException;
import java.util.List;

import DAO.BooksDAOImplementation;
import Model.Books;

public class BooksData
{
	private BooksDAOImplementation m_oBooksDaoImpl;
	
	public BooksData()
	{
		m_oBooksDaoImpl = new BooksDAOImplementation();
	}
	
	public Books SelectWhereID(int ID) throws SQLException
	{
		return m_oBooksDaoImpl.SelectWhereID(ID);
	}
	
	public List<Books> SelectAll() throws SQLException
	{
		return m_oBooksDaoImpl.SelectAll();
	}
	
	public boolean Insert(Books oBook) throws SQLException
	{
		return m_oBooksDaoImpl.Insert(oBook);
	}
	
	public int DeleteWhereID(int ID) throws SQLException
	{
		return m_oBooksDaoImpl.DeleteWhereID(ID);
	}
	
	public void UpdateWhereID(int ID, Books oBook) throws SQLException
	{
		m_oBooksDaoImpl.UpdateWhereID(ID, oBook);
	}
}
