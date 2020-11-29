package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.AccountsData;
import Model.Accounts;

public class InsertAccountsTest {

	AccountsData ad=new AccountsData();
	
	@Test
	public void InsertAccountsTest() {
		try {
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
			assertEquals(true,ad.Insert(a));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
