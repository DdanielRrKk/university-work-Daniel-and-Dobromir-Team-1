package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import BusinessLogic.IssueData;
import Model.Issue;

public class UpdateWhereIDIssueTest {

	IssueData id=new IssueData();
	
	@Test
	public void UpdateWhereIDIssueTest() {
		try {
			Issue i=id.SelectWhereID(17);
			i.setUpdateCounter(101);
			id.UpdateWhereID(17, i);
			Issue iss=id.SelectWhereID(17);
			assertEquals(101,iss.getUpdateCounter());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
