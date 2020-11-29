package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import BusinessLogic.OperatorFunctions;
import Model.Accounts;
import Model.Books;
import javafx.scene.control.TableView;

public class makeBookTableTest {
	
	OperatorFunctions of=new OperatorFunctions();
	
	@Test
	public void makeBookTableTest() {
		TableView<Books> table = new TableView<>();
		assertEquals(table, of.makeBookTable());
	}

}
