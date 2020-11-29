package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.AccountsData;
import Model.Accounts;

public class UpdateWhereIDAccountsTest {

	AccountsData ad=new AccountsData();
	
	@Test
	public void UpdateWhereIDAccountsTest() {
		try {
			Accounts acc=ad.SelectWhereID(25);
			acc.setFirstName("Test2");
			ad.UpdateWhereID(25, acc);
			Accounts a=ad.SelectWhereID(25);
			assertEquals("Test2",a.getFirstName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
