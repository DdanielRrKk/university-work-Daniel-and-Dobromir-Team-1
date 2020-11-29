package Testing;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.AccountsData;
import BusinessLogic.IssueData;
import BusinessLogic.OperatorFunctions;
import Model.Accounts;
import Model.Issue;

public class issueRequestTest {
	
	OperatorFunctions of=new OperatorFunctions();
	IssueData id=new IssueData();
	
	@Test
	public void issueRequestTest() {
		Date d=new Date(2000,1,1);
		
		Issue i=new Issue();
		i.setBookID(3);
		i.setAccountID(3);
		i.setUpdateCounter(99);
		i.setIssueDate(d);
		i.setReturnDate(d);
		i.setReturnedCondition(3);
		of.issueRequest(i);
		
		Issue iss=new Issue();
		
		try{
			List<Issue> li=id.SelectAll();
			for(Issue is : li) {
				if(i.getUpdateCounter()==99) {
					iss=is;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(99, iss.getUpdateCounter());
	}

}
