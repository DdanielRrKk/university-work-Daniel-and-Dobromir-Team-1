package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.AccountsData;
import Model.Accounts;

public class SelectAllAccountsTest {
	
	AccountsData ad=new AccountsData();
	
	@Test
	public void SelectAllAccountsTest() {
		try {
			List<Accounts> la=ad.SelectAll();
			assertEquals("Boris",la.get(0).getFirstName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
