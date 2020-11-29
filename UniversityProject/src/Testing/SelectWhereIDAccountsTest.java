package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import BusinessLogic.AccountsData;
import Model.Accounts;

public class SelectWhereIDAccountsTest {
	
	AccountsData ad=new AccountsData();
	
	@Test
	public void SelectWhereIDAccountsTest() {
		try {
			Accounts a=ad.SelectWhereID(25);
			assertEquals("Test2",a.getFirstName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
