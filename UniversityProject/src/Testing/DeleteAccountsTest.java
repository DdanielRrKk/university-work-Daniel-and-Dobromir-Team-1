package Testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import BusinessLogic.AccountsData;
import Model.Accounts;

public class DeleteAccountsTest {

	AccountsData ad=new AccountsData();
	
	@Test
	public void DeleteAccountsTest() {
		try {
			List<Accounts> la=ad.SelectAll();
			int lSize=la.size();
			ad.DeleteWhereID(25);
			List<Accounts> lacc=ad.SelectAll();
			assertEquals(lSize - 1, lacc.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
