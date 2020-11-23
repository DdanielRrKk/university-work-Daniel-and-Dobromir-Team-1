package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Books;

public interface BooksDAO 
{
	public Books SelectWhereID(int ID) throws SQLException;
	public List<Books> SelectAll() throws SQLException;
	public boolean Insert(Books oBook) throws SQLException;
	public int DeleteWhereID(int ID) throws SQLException;
	public void UpdateWhereID(int ID, Books oBook) throws SQLException;
}
