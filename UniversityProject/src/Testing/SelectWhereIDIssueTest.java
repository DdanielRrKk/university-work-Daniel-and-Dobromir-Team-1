package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import BusinessLogic.AccountsData;
import BusinessLogic.IssueData;
import Model.Issue;

public class SelectWhereIDIssueTest {
	
	IssueData id=new IssueData();
	
	@Test
	public void SelectWhereIDIssueTest() {
		try {
			Issue i=id.SelectWhereID(2);
			assertEquals(2,i.getBookID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
