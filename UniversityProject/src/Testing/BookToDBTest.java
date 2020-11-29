package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.AccountsData;
import BusinessLogic.BooksData;
import BusinessLogic.OperatorFunctions;
import Model.Accounts;
import Model.Books;

public class BookToDBTest {
	
	OperatorFunctions of=new OperatorFunctions();
	BooksData bd=new BooksData();
	
	@Test
	public void BookToDBTest1() {
		Books b=new Books();
		b.setTitle("Test");
		b.setAuthor("Test");
		b.setGenre("Test");
		b.setCondition(3);
		of.BookToDB(b, 1);
		
		Books boo=new Books();
		
		try{
			List<Books> lb=bd.SelectAll();
			for(Books bo : lb) {
				if(bo.getTitle().equals("Test")) {
					boo=bo;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Test", boo.getTitle());
	}
	
	@Test
	public void BookToDBTest2() {
		try {
			Books boo=bd.SelectWhereID(15);
			boo.setTitle("Test2");
			of.BookToDB(boo, 2);
			
			Books b=bd.SelectWhereID(15);
			assertEquals("Test2",b.getTitle());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
