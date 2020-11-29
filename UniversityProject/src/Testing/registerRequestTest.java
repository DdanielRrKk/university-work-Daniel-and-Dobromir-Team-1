package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.AccountsData;
import BusinessLogic.OperatorFunctions;
import Model.Accounts;

public class registerRequestTest {
	
	OperatorFunctions of=new OperatorFunctions();
	AccountsData ad=new AccountsData();
	
	@Test
	public void registerRequestTest() {
		Accounts a=new Accounts();
		a.setFirstName("Test");
		a.setLastName("Test");
		a.setUsername("Test");
		a.setPassword("Test");
		a.setUCN("Test");
		a.setPhoneNumber("Test");
		a.setEmail("Test");
		a.setAddress("Test");
		a.setRoleID(3);
		a.setRatingID(3);
		of.registerRequest(a);
		
		Accounts acc=new Accounts();
		
		try{
			List<Accounts> la=ad.SelectAll();
			for(Accounts ac : la) {
				if(ac.getUsername().equals("Test")) {
					acc=ac;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Test", acc.getUsername());
	}

}
