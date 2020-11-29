package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import BusinessLogic.BooksData;
import Model.Books;

public class UpdateWhereIDBooksTest {
	
	BooksData bd=new BooksData();
	
	@Test
	public void UpdateWhereIDBooksTest() {
		try {
			Books b=bd.SelectWhereID(14);
			b.setTitle("Test2");
			bd.UpdateWhereID(14, b);
			Books boo=bd.SelectWhereID(14);
			assertEquals("Test2",boo.getTitle());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
