package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.BooksData;
import Model.Books;

public class InsertBooksTest {
	
	BooksData bd=new BooksData();
	
	@Test
	public void InsertBooksTest() {
		try {
			Books b=new Books();
			b.setTitle("Test");
			b.setAuthor("Test");
			b.setGenre("Test");
			b.setCondition(3);
			assertEquals(true,bd.Insert(b));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
