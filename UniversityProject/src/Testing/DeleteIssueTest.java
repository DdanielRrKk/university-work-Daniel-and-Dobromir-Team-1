package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.IssueData;
import Model.Issue;

public class DeleteIssueTest {

	IssueData id=new IssueData();
	
	@Test
	public void DeleteIssueTest() {
		try {
			List<Issue> li=id.SelectAll();
			int lSize=li.size();
			id.DeleteWhereID(17);
			List<Issue> liss=id.SelectAll();
			assertEquals(lSize - 1, liss.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
