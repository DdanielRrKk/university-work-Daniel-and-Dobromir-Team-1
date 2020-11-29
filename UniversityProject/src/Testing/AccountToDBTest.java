package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.AccountsData;
import BusinessLogic.OperatorFunctions;
import Model.Accounts;

public class AccountToDBTest {
	
	OperatorFunctions of=new OperatorFunctions();
	AccountsData ad=new AccountsData();
	
	@Test
	public void AccountsToDBTest1() {
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
		of.AccountToDB(a, 1);
		
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
	
	@Test
	public void AccountsToDBTest2() {
		try {
			Accounts acc=ad.SelectWhereID(27);
			acc.setFirstName("Test2");
			of.AccountToDB(acc, 2);
			
			Accounts a=ad.SelectWhereID(27);
			assertEquals("Test2",a.getFirstName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
