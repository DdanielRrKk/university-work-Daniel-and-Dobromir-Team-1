package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import BusinessLogic.OperatorFunctions;
import Model.Accounts;
import javafx.scene.control.TableView;

public class makeAccountsTableTest {
	
	OperatorFunctions of=new OperatorFunctions();
	
	@Test
	public void makeAccountsTableTest() {
		TableView<Accounts> table = new TableView<>();
		assertEquals(table, of.makeAccountsTable());
	}

}
