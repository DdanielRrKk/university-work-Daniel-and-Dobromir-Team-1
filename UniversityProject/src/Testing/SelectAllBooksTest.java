package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.BooksData;
import Model.Books;

public class SelectAllBooksTest {
	
	BooksData bd=new BooksData();
	
	@Test
	public void SelectAllBooksTest() {
		try {
			List<Books> lb=bd.SelectAll();
			assertEquals("Pesen za ogun i led", lb.get(0).getTitle());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
