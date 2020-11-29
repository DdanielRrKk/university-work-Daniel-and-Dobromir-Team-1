package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.BooksData;
import Model.Books;

public class DeleteBooksTest {
	
	BooksData bd=new BooksData();
	
	@Test
	public void DeleteBooksTest() {
		try {
			List<Books> lb=bd.SelectAll();
			int lSize=lb.size();
			bd.DeleteWhereID(14);
			List<Books> lboo=bd.SelectAll();
			assertEquals(lSize - 1,lboo.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
