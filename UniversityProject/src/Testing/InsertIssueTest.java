package Testing;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import BusinessLogic.IssueData;
import Model.Issue;

public class InsertIssueTest {

	IssueData id=new IssueData();
	
	@Test
	public void InsertIssueTest() {
	    Date d=new Date(2000,1,1);
		
		try {
			Issue i=new Issue();
			i.setBookID(3);
			i.setAccountID(3);
			i.setUpdateCounter(99);
			i.setIssueDate(d);
			i.setReturnDate(d);
			i.setReturnedCondition(3);
			assertEquals(true,id.Insert(i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
