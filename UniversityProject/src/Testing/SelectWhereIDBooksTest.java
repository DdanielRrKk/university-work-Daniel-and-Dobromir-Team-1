package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import BusinessLogic.BooksData;
import Model.Books;

public class SelectWhereIDBooksTest {
	
	BooksData bd=new BooksData();
	
	@Test
	public void SelectWhereIDBooksTest() {
		try {
			Books b=bd.SelectWhereID(14);
			assertEquals("Test2",b.getTitle());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
