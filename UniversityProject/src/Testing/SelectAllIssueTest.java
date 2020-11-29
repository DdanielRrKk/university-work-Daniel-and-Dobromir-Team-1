package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.IssueData;
import Model.Issue;

public class SelectAllIssueTest {

	IssueData id=new IssueData();
	
	@Test
	public void SelectAllIssueTest() {
		try {
			List<Issue> li=id.SelectAll();
			assertEquals(2, li.get(0).getBookID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
